package com.birthdaynote.module.main;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import android.os.Bundle;
import android.util.Log;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.module.home.HomeFragment;

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
        BaseData data = mainEven.getData();
        BaseData imageData = mModel.getImageData();

        Log.e(TAG,"cccccc:"+imageData.toString());
        liveData.setValue("xxxxxxxxxxxxxxaaaaaaaaaaaaaaaaaaaaaa");

        Bundle bundle = new Bundle();
        bundle.putParcelable("test",imageData);
        startContainerActivity(HomeFragment.class.getCanonicalName(),bundle);
    }
}
