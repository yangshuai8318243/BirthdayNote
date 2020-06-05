package com.birthdaynote.data.aidl;

import android.os.RemoteException;

public class test extends IMassageAidlInterface.Stub{
    @Override
    public IMassageAidl requestData() throws RemoteException {
        return null;
    }

    @Override
    public void sendData(IMassageAidl massage) throws RemoteException {

    }
}
