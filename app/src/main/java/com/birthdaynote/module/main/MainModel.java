package com.birthdaynote.module.main;

import com.birthdaynote.data.SingleDataManager;
import com.birthdaynote.data.entity.LocalData;
import com.birthdaynote.data.entity.Weather;
import com.birthdaynote.data.global.APP_KEY;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.DecorationData;
import com.birthdaynote.library.mvp.MvpModel;
import com.google.gson.Gson;

import java.util.HashMap;

public class MainModel extends MvpModel<BaseData> {
    private static final String LOCATION_SAVE_KEY = "LOCATION_SAVE_KEY";
    private static final String LOCATION_SAVE_TYPE = "LOCATION_SAVE_TYPE";
    String cityName = "深圳";
    private static final String WEATHER_URL = "https://way.jd.com/jisuapi/weather";


    BaseData getWeatherData() {
        BaseData.Builder builder = new BaseData.Builder();

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("city", cityName);
        hashMap.put("appkey", APP_KEY.WEATHER_APPKEY);
        DecorationData synchronizeData = SingleDataManager.getDataManager().getSynchronizeData(WEATHER_URL, hashMap, Weather.class);
        int dataCode = synchronizeData.getCode();

        if (dataCode == DecorationData.DEF_CODE) {

            Weather data = synchronizeData.getData();
            String code = data.getCode();
            Weather.ResultBeanX result = data.getResult();

            if (result != null && "10000".equals(code)) {

                Weather.ResultBeanX.ResultBean resultBean = result.getResult();

                if (resultBean != null && result.getStatus() == 0) {

                    BaseData build = builder.isOk(true).build();
                    build.putData("city", resultBean.getCity());
                    build.putData("weather", resultBean.getWeather());
                    build.putData("temp", resultBean.getTemp());
                    build.putData("temphigh", resultBean.getTemphigh());
                    build.putData("templow", resultBean.getTemplow());
                    build.putData("sunrise", resultBean.getDaily().get(0).getSunrise());
                    build.putData("sunset", resultBean.getDaily().get(0).getSunset());
                    build.putData("quality", resultBean.getAqi().getQuality());
                    return build;
                } else {
                    return builder.isOk(false).errorCode(00).message(result.getMsg()).build();
                }
            } else {
                return builder.isOk(false).errorCode(00).message(data.getMsg()).build();
            }

        }

        return builder
                .isOk(false)
                .errorCode(synchronizeData.getCode())
                .message(synchronizeData.getMesage())
                .build();

    }


    void saveLocalData(LocalData localData) {
        SingleDataManager.getDataManager().saveLocalData(LOCATION_SAVE_KEY, LOCATION_SAVE_TYPE, localData);
    }

    LocalData getLocalData() {
        return SingleDataManager.getDataManager().getLocalData(LOCATION_SAVE_KEY, LOCATION_SAVE_TYPE, LocalData.class);
    }

    @Override
    public void onCleared() {

    }
}
