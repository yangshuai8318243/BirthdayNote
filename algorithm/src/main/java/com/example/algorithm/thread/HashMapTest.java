package com.example.algorithm.thread;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.HashMap;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/15
 * Time: 17:34
 */
public class HashMapTest extends AlgorithmBaseFragment {

    private HashMap<String, String> stringStringHashMap;

    @Override
    protected void run() {
        stringStringHashMap = new HashMap<>();

        for (int i = 0; i < 20; i++) {
            stringStringHashMap.put("key" + i, "var" + i);
        }

    }


}
