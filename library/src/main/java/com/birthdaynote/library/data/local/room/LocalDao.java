package com.birthdaynote.library.data.local.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LocalDao {

    @Query("SELECT * FROM local_data WHERE `key` IN (:key)")
    List<LocalDataEntity> getKeyAll(String key);

    @Query("SELECT * FROM local_data WHERE 'type' IN (:type)")
    List<LocalDataEntity> getLocalDataFromType(String type);

    @Query("SELECT * FROM local_data WHERE 'key' IN (:key) AND 'type' IN (:type)")
    LocalDataEntity getLocalData(String key, String type);

    @Insert
    void insertAll(LocalDataEntity... users);

    @Delete
    void delete(LocalDataEntity users);

}
