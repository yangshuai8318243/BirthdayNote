package com.birthdaynote.library.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected <F extends Fragment> void addFragment(Class<F> fragmentClass, int viewId){
        Fragment baseFragment = null;
        try {
            baseFragment = fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (baseFragment != null){
            addFragment(baseFragment,viewId);
        }else {
            throw new RuntimeException("baseFragment is Null");
        }
    }

    protected void addFragment(Fragment fragment,int viewId){
        if (fragment != null){
            FragmentTransaction trans = getSupportFragmentManager()
                    .beginTransaction();
            trans.replace(viewId, fragment);
            trans.commitAllowingStateLoss();
        }else {
            throw new RuntimeException("baseFragment is Null");
        }
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(this, clz));
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

}
