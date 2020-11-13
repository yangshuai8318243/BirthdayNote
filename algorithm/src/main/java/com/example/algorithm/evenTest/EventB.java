package com.example.algorithm.evenTest;

import androidx.lifecycle.MutableLiveData;

import com.birthdaynote.library.util.LiveDataBus;

public class EventB {
    public void test(){
        MutableLiveData<Object> test1111 = LiveDataBus.get().with("test1111");
    }
}
