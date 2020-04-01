package com.birthdaynote.module.news;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.birthdaynote.R;
import com.birthdaynote.app.BDFragment;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.data.entity.BaseDataList;
import com.birthdaynote.library.widget.recycler.BaseQuickAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

public class NewsFragment extends BDFragment<NewsPtr, NewsEven> {

    @BindView(R.id.news_list)
    RecyclerView newsList;

    @BindView(R.id.refresh_view)
    SwipeRefreshLayout refreshLayout;
    private NewsListAdapter newsListAdapter;
    private Observer<BaseDataList> updataListData;
    private boolean isRefresh;

    @Override
    protected NewsPtr initPtr() {
        return getPtrFactory().newPtr(NewsPtr.class, this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.news_fragment_layout, container, false);
        bindView(inflate);
        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initList();
        initLiveData();
        isRefresh = true;
        reuestData();

    }


    private void initList() {
        refreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e(TAG, "-----------onRefresh----------->");

                isRefresh = true;
                reuestData();
            }
        });

        newsList.setLayoutManager(new LinearLayoutManager(getContext()));
        newsListAdapter = new NewsListAdapter();
        newsListAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        newsList.setAdapter(newsListAdapter);
        newsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                Log.e(TAG, "-----------newsListAdapter.onLoadMoreRequested----------->");
                isRefresh = false;
                reuestData();
            }
        }, newsList);
    }


    private void initLiveData() {
        updataListData = baseDataList -> {
            if (baseDataList.getOk()) {
                if (isRefresh) {
                    Log.e(TAG, "-----------newsListAdapter.setNewData----------->");
                    newsListAdapter.setNewData(baseDataList.getDataList());
                } else {
                    Log.e(TAG, "-----------newsListAdapter.addData----------->");
                    newsListAdapter.addData(baseDataList.getDataList());
                }
            } else {
                //数据获取失败
            }

            refreshLayout.setRefreshing(false);
            newsListAdapter.loadMoreComplete();
            isRefresh = false;
        };

        bindLiveData(NewsEven.UPDATE_NEWS_DATA, updataListData);
    }

    private void reuestData() {
        NewsEven newsEven = new NewsEven();
        newsEven.setTag(NewsEven.UPDATE_NEWS_DATA);
        BaseData build = new BaseData.Builder().build();
        build.putData(NewsEven.UPDATE_NEWS_DATA_REFRESH_KEY, isRefresh);
        newsEven.setBaseData(build);
        if (isRefresh) {
            newsListAdapter.setEnableLoadMore(false);
        }
        sendEven(newsEven);
    }


}
