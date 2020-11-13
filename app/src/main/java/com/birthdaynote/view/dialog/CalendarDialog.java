package com.birthdaynote.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.birthdaynote.R;
import com.birthdaynote.library.log.AppLog;
import com.necer.calendar.BaseCalendar;
import com.necer.calendar.MonthCalendar;
import com.necer.entity.Lunar;
import com.necer.enumeration.CheckModel;
import com.necer.enumeration.DateChangeBehavior;
import com.necer.listener.OnCalendarChangedListener;
import com.necer.utils.LunarUtil;

import org.joda.time.LocalDate;

public class CalendarDialog extends Dialog {
    private static final String TAG = "CalendarDialog";
    private MonthCalendar monthCalendar;
    private LocalDate nawLocalDate;
    private TextView showText;

    public CalendarDialog(@NonNull Context context) {
        super(context);
    }

    public CalendarDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CalendarDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        initView();
    }

    /**
     * 初始化界面控件
     */
    protected void initView() {
        setContentView(R.layout.calendar_dialog_layout);
        monthCalendar = findViewById(R.id.monthCalendar);
        monthCalendar.setCheckMode(CheckModel.SINGLE_DEFAULT_CHECKED);
        nawLocalDate = new LocalDate();
        monthCalendar.setDefaultCheckedFirstDate(true);
        showText = findViewById(R.id.show_date_text);

        monthCalendar.setOnCalendarChangedListener(new OnCalendarChangedListener() {
            @Override
            public void onCalendarChange(BaseCalendar baseCalendar, int year, int month, LocalDate localDate, DateChangeBehavior dateChangeBehavior) {
                nawLocalDate = localDate;
                updateTextDate();
            }
        });

        findViewById(R.id.last_year_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate localDate = nawLocalDate.minusYears(1);
                AppLog.e(TAG, localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
                monthCalendar.jumpDate(localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
            }
        });
        findViewById(R.id.last_month_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate localDate = nawLocalDate.minusMonths(1);
                AppLog.e(TAG, localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
                monthCalendar.jumpDate(localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
            }
        });
        findViewById(R.id.next_month_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate localDate = nawLocalDate.plusMonths(1);
                AppLog.e(TAG, localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
                monthCalendar.jumpDate(localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
            }
        });
        findViewById(R.id.next_year_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalDate localDate = nawLocalDate.plusYears(1);
                AppLog.e(TAG, localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
                monthCalendar.jumpDate(localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
            }
        });
        findViewById(R.id.affirm_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void updateTextDate() {
        int year = nawLocalDate.getYear();
        int month = nawLocalDate.getMonthOfYear();
        int day = nawLocalDate.getDayOfMonth();
        Lunar lunar = LunarUtil.getLunar(year, month, day);
        String toString = new StringBuilder()
                .append(year)
                .append("-")
                .append(month)
                .append("-")
                .append(day)
                .append("\n")
                .append(lunar.lunarYearStr)
                .append(" ")
                .append(lunar.lunarMonthStr)
                .append(" ")
                .append(lunar.lunarDayStr)
                .toString();
        showText.setText(toString);
    }
}
