package com.birthdaynote.module.tools;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ToolsFragment extends BDFragment<ToolsPtr, ToolsEven> {

    @Override
    protected ToolsPtr initPtr() {
        return getPtrFactory().newPtr(ToolsPtr.class,this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.tools_fragment_layout, container, false);
        bindView(inflate);
        return inflate;
    }
}
