package com.example.testmodule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.mvp.even.EvenInterface;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.sankuai.waimai.router.annotation.RouterUri;

import androidx.annotation.Nullable;

@RouterUri(path = RouterConstants.TEST_ACTIVITY, interceptors = TestActivityInterceptor.class)
public class TestModuleMainActivity extends MvpViewActivity<TestModuleMainPtr, TestModuleMainActivity.TestEven> {
    public static class TestEven implements EvenInterface<BaseData> {

        @Override
        public String getTag() {
            return null;
        }

        @Override
        public void setTag(String tag) {

        }

        @Override
        public BaseData getData() {
            return null;
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_module_im_activity_instant_messaging_main);
        Button button = findViewById(R.id.register_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEven(new TestEven());
            }
        });
    }

    @Override
    protected TestModuleMainPtr initPtr() {
        return getPtrFactory().newPtr(TestModuleMainPtr.class, this);
    }
}
