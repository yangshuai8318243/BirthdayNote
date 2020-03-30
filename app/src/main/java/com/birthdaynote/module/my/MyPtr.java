package com.birthdaynote.module.my;

import com.birthdaynote.library.mvp.MvpPresenter;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;

public class MyPtr extends MvpPresenter<MyFragment, MyEven, MyModle> {
    public MyPtr(MyFragment view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        return new HashMap<>();
    }

    @Override
    protected MyModle bindModel() {
        return coreateModel(MyModle.class);
    }

    @Override
    public void accept(MyEven myEven) throws Exception {

    }
}
