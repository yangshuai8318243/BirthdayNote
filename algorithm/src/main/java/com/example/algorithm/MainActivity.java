package com.example.algorithm;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.widget.recycler.BaseQuickAdapter;
import com.example.algorithm.algorithm.array.ArrayNew;
import com.example.algorithm.algorithm.array.ArraySort;
import com.example.algorithm.algorithm.array.FindData;
import com.example.algorithm.algorithm.base.TestPower;
import com.example.algorithm.algorithm.linked.LinkedListFlip;
import com.example.algorithm.algorithm.step.Step20;
import com.example.algorithm.algorithm.str.Str100W;
import com.example.algorithm.algorithm.thread.ShunXuThread;
import com.example.algorithm.algorithm.tree.TreeAlgorithm;
import com.example.algorithm.designPatterns.ProducerConsumer;
import com.example.algorithm.rx.TestRx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends MvpViewActivity {
    private NameData[] name = new NameData[]{
            new NameData("20台阶", Step20.class.getName()),
            new NameData("翻转一个单向链表", LinkedListFlip.class.getName()),
            new NameData("100W条字符串选出重复最多的十条", Str100W.class.getName()),
            new NameData("数组排序", ArraySort.class.getName()),
            new NameData("二分查找", FindData.class.getName()),
            new NameData("数组创建", ArrayNew.class.getName()),
            new NameData("树算法", TreeAlgorithm.class.getName()),
            new NameData("打印数字", TestPower.class.getName()),
            new NameData("线程算法", ShunXuThread.class.getName()),
            new NameData("生产者消费者", ProducerConsumer.class.getName()),
            new NameData("Rx测试", TestRx.class.getName()),
//            new NameData("ImageFragment", ImagFragment.class.getName()),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.listName);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        NameAdapter nameAdapter = new NameAdapter();
        nameAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        recyclerView.setAdapter(nameAdapter);
        List<NameData> strings = Arrays.asList(name);
        nameAdapter.setNewData(strings);
        nameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NameData o = (NameData) adapter.getData().get(position);
                Log.e(TAG, o.className + "============" + o.name);
                String className = o.getClassName();

                if (o.name.equals("ImageFragment")) {
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test_gre);
                    Bundle bundle = new Bundle();
                    bundle.putBinder("bitmap", new ImageBinder(bitmap));
                    startContainerActivity(className, bundle, o.name);

                } else {
                    startContainerActivity(className, o.name);
                }
            }
        });
        int pid = android.os.Process.myPid();
        Log.e(TAG,"-------1111--------------->" + pid);
        new Handler();
        HandlerThread;
    }



    @Override
    protected PresenterInterface initPtr() {
        return null;
    }

    static class NameData {
        String name;
        String className;

        public NameData(String name, String className) {
            this.name = name;
            this.className = className;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
