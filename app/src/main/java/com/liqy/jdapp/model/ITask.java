package com.liqy.jdapp.model;

import com.liqy.jdapp.model.network.OkCallback;

/**
 * @file FileName
 * 网络请求接口
 * Copyright 星期五 YourCompany.
 */
public interface ITask {
    public void getListData(String url, OkCallback callback);
}
