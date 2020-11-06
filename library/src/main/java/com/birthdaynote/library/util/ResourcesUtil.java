package com.birthdaynote.library.util;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.DrawableRes;


import com.birthdaynote.library.app.BaseApp;

import java.io.File;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/8/31
 * Time: 16:41
 */
public class ResourcesUtil {

    public static String getResourcesUri(@DrawableRes int id) {
        return getResourcesUri(id, BaseApp.getInstance());
    }

    public static String getResourcesUri(@DrawableRes int id, Context context) {
        Resources resources = context.getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }

    public static String getFrescoResourcesUri(@DrawableRes int id) {
        String packageName = BaseApp.getInstance().getPackageName();
        String toString = new StringBuilder()
                .append("res://")
                .append(packageName)
                .append("/")
                .append(id)
                .toString();
        return toString;
    }

    public static String getAssetsUri(String path, String name) {
        String toString = new StringBuilder()
                .append("file")
                .append("://")
                .append("android_asset")
                .append(File.separatorChar)
                .append(path)
                .append(File.separatorChar)
                .append(name)
                .toString();
        return toString;
    }
}
