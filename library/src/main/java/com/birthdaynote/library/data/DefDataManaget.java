package com.birthdaynote.library.data;

import android.util.Log;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.data.entity.ErrorData;
import com.birthdaynote.library.data.local.LocalDataManager;
import com.birthdaynote.library.data.net.NetworkDataManager;
import com.birthdaynote.library.data.net.NetworkUtil;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;

public class DefDataManaget extends DataManager {
    public DefDataManaget(LocalDataManager mLocalDataManager, NetworkDataManager mNetworkDataManager) {
        super(mLocalDataManager, mNetworkDataManager);
    }

    @Override
    public <D> void getData(String url, Map<String, String> param, Class<D> dClass, OnDataListener<D> onDataListener) {
        if (NetworkUtil.isNetworkAvailable(BaseApp.getInstance())) {
            try {
                D data;
                if (param != null && param.size() > 0) {
                    data = mNetworkDataManager.postRequest(dClass, url, param);
                } else {
                    data = mNetworkDataManager.getRequest(dClass, url);
                }
                onDataListener.onData(data);
            } catch (IOException | ErrorData e ) {
                D data = mLocalDataManager.getData(url, "", dClass);
                if (data != null) {
                    onDataListener.onData(data);
                } else {
                    if (e instanceof ErrorData) {
                        onDataListener.onError((ErrorData) e);
                    } else {
                        onDataListener.onError(new ErrorData.Builder().code(00).mesage("没有对应数据").build());
                    }
                }
            } catch (JsonParseException j) {
                onDataListener.onError(new ErrorData.Builder().code(00).mesage("数据解析失败").build());
            }
        } else {
            D data = mLocalDataManager.getData(url, "", dClass);
            if (data != null) {
                onDataListener.onData(data);
            } else {
                onDataListener.onError(new ErrorData.Builder().code(00).mesage("没有本地数据").build());
            }
        }
    }
}
