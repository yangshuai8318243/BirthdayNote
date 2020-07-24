package com.example.algorithm.rx;

import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class TestRx extends AlgorithmBaseFragment {


    @Override
    protected void run() {
        testMap();
    }

    public void testDefer() {
        Observable<String> defer = Observable.defer(new Callable<ObservableSource<String>>() {
            @Override
            public ObservableSource<String> call() throws Exception {
                return Observable.just("1", "2", "3");
            }
        });

    }


    public void testMap() {
        Observable<String> stringObservable = new Observable<String>() {
            @Override
            protected void subscribeActual(Observer observer) {
                observer.onNext("1");
                observer.onNext("2");
                observer.onNext("3");
            }
        };
        stringObservable.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return 1;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        });

        stringObservable.flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return Observable.just("");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {

            }
        });


        Observable.just(new UserParams("lucas", "123123")).flatMap(new Function<UserParams, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(UserParams userParams) throws Exception {
                Log.i("TestRxJava", "---------1111-----------" + userParams.toString());

                return Observable.just(userParams.username + "登录成功").delay(2, TimeUnit.SECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                Log.i("TestRxJava", "---------2222-----------" + s);
            }
        });
    }

    public static class UserParams {

        public UserParams(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String username;
        public String password;

        @Override
        public String toString() {
            return "UserParams{" +
                    "username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}
