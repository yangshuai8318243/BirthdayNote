package com.example.testmodule.hook;

import android.util.Log;

import com.taobao.android.dexposed.XC_MethodHook;

public class ThreadMethodHook extends XC_MethodHook {
    private static final String TAG = "ThreadMethodHook----->";

    @Override
    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
        super.beforeHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        Log.i(TAG, "thread:" + t + ", started..");
    }

    @Override
    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
        super.afterHookedMethod(param);
        Thread t = (Thread) param.thisObject;
        Log.i(TAG, "thread:" + t + ", exit..");
    }
}
