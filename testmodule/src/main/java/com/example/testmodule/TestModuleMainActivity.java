package com.example.testmodule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.mvp.even.EvenInterface;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.example.testmodule.hook.FixInstrumentation;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.lang.reflect.InvocationTargetException;

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
        TextView textView = findViewById(R.id.name);
        textView.setText(getClass().getName());
        try {
            FixInstrumentation.hookInstrumentation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected TestModuleMainPtr initPtr() {
        return getPtrFactory().newPtr(TestModuleMainPtr.class, this);
    }
}
