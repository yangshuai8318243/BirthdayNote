package com.birthdaynote.app;

import android.util.Log;

import com.birthdaynote.BuildConfig;
import com.birthdaynote.library.app.BaseApp;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.sankuai.waimai.router.components.DefaultLogger;
import com.sankuai.waimai.router.core.Debugger;
import com.sankuai.waimai.router.core.OnCompleteListener;
import com.sankuai.waimai.router.core.UriRequest;

import androidx.annotation.NonNull;

public class BirthdayApp extends BaseApp {
    @Override
    public void onCreate() {
        Log.d("BirthdayApp","---BirthdayApp------------onCreate-------------->");

        super.onCreate();
        Fresco.initialize(this);
        DefaultRootUriHandler defaultRootUriHandler = new DefaultRootUriHandler(this);
        defaultRootUriHandler.setGlobalOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onSuccess(@NonNull UriRequest request) {

            }

            @Override
            public void onError(@NonNull UriRequest request, int resultCode) {

            }
        });
        Router.init(defaultRootUriHandler);
        // 自定义Logger
        DefaultLogger logger = new DefaultLogger() {
            @Override
            protected void handleError(Throwable t) {
                super.handleError(t);
                // 此处上报Fatal级别的异常
            }
        };
        // 设置Logger
        Debugger.setLogger(logger);
        if (BuildConfig.isDebug){
            // Log开关，建议测试环境下开启，方便排查问题。
            Debugger.setEnableLog(true);
            // 调试开关，建议测试环境下开启。调试模式下，严重问题直接抛异常，及时暴漏出来。
            Debugger.setEnableDebug(true);
        }
    }
}
