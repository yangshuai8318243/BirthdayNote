package com.birthdaynote.module.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.birthdaynote.R;
import com.birthdaynote.library.app.BaseActivity;
import com.birthdaynote.library.mvp.MvpFragment;
import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;

public class MainActivity extends MvpViewActivity {

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
