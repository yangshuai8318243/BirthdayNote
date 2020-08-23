package com.birthdaynote.module.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.birthdaynote.R;
import com.birthdaynote.app.BDActivity;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.util.ToastUtils;

import androidx.annotation.NonNull;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

public class HomeActivity extends BDActivity {

    private boolean isExit;

    @Override
    protected Unbinder binderView() {
        return null;
    }

    @Override
    protected int getActivityViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {

        } else {
            addFragment(HomeFragment.class, R.id.main_container, "HomeFragment");
        }
        Observable<BaseData> baseDataObservable = new Observable<BaseData>() {
            @Override
            protected void subscribeActual(Observer<? super BaseData> observer) {

            }
        };

        Observable<String> map = baseDataObservable.map(new Function<BaseData, String>() {
            @Override
            public String apply(BaseData baseData) throws Exception {
                return null;
            }
        });

        Observable<BaseData> baseDataObservable1 = map.flatMap(new Function<String, ObservableSource<BaseData>>() {
            @Override
            public ObservableSource<BaseData> apply(String s) throws Exception {
                return Observable.fromArray(new BaseData.Builder().build());
            }
        });

    }

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                ToastUtils.showShortSafe("再次点击退出 APP");
                isExit = true;
                new Handler().postDelayed(() -> isExit = false, 2000);
                return true;
            }
            moveTaskToBack(true);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("xxxx", 1);
        super.onSaveInstanceState(outState);
    }
}
