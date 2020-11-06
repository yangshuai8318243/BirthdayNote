package com.birthdaynote.module.main;

import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.birthdaynote.R;
import com.birthdaynote.app.BDActivity;
import com.birthdaynote.library.mvp.PresenterInterface;

import butterknife.Unbinder;


public class MainActivity extends BDActivity {
    @Override
    protected Unbinder binderView() {
        return null;
    }

    @Override
    protected int getActivityViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("MainActivity","---MainActivity------------onCreate-------------->");
        super.onCreate(savedInstanceState);
        addFragment(MainFragment.class,R.id.main_container);
    }

    @JavascriptInterface
    public void test(){

    }

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
