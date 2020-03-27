package com.birthdaynote.library.data.local.room;

import java.util.Arrays;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "local_data", indices = {@Index("uid")})
public class LocalDataEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "key")
    public String key;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "tag")
    public String tag;

    @ColumnInfo(name = "jsonData")
    public String jsonData;

    @ColumnInfo(name = "byteData")
    public byte[] byteData;

    @ColumnInfo(name = "time")
    public long time;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    public byte[] getByteData() {
        return byteData;
    }

    public void setByteData(byte[] byteData) {
        this.byteData = byteData;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "LocalDataEntity{" +
                "uid=" + uid +
                ", key='" + key + '\'' +
                ", type='" + type + '\'' +
                ", tag='" + tag + '\'' +
                ", jsonData='" + jsonData + '\'' +
                ", byteData=" + Arrays.toString(byteData) +
                ", time=" + time +
                '}';
    }
}
