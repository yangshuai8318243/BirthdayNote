package com.example.algorithm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * Created by Android Studio.
 * User: SnapeYang
 * Date: 2020/9/1
 * Time: 15:46
 */
public class TestViewG2 extends LinearLayout {
    public TestViewG2(Context context) {
        super(context);
    }

    public TestViewG2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewG2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(getClass().getSimpleName(), "------------onInterceptTouchEvent----------");
        return true;
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
