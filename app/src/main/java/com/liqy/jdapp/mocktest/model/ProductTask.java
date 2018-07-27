package com.liqy.jdapp.mocktest.model;

import com.liqy.jdapp.network.OK;
import com.liqy.jdapp.network.OkCallback;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class ProductTask {

    /**
     * 定义实际搜索的方法，执行网络请求
     * @param url
     * @param callback
     */
    public void searchData(String url, OkCallback callback){
        OK.getOk().doGet(url,callback);
    }

}
