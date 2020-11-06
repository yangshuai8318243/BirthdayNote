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

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.folderselector.FolderChooserDialog;
import com.birthdaynote.R;
import com.birthdaynote.app.BDActivity;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.util.ToastUtils;
import com.birthdaynote.module.birthday.BirthdayEven;

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
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(InPutBirthdayActivity.this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                // TODO Auto-generated method stub
                int mYear = year;
                int mMonth = month;
                int mDay = day;
                //更新EditText控件日期 小于10加0

            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        //设置时间范围
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();

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
