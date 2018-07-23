package com.liqy.jdapp.cart.model;

import com.liqy.jdapp.network.OK;
import com.liqy.jdapp.network.OkCallback;

import java.util.Map;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public class CartTask implements ICartTask{

    @Override
    public void getCarts(String url, Map<String, String> params, OkCallback callback) {
        OK.getOk().doPost(url,params,callback);
    }

    @Override
    public void deleteCart(String url, Map<String, String> params, OkCallback callback) {
        OK.getOk().doPost(url,params,callback);
    }
}
