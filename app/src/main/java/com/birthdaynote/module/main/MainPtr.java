package com.birthdaynote.module.main;


import android.Manifest;
import android.util.Log;

import com.birthdaynote.app.BirthdayApp;
import com.birthdaynote.app.Location;
import com.birthdaynote.data.entity.LocalData;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.DecorationData;
import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.library.util.RxUtils;

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
    private MediatorLiveData<BaseData> weatherLiveData;
    private long time;

    public MainPtr(MainFragment mView) {
        super(mView);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        HashMap<String, LiveData> stringLiveDataHashMap = new HashMap<>();
        weatherLiveData = new MediatorLiveData<>();
        stringLiveDataHashMap.put(MainEven.MAIN_GET_WEATHER_DATA, weatherLiveData);
        return stringLiveDataHashMap;
    }


    @Override
    protected MainModel bindModel() {
        return coreateModel(MainModel.class);
    }


    @Override
    public void onSuccessPermissions(String permission) {
        super.onSuccessPermissions(permission);
        if (permission == Manifest.permission.ACCESS_FINE_LOCATION) {
            getLocal();
        }
    }

    private void getLocal(){
        Location.getLocation(BirthdayApp.getInstance(), new Location.LocalListener() {
            @Override
            public void onLocationChanged(LocalData localData) {
                mModel.cityName = localData.getLocality();
                getWeatherData();
            }
        });
    }

    private void getWeatherData(){
        addSubscribe(new Observable<BaseData>() {
            @Override
            protected void subscribeActual(Observer<? super BaseData> observer) {
                BaseData weatherData = mModel.getWeatherData();
                observer.onNext(weatherData);
            }
        }.compose(RxUtils.schedulersTransformer()).subscribe(new Consumer<BaseData>() {
            @Override
            public void accept(BaseData baseData) throws Exception {
                weatherLiveData.setValue(baseData);
            }
        }));
    }

    @Override
    public void onFailurePermissions(String permission) {
        super.onFailurePermissions(permission);
        if (permission == Manifest.permission.ACCESS_FINE_LOCATION) {
            mModel.cityName = "深圳";
        }
    }

    @Override
    public void accept(MainEven mainEven) throws Exception {
        String tag = mainEven.getTag();
        BaseData data = mainEven.getData();

//        getData();

//        rxTest();
        requestPerTest();
    }

    private void requestPerTest() {
        requestPermissions(Manifest.permission.ACCESS_FINE_LOCATION);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestPerTest();
    }

    private void rxTest() {
        Observable<Object> compose = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                observer.onNext("1111");
                observer.onError(new RuntimeException("xxxxxx"));
            }
        }.compose((ObservableTransformer) upstream -> {
            Log.e(TAG, "-----ObservableSource----apply----->");

            return upstream.onErrorResumeNext(new ObservableSource() {
                @Override
                public void subscribe(Observer observer) {
                    Log.e(TAG, "-----onErrorResumeNext----ObservableTransformer----->");

                }
            });
        });

        Disposable subscribe = compose.subscribe(o ->
                        Log.e(TAG, "=======观察者==accept===="),
                throwable ->
                        Log.e(TAG, "=======观察者=error=accept===="));

        addSubscribe(subscribe);
    }


    private void getData() {
        time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            addSubscribe(new Observable<BaseData>() {
                @Override
                protected void subscribeActual(Observer<? super BaseData> observer) {
//                BaseData imageData = mModel.getImageData();
//                BaseData imageData = mModel.getPost();

                    observer.onComplete();
                }
            }.compose(RxUtils.schedulersTransformer())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            Log.e(TAG, "网络请求开始：" + finalI);
                        }
                    }).subscribe((Consumer<BaseData>) baseData -> {
//                        weatherLiveData.setValue("xxxxxxxxxxxxxxaaaaaaaaaaaaaaaaaaaaaa");


                        long timeMillis = System.currentTimeMillis();
                        long l = timeMillis - time;
                        Log.e(TAG, "网络请求结束：" + finalI + "\n耗时：" + l + "\n 数据为:" + baseData.print());
//            startContainerActivity(HomeFragment.class.getCanonicalName(), bundle);
                    }));
        }

    }
}
