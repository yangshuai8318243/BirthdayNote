package com.example.testmodule;

import android.os.Bundle;

import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.sankuai.waimai.router.annotation.RouterUri;

import androidx.annotation.Nullable;
@RouterUri(path = RouterConstants.TEST_ACTIVITY1)
public class TestActivity1 extends MvpViewActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_module_im_activity_instant_messaging_main);
    }

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
