package com.birthdaynote.module.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import com.birthdaynote.library.data.DefultData;
import com.birthdaynote.library.mvp.MvpPresenter;

import java.util.HashMap;
import java.util.Map;

public class MainPtr extends MvpPresenter<MainFragment,MainEven,MainModel>{
    private MediatorLiveData<String> liveData;

    public MainPtr(MainFragment mView) {
        super(mView);
    }


    @Override
    protected Map<String, LiveData> addLiveData() {
        HashMap<String, LiveData> stringLiveDataHashMap = new HashMap<>();
        liveData = new MediatorLiveData<>();
        stringLiveDataHashMap.put(MainEven.MAIN_GET_IMAGE_DATA,liveData);
        return stringLiveDataHashMap;
    }

    @Override
    protected MainModel bindModel() {
        return coreateModel(MainModel.class);
    }

    @Override
    public void accept(MainEven mainEven) throws Exception {
        Log.e(TAG,"----------accept------>");
        String tag = mainEven.getTag();
        DefultData data = mainEven.getData();
        mModel.getImageData();
        liveData.setValue("xxxxxxxxxxxxxxaaaaaaaaaaaaaaaaaaaaaa");
    }
}
