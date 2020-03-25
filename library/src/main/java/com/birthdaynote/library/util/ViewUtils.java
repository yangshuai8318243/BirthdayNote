package com.birthdaynote.library.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.Nullable;

public class ViewUtils {
    private static float sSysDensity;
    private static float sSysScaledDensity;

    public static void setCustomDensity(@Nullable Activity activity, @Nullable final Application application) {
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        if (sSysDensity == 0) {
            sSysDensity = appDisplayMetrics.density;
            sSysScaledDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sSysScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {
                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels / 360; //(暂定宽度为360，也可以改为高度方向等);
        final float targetScaleDensity = targetDensity * (sSysScaledDensity / sSysDensity);
        final int targetDensityDpi = (int) (160 * targetDensity);
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.scaledDensity = targetScaleDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        final DisplayMetrics atyDisplayMetrics = activity.getResources().getDisplayMetrics();
        atyDisplayMetrics.density = targetDensity;
        atyDisplayMetrics.scaledDensity = targetScaleDensity;
        atyDisplayMetrics.densityDpi = targetDensityDpi;


    }

}
