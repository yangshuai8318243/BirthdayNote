package com.birthdaynote.module.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyFragment extends BDFragment<MyPtr, MyEven> {

    @Override
    protected MyPtr initPtr() {
        return getPtrFactory().newPtr(MyPtr.class, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.my_fragment_layout, container, false);
        bindView(inflate);
        return inflate;
    }
}
