package com.birthdaynote.library.util;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.birthdaynote.library.app.AppHander;


public class Utils {

    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(@NonNull final Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("should be initialized in application");
    }


    public static <T> AppHander<T> getSafetyHander(T t) {
        return new AppHander<T>(t);
    }

    public static boolean isNoll(Object o) {
        return o == null;
    }
}
