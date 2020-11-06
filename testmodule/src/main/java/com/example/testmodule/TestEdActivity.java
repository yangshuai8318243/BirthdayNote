package com.example.testmodule;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.birthdaynote.library.app.BaseActivity;

import cn.dreamtobe.kpswitch.widget.KPSwitchPanelLinearLayout;
import cn.dreamtobe.kpswitch.widget.KPSwitchRootLinearLayout;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/22
 * Time: 17:06
 */
public class TestEdActivity extends BaseActivity {
    private KPSwitchRootLinearLayout mPanelLayout;
    private KPSwitchPanelLinearLayout mPanelRoot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_module_test_ed_layout);
        mPanelLayout = findViewById(R.id.KPSwitchRootLinearLayout);
        mPanelRoot = findViewById(R.id.panel_root);
        mPanelRoot.setIgnoreRecommendHeight(false);
    }
}
