package com.liqy.jdapp.network;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public abstract class OkCallback implements Callback{

    public abstract void onUI(String json);//更新UI
    public abstract void onFailed(String json);//返回错误信息

    @Override
    public void onFailure(Call call,final IOException e) {
        OK.getHandler().post(new Runnable() {
            @Override
            public void run() {
                onFailed(e.getMessage());
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String json=response.body().string();//注意
        OK.getHandler().post(new Runnable() {
            @Override
            public void run() {
                onUI(json);
            }
        });

    }
}
