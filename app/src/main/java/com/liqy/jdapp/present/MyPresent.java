package com.liqy.jdapp.present;

import android.util.Log;

import com.google.gson.Gson;
import com.liqy.jdapp.MainActivity;
import com.liqy.jdapp.model.ITask;
import com.liqy.jdapp.model.ResData;
import com.liqy.jdapp.model.Task;
import com.liqy.jdapp.model.network.OkCallback;
import com.liqy.jdapp.view.IView;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class MyPresent extends OkCallback implements IPresent {

    ITask task;
    IView view;

    public MyPresent(MainActivity activity) {
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
