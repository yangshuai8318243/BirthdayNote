package com.example.testproguard;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.testproguard.pkg.PkgTest;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testOkHttp();
        testRx();
        test();
    }

    private void testOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("http://publicobject.com/helloworld.txt")
                        .build();

                Response response = null;
                try {
                    response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!response.isSuccessful())
                    return;

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
            }
        }).start();
    }

    @SuppressLint("CheckResult")
    private void testRx() {
        // 1. 创建被观察者 Observable 对象
        new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                Log.e("11111111", "1111111111111111111111");

                observer.onNext(1);
                observer.onNext(2);
                observer.onNext(3);
                observer.onComplete();
            }
        }.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.e("11111111", integer.toString());
            }
        });
    }

    private void test(){
        new PkgTest().print();
        new Test().print();
    }
}
