package com.example.algorithm.algorithm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.birthdaynote.library.mvp.MvpFragment;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.example.algorithm.ImageBinder;
import com.example.algorithm.R;

public class ImagFragment extends MvpFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.image_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
           ImageBinder bitmap = (ImageBinder) arguments.getBinder("bitmap");
            if (bitmap != null) {
                ImageView imageView = getView().findViewById(R.id.image_view);
                imageView.setImageBitmap(bitmap.getBitmap());
            }
        }
        int pid = android.os.Process.myPid();
        Log.e(TAG,"---------------------->" + pid);
    }

    @Override
    protected PresenterInterface initPtr() {
        return null;
    }
}
