package com.birthdaynote.module.main;

import android.util.Log;

import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.data.entity.DataItem;
import com.birthdaynote.library.data.entity.DataItemArray;
import com.birthdaynote.library.mvp.MvpModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainModel extends MvpModel<BaseData> {
    private static final OkHttpClient client = new OkHttpClient.Builder().build();
    private static final String APPKEY = "43d4085685512e1c2e9566b79f329936";

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
