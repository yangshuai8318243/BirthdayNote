package com.birthdaynote.module.birthday;

import com.birthdaynote.library.mvp.MvpPresenter;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;

public class BirthdayPtr extends MvpPresenter<BirthdayFragment, BirthdayEven, BirthdayModle> {
    public BirthdayPtr(BirthdayFragment view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        return new HashMap<>();
    }

    @Override
    protected BirthdayModle bindModel() {
        return coreateModel(BirthdayModle.class);
    }

    @Override
    public void accept(BirthdayEven birthdayEven) throws Exception {

    }
}
