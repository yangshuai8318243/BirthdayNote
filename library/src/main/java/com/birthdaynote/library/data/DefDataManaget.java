package com.birthdaynote.library.data;

import android.util.Log;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.data.entity.DecorationData;
import com.birthdaynote.library.data.local.LocalDataManager;
import com.birthdaynote.library.data.net.NetworkDataManager;
import com.birthdaynote.library.data.net.NetworkUtil;
import com.birthdaynote.library.util.PrintUtils;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;

public class DefDataManaget extends DataManager {
    public DefDataManaget(LocalDataManager mLocalDataManager, NetworkDataManager mNetworkDataManager) {
        super(mLocalDataManager, mNetworkDataManager);
    }

    @Override
    public <D> void getAsynchronousData(String url, Map<String, String> param, Class<D> dClass, OnDataListener onDataListener) {

    }

    @Override
    public <D> DecorationData getSynchronizeData(String url, Map<String, String> param, Class<D> dClass) {
        DecorationData decorationData = null;

        if (NetworkUtil.isNetworkAvailable(BaseApp.getInstance())) {
            try {
                D data;
                if (param != null && param.size() > 0) {
                    data = mNetworkDataManager.postRequest(dClass, url, param);
                } else {
                    data = mNetworkDataManager.getRequest(dClass, url);
                }
                decorationData = new DecorationData.Builder().data(data).build();
            } catch (IOException | DecorationData e) {
                D data = mLocalDataManager.getData(url, "", dClass);
                if (data != null) {
                    decorationData = new DecorationData.Builder().data(data).build();
                } else {
                    if (e instanceof DecorationData) {
                        decorationData = (DecorationData) e;
                    } else {
                        decorationData = new DecorationData.Builder().code(00).mesage("没有对应数据").build();
                    }
                }
            } catch (JsonParseException j) {
                decorationData = new DecorationData.Builder().code(00).mesage("数据解析失败").build();
            } catch (Throwable throwable) {
                decorationData = new DecorationData.Builder().code(00).mesage("网络请求异常").build();
                Log.e("", "" + PrintUtils.errInfo( throwable));
            }
        } else {
            D data = mLocalDataManager.getData(url, "", dClass);
            if (data != null) {
                decorationData = new DecorationData.Builder().data(data).build();
            } else {
                decorationData = new DecorationData.Builder().code(00).mesage("没有本地数据").build();
            }
        }
        return decorationData;
    }


}
