package com.birthdaynote.app;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.birthdaynote.BuildConfig;
import com.birthdaynote.data.SingleDataManager;
import com.birthdaynote.library.app.AppHander;
import com.birthdaynote.library.app.BaseApp;
import com.birthdaynote.library.data.DataManager;
import com.birthdaynote.library.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.FetchState;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.sankuai.waimai.router.components.DefaultLogger;
import com.sankuai.waimai.router.core.Debugger;
import com.sankuai.waimai.router.core.OnCompleteListener;
import com.sankuai.waimai.router.core.UriRequest;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        ImagePipelineConfig pipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setNetworkFetcher(new NetworkFetcher() {
                    @Override
                    public FetchState createFetchState(Consumer consumer, ProducerContext producerContext) {
                        return new FetchState(consumer, producerContext);
                    }

                    @Override
                    public void fetch(FetchState fetchState, Callback callback) {
                        String toString = fetchState.getUri().toString();
                        Log.i(TAG, "---BirthdayApp------------NetworkFetcher------getUri-------->" + toString);
                        Log.i(TAG, "---BirthdayApp------------NetworkFetcher------thrad-------->" + Thread.currentThread().getName());
                        try {
                            byte[] request = SingleDataManager.getDataManager().getmNetworkDataManager().getRequest(toString);
                            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(request);
                            callback.onResponse(byteArrayInputStream, -1);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                            callback.onFailure(throwable);
                        }
                    }

                    @Override
                    public boolean shouldPropagate(FetchState fetchState) {
                        return true;
                    }

                    @Override
                    public void onFetchCompletion(FetchState fetchState, int byteSize) {

                    }

                    @Override
                    public Map<String, String> getExtraMap(FetchState fetchState, int byteSize) {
                        return null;
                    }
                }).build();
        Fresco.initialize(this, pipelineConfig);
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
