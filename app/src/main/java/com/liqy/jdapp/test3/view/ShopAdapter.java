package com.liqy.jdapp.test3.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqy.jdapp.R;
import com.liqy.jdapp.test3.model.Shop;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期六 YourCompany.
 */
public class ShopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Shop> shops;
    Context context;
    LayoutInflater inflater;
    DisplayImageOptions options;
    ImageLoader imageLoader;

    public ShopAdapter(Context context) {
        this.shops = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();

        imageLoader = ImageLoader.getInstance();
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void addData(List<Shop> list) {
        this.shops.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.item_list, viewGroup, false);

        return new ShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Shop shop = shops.get(i);

        if (viewHolder instanceof ShopViewHolder) {
            ShopViewHolder holder = (ShopViewHolder) viewHolder;
            holder.txt.setText(shop.name);
            imageLoader.displayImage(shop.pic_url, holder.img, options);
        }
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    static class ShopViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txt;

        public ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img_shop);
            txt = (TextView) itemView.findViewById(R.id.txt_shop);
        }
    }
}
