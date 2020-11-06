package com.example.algorithm.json;

import com.birthdaynote.library.log.AppLog;
import com.example.algorithm.AlgorithmBaseFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/10/9
 * Time: 15:37
 */
public class GosnTest extends AlgorithmBaseFragment {
    @Override
    protected void run() {

        String toJson = new Gson().toJson(new JsonData.Builder<JsonDataContent>().content(new JsonDataContent.Builder().age(11).tag("TEST_TAG").type("TEST_TYPE").build()).name("NAME-TAG").build());
        String data = " {\"age\":11,\"tag\":\"TEST_TAG\",\"type\":\"TEST_TYPE\"}";
        AppLog.i(TAG, "--------------", toJson);
        JsonData jsonData = new Gson().fromJson(toJson, JsonData.class);
        AppLog.i(TAG, "--------------", jsonData);

        String content = jsonData.getContent().toString();
        JsonDataContent jsonDataContent = new Gson().fromJson(content, JsonDataContent.class);

//        Map<String,Object> map = new Gson().fromJson(toJson, new TypeToken<HashMap<String,Object>>(){}.getType());
        AppLog.i(TAG, "--------------", jsonDataContent);

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("" + i);
        }
        for (int i = strings.size() - 1; i >= 0; i--) {
            String s = strings.get(i);
            if (s.equals("5")) {
                strings.remove(i);
            }
            AppLog.i(TAG, "--------->::", s);
        }
        AppLog.i(TAG, "--------->::", strings);

    }
}
