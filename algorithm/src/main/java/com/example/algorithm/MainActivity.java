package com.example.algorithm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.birthdaynote.library.mvp.MvpViewActivity;
import com.birthdaynote.library.mvp.PresenterInterface;
import com.birthdaynote.library.widget.recycler.BaseQuickAdapter;
import com.example.algorithm.algorithm.Step20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends MvpViewActivity {
    private NameData[] name = new NameData[]{
            new NameData("200台阶", Step20.class.getName()),
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
                startContainerActivity(className);
            }
        });
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
