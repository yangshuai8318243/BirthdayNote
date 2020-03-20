package com.birthdaynote.library.data.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BaseDataList implements Parcelable {
    private static final String TAG = "BaseDataList";
    private static final int CURRENT_PARCEL_VERSION = 1; // 当前序列化格式的版本号
    private List<BaseData> mDataList = new ArrayList<>();


    public BaseData getData(int key) {
        BaseData baseData = mDataList.get(key);
        return baseData;
    }

    public boolean saveData(BaseData baseData) {
        return saveData(-1, baseData);
    }


    public boolean saveData(int position, BaseData baseData) {
        if (isDataNull(baseData)) {
            return false;
        }

        if (position < 0 || position >= mDataList.size()) {
            return mDataList.add(baseData);
        } else {
            mDataList.add(position, baseData);
            return true;
        }
    }


    private boolean isDataNull(BaseData baseData) {
        if (baseData == null) {
            return true;
        }
        return false;
    }


    public String print() {
        return "BaseDataList{" +
                "mDataMap=" + mDataList +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化对象的格式版本号
        dest.writeInt(CURRENT_PARCEL_VERSION);

        // 写入序列化对象的键值对个数
        dest.writeInt(mDataList.size());

        for (BaseData baseData : mDataList) {
            baseData.writeToParcel(dest, flags);
        }

    }

    public BaseDataList() {
    }

    protected BaseDataList(Parcel in) {
        fromParcel(in);
    }

    /**
     * 数据列表容器 从一个Parcel容器中反序列化
     */
    public final boolean fromParcel(Parcel in) {
        try {
            int parcelVersion = in.readInt();
            Log.e(TAG, "--parcelVersion--->" + parcelVersion);
            if (parcelVersion == 1) { // 目前只支持第一版
                fromParcelV1(in);
            } else {
                throw new Exception("BaseDataList.fromParcel(in): unkown parcel version: " + parcelVersion);
            }

            return true;
        } catch (Throwable e) {
            Log.e("BaseDataList", e.getMessage());
            return false;
        }
    }

    /**
     * 数据列表容器 反序列化第一版规则
     */
    private void fromParcelV1(Parcel in) throws Throwable {
        int itemCount = in.readInt();
        Log.e(TAG, "--itemCount--->" + itemCount);

        for (int i = 0; i < itemCount; i++) {
            BaseData baseData = new BaseData(in);
            mDataList.add(baseData);
        }
    }

    public static final Creator<BaseDataList> CREATOR = new Creator<BaseDataList>() {
        @Override
        public BaseDataList createFromParcel(Parcel source) {
            return new BaseDataList(source);
        }

        @Override
        public BaseDataList[] newArray(int size) {
            return new BaseDataList[size];
        }
    };
}
