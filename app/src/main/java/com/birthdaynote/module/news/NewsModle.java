package com.birthdaynote.module.news;

import android.util.Log;

import com.birthdaynote.data.SingleDataManager;
import com.birthdaynote.data.entity.NewData;
import com.birthdaynote.data.global.APP_KEY;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.data.entity.DecorationData;
import com.birthdaynote.library.mvp.MvpModel;

import java.util.HashMap;
import java.util.List;

public class NewsModle extends MvpModel<BaseData> {
    private static final String NEWS_URL = "https://way.jd.com/jisuapi/get";
    private int newNum;
    private String newsChannel;

    BaseDataList getNewsData(boolean refresh) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("appkey", APP_KEY.JING_DONG_API_KEY);
        hashMap.put("channel", "头条");
        if (refresh) {
            hashMap.put("start", "0");
        } else {
            hashMap.put("start", String.valueOf(newNum));
        }
        hashMap.put("num", "10");

        DecorationData synchronizeData = SingleDataManager.getDataManager().getSynchronizeData(NEWS_URL, hashMap, NewData.class);
        BaseDataList build;
        if (synchronizeData.isOk()) {
            NewData newData = synchronizeData.getData();
            String code = newData.getCode();
            if ("10000".equals(code)) {
                NewData.ResultBeanX result = newData.getResult();
                if (result.getStatus() == 0) {
                    NewData.ResultBeanX.ResultBean resultBean = result.getResult();
                    build = new BaseDataList.Builder().build();
                    if (refresh) {
                        newNum = resultBean.getNum();
                    } else {
                        newNum = newNum + resultBean.getNum();
                    }
                    newsChannel = resultBean.getChannel();
                    List<NewData.ResultBeanX.ResultBean.ListBean> list = resultBean.getList();

                    for (NewData.ResultBeanX.ResultBean.ListBean news : list) {
                        BaseData baseData = new BaseData.Builder().build();
                        String category = news.getCategory();
                        baseData.putData("category", category);

                        String content = news.getContent();
                        baseData.putData("content", content);

                        String pic = news.getPic();
                        baseData.putData("pic", pic);

                        String src = news.getSrc();
                        baseData.putData("src", src);

                        String time = news.getTime();
                        baseData.putData("time", time);

                        String title = news.getTitle();
                        baseData.putData("title", title);

                        String url = news.getUrl();
                        baseData.putData("url", url);

                        String weburl = news.getWeburl();
                        baseData.putData("weburl", weburl);

                        build.saveData(baseData);
                    }
                } else {
                    build = new BaseDataList.Builder().isOk(false).errorCode(result.getStatus()).message(result.getMsg()).build();
                }
            } else {
                build = new BaseDataList.Builder().isOk(false).errorCode(Integer.valueOf(code)).message(newData.getMsg()).build();
            }
        } else {
            build = new BaseDataList.Builder().isOk(false).errorCode(synchronizeData.getCode()).message(synchronizeData.getMesage()).build();
        }

        return build;
    }

    @Override
    public void onCleared() {

    }
}
