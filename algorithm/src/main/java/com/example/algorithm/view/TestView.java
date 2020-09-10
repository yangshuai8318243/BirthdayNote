package com.example.algorithm.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/1
 * Time: 15:48
 */
@SuppressLint("AppCompatCustomView")
public class TestView extends Button {
    public TestView(Context context) {
        super(context);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        Log.e(getClass().getSimpleName(), "------------onTouchEvent----------" + x + "  " + y);
        return true;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e(getClass().getSimpleName(), "------------dispatchTouchEvent----------");
        return super.dispatchTouchEvent(ev);
    }

}
