package com.birthdaynote.module.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.birthdaynote.data.aidl.IMassageAidl;
import com.birthdaynote.data.aidl.IMassageAidlInterface;

import androidx.annotation.Nullable;

public class AIDLService extends Service {
    public static final String TAG = AIDLService.class.getName();
    private IMassageAidl iMassageAidl;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void initData() {
        iMassageAidl = new IMassageAidl();
        iMassageAidl.setCode(100);
        iMassageAidl.setData("测试1");
    }

    private IMassageAidlInterface.Stub aidlInterface = new IMassageAidlInterface.Stub() {
        @Override
        public IMassageAidl requestData() throws RemoteException {
            return iMassageAidl;
        }

        @Override
        public void sendData(IMassageAidl massage) throws RemoteException {
            Log.e(TAG, "--------------->" + massage.toString());
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return aidlInterface;
    }
}
