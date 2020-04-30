package com.example.testmodule;

import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.library.util.RxUtils;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;

public class TestModuleMainPtr extends MvpPresenter<TestModuleMainActivity, TestModuleMainActivity.TestEven, TestModuleMainModle> {
    public TestModuleMainPtr(TestModuleMainActivity view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        return new HashMap<>();
    }

    @Override
    protected TestModuleMainModle bindModel() {
        return new TestModuleMainModle();
    }

    @Override
    public void accept(TestModuleMainActivity.TestEven testEven) throws Exception {
        testData();
    }



    private void testData() {
        addSubscribe(new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                String test = mModel.test();
                observer.onNext(test);
            }
        }.compose(RxUtils.schedulersTransformer()).subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {

            }
        }));
    }
}
