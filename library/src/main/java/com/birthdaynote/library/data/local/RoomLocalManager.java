package com.birthdaynote.library.data.local;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.data.local.room.AppLocalDatabase;

import androidx.room.Room;

public class RoomLocalManager implements LocalDataManager{
    private static AppLocalDatabase s_AppLocalDatabase = BuildSingletonLocalDatabase.s_AppLocalDatabase;

    public static AppLocalDatabase getS_AppLocalDatabase() {
        return s_AppLocalDatabase;
    }

    @Override
    public <D> D getData(String ket, String type) {
        return null;
    }

    @Override
    public <D> void saveData(String url, String type, D data) {

    }


    private static class BuildSingletonLocalDatabase {
        private static final AppLocalDatabase s_AppLocalDatabase = Room.databaseBuilder(BaseApp.getInstance(),AppLocalDatabase.class,"local_data").build();
    }
}
