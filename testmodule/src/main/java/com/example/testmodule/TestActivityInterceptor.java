package com.example.testmodule;

import android.net.Uri;
import android.text.TextUtils;

import com.birthdaynote.library.util.constant.RouterCode;
import com.birthdaynote.library.util.constant.RouterConstants;
import com.sankuai.waimai.router.components.DefaultLogger;
import com.sankuai.waimai.router.core.UriCallback;
import com.sankuai.waimai.router.core.UriInterceptor;
import com.sankuai.waimai.router.core.UriRequest;

import androidx.annotation.NonNull;

public class TestActivityInterceptor implements UriInterceptor {
    @Override
    public void intercept(@NonNull UriRequest request, @NonNull UriCallback callback) {
        Uri uri = request.getUri();

        String uriStr = uri.toString();
        String netUrl = RouterConstants.TEST_ACTIVITY;

//        String netUrl = "----->";
        if (!TextUtils.isEmpty(uriStr) && uriStr.equals(netUrl)) {
            callback.onNext();
        } else {
            callback.onComplete(RouterCode.NOT_PAGE);
            DefaultLogger.INSTANCE.d("TestActivityInterceptor", "------------->", uriStr);
        }
    }
}
