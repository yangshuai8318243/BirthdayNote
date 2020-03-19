package com.birthdaynote.library.data.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkNetManager implements NetworkDataManager {

    private OkHttpClient mOkHttpClient;
    private Headers mHeader;
    private String mMediaType;

    public OkNetManager(OkHttpClient mOkHttpClient, Headers mHeader, String mMediaType) {
        this.mOkHttpClient = mOkHttpClient;
        this.mHeader = mHeader;
        this.mMediaType = mMediaType;
    }

    private Request buildReques(String requestUrl, Map<String, String> param) {
        Request.Builder builder = new Request.Builder();
        if (mHeader != null && mHeader.size() > 0) {
            builder.headers(mHeader);
        }

        if (param != null) {
            FormBody.Builder formBder = new FormBody.Builder();
            for (String key : param.keySet()) {
                formBder.add(key,param.get(key));
            }
            builder.post(formBder.build());
        }

        Request build = builder.url(requestUrl).build();
        return build;
    }


    @Override
    public <R> R getRequest(Class<R> rClass, String requestUrl) {
        return request(rClass,requestUrl,null);
    }

    @Override
    public <R> R postRequest(Class<R> rClass, String requestUrl, Map<String, String> param) {
        return request(rClass, requestUrl, param);
    }


    private <R> R request(Class<R> rClass, String requestUrl, Map<String, String> param) {
        Request request = buildReques(requestUrl, param);
        Call call = mOkHttpClient.newCall(request);

        try {
            Response execute = call.execute();
            String string = execute.body().string();
            R fromJson = new Gson().<R>fromJson(string, rClass);
            return fromJson;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
