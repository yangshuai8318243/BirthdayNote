package com.birthdaynote.library.mvp;

import com.birthdaynote.library.data.entity.BaseData;

public abstract class MvpModel<D extends BaseData> implements ModelInterface{
    protected static String TAG = "";

    public MvpModel() {
        TAG = getClass().getName();
    }


}
