package com.example.algorithm.thread;

import android.app.IntentService;

import com.example.algorithm.AlgorithmBaseFragment;

import java.util.concurrent.atomic.AtomicInteger;

public class LockTest extends AlgorithmBaseFragment {
    @Override
    protected void run() {
        AtomicInteger atomicInteger = new AtomicInteger();
        int i = atomicInteger.get();
        int andIncrement = atomicInteger.getAndIncrement();
        IntentService intentService = null;
    }


}
