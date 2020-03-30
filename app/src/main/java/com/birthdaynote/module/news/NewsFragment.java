package com.birthdaynote.module.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsFragment extends BDFragment<NewsPtr,NewsEven> {

    @Override
    protected NewsPtr initPtr() {
        return getPtrFactory().newPtr(NewsPtr.class,this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.news_fragment_layout, container, false);
        bindView(inflate);
        return inflate;
    }
}
