package com.example.algorithm.tablayout;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.algorithm.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder> {
    private List<ExpressionEntity> expressionEntities;

    public ViewPagerAdapter(List<ExpressionEntity> expressionEntities) {
        this.expressionEntities = expressionEntities;
    }

    public List<ExpressionEntity> getExpressionEntities() {
        return expressionEntities;
    }

    @NonNull
    @Override
    public ViewPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_item_layout, parent, false);
        return new ViewPagerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerViewHolder holder, int position) {
        ExpressionEntity expressionEntity = getExpressionEntities().get(position);
        holder.setText("viewpager数据为" + expressionEntity.getId() + " " + expressionEntity.getType());
    }

    @Override
    public int getItemCount() {
        if (expressionEntities != null) {
            return expressionEntities.size();
        }
        return 0;
    }

    public static class ViewPagerViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewPagerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.viewpager_item_text);
        }

        public void setText(String text) {
            if (TextUtils.isEmpty(text)) return;
            if (textView != null) {
                textView.setText(text);
            }
        }
    }
}
