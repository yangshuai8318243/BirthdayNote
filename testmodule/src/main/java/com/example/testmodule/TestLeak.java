package com.example.testmodule;

import android.content.Context;
import android.util.Log;

public class TestLeak {
    private static TestLeak tl;
    private Context context;

    private TestLeak(Context context) {
        this.context = context;
    }
    //导致了内存泄漏
    public static TestLeak getInstance(Context context) {
        if (tl == null) {
            tl = new TestLeak(context);
// tl = new TestLeak(context.getApplicationContext());
        }
        return tl;
    }
    public void fly(){
        Log.e("TestLeak","--------TestLeak----fly-------->");
    }
}
