package com.birthdaynote.module.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.birthdaynote.R;
import com.birthdaynote.library.widget.recycler.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends BaseViewHolder {
    @BindView(R.id.news_item_image)
    ImageView news_item_image;

    @BindView(R.id.news_item_title)
    TextView news_item_title;

    @BindView(R.id.news_item_src)
    TextView news_item_src;

    @BindView(R.id.news_item_time)
    TextView news_item_time;

    public NewsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }


}
