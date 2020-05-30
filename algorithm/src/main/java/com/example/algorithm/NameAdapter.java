package com.example.algorithm;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.birthdaynote.library.widget.recycler.BaseQuickAdapter;
import com.birthdaynote.library.widget.recycler.BaseViewHolder;

public class NameAdapter extends BaseQuickAdapter<MainActivity.NameData, NameAdapter.NameHolder> {
    public NameAdapter() {
        super(R.layout.item_name_layout);
    }

    @Override
    protected void convert(@NonNull NameHolder helper, MainActivity.NameData item) {
        helper.name.setText(item.getName());
    }


    static class NameHolder extends BaseViewHolder {
        TextView name;

        public NameHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name_text);
        }

    }
}
