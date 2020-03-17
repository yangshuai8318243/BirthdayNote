package com.birthdaynote.library.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {LocalDataEntity.class},version = 1)
public abstract class AppLocalDatabase extends RoomDatabase {
    public abstract LocalDao localDao();
}
