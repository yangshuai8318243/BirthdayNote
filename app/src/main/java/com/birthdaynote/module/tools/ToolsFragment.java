package com.birthdaynote.module.tools;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.OnClick;

import static android.content.pm.PackageManager.GET_META_DATA;

public class ToolsFragment extends BDFragment<ToolsPtr, ToolsEven> {

    public final static String sPluginManagerName = "pluginmanager.apk";


    @Override
    protected ToolsPtr initPtr() {
        return getPtrFactory().newPtr(ToolsPtr.class, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.tools_fragment_layout, container, false);
        bindView(inflate);
        return inflate;
    }

    @OnClick(R.id.test_pkg)
    void testPkg(View view) {

        File pluginManagerFile = new File(getContext().getFilesDir(), sPluginManagerName);

        try {
            InputStream is = getContext().getAssets().open(sPluginManagerName);
            FileUtils.copyInputStreamToFile(is, pluginManagerFile);
            String absolutePath = pluginManagerFile.getAbsolutePath();
            String canonicalPath = pluginManagerFile.getCanonicalPath();

            PackageManager packageManager = getActivity().getApplication().getPackageManager();
            PackageInfo packageArchiveInfo = packageManager.getPackageArchiveInfo(canonicalPath, GET_META_DATA);
            packageArchiveInfo.applicationInfo.publicSourceDir = canonicalPath;
            packageArchiveInfo.applicationInfo.sourceDir = canonicalPath;
            packageManager.getResourcesForApplication(packageArchiveInfo.applicationInfo);

            Log.e(TAG, "===ChangeApkContextWrapper=====>" + absolutePath);
            Log.e(TAG, "===ChangeApkContextWrapper=====>" + canonicalPath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

}
