package com.birthdaynote.module.home;

import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.library.mvp.even.DefEven;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;

public class HomePtr extends MvpPresenter<HomeFragment, DefEven,HomeModel> {
    public HomePtr(HomeFragment view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        return new HashMap<>();
    }

    @Override
    protected HomeModel bindModel() {
        return null;
    }

    @Override
    public void accept(DefEven defEven) throws Exception {

    }
}
