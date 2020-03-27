package com.birthdaynote.library.data.local.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

@Dao
public interface LocalDao {

    @Query("SELECT * FROM local_data WHERE key =:key")
    List<LocalDataEntity> getKeyAll(String key);

    @Query("SELECT * FROM local_data WHERE type =:type")
    List<LocalDataEntity> getLocalDataFromType(String type);

    @Query("SELECT * FROM local_data WHERE key=:key AND  type=:type")
    List<LocalDataEntity> getLocalData(String key, String type);

    @Query("SELECT *  FROM local_data WHERE key=:key AND type=:type ORDER BY time DESC LIMIT 1")
    LocalDataEntity getLocalDataLast(String key, String type);

    @Insert
    void insertAll(LocalDataEntity... users);

    @Query("DELETE FROM local_data WHERE key =:key AND type =:type")
    void deleteData(String key, String type);

}
