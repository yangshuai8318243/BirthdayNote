package com.birthdaynote.library.data.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;

/**
 * 接口数据解析结果容器
 * 当前数据结构对应接口文档中接口的格式
 * 序列化和反序列化时，有版本概念，目前只做了第一版
 */
public class DataResult implements Parcelable {
    private static final int CURRENT_PARCEL_VERSION = 1; // 当前序列化格式的版本号
    public boolean hasError = false; // 是否出错
    public boolean localError = false; // 是否本地错误
    public int statusCode = 0;
    public int totalCount = 0;
    public String message = "";
    public final DataItem detailinfo = new DataItem();
    public final DataItemArray items = new DataItemArray();
    private String itemUniqueKey = ""; // 多条数据时，用于防止重复的键名，此数据不被序列化或反序列化
    private String debuginfo = ""; // 调试时，记录调试信息的字符串，开始时有用，此项数据不会序列化或反序列化

    public DataResult() {
    }

    public DataResult(Parcel in) {
        fromParcel(in);
    }

    /**
     * 接口数据解析结果容器 从一个Parcel容器中反序列化
     */
    public final boolean fromParcel(Parcel in) {
        try {
            int parcelVersion = in.readInt();
            if (parcelVersion == 1) { // 目前只支持第一版
                fromParcelV1(in);
            } else {
                throw new Exception("DataResult.fromParcel(in): unkown parcel version: " + parcelVersion);
            }

            return true;
        } catch (Throwable e) {
            Log.e("DataResult",e.getMessage());
            return false;
        }
    }

    /**
     * 添加一个对象到队列末尾
     */
    public boolean addItem(DataItem item) {
        if (null == item) {
            return false;
        }

        if (items.add(item, -1, itemUniqueKey)) {
            if (totalCount < items.size()) {
                totalCount = items.size();
            }

            return true;
        }

        return false;
    }

    /**
     * 添加一个对象到指定位置，指定位置不合法则加到队列末尾
     */
    public boolean addItem(int position, DataItem item) {
        if (null == item) {
            return false;
        }

        if (items.add(item, position, itemUniqueKey)) {
            if (totalCount < items.size()) {
                totalCount = items.size();
            }

            return true;
        }

        return false;
    }

    /**
     * 获取当前列表数据条数
     */
    public int getItemsCount() {
        return items.size();
    }

    /**
     * 是否是一个有效的详细页数据
     */
    public boolean isValidDetailData() {
        return null != detailinfo && !hasError && detailinfo.size() > 0;
    }

    /**
     * 是否是一个有效的列表数据
     */
    public boolean isValidListData() {
        return null != items && !hasError && items.size() > 0;
    }

    /**
     * 通过索引删除一个对象
     */
    public Object removeByIndex(int index) {
        if (index < 0 || index >= items.mItems.size()) {
            return null;
        }

        Object item = items.mItems.remove(index);

        if (null != item) {
            totalCount--;
        }

        return item;
    }

    /**
     * 删除一个对象
     */
    public boolean removeItem(Object item) {
        if (items.mItems.remove(item)) {
            totalCount--;
            return true;
        }

        return false;
    }

    /**
     * 通过索引取得一个对象
     */
    public DataItem getItem(int index) {
        if (index < 0 || index >= items.size()) {
            return null;
        }

        Object item = items.get(index);
        if(!(item instanceof DataItem)){
            return new DataItem();
        }

        return (DataItem)items.get(index);
    }

    /**
     * 清除所有元素，不包括不重复主键
     */
    public DataResult clear() {
        items.clear();
        detailinfo.clear();

        debuginfo = "";
        hasError = false;
        localError = false;
        statusCode = 0;
        totalCount = 0;
        message = "";

        return this;
    }

    /**
     * 获取item的唯一值
     */
    public int getItemID(int index) {
        if (null == itemUniqueKey || itemUniqueKey.length() < 1) {
            return index;
        }

        Object item = getItem(index);

        if (item == null || !(item instanceof DataItem)) {
            return index;
        }

        int uniqueID = ((DataItem)item).getInt(itemUniqueKey);
        if(uniqueID == 0){
            return index;
        }

        return uniqueID;
    }

    /**
     * 给定一个页码，计算当前总页数
     */
    public int getTotalPage(int pageSize) {
        if (pageSize < 1 || totalCount < 1) {
            return 0;
        }

        return (int) Math.ceil((float) totalCount / pageSize);
    }

    /**
     * 获取从服务器端返回的错误信息
     */
    public String getServerErrorMessage() {
        if (!hasError || localError) {
            return "";
        }
        return message;
    }

    /**
     * 设置列表item的不重复键名
     */
    public void setItemUniqueKey(String key) {
        itemUniqueKey = (null == key ? "" : key);
    }

    /**
     * 把当前对象复制一份
     */
    public DataResult makeCopy() {
        DataResult result = new DataResult();

        result.hasError = hasError;
        result.localError = localError;
        result.statusCode = statusCode;
        result.totalCount = totalCount;
        result.message = message;
        result.detailinfo.append(detailinfo.makeCopy());
        result.items.append(items.makeCopy());
        result.itemUniqueKey = itemUniqueKey;
        result.debuginfo = debuginfo;

        return result;
    }

    /**
     * 把items数组中某个键名对应的元素全部同步到另一个键上
     */
    public synchronized DataResult syncItemsDataFromKey(String fromKey, String toKey){
        if(TextUtils.isEmpty(fromKey) || TextUtils.isEmpty(toKey) || fromKey.equals(toKey)){
            return this;
        }

        for (int i=0;i<items.size();i++){
            DataItem item = items.getItem(i);
            item.syncDataFromKey(fromKey, toKey);
        }

        return this;
    }

    /**
     * 统计包含指定布尔键值对的 item 个数
     */
    public int countItemsWithBooleanValue(String key, boolean value) {
        if (items.size() < 1) {
            return 0;
        }

        int result = 0;
        int startIndex = items.size() - 1;
        for (int i = startIndex; i > -1; i--) {
            DataItem item = getItem(i);
            if (item == null) {
                continue;
            }

            if (item.getBool(key) == value) {
                result++;
            }
        }

        return result;
    }

    /**
     * 统计包含指定字符键值对的 item 个数
     */
    public int countItemsWithStringValue(String key, String value) {
        if (items.size() < 1) {
            return 0;
        }

        int result = 0;
        int startIndex = items.size() - 1;
        for (int i = startIndex; i > -1; i--) {
            DataItem item = getItem(i);
            if (item == null) {
                continue;
            }

            if (item.getString(key).equals(value)) {
                result++;
            }
        }

        return result;
    }

    /**
     * 获取包含某个布尔键值对的item对应的主键列表，主键名为空时返回空
     */
    public String getItemsIDWithBooleanValue(String key, boolean value) {
        if (TextUtils.isEmpty(itemUniqueKey)) {
            return "";
        }

        if (items.size() < 1) {
            return "";
        }

        String result = "";
        int startIndex = items.size() - 1;
        for (int i = startIndex; i > -1; i--) {
            DataItem item = getItem(i);
            if (item == null) {
                continue;
            }

            if (item.getBool(key) == value) {
                String ID = item.getString(itemUniqueKey);

                if (ID.length() > 0) {
                    if (result.length() > 0) {
                        result += ",";
                    }

                    result += ID;
                }
            }
        }

        return result;
    }

    /**
     * 清除包含指定键值对的元素
     */
    public boolean removeItemsWithStringValue(String key, String value) {
        if (items.size() < 1) {
            return true;
        }

        int startIndex = items.size() - 1;
        for (int i = startIndex; i > -1; i--) {
            DataItem item = getItem(i);
            if (item == null) {
                return false;
            }

            if (item.matches(key, value)) {
                if (null == this.removeByIndex(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 把所有元素的指定键名的值都置成指定字符串值
     */
    public boolean setAllItemsToStringValue(String key, String value) {
        if (items.size() < 1) {
            return true;
        }

        for (int i = 0; i < items.size(); i++) {
            DataItem item = getItem(i);

            if (item == null) {
                return false;
            }

            if (!value.equals(item.setString(key, value))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 获取第一个匹配指定布尔值键值对的 DataItem 对象
     */
    public DataItem firstItemMatches(String key, Boolean value) {
        if (items.size() < 1) {
            return null;
        }

        for (int i = 0; i < items.size(); i++) {
            Object obj = items.get(i);
            if (obj == null || !(obj instanceof DataItem)) {
                continue;
            }

            DataItem item = (DataItem)obj;
            if(item.matches(key, value)) {
                return item;
            }
        }

        return null;
    }

    /**
     * 获取第一个匹配指定字符串键值对的 DataItem 对象
     */
    public DataItem firstItemMatches(String key, String value) {
        if (items.size() < 1) {
            return null;
        }

        for (int i = 0; i < items.size(); i++) {
            Object obj = items.get(i);
            if (obj == null || !(obj instanceof DataItem)) {
                continue;
            }

            DataItem item = (DataItem)obj;
            if(item.matches(key, value)) {
                return item;
            }
        }

        return null;
    }

    /**
     * 把所有元素的指定键名的值都置成布尔值
     */
    public boolean setAllItemsToBooleanValue(String key, Boolean value) {
        if (items.size() < 1) {
            return true;
        }

        for (int i = 0; i < items.size(); i++) {
            DataItem item = getItem(i);
            if (item == null) {
                return false;
            }

            if (!item.setBool(key, value)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 清除对应键名为true的元素
     */
    public boolean removeItemsWithTrueValue(String key) {
        if (items.size() < 1) {
            return true;
        }

        int startIndex = items.size() - 1;
        for (int i = startIndex; i > -1; i--) {
            DataItem item = getItem(i);
            if (item == null || item.isEmpty()) {
                return false;
            }

            if (item.getBool(key)) {
                if (null == removeByIndex(i)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 判断两个 DataResult 对象是否相等
     */
    @Override
    public boolean equals(Object o) {
        if (null == o) {
            return false;
        }

        if(o == this){
            return true;
        }

        if (!(o instanceof DataResult)) {
            return false;
        }

        DataResult obj = (DataResult) o;

        if (obj.hasError != hasError) {
            return false;
        }

        if (obj.localError != localError) {
            return false;
        }

        if (obj.statusCode != statusCode) {
            return false;
        }

        if (obj.totalCount != totalCount) {
            return false;
        }

        if (TextUtils.isEmpty(obj.message) != TextUtils.isEmpty(message)) {
            return false;
        }

        if (null != obj.message) {
            if (!obj.message.equals(message)) {
                return false;
            }
        }

        if (!obj.detailinfo.equals(detailinfo)) {
            return false;
        }

        if (!obj.items.equals(items)) {
            return false;
        }

        return true;
    }

//    /**
//     * 记录出错的堆栈信息
//     */
//    public void errorRecord(Throwable ex) {
//        debuginfo = AppException.getExceptionStackInfo(ex);
//    }

    /**
     * 设置出错的堆栈信息
     */
    public void setErrorStack(String str) {
        debuginfo = (null == str) ? "" : str;
    }

    /**
     * 获取出错的堆栈信息
     */
    public String getErrorStack() {
        return debuginfo;
    }

    /**
     * 接口数据解析结果容器 反序列化第一版规则
     */
    private void fromParcelV1(Parcel in) throws Throwable {
        // 是否出错
        hasError = (Boolean) in.readSerializable();

        // 本地是否出错
        localError = (Boolean) in.readSerializable();

        // 状态码
        statusCode = in.readInt();

        // 总数
        totalCount = in.readInt();

        // 数据结果集返回的提示信息
        message = in.readString();

        // 详情信息
        if (!detailinfo.fromParcel(in)) {
            throw new Exception("DataResult.fromParcelV1(in): read detailinfo type error!");
        }

        // 列表信息
        if (!items.fromParcel(in)) {
            throw new Exception("DataResult.fromParcelV1(in): read items type error!");
        }
    }

    /**
     * 往队列头部追加数据
     */
    public boolean appendFront(DataResult result){
        if (null == result || result.hasError) {
            return false;
        }

        totalCount = result.totalCount;
        statusCode = result.statusCode;
        hasError = result.hasError;
        localError = result.localError;

        detailinfo.append(result.detailinfo);

        if(result.items.size() > 0) {
            for (int i = result.items.size() - 1; i > -1; i--) {
                items.add(result.items.get(i), 0, itemUniqueKey);
            }
        }

        if (totalCount < items.size()) {
            totalCount = items.size();
        }

        return true;
    }
    /**
     * 往当前接口数据解析结果容器的后端追加另一个接口数据解析结果容器所有的数据
     */
    public boolean append(DataResult result) {
        if (null == result || result.hasError) {
            return false;
        }

        totalCount = result.totalCount;
        statusCode = result.statusCode;
        hasError = result.hasError;
        localError = result.localError;

        detailinfo.append(result.detailinfo);

        for (int i = 0; i < result.items.size(); i++) {
            items.add(result.items.get(i), -1, itemUniqueKey);
        }

        if (totalCount < items.size()) {
            totalCount = items.size();
        }

        return true;
    }

    /**
     * 往当前接口数据解析结果容器的后端追加 DataItemArray 列表数据
     */
    public boolean append(DataItemArray dataItemArray) {
        if (null == dataItemArray || dataItemArray.size() < 1) {
            return false;
        }

        for (int i = 0; i < dataItemArray.size(); i++) {
            items.add(dataItemArray.get(i), -1, itemUniqueKey);
        }

        if (totalCount < items.size()) {
            totalCount = items.size();
        }

        return true;
    }

    /**
     * 调试用，输出接口数据解析结果容器中所有元素
     */
    public void Dump() {
        Log.v("Dump", "==========  [basicInfo] ==========");
        Log.v("Dump", "  .hasError: " + hasError);
        Log.v("Dump", "  .localError: " + localError);
        Log.v("Dump", "  .statusCode: " + statusCode);
        Log.v("Dump", "  .totalCount: " + totalCount);
        Log.v("Dump", "  .message: " + message);
        Log.v("Dump", "  .debuginfo: " + debuginfo);

        Log.v("Dump", "==========  [detailInfo] ==========");
        detailinfo.Dump();

        Log.v("Dump", "==========  [dataList] ==========");
        items.Dump();
    }

    /**
     * 把对象数据转为字节数组
     *
     * @return byte[]
     */
    public byte[] toBytes() {
        byte[] data = null;

        try {
            Parcel out = Parcel.obtain();
            writeToParcel(out, 0);
            out.setDataPosition(0);

            data = out.marshall();
            out.recycle();
        } catch (Throwable e) {
            Log.e("DataResult", e.getMessage());
        }

        return data;
    }

    /**
     * 把字节数据转换为 DataResult 对象
     *
     * @param bytesData 字节数据
     * @return DataResult 返回对象
     */
    public static DataResult fromBytes(byte[] bytesData) {
        if (null == bytesData) {
            return new DataResult();
        }

        try {
            Parcel in = Parcel.obtain();

            in.unmarshall(bytesData, 0, bytesData.length);
            in.setDataPosition(0);

            return DataResult.CREATOR.createFromParcel(in);
        } catch (Throwable e) {
            Log.e("DataResult", e.getMessage());
        }

        return new DataResult();
    }




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化对象的格式版本号
        dest.writeInt(CURRENT_PARCEL_VERSION);

        // 是否出错
        dest.writeSerializable(hasError);

        // 本地是否出错
        dest.writeSerializable(localError);

        // 状态码
        dest.writeInt(statusCode);

        // 总数
        dest.writeInt(totalCount);

        // 数据结果集返回的提示信息
        dest.writeString(TextUtils.isEmpty(message) ? "" : message);

        // 详情信息
        detailinfo.writeToParcel(dest, flags);

        // 列表信息
        items.writeToParcel(dest, flags);
    }

    /**
     * 对象反序列化构造容器
     */
    public static final Creator<DataResult> CREATOR = new Creator<DataResult>() {
        public DataResult createFromParcel(Parcel in) {
            return new DataResult(in);
        }

        public DataResult[] newArray(int size) {
            return new DataResult[size];
        }
    };
}
