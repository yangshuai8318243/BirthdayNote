package com.birthdaynote.module.main;

import android.util.Log;

import com.birthdaynote.data.entity.CarApiResult;
import com.birthdaynote.data.entity.Weather;
import com.birthdaynote.data.global.APP_KEY;
import com.birthdaynote.library.data.DataManager;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.data.entity.DecorationData;
import com.birthdaynote.library.mvp.MvpModel;
import com.birthdaynote.data.SingleDataManager;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainModel extends MvpModel<BaseData> {
    private static final OkHttpClient client = new OkHttpClient.Builder().build();
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
            Log.e(TAG, "2222222222222");

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

        return builder.isOk(false).

                errorCode(synchronizeData.getCode()).

                message(synchronizeData.getMesage()).

                build();

    }

    private void postReq() {
        Request.Builder builder = new Request.Builder();

        builder.url("http://v.juhe.cn/toutiao/index");
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        FormBody formBody = new FormBody.Builder()
                .add("type", "top")
                .add("key", APP_KEY.APPKEY)
                .build();
        builder.post(formBody);
        Call call = client.newCall(builder.build());
        try {
            Response execute = call.execute();
            ResponseBody body = execute.body();
            String string = body.string();
            Log.e("getImageData", "---->" + string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCleared() {

    }
}
