package com.birthdaynote.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.birthdaynote.library.mvp.EvenInterface;
import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;

import butterknife.Unbinder;

public abstract class BDActivity<P extends PresenterInterface,E extends EvenInterface> extends MvpViewActivity<P,E> {

    protected Unbinder mUnbinder;

    protected abstract Unbinder binderView();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUnbinder = binderView();
    }

    @Override
    protected void onDestroy() {
        if (mUnbinder!=null){
            mUnbinder.unbind();
        }
        super.onDestroy();
    }
}
