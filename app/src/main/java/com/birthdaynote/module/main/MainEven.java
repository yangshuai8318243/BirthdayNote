package com.birthdaynote.module.main;

import com.birthdaynote.library.data.DefultData;
import com.birthdaynote.library.mvp.EvenInterface;

public class MainEven implements EvenInterface<DefultData> {
    public static final String MAIN_GET_IMAGE_DATA = "MAIN_GET_IMAGE_DATA";
    private String mTag = "";
    private DefultData mDefultData = new DefultData();

    @Override
    public String getTag() {
        return mTag;
    }

    @Override
    public void setTag(String tag) {
        mTag = tag;
    }


    @Override
    public DefultData getData() {
        return mDefultData;
    }




}
