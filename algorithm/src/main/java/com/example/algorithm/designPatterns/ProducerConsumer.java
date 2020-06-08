package com.example.algorithm.designPatterns;

import android.util.Log;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者消费者实现
 */
public class ProducerConsumer extends AlgorithmBaseFragment {
    private boolean isData;
    private String data;
    private int max = 10;
    private List<String> strings = new ArrayList<>();

    public synchronized void add(String addData) {
        while (strings.size() >= max) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        strings.add(addData);
        this.notifyAll();
        Log.e(TAG, "生产了数据添加:" + addData);
    }

    public synchronized String get() {
        while (strings.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.notifyAll();
        String remove = strings.remove(0);
        Log.e(TAG, "消费了数据:" + remove);
        return remove;
    }

    private int index = 0;

    @Override
    protected void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    add("数据：" + i);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    String str = get();
                }
            }
        }).start();

    }
}
