package com.birthdaynote.library.data.net;

import android.util.Log;

import com.birthdaynote.library.data.entity.DecorationData;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkNetManager implements NetworkDataManager {

    private OkHttpClient mOkHttpClient;
    private String mMediaType;

    public OkNetManager(OkHttpClient mOkHttpClient, Headers mHeader, String mMediaType) {
        OkHttpClient build = mOkHttpClient.newBuilder()
                .addInterceptor(new ErrorInterceptor(new HashMap<Integer, String>()))
                .dns(new Dns() {
                    @Override
                    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                        Log.e("---Dns---", hostname);

                        return Arrays.asList(InetAddress.getAllByName(hostname));
                    }
                }).build();

        this.mOkHttpClient = build;
        this.mMediaType = mMediaType;
    }

    private Request buildReques(String requestUrl, Map<String, String> param) {
        Request.Builder builder = new Request.Builder();

        if (param != null) {
            FormBody.Builder formBder = new FormBody.Builder();
            for (String key : param.keySet()) {
                formBder.add(key, param.get(key));
            }
            builder.post(formBder.build());
        }

        Request build = builder.url(requestUrl).build();
        return build;
    }


    @Override
    public <R> R getRequest(Class<R> rClass, String requestUrl) throws Throwable {
        return request(rClass, requestUrl, null);
    }

    @Override
    public <R> R postRequest(Class<R> rClass, String requestUrl, Map<String, String> param) throws Throwable {
        return request(rClass, requestUrl, param);
    }

    @Override
    public byte[] getRequest(String requestUrl) throws IOException, DecorationData, Throwable {
        Request request = buildReques(requestUrl, null);
        Call call = mOkHttpClient.newCall(request);
        Response execute = call.execute();
        byte[] bytes = execute.body().bytes();
        return bytes;
    }


    private <R> R request(Class<R> rClass, String requestUrl, Map<String, String> param) throws Throwable {
        Request request = buildReques(requestUrl, param);
        Call call = mOkHttpClient.newCall(request);

        Response execute = call.execute();

        if (execute.code() != 200) {
            throw new DecorationData.Builder().mesage(execute.message()).code(execute.code()).build();
        }

        String string = execute.body().string();
        R fromJson = new Gson().<R>fromJson(string, rClass);
        return fromJson;

    }

}
