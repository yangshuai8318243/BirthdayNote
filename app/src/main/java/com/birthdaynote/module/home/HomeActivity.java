package com.birthdaynote.module.home;

import android.os.Bundle;

import com.birthdaynote.R;
import com.birthdaynote.app.BDActivity;
import com.birthdaynote.library.mvp.PresenterInterface;

import butterknife.Unbinder;

public class HomeActivity extends BDActivity {
    @Override
    protected Unbinder binderView() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment(HomeFragment.class,R.id.main_container);
    }

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
