package com.liqy.jdapp.category.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liqy.jdapp.R;
import com.liqy.jdapp.category.model.Spus;

import java.util.ArrayList;
import java.util.List;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class RightAdapter extends RecyclerView.Adapter<RightAdapter.DescViewHolder>{

    List<Spus> list;

    public RightAdapter() {
        list=new ArrayList<>();
    }

    public void addData(List<Spus> spusList){
        this.list.clear();
        this.list.addAll(spusList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DescViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_left,viewGroup,false);
        return new DescViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescViewHolder descViewHolder, int i) {
        Spus temp=list.get(i);
        descViewHolder.textView.setText(temp.name);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DescViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public DescViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.txt_meal);
        }
    }

}
