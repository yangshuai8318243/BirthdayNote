package com.birthdaynote.module.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.mvp.even.DefEven;
import com.birthdaynote.module.birthday.BirthdayFragment;
import com.birthdaynote.module.my.MyFragment;
import com.birthdaynote.module.news.NewsFragment;
import com.birthdaynote.module.tools.ToolsFragment;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BDFragment<HomePtr, DefEven> {
    private static final String SAVE_TAB_KEY = "SAVE_TAB_KEY";

    @BindView(R.id.news_icon)
    Button news_itme;

    @BindView(R.id.brithday_icon)
    Button brithday_icon;

    @BindView(R.id.tools_icon)
    Button tools_icon;

    @BindView(R.id.my_icon)
    Button my_icon;
    private int thisViewId;
    private int saveViewId = -1;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            BaseDataList test = arguments.getParcelable("test");
            if (test != null) {
                Log.e(TAG, "========>" + test.toString());
            } else {
                Log.e(TAG, "==test==null====>");
            }
        }
    }

    private void shwoFragment(int viewId) {

        Fragment fragment = null;
        thisViewId = viewId;
        switch (viewId) {
            case R.id.brithday_icon:
                fragment = getChildFragmentManager().findFragmentByTag(BirthdayFragment.class.getName());
                if (fragment == null) {
                    addFragment(BirthdayFragment.class, R.id.home_content);
                }

                break;
            case R.id.tools_icon:
                fragment = getChildFragmentManager().findFragmentByTag(ToolsFragment.class.getName());
                if (fragment == null) {
                    addFragment(ToolsFragment.class, R.id.home_content);
                }

                break;
            case R.id.my_icon:
                fragment = getChildFragmentManager().findFragmentByTag(MyFragment.class.getName());
                if (fragment == null) {
                    addFragment(MyFragment.class, R.id.home_content);
                }

                break;
            case R.id.news_icon:
                fragment = getChildFragmentManager().findFragmentByTag(NewsFragment.class.getName());
                if (fragment == null) {
                    addFragment(NewsFragment.class, R.id.home_content);
                }
                break;
        }

        Fragment fragmentByTag = getChildFragmentManager().findFragmentByTag(NewsFragment.class.getName());
        if (fragmentByTag != null) {
            hideFragment(fragmentByTag);
        }
        fragmentByTag = getChildFragmentManager().findFragmentByTag(BirthdayFragment.class.getName());
        if (fragmentByTag != null) {
            hideFragment(fragmentByTag);
        }
        fragmentByTag = getChildFragmentManager().findFragmentByTag(ToolsFragment.class.getName());
        if (fragmentByTag != null) {
            hideFragment(fragmentByTag);
        }
        fragmentByTag = getChildFragmentManager().findFragmentByTag(MyFragment.class.getName());
        if (fragmentByTag != null) {
            hideFragment(fragmentByTag);
        }

        if (fragment != null) {
            shwoFragment(fragment);
        }

        FrameLayout viewById = findViewById(R.id.home_content);
        int childCount = viewById.getChildCount();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        news_itme.setEnabled(false);
        if (saveViewId != -1) {
            shwoFragment(saveViewId);
            View view = findViewById(saveViewId);
            showTable(view);
        } else {
            shwoFragment(R.id.news_icon);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @OnClick({R.id.brithday_icon, R.id.news_icon, R.id.tools_icon, R.id.my_icon})
    void onBrithdayIconClick(View view) {
        showTable(view);
        shwoFragment(view.getId());
    }

    private void showTable(View view) {
        news_itme.setEnabled(true);
        brithday_icon.setEnabled(true);
        tools_icon.setEnabled(true);
        my_icon.setEnabled(true);
        view.setEnabled(false);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home_layout, container, false);
        bindView(inflate);
        return inflate;
    }

    @Override
    protected HomePtr initPtr() {
        return getPtrFactory().newPtr(HomePtr.class, this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(SAVE_TAB_KEY, thisViewId);
        getChildFragmentManager().getFragments().clear();
        super.onSaveInstanceState(outState);
    }

}
