package com.example.testmodule;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.data.DataManagerDefFactory;
import com.birthdaynote.library.data.DefDataManaget;
import com.birthdaynote.library.util.PrintUtils;

import androidx.annotation.Nullable;

public class TestService extends Service {

    //只有初始化的时候会运行
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(getClass().getName(), "------Override------->");

    }


    private void saveData() {
        DefDataManaget defDataManaget = new DataManagerDefFactory.Builder().build().createDataManager(DefDataManaget.class);
        defDataManaget.saveLocalData("key1", "type1", "111111111111111");
    }

    //多次startService每次都会运行
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle testData = intent.getBundleExtra("testData");
        String key = testData.getString("key");
        Log.e(getClass().getName(), "------ssssss------->" + key);
//        saveData();
        Log.e(getClass().getName(), "-------BaseApp.tst---->" + BaseApp.tst);
        PrintUtils.printProcess(getClass().getName());
        return super.onStartCommand(intent, flags, startId);
    }

    //销毁的时候会运行
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //解绑的时候会进入
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    // 绑定的时候会进入
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
