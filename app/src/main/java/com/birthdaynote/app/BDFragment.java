package com.birthdaynote.app;

import android.view.View;

import com.birthdaynote.library.mvp.EvenInterface;
import com.birthdaynote.library.mvp.MvpFragment;
import com.birthdaynote.library.mvp.PresenterInterface;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BDFragment<P extends PresenterInterface,E extends EvenInterface> extends MvpFragment<P,E>{

    protected Unbinder mUnbinder;

    protected void bindView(View view){
        mUnbinder = ButterKnife.bind(this, view);
    }


    @Override
    public void onDestroy() {
        if (mUnbinder != null){
            mUnbinder.unbind();
        }
        super.onDestroy();
    }
}
