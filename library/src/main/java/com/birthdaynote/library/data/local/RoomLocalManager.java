package com.birthdaynote.library.data.local;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.data.local.room.AppLocalDatabase;
import com.birthdaynote.library.data.local.room.LocalDataEntity;
import com.google.gson.Gson;

import androidx.room.Room;

public class RoomLocalManager implements LocalDataManager {
    private static AppLocalDatabase s_AppLocalDatabase = BuildSingletonLocalDatabase.s_AppLocalDatabase;
    private static final String LOCAL_TYPE = "LOCAL_TYPE";

    static AppLocalDatabase getS_AppLocalDatabase() {
        return s_AppLocalDatabase;
    }


    @Override
    public <D> D getData(String key, String type, Class<D> dClass) {
        LocalDataEntity localData = getS_AppLocalDatabase().localDao().getLocalDataLast(key, type);
        if (localData != null ) {
            String jsonData = localData.jsonData;
            D d = new Gson().fromJson(jsonData, dClass);
            return d;
        }
        return null;
    }

    @Override
    public <D> void saveData(String key, String type, D data) {
        String toJson = new Gson().toJson(data);
        LocalDataEntity localDataEntity = new LocalDataEntity();
        localDataEntity.jsonData = toJson;
        localDataEntity.key = key;
        localDataEntity.type = type;
        localDataEntity.tag = LOCAL_TYPE;
        localDataEntity.time = System.currentTimeMillis();
        getS_AppLocalDatabase().localDao().insertAll(localDataEntity);
    }

    @Override
    public <D> void deleteData(String key, String type) {
        getS_AppLocalDatabase().localDao().deleteData(key, type);
    }


    private static class BuildSingletonLocalDatabase {
        private static final AppLocalDatabase s_AppLocalDatabase =
                Room.databaseBuilder(BaseApp.getInstance(), AppLocalDatabase.class, "local_data")
                        .fallbackToDestructiveMigration()
                        .build();
    }
}
