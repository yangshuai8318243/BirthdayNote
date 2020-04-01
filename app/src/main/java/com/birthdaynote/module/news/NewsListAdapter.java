package com.birthdaynote.module.news;

import com.birthdaynote.R;
import com.birthdaynote.library.data.entity.BaseData;
import com.birthdaynote.library.widget.recycler.BaseQuickAdapter;

import androidx.annotation.NonNull;

public class NewsListAdapter extends BaseQuickAdapter<BaseData, NewsViewHolder> {
    public NewsListAdapter() {
        super(R.layout.news_item_layout, null);
    }

    @Override
    protected void convert(@NonNull NewsViewHolder helper, BaseData item) {
        String category = item.getData("category");
        String content = item.getData("content");
        String src = item.getData("src");
        String time = item.getData("time");
        String title = item.getData("title");
        String url = item.getData("url");
        String weburl = item.getData("weburl");

        helper.news_item_src.setText(src);
        helper.news_item_time.setText(time);
        helper.news_item_title.setText(title);

    }
}