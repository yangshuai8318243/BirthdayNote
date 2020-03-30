package com.birthdaynote.module.news;

import com.birthdaynote.library.mvp.MvpPresenter;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;

public class NewsPtr extends MvpPresenter<NewsFragment, NewsEven, NewsModle> {
    public NewsPtr(NewsFragment view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        return new HashMap<>();
    }

    @Override
    protected NewsModle bindModel() {
        return coreateModel(NewsModle.class);
    }

    @Override
    public void accept(NewsEven newsEven) throws Exception {

    }
}
