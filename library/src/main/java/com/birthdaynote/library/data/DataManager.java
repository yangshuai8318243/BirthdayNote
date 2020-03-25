package com.birthdaynote.library.data;

import com.birthdaynote.library.data.entity.DecorationData;
import com.birthdaynote.library.data.local.LocalDataManager;
import com.birthdaynote.library.data.net.NetworkDataManager;

import java.util.Map;

public abstract class DataManager {
    protected LocalDataManager mLocalDataManager;
    protected NetworkDataManager mNetworkDataManager;

    public DataManager(NetworkDataManager mNetworkDataManager) {
        this.mNetworkDataManager = mNetworkDataManager;
    }

    public DataManager(LocalDataManager mLocalDataManager) {
        this.mLocalDataManager = mLocalDataManager;
    }

    public DataManager(LocalDataManager mLocalDataManager, NetworkDataManager mNetworkDataManager) {
        this.mLocalDataManager = mLocalDataManager;
        this.mNetworkDataManager = mNetworkDataManager;
    }

    /**
     * @param url
     * @param param
     */
    public abstract <D> void getAsynchronousData(String url, Map<String, String> param, Class<D> dClass, OnDataListener onDataListener);

    public abstract <D> DecorationData getSynchronizeData(String url, Map<String, String> param, Class<D> dClass);


    public interface OnDataListener {
        void onData(DecorationData d);
    }


}
