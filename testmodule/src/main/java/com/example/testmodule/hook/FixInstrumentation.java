package com.example.testmodule.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FixInstrumentation extends Instrumentation {
    private Instrumentation instrumentation;

    public FixInstrumentation(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public static FixInstrumentation hookInstrumentation() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        //获取系统class对象
        Class<?> aClass = Class.forName("android.app.ActivityThread");
        //找到其静态方法
        Method currentActivityThread = aClass.getDeclaredMethod("currentActivityThread");
        currentActivityThread.setAccessible(true);
        //调用静态方法，获得class实例
        Object activityThread = currentActivityThread.invoke(null);
        //找到需要的实例对象
        Field mInstrumentation = aClass.getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        //从拿到的实例中找到需要的实例
        Instrumentation o = (Instrumentation) mInstrumentation.get(activityThread);
        //创建代理
        FixInstrumentation fixInstrumentation = new FixInstrumentation(o);
        //将代理对象，替换原有对象
        mInstrumentation.set(activityThread, fixInstrumentation);
        return fixInstrumentation;
    }

//    public ActivityResult execStartActivity(
//            Context who, IBinder contextThread, IBinder token, Activity target,
//            Intent intent, int requestCode, Bundle options) {
//
//    }
}
