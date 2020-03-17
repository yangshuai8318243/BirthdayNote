package com.birthdaynote.library.data.local.room;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface LocalDao {

    @Query("SELECT * FROM local_data WHERE url IN (:url)")
    List<LocalDataEntity> getUrlAll(String url);

    @Query("SELECT * FROM local_data WHERE type IN (:type)")
    List<LocalDataEntity> getLocalDataFromType(String type);

    @Insert
    void insertAll(LocalDataEntity... users);

    @Delete
    void delete(LocalDataEntity users);

}
