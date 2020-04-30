package com.example.pluginapp;

import android.os.Parcel;
import android.os.Parcelable;

public class IMassageAidl implements Parcelable {
    private int code;
    private String data;

    public IMassageAidl(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public IMassageAidl() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.data);
    }

    protected IMassageAidl(Parcel in) {
        this.code = in.readInt();
        this.data = in.readString();
    }

    public void readFromParcel(Parcel in){
        this.code = in.readInt();
        this.data = in.readString();
    }

    public static final Creator<IMassageAidl> CREATOR = new Creator<IMassageAidl>() {
        @Override
        public IMassageAidl createFromParcel(Parcel source) {
            return new IMassageAidl(source);
        }

        @Override
        public IMassageAidl[] newArray(int size) {
            return new IMassageAidl[size];
        }
    };

    @Override
    public String toString() {
        return "IMassageAidlInterface{" +
                "code=" + code +
                ", data='" + data + '\'' +
                '}';
    }
}
