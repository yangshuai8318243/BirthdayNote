package com.birthdaynote.module.main;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.EvenInterface;

public class MainEven implements EvenInterface<BaseData> {
    public static final String MAIN_GET_IMAGE_DATA = "MAIN_GET_IMAGE_DATA";
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
