package com.birthdaynote.library.data.local.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "local_data",indices = {@Index("name")})
public class LocalDataEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "url")
    public String url;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "jsonData")
    public String jsonData;

    @ColumnInfo(name = "byteData")
    public byte[] byteData;

}
