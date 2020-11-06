package com.mengjia.chat;

import android.app.Activity;

import com.mechanist.sdk.sdkcommon.aihelp.SDKAIHelp;
import com.mechanist.sdk.sdkcommon.appsflyer.SDKAppsFlyerManager;
import com.mechanist.sdk.sdkcommon.event.SDKEventManager;
import com.mechanist.sdk.sdkcommon.http.SDKHttpUtil;

public class Test {
    private Activity activity;

    public Test(Activity activity) {
        this.activity = activity;
    }

    public void testSDKAIHelp() {
        SDKAIHelp.init(activity, "sssss");
    }

    public void testSDKAppsFlyerManager() {
        SDKAppsFlyerManager.getInstance().Init(activity);
    }

    public void testSDKEventManager() {
        SDKEventManager.getInstance().CallEvent("", "");
    }

    public void testSDKHttpUtil() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SDKHttpUtil.SDKHttpGet("http://192.168.1.46:9999/gateway/server/chat.html");
            }
        }).start();
    }

}
