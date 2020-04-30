// IMassageAidlInterface.aidl
package com.birthdaynote.data.aidl;

// Declare any non-default types here with import statements
import com.birthdaynote.data.aidl.IMassageAidl;


interface IMassageAidlInterface {
    IMassageAidl requestData();

    void sendData(inout IMassageAidl massage);

}
