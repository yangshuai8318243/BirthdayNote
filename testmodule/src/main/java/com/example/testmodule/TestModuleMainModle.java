package com.example.testmodule;

import android.util.Log;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.MvpModel;

public class TestModuleMainModle extends MvpModel<BaseData> {

    private MyRunnable myRunnable;

    public class MyRunnable implements Runnable {

        //定义退出标志，true会一直执行，false会退出循环
        //使用volatile目的是保证可见性，一处修改了标志，处处都要去主存读取新的值，而不是使用缓存
        public volatile boolean flag = true;

        public void run() {
           Log.e(TAG,"第" + Thread.currentThread().getName() + "个线程创建");
            //退出标志生效位置
            while (flag) {
                try {
                    Log.e(TAG,"第" + Thread.currentThread().getName() + "运行");
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    String test() {
        myRunnable = new MyRunnable();
        myRunnable.run();
//        //创建3个线程
//        for (int i = 1; i <= 3; i++) {
//            Thread thread = new Thread(runnable, i + "");
//            thread.start();
//        }
////        runnable.flag = false;
        return "线程运行结束";
    }

    @Override
    public void onCleared() {
        Log.e(TAG,"=========onCleared============>");
        if (myRunnable != null){
//            myRunnable.flag = false;
        }
    }
}
