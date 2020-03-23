package com.birthdaynote.module.main;


import android.os.Bundle;
import android.util.Log;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.data.entity.DataItemArray;
import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.library.util.RxUtils;
import com.birthdaynote.module.home.HomeFragment;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
        String tag = mainEven.getTag();
        BaseData data = mainEven.getData();

        getData();

//        rxTest();
    }


    private void rxTest() {
        Observable<Object> compose = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                observer.onNext("1111");
                observer.onError(new RuntimeException("xxxxxx"));
            }
        }.compose(new ObservableTransformer() {
            @Override
            public ObservableSource<Object> apply(Observable upstream) {
                Log.e(TAG, "-----ObservableSource----apply----->");

                return upstream.onErrorResumeNext(new ObservableSource() {
                    @Override
                    public void subscribe(Observer observer) {
                        Log.e(TAG, "-----onErrorResumeNext----ObservableTransformer----->");

                    }
                });
            }
        });

        Disposable subscribe = compose.subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                Log.e(TAG, "=======观察者==accept====");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e(TAG, "=======观察者=error=accept====");

            }
        });

        addSubscribe(subscribe);
    }


    private void getData() {
        addSubscribe(new Observable<BaseData>() {
            @Override
            protected void subscribeActual(Observer<? super BaseData> observer) {
//                BaseData imageData = mModel.getImageData();
//                BaseData imageData = mModel.getPost();

                mModel.testNetData();

                BaseData baseData = new BaseData.Builder().build();
                observer.onNext(baseData);
                observer.onComplete();
            }
        }.compose(RxUtils.schedulersTransformer()).doOnSubscribe(disposable -> {

        }).subscribe((Consumer<BaseData>) o -> {
            liveData.setValue("xxxxxxxxxxxxxxaaaaaaaaaaaaaaaaaaaaaa");
            BaseDataList list = mModel.getList();
            Bundle bundle = new Bundle();
            bundle.putParcelable("test", list);
            Log.e(TAG, "cccccc:" + list.toString());

//            startContainerActivity(HomeFragment.class.getCanonicalName(), bundle);
        }));
    }
}
