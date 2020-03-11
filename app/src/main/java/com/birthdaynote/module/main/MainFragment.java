package com.birthdaynote.module.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.library.mvp.MvpFragment;
import com.birthdaynote.library.mvp.PtrFactoryInterface;

public class MainFragment extends MvpFragment<MainPtr,MainEven>{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fm,container,false);
    }

    @Override
    protected MainPtr initPtr() {
        PtrFactoryInterface ptrFactory = getPtrFactory();
        return ptrFactory.newPtr(MainPtr.class);
    }
}
