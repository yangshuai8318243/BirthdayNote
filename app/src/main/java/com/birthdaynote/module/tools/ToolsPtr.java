package com.birthdaynote.module.tools;

import com.birthdaynote.library.mvp.MvpPresenter;

import java.util.HashMap;
import java.util.Map;

import androidx.lifecycle.LiveData;

public class ToolsPtr extends MvpPresenter<ToolsFragment, ToolsEven, ToolsModle> {
    public ToolsPtr(ToolsFragment view) {
        super(view);
    }

    @Override
    protected Map<String, LiveData> addLiveData() {
        return new HashMap<>();
    }

    @Override
    protected ToolsModle bindModel() {
        return coreateModel(ToolsModle.class);
    }

    @Override
    public void accept(ToolsEven toolsEven) throws Exception {

    }
}
