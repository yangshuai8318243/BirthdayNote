package com.birthdaynote.library.data.net;

import com.birthdaynote.library.data.entity.ErrorData;

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
    <R> R getRequest(Class<R> rClass, String requestUrl) throws IOException, ErrorData;

    /**
     *
     * @param rClass
     * @param requestUrl
     * @param param
     * @param <D>
     * @param <R>
     * @return
     */
    <R> R postRequest(Class<R> rClass, String requestUrl, Map<String,String> param) throws IOException, ErrorData;


}
