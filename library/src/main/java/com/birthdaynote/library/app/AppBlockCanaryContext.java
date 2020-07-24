package com.birthdaynote.library.app;

import android.content.Context;
import android.util.Log;

import com.github.moduth.blockcanary.BlockCanaryContext;
import com.github.moduth.blockcanary.internal.BlockInfo;

public class AppBlockCanaryContext extends BlockCanaryContext {
    private static final String TAG = "AppBlockCanaryContext";

    @Override
    public String provideUid() {
        return "ShuaiYang";
    }

    @Override
    public void onBlock(Context context, BlockInfo blockInfo) {
        super.onBlock(context, blockInfo);
        Log.e(TAG, blockInfo.toString());
    }
}
