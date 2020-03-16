package com.birthdaynote.library.data.net;

import java.util.Map;

public interface NetworkDataManager {

    <D> D getRequest(D data,String requestUrl);

    <D> D postRequest(D data, String requestUrl, String jsonStr);


}
