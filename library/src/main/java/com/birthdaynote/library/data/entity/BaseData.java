package com.birthdaynote.library.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class BaseData implements Parcelable {

    private Integer mInt;
    private Long mLong ;
    private Double mDouble ;
    private Boolean mBoolean;
    private String mStr;
    private BaseDataList mListData ;
    private Map<String,String> mSimpleMapData;

    public BaseDataList getmListData() {
        return mListData;
    }

    public Map<String, String> getmSimpleMapData() {
        return mSimpleMapData;
    }

    public Integer getmInt() {
        return mInt;
    }

    public void setmInt(Integer mInt) {
        this.mInt = mInt;
    }

    public Long getmLong() {
        return mLong;
    }

    public void setmLong(Long mLong) {
        this.mLong = mLong;
    }

    public Double getmDouble() {
        return mDouble;
    }

    public void setmDouble(Double mDouble) {
        this.mDouble = mDouble;
    }

    public Boolean getmBoolean() {
        return mBoolean;
    }

    public void setmBoolean(Boolean mBoolean) {
        this.mBoolean = mBoolean;
    }

    public String getmStr() {
        return mStr;
    }

    public void setmStr(String mStr) {
        this.mStr = mStr;
    }

    private BaseData(Builder builder) {
        mInt = builder.mInt;
        mLong = builder.mLong;
        mDouble = builder.mDouble;
        mBoolean = builder.mBoolean;
        mStr = builder.mStr;
        mListData = builder.mListData;
        mSimpleMapData = builder.mSimpleMapData;
    }


    public static final class Builder {
        private Integer mInt = -1;
        private Long mLong = -1L;
        private Double mDouble = -1d;
        private Boolean mBoolean = false;
        private String mStr = "";
        private BaseDataList mListData = new BaseDataList();
        private Map<String,String> mSimpleMapData = new HashMap<>();

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


        public Builder mSimpleMapData(Map<String, String> val) {
            mSimpleMapData = val;
            return this;
        }

        public BaseData build() {
            return new BaseData(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.mInt);
        dest.writeValue(this.mLong);
        dest.writeValue(this.mDouble);
        dest.writeValue(this.mBoolean);
        dest.writeString(this.mStr);
        dest.writeParcelable(this.mListData, flags);
        dest.writeInt(this.mSimpleMapData.size());
        for (Map.Entry<String, String> entry : this.mSimpleMapData.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
    }

    protected BaseData(Parcel in) {
        this.mInt = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mLong = (Long) in.readValue(Long.class.getClassLoader());
        this.mDouble = (Double) in.readValue(Double.class.getClassLoader());
        this.mBoolean = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mStr = in.readString();

        BaseDataList dBaseDataList = new BaseDataList(in);
        this.mListData = in.readParcelable(dBaseDataList.getClass().getClassLoader());
        int mSimpleMapDataSize = in.readInt();
        this.mSimpleMapData = new HashMap<String, String>(mSimpleMapDataSize);
        for (int i = 0; i < mSimpleMapDataSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.mSimpleMapData.put(key, value);
        }
    }

    public static final Creator<BaseData> CREATOR = new Creator<BaseData>() {
        @Override
        public BaseData createFromParcel(Parcel source) {
            return new BaseData(source);
        }

        @Override
        public BaseData[] newArray(int size) {
            return new BaseData[size];
        }
    };

    @Override
    public String toString() {
        return "BaseData{" +
                "mInt=" + mInt +
                ", mLong=" + mLong +
                ", mDouble=" + mDouble +
                ", mBoolean=" + mBoolean +
                ", mStr='" + mStr + '\'' +
                ", mListData=" + mListData +
                ", mSimpleMapData=" + mSimpleMapData +
                '}';
    }
}
