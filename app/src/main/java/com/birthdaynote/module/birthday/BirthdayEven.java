package com.birthdaynote.module.birthday;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.even.EvenInterface;

public class BirthdayEven implements EvenInterface<BaseData> {
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
