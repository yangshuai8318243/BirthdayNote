package com.example.testmodule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.util.PrintUtils;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.sankuai.waimai.router.annotation.RouterUri;

import java.lang.ref.WeakReference;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@RouterUri(path = RouterConstants.TEST_ACTIVITY_PROCESS)
public class TestProcessActivity extends MvpViewActivity {
    public static final String MASSAGE_KEY = "TEST_ACTIVITY_PROCESS_MASSAGE_KEY";
    public static final int S2C_REQ = 1;
    public static final int C2S_REQ = 2;

    private static class MyH extends Handler {
        private WeakReference<TestProcessActivity> mActivit;

        public MyH(TestProcessActivity mActivit) {
            this.mActivit = new WeakReference<>(mActivit);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case C2S_REQ:
                    Bundle data = msg.getData();
                    String string = data.getString(MASSAGE_KEY);
                    if (mActivit != null) {
                        Log.e(mActivit.getClass().getName(), "----------收到原来进程的数据---->" + string);
                    }

                    break;
            }

        }
    }

    Messenger messenger = new Messenger(new MyH(this));


    @Override
    protected PresenterInterface initPtr() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_module_im_activity_instant_messaging_main);
        TextView textView = findViewById(R.id.name);
        textView.setText(getClass().getSimpleName());
        Button srBtn = findViewById(R.id.start_serv);
        srBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "-------setOnClickListener---->" + BaseApp.tst);
                Intent intent = new Intent(getApplicationContext(), TestService.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", "--------------1-------------");
                intent.putExtra("testData", bundle);
                Log.e(TestProcessActivity.this.getClass().getName(), "-----------startService----------------");
                startService(intent);
            }
        });
        PrintUtils.printProcess(getClass().getName());
    }
}
