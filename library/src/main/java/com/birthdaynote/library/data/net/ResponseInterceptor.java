package com.birthdaynote.library.data.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response proceed = chain.proceed(chain.request());
        String s = proceed.toString();
        String string = proceed.body().string();
        Log.e("ResponseInterceptor", s + "\n" + string);

        return chain.proceed(chain.request());
    }
}
