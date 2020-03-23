package com.birthdaynote.library.data.net;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

public class ReqestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        RequestBody body = chain.request().body();
        Buffer buffer = new Buffer();
        body.writeTo(buffer);
        byte[] bytes = buffer.readByteArray();
        String s = new String(bytes);
        Log.e("ReqestInterceptor", s);
        return chain.proceed(chain.request());
    }
}
