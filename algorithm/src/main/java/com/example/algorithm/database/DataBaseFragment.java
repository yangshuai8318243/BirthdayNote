package com.example.algorithm.database;

import com.example.algorithm.AlgorithmBaseFragment;
import com.example.algorithm.database.test.CreatData;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/4
 * Time: 11:16
 */
public class DataBaseFragment extends AlgorithmBaseFragment {
    CreatData creatData;

    @Override
    protected void run() {
        if (creatData == null) {
            creatData = new CreatData(getContext());
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
//                creatData.findData();
                creatData.insert();
//                creatData.initData();
//                creatData.getData();
//                creatData.test(getContext());
            }
        }).start();
    }
}
