package com.example.algorithm.view;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birthdaynote.library.app.BaseFragment;
import com.birthdaynote.library.widget.recycler.BaseQuickAdapter;
import com.example.algorithm.ImageBinder;
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
import com.example.algorithm.tablayout.TabLayoutActivity;

import java.util.Arrays;
import java.util.List;

public class OnTouchFragment extends BaseFragment {

    private MainActivity.NameData[] name = new MainActivity.NameData[]{
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.on_touch_fragment_layout, null, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView recyclerView = findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NameAdapter nameAdapter = new NameAdapter();
        nameAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(nameAdapter);
        List<MainActivity.NameData> strings = Arrays.asList(name);
        nameAdapter.setNewData(strings);


    }
}
