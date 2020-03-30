package com.birthdaynote.module.main;


import android.Manifest;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.birthdaynote.app.BirthdayApp;
import com.birthdaynote.app.Location;
import com.birthdaynote.data.entity.LocationData;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.library.util.RxUtils;
import com.birthdaynote.library.util.constant.TimeConstants;
import com.birthdaynote.module.home.HomeActivity;
import com.birthdaynote.module.home.HomeFragment;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
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
    private MediatorLiveData<Integer> updateTimerText;

    private int timerIndex = 3;
    private Handler mTimer = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    timerIndex = timerIndex - 1;
                    updateTimer();
                    break;
                case 1:
                    mTimer.removeMessages(0);
                    timerIndex = -1;
                    updateTimer();
                    break;
            }
        }
    };


    private void updateTimer() {
        if (timerIndex > 0) {
            Log.e(TAG, "=============>" + timerIndex);
            mTimer.sendEmptyMessageDelayed(0, TimeConstants.SEC);
            updateTimerText.setValue(timerIndex);
        } else {
            toView();
        }
    }

    public MainPtr(MainFragment mView) {
        super(mView);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        HashMap<String, LiveData> stringLiveDataHashMap = new HashMap<>();
        weatherLiveData = new MediatorLiveData<>();
        stringLiveDataHashMap.put(MainEven.MAIN_GET_WEATHER_DATA, weatherLiveData);

        updateTimerText = new MediatorLiveData<>();
        stringLiveDataHashMap.put(MainEven.MAIN_UPDATE_TIMER_TEXT, updateTimerText);

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

    private void getLocal() {
        addSubscribe(new Observable<LocationData>() {
            @Override
            protected void subscribeActual(Observer<? super LocationData> observer) {
                LocationData locationData = mModel.getLocationData();

                if (locationData == null) {
                    locationData = new LocationData();
                }
                observer.onNext(locationData);
            }
        }.compose(RxUtils.schedulersTransformer()).subscribe((Consumer<LocationData>) localData -> {

            if (localData.getLocality() != null && !mModel.timeMoreThanTheKey(localData.getTime(), 6)) {
                mModel.cityName = localData.getLocality();
                getWeatherData();
                return;
            }

            Location.getLocation(BirthdayApp.getInstance(), locationData -> {
                mModel.cityName = locationData.getLocality();

                addSubscribe(new Observable<LocationData>() {
                    @Override
                    protected void subscribeActual(Observer<? super LocationData> observer) {
                        mModel.saveLocationData(locationData);
                    }
                }.compose(RxUtils.schedulersTransformer()).subscribe((Consumer) -> {
                }));

                getWeatherData();
            });
        }));


    }

    private void toView() {
        startActivity(HomeActivity.class);
        finishActivity();
    }

    private void getWeatherData() {
        addSubscribe(new Observable<BaseData>() {
            @Override
            protected void subscribeActual(Observer<? super BaseData> observer) {
                BaseData weatherData = mModel.getWeatherData();
                observer.onNext(weatherData);
            }
        }.compose(RxUtils.schedulersTransformer()).subscribe((Consumer<BaseData>) baseData -> {
            if (baseData.getIsOk()) {
                weatherLiveData.setValue(baseData);
                updateTimer();
            } else {
                toView();
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
//        requestPerTest();
//        delet();
        mTimer.sendEmptyMessage(1);
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


}
