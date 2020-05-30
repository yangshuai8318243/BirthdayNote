package com.example.algorithm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.birthdaynote.library.mvp.MvpFragment;
import com.birthdaynote.library.mvp.PresenterInterface;

public abstract class BaseFragment extends MvpFragment {
    protected Button runBtn;
    protected TextView textView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.algorithm_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        runBtn = view.findViewById(R.id.run_btn);
        textView = view.findViewById(R.id.algorithm_text);
        runBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                run();
            }
        });
    }

    protected abstract void run();

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
