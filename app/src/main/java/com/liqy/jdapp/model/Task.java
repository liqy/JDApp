package com.liqy.jdapp.model;

import com.liqy.jdapp.model.network.OK;
import com.liqy.jdapp.model.network.OkCallback;

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
