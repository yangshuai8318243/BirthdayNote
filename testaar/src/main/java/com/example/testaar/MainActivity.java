package com.example.testaar;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test_arr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        requestAppPermission();
    }

    private void requestAppPermission() {
        String[] strings = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA};

        requestPermissions(strings);
    }

    public void requestPermissions(String[] permissions) {
        List<String> permissionList = new ArrayList<>();

        for (String pes : permissions) {
            if (ContextCompat.checkSelfPermission(this, pes) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pes);
            } else {
                onSuccessPermissions(pes);
            }
        }
        if (!permissionList.isEmpty()) {  //申请的集合不为空时，表示有需要申请的权限
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        }
    }

    public void onFailurePermissions(String permission) {

    }

    public void onSuccessPermissions(String permission) {

    }

    /**
     * 权限申请返回结果
     *
     * @param requestCode  请求码
     * @param permissions  权限数组
     * @param grantResults 申请结果数组，里面都是int类型的数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 获取到Activity下的Fragment
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments == null) {
            return;
        }
        // 查找在Fragment中onRequestPermissionsResult方法并调用
        for (Fragment fragment : fragments) {
            if (fragment != null) {
                // 这里就会调用我们Fragment中的onRequestPermissionsResult方法
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }

        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) { //安全写法，如果小于0，肯定会出错了
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String pes = permissions[i];

                        if (grantResult == PackageManager.PERMISSION_DENIED) { //这个是权限拒绝
                            onFailurePermissions(pes);
                        } else { //授权成功了
                            onSuccessPermissions(pes);
                        }
                    }
                }
                break;
            default:
                break;
        }
    }


}
