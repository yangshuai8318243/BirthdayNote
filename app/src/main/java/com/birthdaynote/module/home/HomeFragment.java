package com.birthdaynote.module.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.mvp.even.DefEven;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class HomeFragment extends BDFragment<HomePtr, DefEven> {

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
}
