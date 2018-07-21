package com.liqy.jdapp.category.present;

import android.util.Log;

import com.google.gson.Gson;
import com.liqy.jdapp.category.CategoryActivity;
import com.liqy.jdapp.category.model.ITask;
import com.liqy.jdapp.category.model.ResData;
import com.liqy.jdapp.category.model.Task;
import com.liqy.jdapp.network.OkCallback;
import com.liqy.jdapp.category.view.IView;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class MyPresent extends OkCallback implements IPresent {

    ITask task;
    IView view;

    public MyPresent(CategoryActivity activity) {
        task = new Task();
        view = activity;
    }

    /**
     * 获取数据
     */
    @Override
    public void getData() {
        task.getListData("http://39.108.3.12:3000/v1/food/32", this);
    }

    @Override
    public void onUI(String json) {
        Gson gson=new Gson();
        ResData data= gson.fromJson(json,ResData.class);
        view.showData(data.data);
        Log.d("UI", json);
    }

    @Override
    public void onFailed(String json) {
        Log.d("UI", json);
    }

    /**
     * 销毁，防止内存泄漏
     */
    @Override
    public void onDestroy() {
        //销毁视图
        view = null;
    }
}
