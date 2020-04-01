package com.birthdaynote.module.news;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.even.EvenInterface;

public class NewsEven implements EvenInterface<BaseData> {
    public static final String UPDATE_NEWS_DATA = "UPDATE_NEWS_DATA";
    public static final String UPDATE_NEWS_DATA_REFRESH_KEY = "UPDATE_NEWS_DATA_REFRESH_KEY";
    private String tag;
    private BaseData baseData;

    public void setBaseData(BaseData baseData) {
        this.baseData = baseData;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public BaseData getData() {
        return baseData;
    }
}
