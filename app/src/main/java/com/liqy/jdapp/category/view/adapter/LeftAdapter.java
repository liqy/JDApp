package com.liqy.jdapp.category.view.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqy.jdapp.R;
import com.liqy.jdapp.category.model.SetMeal;

import java.util.ArrayList;
import java.util.List;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.TextViewHolder> {

    List<SetMeal> list;
    ItemClickListener listener;

    public LeftAdapter() {
        this.list = new ArrayList<>();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }

    public void addData(List<SetMeal> meals) {
        this.list.addAll(meals);
        notifyDataSetChanged();
    }

    class TextViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public TextViewHolder(@NonNull View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.txt_meal);
        }
    }

    @NonNull
    @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_left, viewGroup, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TextViewHolder textViewHolder, int i) {
        final SetMeal meal = list.get(i);
        textViewHolder.textView.setText(meal.name);
        //根据选中状态更新条目背景
        if (meal.isClick) {
            textViewHolder.itemView.setBackgroundColor(Color.GRAY);
        } else {
            textViewHolder.itemView.setBackgroundColor(Color.WHITE);
        }

        textViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClick(meal);

                //遍历集合 ，修改中状态
                for (SetMeal temp : list) {
                    if (temp.id == meal.id) {//判断是否为当前条目
                        temp.isClick = true;
                    } else {
                        temp.isClick = false;
                    }
                }
                //帅新当前页面
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ItemClickListener {
        void onItemClick(SetMeal meal);
    }
}
