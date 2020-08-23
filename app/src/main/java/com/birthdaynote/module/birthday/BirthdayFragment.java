package com.birthdaynote.module.birthday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;
import com.birthdaynote.module.birthday.show.ShowEdActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.OnClick;

public class BirthdayFragment extends BDFragment<BirthdayPtr, BirthdayEven> {


    @Override
    protected BirthdayPtr initPtr() {
        return getPtrFactory().newPtr(BirthdayPtr.class, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.brithday_fragment_layout, container, false);
        bindView(inflate);
        return inflate;
    }

    @OnClick(R.id.to_show_bt)
    public void toShowView() {
        startActivity(ShowEdActivity.class);
    }
}
