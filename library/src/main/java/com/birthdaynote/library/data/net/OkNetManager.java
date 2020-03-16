package com.birthdaynote.library.data.net;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkNetManager implements NetworkDataManager {

    private OkHttpClient mOkHttpClient;
    private Headers mHeader;
    private MediaType mMediaType;

    public OkNetManager(OkHttpClient mOkHttpClient, Headers mHeader, MediaType mMediaType) {
        this.mOkHttpClient = mOkHttpClient;
        this.mHeader = mHeader;
        this.mMediaType = mMediaType;
    }

    private Request buildReques(String requestUrl, String jsonStr) {
        Request.Builder builder = new Request.Builder();

        if (mHeader != null && mHeader.size() > 0) {
            builder.headers(mHeader);
        }

        if (jsonStr != null) {
            RequestBody requestBody = RequestBody.create(mMediaType, jsonStr);
            builder.post(requestBody);
        }

        Request build = builder.url(requestUrl).build();
        return build;
    }

    @Override
    public <D> D getRequest(D data, String requestUrl) {
        return request(data, requestUrl, null);
    }

    @Override
    public <D> D postRequest(D data, String requestUrl, String jsonStr) {
        return request(data, requestUrl, jsonStr);
    }

    private <D> D request(D data, String requestUrl, String jsonStr) {
        Request request = buildReques(requestUrl, null);
        Call call = mOkHttpClient.newCall(request);

        try {
            Response execute = call.execute();
            String string = execute.body().string();
            D fromJson = new Gson().<D>fromJson(string, data.getClass());
            return fromJson;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
