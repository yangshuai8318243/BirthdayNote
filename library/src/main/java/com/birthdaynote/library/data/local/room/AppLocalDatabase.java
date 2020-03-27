package com.birthdaynote.library.data.local.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LocalDataEntity.class}, version = 4, exportSchema = false)
public abstract class AppLocalDatabase extends RoomDatabase {
    public abstract LocalDao localDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE local_data");
        }
    };

}
