package com.birthdaynote.library.data.local;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.data.local.room.AppLocalDatabase;
import com.birthdaynote.library.data.local.room.LocalDataEntity;
import com.google.gson.Gson;

import androidx.room.Room;

public class RoomLocalManager implements LocalDataManager {
    private static AppLocalDatabase s_AppLocalDatabase = BuildSingletonLocalDatabase.s_AppLocalDatabase;

    public static AppLocalDatabase getS_AppLocalDatabase() {
        return s_AppLocalDatabase;
    }

    @Override
    public <D> D getData(String key, String type, Class<D> dClass) {
        LocalDataEntity localData = getS_AppLocalDatabase().localDao().getLocalData(key, type);
        String jsonData = localData.jsonData;
        D d = new Gson().fromJson(jsonData, dClass);
        return d;
    }

    @Override
    public <D> void saveData(String url, String type, D data) {

    }


    private static class BuildSingletonLocalDatabase {
        private static final AppLocalDatabase s_AppLocalDatabase = Room.databaseBuilder(BaseApp.getInstance(), AppLocalDatabase.class, "local_data").build();
    }
}
