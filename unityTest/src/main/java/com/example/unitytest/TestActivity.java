package com.example.unitytest;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/28
 * Time: 10:30
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
    }
}
