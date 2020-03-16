package com.birthdaynote.module.main;

import android.os.Bundle;

import com.birthdaynote.R;
import com.birthdaynote.app.BDActivity;
import com.birthdaynote.library.mvp.PresenterInterface;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends BDActivity {

    @Override
    protected Unbinder binderView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(MainFragment.class,R.id.main_container);
    }

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
