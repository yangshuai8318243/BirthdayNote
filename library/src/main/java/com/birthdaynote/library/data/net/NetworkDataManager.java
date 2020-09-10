package com.birthdaynote.library.data.net;

import com.birthdaynote.library.data.entity.DecorationData;

import java.io.IOException;
import java.util.Map;

public interface NetworkDataManager {
    /**
     * get请求统一接口
     *
     * @param data
     * @param requestUrl
     * @param <D>
     * @return
     */
    <R> R getRequest(Class<R> rClass, String requestUrl) throws IOException, DecorationData, Throwable;

    byte[] getRequest(String requestUrl) throws IOException, DecorationData, Throwable;

    /**
     * @param rClass
     * @param requestUrl
     * @param param
     * @param <D>
     * @param <R>
     * @return
     */
    <R> R postRequest(Class<R> rClass, String requestUrl, Map<String, String> param) throws IOException, DecorationData, Throwable;


}
