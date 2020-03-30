package com.birthdaynote.module.my;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.even.EvenInterface;

public class MyEven implements EvenInterface<BaseData> {
    @Override
    public String getTag() {
        return null;
    }

    @Override
    public void setTag(String tag) {

    }

    @Override
    public BaseData getData() {
        return null;
    }
}
