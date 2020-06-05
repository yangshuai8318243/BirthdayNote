package com.birthdaynote.app;

import android.os.Message;
import android.util.Log;

import com.birthdaynote.BuildConfig;
import com.birthdaynote.library.app.AppHander;
import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.sankuai.waimai.router.components.DefaultLogger;
import com.sankuai.waimai.router.core.Debugger;
import com.sankuai.waimai.router.core.OnCompleteListener;
import com.sankuai.waimai.router.core.UriRequest;

import androidx.annotation.NonNull;

public class BirthdayApp extends BaseApp {
    private AppHander<BirthdayApp> safetyHander;
    //    private RefWatcher install;


    @Override
    public void onCreate() {
        super.onCreate();
        safetyHander = Utils.getSafetyHander(this);
        safetyHander.setListener(new AppHander.AppHanderListener<BirthdayApp>() {
            @Override
            public void handleMessage(Message msg) {

            }
        });
//        Looper.myQueue().addIdleHandler(() -> {
//            Log.e(TAG, "------------addIdleHandler-----11111--------->");
//            return true;
//        });


        Log.i(TAG, "---BirthdayApp------------onCreate-------------->");
        new Test().fun1("--BirthdayApp-");
//        图片加载配置
        Fresco.initialize(this);
//        路由配置
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
        if (BuildConfig.isDebug) {
            // Log开关，建议测试环境下开启，方便排查问题。
            Debugger.setEnableLog(true);
            // 调试开关，建议测试环境下开启。调试模式下，严重问题直接抛异常，及时暴漏出来。
            Debugger.setEnableDebug(true);
//            if (!LeakCanary.isInAnalyzerProcess(this)) {
//                install = LeakCanary.install(this);
//            }

        }

        //插件初始化
//        PluginManager.getInstance(this).init();
        new com.birthdaynote.library.mvp.Test().print();
    }
}
