package com.example.testmodule;

import android.os.Bundle;
import android.view.SurfaceView;

import androidx.annotation.Nullable;

import com.birthdaynote.library.app.BaseActivity;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/22
 * Time: 9:15
 */
public class TestSurfaceViewActivity extends BaseActivity {
    private SurfaceView surfaceView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_surface_view_layout);
        surfaceView = findViewById(R.id.surface_view);
    }
}
