package com.birthdaynote.module.main;

import android.util.Log;

import com.birthdaynote.library.data.DataManager;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.data.entity.DataItem;
import com.birthdaynote.library.data.entity.DataItemArray;
import com.birthdaynote.library.data.entity.ErrorData;
import com.birthdaynote.library.mvp.MvpModel;
import com.birthdaynote.net.SingleDataManager;

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
    private static final String APPKEY = "cd8b3d4c5ff88e61058d3d45f2db7d06";


    BaseData getImageData() {
        Request.Builder builder = new Request.Builder();

        builder.url("http://v.juhe.cn/toutiao/index?type=top&key=" + APPKEY);
        Call call = client.newCall(builder.build());
        try {
            Response execute = call.execute();
            ResponseBody body = execute.body();
            String string = body.string();
            Log.e("getImageData", "---->" + string);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BaseData build = new BaseData.Builder()
                .mBoolean(false)
                .mStr("11111")
                .mDouble(1.0)
                .mInt(233)
                .mLong(1233L).build();
//        build.getmListData().saveData("key1",new BaseData.Builder().mStr("xxxxxx1111111xxxxxxxxxx").build());
        return build;
    }

    private void postReq() {
        Request.Builder builder = new Request.Builder();

        builder.url("http://v.juhe.cn/toutiao/index");
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        FormBody formBody = new FormBody.Builder()
                .add("type", "top")
                .add("key", APPKEY)
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

    void testNetData() {
        DataManager dataManager = SingleDataManager.getDataManager();
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("subject", "1");
        stringStringHashMap.put("model", "c1");
        stringStringHashMap.put("testType", "rand");
        stringStringHashMap.put("key", APPKEY);
        dataManager.getData("http://v.juhe.cn/jztk/query", stringStringHashMap, CarApiResult.class, new DataManager.OnDataListener<CarApiResult>() {
            @Override
            public void onError(ErrorData errorData) {
                Log.e(TAG, "---testNetData-----onError----->" + errorData.getMesage());
            }

            @Override
            public void onData(CarApiResult carApiResult) {
                Log.e(TAG, "---testNetData-----onData----->" + carApiResult.toString());

            }
        });
    }

    BaseDataList getList() {
        BaseDataList baseDataList = new BaseDataList();
        baseDataList.saveData(new BaseData.Builder().mStr("getList").build());
        return baseDataList;
    }

    DataItemArray getArrList() {
        DataItemArray dataItemArray = new DataItemArray();
        dataItemArray.add("dataItemArrayStr");
        DataItem dataItem = new DataItem();
        dataItem.setBool("setBool", true);
        dataItemArray.add(dataItem);
        return dataItemArray;
    }

    BaseData getPost() {
        BaseData build = new BaseData.Builder()
                .mBoolean(false)
                .mStr("11111")
                .mDouble(1.0)
                .mInt(233)
                .mLong(1233L).build();
        build.putData("xxxxx", "sdada");

//        build.getmListData().saveData("key1",new BaseData.Builder().mStr("xxxxxx1111111xxxxxxxxxx").build());
        return build;
    }

    @Override
    public void onCleared() {

    }
}
