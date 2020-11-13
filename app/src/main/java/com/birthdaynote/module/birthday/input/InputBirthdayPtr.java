package com.birthdaynote.module.birthday.input;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.log.AppLog;
import com.birthdaynote.library.mvp.MvpPresenter;
import com.birthdaynote.module.birthday.BirthdayEven;
import com.birthdaynote.module.birthday.BirthdayModle;

import java.util.HashMap;
import java.util.Map;

public class InputBirthdayPtr extends MvpPresenter<InPutBirthdayActivity, BirthdayEven, BirthdayModle> {
    private MediatorLiveData<BaseData> saveResult;

    public InputBirthdayPtr(InPutBirthdayActivity view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        HashMap<String, LiveData> liveDataHashMap = new HashMap<>();
        saveResult = new MediatorLiveData<>();
        liveDataHashMap.put(BirthdayEven.INPUT_BIRTHDAY_SAVE, saveResult);
        AppLog.e(TAG, "----addLiveData------>", liveDataHashMap.entrySet().size());
        return liveDataHashMap;
    }

    @Override
    protected BirthdayModle bindModel() {
        return new BirthdayModle();
    }

    @Override
    public void accept(BirthdayEven birthdayEven) throws Exception {

    }
}
