package com.birthdaynote.module.birthday.input;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.birthdaynote.R;
import com.birthdaynote.app.BDActivity;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.util.ToastUtils;
import com.birthdaynote.module.birthday.BirthdayEven;
import com.birthdaynote.view.dialog.CalendarDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InPutBirthdayActivity extends BDActivity<InputBirthdayPtr, BirthdayEven> {

    @BindView(R.id.gregorian_calendar_btn)
    Button gregorianCalendarBtn;
    @BindView(R.id.lunar_calendar_btn)
    Button lunarCalendarBtn;
    @BindView(R.id.show_input_text)
    TextView showInputText;


    @Override
    protected Unbinder binderView() {
        return ButterKnife.bind(this);
    }

    @Override
    protected int getActivityViewId() {
        return R.layout.input_brithday_ac_layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindLiveData();
    }

    @OnClick(R.id.lunar_calendar_btn)
    public void onLunarCalendarBtnClick(View view) {

    }

    @OnClick(R.id.gregorian_calendar_btn)
    public void onGregorianCalendarBtnClick(View view) {

    }

    @OnClick(R.id.select_date_btn)
    public void onSelectDateBtnClick(View view) {
        new CalendarDialog(this).show();
    }

    private void bindLiveData() {
        bindLiveData(BirthdayEven.INPUT_BIRTHDAY_SAVE, new Observer<BaseData>() {
            @Override
            public void onChanged(BaseData baseData) {
                if (baseData != null && baseData.getIsOk()) {
                    ToastUtils.showShortSafe("保存成功");
                    finish();
                }
            }
        });
    }

    @Override
    protected InputBirthdayPtr initPtr() {
        return getPtrFactory().newPtr(InputBirthdayPtr.class, this);
    }
}
