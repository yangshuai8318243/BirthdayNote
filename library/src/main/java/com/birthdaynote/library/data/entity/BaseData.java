package com.birthdaynote.library.data.entity;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class BaseData implements Parcelable {
    private static final byte Integer_TAG = 'I';
    private static final byte String_TAG = 'S';
    private static final byte Long_TAG = 'L';
    private static final byte Double_TAG = 'D';
    private static final byte Boolean_TAG = 'B';
    private static final byte BaseDataList_TAG = 'X';
    private static final byte BaseData_TAG = 'Y';
    private static final byte OBJ_TAG = 'O';
    private static final int CURRENT_PARCEL_VERSION = 1; // 当前序列化格式的版本号

    private static final String TAG = "BaseData";
    private Integer mInt;
    private Long mLong;
    private Double mDouble;
    private Boolean mBoolean;
    private String mStr;
    private BaseDataList mListData;
    private Map<String, String> mVarClassName;
    private Map<String, Object> mSimpleMapData;

    private BaseData(Builder builder) {
        mInt = builder.mInt;
        mLong = builder.mLong;
        mDouble = builder.mDouble;
        mBoolean = builder.mBoolean;
        mStr = builder.mStr;
        mListData = builder.mListData;
        mVarClassName = builder.mVarClassName;
        mSimpleMapData = builder.mSimpleMapData;
    }


    private boolean objType(Object o) {

        if (o instanceof String) {
            return true;
        }

        if (o instanceof Double) {
            return true;
        }

        if (o instanceof Integer) {
            return true;
        }

        if (o instanceof Boolean) {
            return true;
        }

        if (o instanceof Long) {
            return true;
        }

        if (o instanceof Parcelable) {
            return true;
        }

        return false;
    }

    public boolean putData(String key, Object o) {
        if (key == null || o == null) {
            return false;
        }

        if (!objType(o)) {
            return false;
        }

        String canonicalName = o.getClass().getCanonicalName();
        mVarClassName.put(key, canonicalName);
        mSimpleMapData.put(key, o);
        return true;
    }


    protected BaseData(Parcel in) {

    }

    public final boolean fromParcel(Parcel in) {
        try {
            int parcelVersion = in.readInt();
            if (parcelVersion == 1) { // 目前只支持第一版
                fromParcelV1(in);
            } else {
                throw new Exception("BaseData.fromParcel(in): unkown parcel version: " + parcelVersion);
            }

            return true;
        } catch (Throwable e) {
            Log.e(TAG, e.getMessage());
            return false;
        }
    }

    private void fromParcelV1(Parcel in) throws Throwable {
        byte b = in.readByte();
        if (b == Integer_TAG) {
            mInt = in.readInt();
        } else {

        }
        b = in.readByte();

        if (b == Long_TAG) {

        }

    }

    public static final Creator<BaseData> CREATOR = new Creator<BaseData>() {
        @Override
        public BaseData createFromParcel(Parcel in) {
            return new BaseData(in);
        }

        @Override
        public BaseData[] newArray(int size) {
            return new BaseData[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // 序列化对象的格式版本号
        dest.writeInt(CURRENT_PARCEL_VERSION);

        dest.writeByte(Integer_TAG);
        dest.writeInt(mInt);

        dest.writeByte(Long_TAG);
        dest.writeLong(mLong);

        dest.writeByte(Double_TAG);
        dest.writeDouble(mDouble);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(mBoolean);
        } else {
            dest.writeString(mBoolean ? "0" : "1");
        }

        dest.writeByte(String_TAG);
        dest.writeString(mStr);


        for (String key : mSimpleMapData.keySet()) {
            Object jo = mSimpleMapData.get(key);
            if (jo instanceof BaseData) { // DataItem
                dest.writeByte(BaseData_TAG);
                ((BaseData) jo).writeToParcel(dest, flags);
            } else if (jo instanceof BaseDataList) { // DataItemArray
                dest.writeByte(BaseDataList_TAG);
                ((BaseDataList) jo).writeToParcel(dest, flags);
            } else if (jo instanceof String) { // String
                dest.writeByte(String_TAG);
                dest.writeString((String) jo);
            } else if (jo instanceof Long) { // Long
                dest.writeByte(Long_TAG);
                dest.writeLong((Long) jo);
            } else if (jo instanceof Integer) { // Integer
                dest.writeByte(Integer_TAG);
                dest.writeInt((Integer) jo);
            } else if (jo instanceof Double) { // Double
                dest.writeByte(Double_TAG);
                dest.writeDouble((Double) jo);
            } else if (jo instanceof Boolean) { // Boolean
                dest.writeByte(Boolean_TAG);
                dest.writeSerializable((Boolean) jo);
            } else { // 其他类型做为空字符串占位处理
                String className = mVarClassName.get(key);

                dest.writeByte(OBJ_TAG);
                dest.writeString(className);
                dest.writeParcelable((Parcelable) jo, flags);
            }

        }
    }


    public static final class Builder {
        private Integer mInt = -1;
        private Long mLong = -1L;
        private Double mDouble = -1.0;
        private Boolean mBoolean = false;
        private String mStr = "";
        private BaseDataList mListData = new BaseDataList();
        private Map<String, String> mVarClassName = new HashMap<>();
        private Map<String, Object> mSimpleMapData = new HashMap<>();

        public Builder() {
        }

        public Builder mInt(Integer val) {
            mInt = val;
            return this;
        }

        public Builder mLong(Long val) {
            mLong = val;
            return this;
        }

        public Builder mDouble(Double val) {
            mDouble = val;
            return this;
        }

        public Builder mBoolean(Boolean val) {
            mBoolean = val;
            return this;
        }

        public Builder mStr(String val) {
            mStr = val;
            return this;
        }

        public Builder mListData(BaseDataList val) {
            mListData = val;
            return this;
        }

        public Builder mVarClassName(Map<String, String> val) {
            mVarClassName = val;
            return this;
        }

        public Builder mSimpleMapData(Map<String, Object> val) {
            mSimpleMapData = val;
            return this;
        }

        public BaseData build() {
            return new BaseData(this);
        }
    }
}
