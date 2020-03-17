package com.birthdaynote.library.mvp.even;

import com.birthdaynote.library.data.entity.BaseData;

public class DefEven implements EvenInterface<BaseData> {
    private String mTag = "";
    private BaseData mDefultData = new BaseData.Builder().build();

    @Override
    public String getTag() {
        return mTag;
    }

    @Override
    public void setTag(String tag) {
        mTag = tag;
    }


    @Override
    public BaseData getData() {
        return mDefultData;
    }
}
