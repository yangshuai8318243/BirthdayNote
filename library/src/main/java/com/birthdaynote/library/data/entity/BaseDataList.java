package com.birthdaynote.library.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class BaseDataList implements Parcelable {
    private Map<String, BaseData> mDataMap = new HashMap<>();


    public BaseData getData(String key) {
        if (isKeyNull(key)) {
            return null;
        }

        BaseData baseData = mDataMap.get(key);
        return baseData;
    }


    public boolean saveData(String key, BaseData baseData) {
        if (isDataNull(baseData) || isKeyNull(key)) {
            return false;
        }

        BaseData put = mDataMap.put(key, baseData);

        if (put != null) {
            return true;
        }
        return false;
    }

    private boolean isKeyNull(String key) {
        if (key == null || key == "") {
            return true;
        }
        return false;
    }


    private boolean isDataNull(BaseData baseData) {
        if (baseData == null) {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "BaseDataList{" +
                "mDataMap=" + mDataMap +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mDataMap.size());
        for (Map.Entry<String, BaseData> entry : this.mDataMap.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeParcelable(entry.getValue(), flags);
        }
    }

    public BaseDataList() {
    }

    protected BaseDataList(Parcel in) {
        int mDataMapSize = in.readInt();
        this.mDataMap = new HashMap<String, BaseData>(mDataMapSize);
        for (int i = 0; i < mDataMapSize; i++) {
            String key = in.readString();
            BaseData value = in.readParcelable(BaseData.class.getClassLoader());
            this.mDataMap.put(key, value);
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
