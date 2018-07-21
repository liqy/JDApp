package com.liqy.jdapp.test3.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.liqy.jdapp.test3.model.BannerImage;
import com.youth.banner.loader.ImageLoader;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期六 YourCompany.
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide.with(context).load(((BannerImage) path).imagePath).into(imageView);
    }
}
