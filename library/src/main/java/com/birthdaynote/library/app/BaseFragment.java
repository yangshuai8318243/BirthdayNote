package com.birthdaynote.library.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.library.mvp.ContainerActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class BaseFragment extends Fragment implements PermissionsListener {
    protected static String TAG = "";
    private static final String BASE_TAG = "BaseFragment";

    @Override
    public void onAttach(Context context) {
        TAG = this.getClass().getName();
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 添加Fragment
     *
     * @param fragmentClass
     * @param viewId
     * @param <F>
     */
    protected <F extends Fragment> void addFragment(Class<F> fragmentClass, int viewId) {
        addFragment(fragmentClass, viewId, fragmentClass.getName());
    }

    /**
     * 添加Fragment
     *
     * @param fragment
     * @param viewId
     */
    protected void addFragment(Fragment fragment, int viewId) {
        addFragment(fragment, viewId, fragment.getClass().getName());
    }

    /**
     * 添加Fragment
     *
     * @param fragmentClass
     * @param viewId
     * @param tag
     * @param <F>
     */
    protected <F extends Fragment> void addFragment(Class<F> fragmentClass, int viewId, String tag) {
        Fragment baseFragment = null;
        try {
            baseFragment = fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        if (baseFragment != null) {
            addFragment(baseFragment, viewId, tag);
        } else {
            throw new RuntimeException("baseFragment is Null");
        }
    }

    /**
     * 添加Fragment
     *
     * @param fragment
     * @param viewId
     * @param tag
     */
    protected void addFragment(Fragment fragment, int viewId, String tag) {
        if (fragment != null) {
            FragmentTransaction trans = getActivity().getSupportFragmentManager()
                    .beginTransaction();
            trans.add(viewId, fragment, tag);
            trans.commitAllowingStateLoss();
        } else {
            throw new RuntimeException("baseFragment is Null");
        }
    }

    /**
     * 显示Fragment
     *
     * @param fragment
     */
    protected void shwoFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 隐藏Fragment
     *
     * @param fragment
     */
    protected void hideFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }

    /**
     * 替换Fragment
     *
     * @param fragmentClass
     * @param viewId
     * @param <F>
     */
    protected <F extends Fragment> void replaceFragment(Class<F> fragmentClass, int viewId, String tag) {
        Fragment baseFragment = null;
        try {
            baseFragment = fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        if (baseFragment != null) {
            replaceFragment(baseFragment, viewId);
        } else {
            throw new RuntimeException("baseFragment is Null");
        }
    }


    /**
     * 替换Fragment
     *
     * @param fragment
     * @param viewId
     */
    protected void replaceFragment(Fragment fragment, int viewId) {
        if (fragment != null) {
            FragmentTransaction trans = getActivity().getSupportFragmentManager()
                    .beginTransaction();
            trans.replace(viewId, fragment);
            trans.commitAllowingStateLoss();
        } else {
            throw new RuntimeException("baseFragment is Null");
        }
    }


    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(getContext(), clz));
    }

    /**
     * 单个权限申请
     *
     * @param permission
     */
    protected void requestPermissions(String permission) {
        String[] strings = new String[]{permission};
        requestPermissions(strings);
    }

    /**
     * 多个权限申请
     *
     * @param permissions
     */
    @Override
    public void requestPermissions(String[] permissions) {
        List<String> permissionList = new ArrayList<>();

        for (String pes : permissions) {
            if (ContextCompat.checkSelfPermission(getContext(), pes) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pes);
            } else {
                onSuccessPermissions(pes);
            }
        }

        if (!permissionList.isEmpty()) {  //申请的集合不为空时，表示有需要申请的权限
            ActivityCompat.requestPermissions(getActivity(), permissionList.toArray(new String[permissionList.size()]), 1);
        }
    }


    @Override
    public void onFailurePermissions(String Permission) {

    }

    @Override
    public void onSuccessPermissions(String Permission) {

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

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(getContext(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    public void startContainerActivity(String canonicalName) {
        startContainerActivity(canonicalName, null, null);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param activityName  需要显示的ActivityName
     */
    public void startContainerActivity(String canonicalName, String activityName) {
        startContainerActivity(canonicalName, null, activityName);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     */
    public void startContainerActivity(String canonicalName, Bundle bundle) {
        startContainerActivity(canonicalName, bundle, null);
    }

    /**
     * 跳转容器页面
     *
     * @param canonicalName 规范名 : Fragment.class.getCanonicalName()
     * @param bundle        跳转所携带的信息
     */
    public void startContainerActivity(String canonicalName, Bundle bundle, String activityName) {
        Intent intent = new Intent(getContext(), ContainerActivity.class);
        Log.e(TAG, "===canonicalName===>" + canonicalName);
        intent.putExtra(ContainerActivity.FRAGMENT, canonicalName);
        if (activityName != null) {
            intent.putExtra(ContainerActivity.ACTIVITY_NAME, activityName);
        }
        if (bundle != null) {
            intent.putExtra(ContainerActivity.BUNDLE, bundle);
        }
        startActivity(intent);
    }
}
