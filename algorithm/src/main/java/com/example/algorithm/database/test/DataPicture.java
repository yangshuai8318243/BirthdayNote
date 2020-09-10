package com.example.algorithm.database.test;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/3
 * Time: 20:22
 */

@Entity(tableName = "data_picture")
public class DataPicture {

    @Override
    public String toString() {
        return "DataPicture{" +
                "pictureId=" + pictureId +
                ", image_size=" + image_size +
                ", thumbnail_size=" + thumbnail_size +
                ", image_remote_url='" + image_remote_url + '\'' +
                ", humbnail_remote_url='" + humbnail_remote_url + '\'' +
                '}';
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "picture_id")
    public long pictureId;

    @ColumnInfo(name = "image_size")
    public int image_size;//图源大小(单位:字节)

    @ColumnInfo(name = "thumbnail_size")
    public int thumbnail_size;//缩略图大小

    @ColumnInfo(name = "image_remote_url")
    public String image_remote_url;//原图url

    @ColumnInfo(name = "humbnail_remote_url")
    public String humbnail_remote_url;// 缩略图url

    public DataPicture() {
    }

    private DataPicture(Builder builder) {
        setImage_size(builder.image_size);
        thumbnail_size = builder.thumbnail_size;
        image_remote_url = builder.image_remote_url;
        humbnail_remote_url = builder.humbnail_remote_url;
    }

    public int getImage_size() {
        return image_size;
    }

    public void setImage_size(int image_size) {
        this.image_size = image_size;
    }

    public static final class Builder {
        private int size;
        private int thumbnail_size;
        private String image_remote_url;
        private String humbnail_remote_url;
        private int image_size;

        public Builder() {
        }

        public Builder size(int val) {
            size = val;
            return this;
        }

        public Builder thumbnail_size(int val) {
            thumbnail_size = val;
            return this;
        }

        public Builder image_remote_url(String val) {
            image_remote_url = val;
            return this;
        }

        public Builder humbnail_remote_url(String val) {
            humbnail_remote_url = val;
            return this;
        }

        public DataPicture build() {
            return new DataPicture(this);
        }

        public Builder image_size(int val) {
            image_size = val;
            return this;
        }
    }
}
