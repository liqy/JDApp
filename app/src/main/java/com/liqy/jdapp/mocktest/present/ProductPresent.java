package com.liqy.jdapp.mocktest.present;

import com.google.gson.Gson;
import com.liqy.jdapp.mocktest.ProductListActivity;
import com.liqy.jdapp.mocktest.model.ProductData;
import com.liqy.jdapp.mocktest.model.ProductTask;
import com.liqy.jdapp.mocktest.view.IProductView;
import com.liqy.jdapp.network.OkCallback;

import java.net.URLEncoder;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class ProductPresent implements IProductPresent {

    ProductTask task; //定义M层
    IProductView view;//定义V层

    /**
     * 构造方法
     * @param activity
     */
    public ProductPresent(ProductListActivity activity) {
        task = new ProductTask();//初始化 M层
        view = activity;//初始化V层
    }

    /**
     * 实现接口
     * @param key 搜索的关键词
     * @param page 页数
     */
    @Override
    public void getSearchData(String key, int page) {

//        拼接字符串
        String keyword = URLEncoder.encode(key);//记得中文编码
        String url = "http://www.zhaoapi.cn/product/searchProducts?keywords=" + keyword + "&page=" + page + "&sort=0";


        //M 层实际网络方法调用
        task.searchData(url, new OkCallback() {
            @Override
            public void onUI(String json) {

                //回调结果
                Gson gson = new Gson();
                ProductData data = gson.fromJson(json, ProductData.class);
                if (view != null)
                    view.showData(data.data);
            }

            @Override
            public void onFailed(String json) {
                if (view != null)
                    view.showError(json);
            }
        });
    }

    @Override
    public void onDestroy() {
        view = null;// 销毁v层，避免内存泄漏
    }

}
