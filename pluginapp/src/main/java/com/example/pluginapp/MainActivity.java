package com.example.pluginapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.birthdaynote.library.app.BaseActivity;
import com.birthdaynote.library.log.AppLog;
import com.birthdaynote.library.util.ScreenUtil;
import com.birthdaynote.library.widget.loading.LoadingView;
import com.birthdaynote.library.widget.test_view.InterceptRelativeLayout;
import com.birthdaynote.library.widget.test_view.TestViewGroup;

public class MainActivity extends BaseActivity {

    private LoadingView viewById;
    InterceptRelativeLayout testViewGroup;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.change_direction);
        viewById = findViewById(R.id.loading_view);
        testViewGroup = findViewById(R.id.content);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.viewById.setVisibility(View.VISIBLE);
                MainActivity.this.viewById.startAnim();
                testViewGroup.setTouch(true);
                testViewGroup.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        testViewGroup.setTouch(false);
                    }
                },1000);
//                newLoadingD();
            }
        });

    }


    private void cha() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            //切换竖屏
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            //切换横屏
            MainActivity.this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        int windowHeight = ScreenUtil.getWindowHeight(MainActivity.this);
        int windowWidth = ScreenUtil.getWindowWidth(MainActivity.this);
        AppLog.i(TAG, "----111----->", windowWidth, windowHeight);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        int layoutDirection = newConfig.getLayoutDirection();

    }
}
