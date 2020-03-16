package com.birthdaynote.library.data;

import com.birthdaynote.library.data.local.LocalDataManager;
import com.birthdaynote.library.data.net.NetworkDataManager;

public class DataManager {
    private LocalDataManager mLocalDataManager;
    private NetworkDataManager mNetworkDataManager;

    public DataManager(LocalDataManager mLocalDataManager, NetworkDataManager mNetworkDataManager) {
        this.mLocalDataManager = mLocalDataManager;
        this.mNetworkDataManager = mNetworkDataManager;
    }



}
