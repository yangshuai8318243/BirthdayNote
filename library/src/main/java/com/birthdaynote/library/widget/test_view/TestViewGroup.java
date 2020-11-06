package com.birthdaynote.library.widget.test_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class TestViewGroup extends RelativeLayout {

    public TestViewGroup(Context context) {
        super(context);
    }

    public TestViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int layoutWidth = 0;
        int layoutHeight = 0;
        int cWidth = 0;
        int cHeight = 0;
        int count = getChildCount();

        measureChildren(widthMeasureSpec, heightMeasureSpec);
        int defWith = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int defHight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(defWith, defHight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        int childAtMeasuredWidth = 0;
        int childAtMeasuredHeight = 0;

        int layoutWidth = 0;
        int layoutHight = 0;
        int maxChildHeight = 0;


        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);

            childAtMeasuredWidth = childAt.getMeasuredWidth();
            childAtMeasuredHeight = childAt.getMeasuredHeight();
            int paddingLeft = childAt.getPaddingLeft();
            int paddingRight = childAt.getPaddingRight();
            int paddingTop = childAt.getPaddingTop();
            int paddingBottom = childAt.getPaddingBottom();
            int maxHitht = childAtMeasuredHeight + paddingTop + paddingBottom;
            int maxWidth = childAtMeasuredWidth + paddingLeft + paddingRight;


            if ((layoutWidth + maxWidth) < getWidth()) {
                l = layoutWidth;
                r = l + maxWidth;
                t = layoutHight;
                b = layoutHight + maxHitht;

            } else {
                layoutWidth = 0;
                layoutHight += maxChildHeight;
                maxChildHeight = 0;

                l = layoutWidth;
                r = l + maxWidth;
                t = layoutHight;
                b = layoutHight + maxHitht;

            }
            layoutWidth += maxWidth;

            if (maxHitht > maxChildHeight) {
                maxChildHeight = maxHitht;
            }

            childAt.layout(l, t, r, b);
        }


    }
}
