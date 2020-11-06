package com.example.testaar;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.mengjia.chatmjlibrary.ChatSdk;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/28
 * Time: 11:40
 */
public class TestApp extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ChatSdk.getInstance().init(this);
    }
}
