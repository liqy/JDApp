package com.liqy.jdapp.cart.model;

import com.liqy.jdapp.network.OkCallback;

import java.util.Map;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public interface ICartTask {
    void getCarts(String url, Map<String,String> params, OkCallback callback);
    void deleteCart(String url, Map<String,String> params, OkCallback callback);
}
