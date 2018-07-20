package com.liqy.jdapp.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqy.jdapp.R;
import com.liqy.jdapp.model.SetMeal;

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
    public void onBindViewHolder(@NonNull TextViewHolder textViewHolder, int i) {
        final SetMeal meal = list.get(i);
        textViewHolder.textView.setText(meal.name);
        textViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(meal);
                //TODO
                if (meal.isClick){

                }else {

                }
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
