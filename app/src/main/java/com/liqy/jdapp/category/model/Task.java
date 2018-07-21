package com.liqy.jdapp.category.model;

import com.liqy.jdapp.network.OK;
import com.liqy.jdapp.network.OkCallback;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class Task implements ITask{
    @Override
    public void getListData(String url, OkCallback callback) {
        OK.getOk().doGet(url,callback);
    }
}
