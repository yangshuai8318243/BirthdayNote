package com.birthdaynote.module.main;


import android.os.Bundle;
import android.util.Log;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.library.util.RxUtils;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;


public class MainPtr extends MvpPresenter<MainFragment, MainEven, MainModel> {
    private MediatorLiveData<String> liveData;

    public MainPtr(MainFragment mView) {
        super(mView);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        HashMap<String, LiveData> stringLiveDataHashMap = new HashMap<>();
        liveData = new MediatorLiveData<>();
        stringLiveDataHashMap.put(MainEven.MAIN_GET_IMAGE_DATA, liveData);
        return stringLiveDataHashMap;
    }


    @Override
    protected MainModel bindModel() {
        return coreateModel(MainModel.class);
    }

    @Override
    public void accept(MainEven mainEven) throws Exception {
        Log.e(TAG, "----------accept------>");
        String tag = mainEven.getTag();
        BaseData data = mainEven.getData();

        addSubscribe(new Observable<BaseData>() {
            @Override
            protected void subscribeActual(Observer<? super BaseData> observer) {
//                BaseData imageData = mModel.getImageData();
                BaseData imageData = mModel.getPost();
                observer.onNext(imageData);
                observer.onComplete();
            }
        }.compose(RxUtils.schedulersTransformer()).doOnSubscribe(disposable -> {

        }).subscribe((Consumer<BaseData>) o -> {
            Log.e(TAG, "cccccc:" + o.toString());
            liveData.setValue("xxxxxxxxxxxxxxaaaaaaaaaaaaaaaaaaaaaa");
            Bundle bundle = new Bundle();
            bundle.putParcelable("test", o);
//            startContainerActivity(HomeFragment.class.getCanonicalName(), bundle);
        }));



    }
}
