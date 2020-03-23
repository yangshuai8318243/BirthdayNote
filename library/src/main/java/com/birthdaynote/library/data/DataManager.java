package com.birthdaynote.library.data;

import com.birthdaynote.library.data.entity.ErrorData;
import com.birthdaynote.library.data.local.LocalDataManager;
import com.birthdaynote.library.data.net.NetworkDataManager;

import java.util.Map;

public abstract class DataManager {
    protected LocalDataManager mLocalDataManager;
    protected NetworkDataManager mNetworkDataManager;

    public DataManager(LocalDataManager mLocalDataManager, NetworkDataManager mNetworkDataManager) {
        this.mLocalDataManager = mLocalDataManager;
        this.mNetworkDataManager = mNetworkDataManager;
    }

    /**
     * @param url
     * @param param
     */
    public abstract <D> void getData(String url, Map<String, String> param, Class<D> dClass, OnDataListener<D> onDataListener);

    public interface OnDataListener<D> {
        void onError(ErrorData errorData);

        void onData(D d);
    }


}
