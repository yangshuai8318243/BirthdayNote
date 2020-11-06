package com.birthdaynote.module.birthday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;
import com.birthdaynote.module.birthday.input.InPutBirthdayActivity;
import com.birthdaynote.uitls.LunarSolarConverter;

import butterknife.BindView;
import butterknife.OnClick;

public class BirthdayFragment extends BDFragment<BirthdayPtr, BirthdayEven> {

    @BindView(R.id.show_text)
    TextView showText;

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
        String today = LunarSolarConverter.today();
        showText.setText(today);
        startActivity(InPutBirthdayActivity.class);
    }
}
