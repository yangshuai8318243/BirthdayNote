package com.birthdaynote.library.data.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class MvpData implements Parcelable {

    private Class mClassName;
    private String mFragmentName;
    private Bundle mData;

    public Class getmClassName() {
        return mClassName;
    }

    public void setmClassName(Class mClassName) {
        this.mClassName = mClassName;
    }

    public String getmFragmentName() {
        return mFragmentName;
    }

    public void setmFragmentName(String mFragmentName) {
        this.mFragmentName = mFragmentName;
    }

    public Bundle getmData() {
        return mData;
    }

    public void setmData(Bundle mData) {
        this.mData = mData;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.mClassName);
        dest.writeString(this.mFragmentName);
        dest.writeBundle(this.mData);
    }

    public MvpData() {
    }

    protected MvpData(Parcel in) {
        this.mClassName = (Class) in.readSerializable();
        this.mFragmentName = in.readString();
        this.mData = in.readBundle();
    }

    public static final Creator<MvpData> CREATOR = new Creator<MvpData>() {
        @Override
        public MvpData createFromParcel(Parcel source) {
            return new MvpData(source);
        }

        @Override
        public MvpData[] newArray(int size) {
            return new MvpData[size];
        }
    };
}
