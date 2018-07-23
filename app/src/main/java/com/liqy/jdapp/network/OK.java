package com.liqy.jdapp.network;

import android.os.Handler;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class OK {

    private static OK ok;

    private static OkHttpClient client;

    private static Handler handler;

    protected static Handler getHandler() {
        return handler;
    }

    private OK() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                //TODO 拦截器，打印日志
                .addInterceptor(logging)
                .build();

        handler = new Handler();
    }

    /**
     * 获得单例
     *
     * @return
     */
    public static OK getOk() {
        if (ok == null) {
            synchronized (OK.class) {
                if (ok == null) {
                    ok = new OK();
                }
            }
        }
        return ok;
    }

    /**
     * GET方法
     */
    public void doGet(String url, Callback callback) {
        //组装请求
        Request request = new Request.Builder()
                .url(url)
                .build();

        //初始化调用类
        Call call = client.newCall(request);

        //加入请求队列，回调结果
        call.enqueue(callback);

    }

    /**
     * POST方法
     */
    public void doPost(String url, Map<String, String> params, OkCallback callback) {

        //处理POST参数
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : params.keySet()) {
            builder.add(key, params.get(key));
        }

        //组装请求
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        //初始化调用类
        Call call = client.newCall(request);
        //加入请求队列，回调结果
        call.enqueue(callback);

    }

}
