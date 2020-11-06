package com.example.algorithm.tablayout;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

import com.birthdaynote.library.app.BaseActivity;
import com.birthdaynote.library.log.AppLog;
import com.birthdaynote.library.util.ScreenUtil;
import com.example.algorithm.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends BaseActivity {
    private static final String TAG = "TabLayoutActivity";

    private LinearLayout mTabLayout;
    private ViewPager2 testViewPager;
    private List<ExpressionEntity> expressionEntities;

    {
        expressionEntities = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ExpressionEntity expressionEntity = new ExpressionEntity().setId(i).setType(i);
            for (int j = 0; j < 10; j++) {
                expressionEntity.addExperssionItem(new ExpressionEntity.ExperssionItem.Builder().key("i:" + i + " j:" + j).name("item_" + j).build());
            }
            expressionEntities.add(expressionEntity);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity_layout);
        mTabLayout = findViewById(R.id.test_tab);
        testViewPager = findViewById(R.id.test_view_pager);

        mTabLayout.removeAllViews();
        for (int i = 0; i < expressionEntities.size(); i++) {
            ExpressionEntity expressionEntity = expressionEntities.get(i);

            Button button = new Button(getBaseContext());
            mTabLayout.addView(button);
            int with = ScreenUtil.dip2px(getBaseContext(), 50);
            ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
            layoutParams.width = with;
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
            button.setLayoutParams(layoutParams);
            final int finalI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    testViewPager.setCurrentItem(finalI);
                }
            });
        }

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(expressionEntities);
        testViewPager.setAdapter(viewPagerAdapter);
        testViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                AppLog.e(TAG, "-------onPageSelected--------->" + position);
            }
        });

    }
}
