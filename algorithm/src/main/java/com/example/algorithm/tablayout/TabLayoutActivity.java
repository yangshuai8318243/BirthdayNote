package com.example.algorithm.tablayout;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.birthdaynote.library.app.BaseActivity;
import com.birthdaynote.library.log.AppLog;
import com.birthdaynote.library.util.ScreenUtil;
import com.birthdaynote.library.widget.recycler.BaseQuickAdapter;
import com.example.algorithm.MainActivity;
import com.example.algorithm.NameAdapter;
import com.example.algorithm.R;
import com.example.algorithm.algorithm.array.ArrayNew;
import com.example.algorithm.algorithm.array.ArraySort;
import com.example.algorithm.algorithm.array.FindData;
import com.example.algorithm.algorithm.base.TestPower;
import com.example.algorithm.algorithm.linked.LinkedListFlip;
import com.example.algorithm.algorithm.step.Step20;
import com.example.algorithm.algorithm.str.Str100W;
import com.example.algorithm.algorithm.thread.ShunXuThread;
import com.example.algorithm.algorithm.tree.TreeAlgorithm;
import com.example.algorithm.database.DataBaseFragment;
import com.example.algorithm.designPatterns.ProducerConsumer;
import com.example.algorithm.evenTest.EventTestFragment;
import com.example.algorithm.file.FileDataTest;
import com.example.algorithm.json.GosnTest;
import com.example.algorithm.rx.TestRx;
import com.example.algorithm.view.OnTouchFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabLayoutActivity extends BaseActivity {
    private static final String TAG = "TabLayoutActivity";
    private String shareText = "<a href=29441010001><color=#68c0e5>[光荣宝戒]</color></a>";
    MainActivity.NameData[] name;
    private LinearLayout mTabLayout;
    private ViewPager2 testViewPager;
    private List<ExpressionEntity> expressionEntities;
    private View inflate;

    {
        expressionEntities = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ExpressionEntity expressionEntity = new ExpressionEntity().setId(i).setType(i);
            for (int j = 0; j < 10; j++) {
                expressionEntity.addExperssionItem(new ExpressionEntity.ExperssionItem.Builder().key("i:" + i + " j:" + j).name("item_" + j).build());
            }
            expressionEntities.add(expressionEntity);
        }

        name = new MainActivity.NameData[]{
                new MainActivity.NameData("20台阶", Step20.class.getName()),
                new MainActivity.NameData("翻转一个单向链表", LinkedListFlip.class.getName()),
                new MainActivity.NameData("100W条字符串选出重复最多的十条", Str100W.class.getName()),
                new MainActivity.NameData("数组排序", ArraySort.class.getName()),
                new MainActivity.NameData("二分查找", FindData.class.getName()),
                new MainActivity.NameData("数组创建", ArrayNew.class.getName()),
                new MainActivity.NameData("树算法", TreeAlgorithm.class.getName()),
                new MainActivity.NameData("打印数字", TestPower.class.getName()),
                new MainActivity.NameData("线程算法", ShunXuThread.class.getName()),
                new MainActivity.NameData("生产者消费者", ProducerConsumer.class.getName()),
                new MainActivity.NameData("Rx测试", TestRx.class.getName()),
                new MainActivity.NameData("Rx事件测试测试", EventTestFragment.class.getName()),
                new MainActivity.NameData("数据库测试", DataBaseFragment.class.getName()),
                new MainActivity.NameData("文件测试", FileDataTest.class.getName()),
                new MainActivity.NameData("json测试", GosnTest.class.getName()),
                new MainActivity.NameData("tab测试", TabLayoutActivity.class.getName()).setActivity(true),
                new MainActivity.NameData("滑动冲突测试", OnTouchFragment.class.getName()),
//            new NameData("ImageFragment", ImagFragment.class.getName()),
        };

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_activity_layout);
        mTabLayout = findViewById(R.id.test_tab);
        testViewPager = findViewById(R.id.test_view_pager);

        mTabLayout.removeAllViews();

        inflate = LayoutInflater.from(getBaseContext()).inflate(R.layout.on_touch_fragment_layout, null, false);
        getWindow().addContentView(inflate, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        inflate.setVisibility(View.GONE);

        RecyclerView recyclerView = inflate.findViewById(R.id.list);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        NameAdapter nameAdapter = new NameAdapter();
        nameAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(nameAdapter);
        List<MainActivity.NameData> strings = Arrays.asList(name);
        nameAdapter.setNewData(strings);

        TextView textView = findViewById(R.id.show_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflate.setVisibility(View.VISIBLE);
            }
        });
//        textView.setText(Html.fromHtml(shareText));
//
//        for (int i = 0; i < expressionEntities.size(); i++) {
//            ExpressionEntity expressionEntity = expressionEntities.get(i);
//
//            Button button = new Button(getBaseContext());
//            mTabLayout.addView(button);
//            int with = ScreenUtil.dip2px(getBaseContext(), 50);
//            ViewGroup.LayoutParams layoutParams = button.getLayoutParams();
//            layoutParams.width = with;
//            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
//            button.setLayoutParams(layoutParams);
//            final int finalI = i;
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    testViewPager.setCurrentItem(finalI);
//                }
//            });
//        }
//
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(expressionEntities);
//        testViewPager.setAdapter(viewPagerAdapter);
//        testViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                AppLog.e(TAG, "-------onPageSelected--------->" + position);
//            }
//        });

    }
}
