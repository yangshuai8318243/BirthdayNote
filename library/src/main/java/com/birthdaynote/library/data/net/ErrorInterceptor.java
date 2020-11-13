package com.birthdaynote.library.data.net;

import android.util.Log;

import com.birthdaynote.library.util.PrintUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ErrorInterceptor implements Interceptor {
    private HashMap<Integer, String> massage = new HashMap<>();

    public ErrorInterceptor(HashMap<Integer, String> massage) {
        this.massage = massage;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        if (proceed.code() == 200) {
            return proceed;
        } else {
            Response build = proceed.newBuilder().message("网络错误").build();
            return build;
        }

    }
}
