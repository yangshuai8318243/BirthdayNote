package com.birthdaynote.library.app;

import android.app.Application;

import androidx.annotation.NonNull;

public class BaseApp extends Application {
    private static Application sInstance;
    public static String TAG = "";

    @Override
    public void onCreate() {
        TAG = getClass().getName();
        super.onCreate();
        setApplication(this);
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
     *
     * @param application
     */
    public static synchronized void setApplication(@NonNull Application application) {
        sInstance = application;
    }

    /**
     * 获得当前app运行的Application
     */
    public static Application getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }

}
