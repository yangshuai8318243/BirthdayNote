package com.birthdaynote.module.my;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.core.OnCompleteListener;
import com.sankuai.waimai.router.core.UriRequest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

public class MyFragment extends BDFragment<MyPtr, MyEven> {

    @BindView(R.id.tiao_zhuan)
    Button luyouBtn;

    @BindView(R.id.tiao_zhuan1)
    Button tiao_zhuan1;

    @BindView(R.id.cha_jian)
    Button chaJianBtn;

    private ClassLoader mPluginClassLoader;

    @Override
    protected MyPtr initPtr() {
        return getPtrFactory().newPtr(MyPtr.class, this);
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            Log.e(TAG, "--------handleMessage-----Callback----->" + msg.what);
            return false;
        }
    }) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Log.e(TAG, "----handler--handleMessage--------->" + msg.what);

            switch (msg.what) {
                case 1:
                    Log.e(TAG, "------handler-----handleMessage----111---->");
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.my_fragment_layout, container, false);
        bindView(inflate);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.tiao_zhuan1)
    void tiaoZhuan1(View view) {
        tiao(RouterConstants.TEST_ACTIVITY1);
    }


    @OnClick(R.id.tiao_zhuan)
    void tiaoZhuan(View view) {
        tiao(RouterConstants.TEST_ACTIVITY);
    }

    private void tiao(String action) {
        UriRequest uriRequest = new UriRequest(getContext(), action);
        uriRequest.onComplete(new OnCompleteListener() {
            @Override
            public void onSuccess(@NonNull UriRequest request) {
                Log.e(TAG, "-------request----->" + request.toString());
            }

            @Override
            public void onError(@NonNull UriRequest request, int resultCode) {
                Log.e(TAG, "----request-------->" + request.toString());
                Log.e(TAG, "-------resultCode----->" + resultCode);
            }
        });
        Router.startUri(uriRequest);

    }

    @OnClick(R.id.cha_jian)
    void chaJian(View view) {
        handler.sendEmptyMessage(1);
        handler.sendEmptyMessage(2);
        Message message = new Message();
        message.what = 12;
        handler.sendMessageDelayed(message, 1);
        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "------post--------->");
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "------postDelayed--------->");
            }
        }, 2);
    }

}
