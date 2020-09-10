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
     * 同步请求数据
     *
     * @param url
     * @param param
     * @param dClass
     * @param onDataListener
     * @param <D>
     */
    public abstract <D> void getAsynchronousData(String url, Map<String, String> param, Class<D> dClass, OnDataListener onDataListener);

    /**
     * 异步请求数据
     *
     * @param url
     * @param param
     * @param dClass
     * @param <D>
     * @return
     */
    public abstract <D> DecorationData getSynchronizeData(String url, Map<String, String> param, Class<D> dClass);

    /**
     * 将数据存储到本地
     *
     * @param key
     * @param type
     * @param jsonData
     * @return
     */
    public abstract <D> void saveLocalData(String key, String type, D data);

    /**
     * 从本地读取数据
     *
     * @param key
     * @param type
     * @return
     */
    public abstract <D> D getLocalData(String key, String type, Class<D> dClass);

    /**
     * 删除本地数据
     *
     * @param key
     * @param type
     * @param <D>
     */
    public abstract <D> void deleteData(String key, String type);

    public interface OnDataListener {
        void onData(DecorationData d);
    }

    public LocalDataManager getmLocalDataManager() {
        return mLocalDataManager;
    }

    public NetworkDataManager getmNetworkDataManager() {
        return mNetworkDataManager;
    }
}
