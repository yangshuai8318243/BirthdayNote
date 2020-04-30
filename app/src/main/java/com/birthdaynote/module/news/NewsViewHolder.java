package com.birthdaynote.module.news;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.TextView;

import com.birthdaynote.R;
import com.birthdaynote.library.widget.recycler.BaseViewHolder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends BaseViewHolder {
    @BindView(R.id.news_item_image)
    SimpleDraweeView news_item_image;

    @BindView(R.id.news_item_title)
    TextView news_item_title;

    @BindView(R.id.news_item_src)
    TextView news_item_src;

    @BindView(R.id.news_item_time)
    TextView news_item_time;

    public NewsViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
        Context context = view.getContext();
        Resources resources = context.getResources();
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(resources);
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)
//                .setPlaceholderImage(new MyCustomDrawable())
//                .setBackgrounds(backgroundList)
//                .setOverlays(overlaysList)
                .build();
        news_item_image.setHierarchy(hierarchy);

    }


}
