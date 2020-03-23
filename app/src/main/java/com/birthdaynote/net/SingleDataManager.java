package com.birthdaynote.net;

import com.birthdaynote.library.data.DataManager;
import com.birthdaynote.library.data.DataManagerDefFactory;
import com.birthdaynote.library.data.DefDataManaget;

public class SingleDataManager {
    private static DataManager DATA_MANAGER = InsertDataManaget.DATA_MANAGER;

    public static DataManager getDataManager() {
        return DATA_MANAGER;
    }

    private static final class InsertDataManaget {
        static DataManager DATA_MANAGER = new DataManagerDefFactory.Builder().build().createDataManager(DefDataManaget.class);
    }


}
