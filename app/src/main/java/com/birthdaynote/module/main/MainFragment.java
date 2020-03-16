package com.birthdaynote.module.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import butterknife.BindView;
import butterknife.OnClick;

public class MainFragment extends BDFragment<MainPtr,MainEven> {
    @BindView(R.id.back_btn)
    Button mButton;
    @BindView(R.id.main_image)
    ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.main_fm, container, false);
        bindView(inflate);
        return inflate;
    }


    @OnClick(R.id.back_btn)
    protected void onClick(){
        Log.e(TAG,"----------OnClick------>");
        MainEven mainEven = new MainEven();
        mainEven.setTag(MainEven.MAIN_GET_IMAGE_DATA);
        sendEvenBindData(mainEven).bindLiveData(MainEven.MAIN_GET_IMAGE_DATA, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String o) {
            }
        });

    }


    @Override
    protected MainPtr initPtr() {
        return getPtrFactory().newPtr(MainPtr.class,this);
    }
}
