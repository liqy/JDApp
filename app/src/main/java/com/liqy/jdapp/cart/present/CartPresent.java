package com.liqy.jdapp.cart.present;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.liqy.jdapp.cart.CartActivity;
import com.liqy.jdapp.cart.model.CartTask;
import com.liqy.jdapp.cart.model.ICartTask;
import com.liqy.jdapp.cart.model.bean.CartData;
import com.liqy.jdapp.cart.view.ICartView;
import com.liqy.jdapp.network.OkCallback;
import com.liqy.jdapp.utils.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public class CartPresent implements ICartPresent {

    ICartTask cartTask;
    ICartView cartView;

    public CartPresent(CartActivity activity) {
        cartTask = new CartTask();
        cartView=activity;
    }

    @Override
    public void getCarts() {
        Map<String, String> map = new HashMap<>();
        map.put("uid","4243");
        map.put("token","94A2C256471982A75C170CAB844FE4FE");
        cartTask.getCarts(Constants.GET_CART, map, new OkCallback() {
            @Override
            public void onUI(String json) {
                Gson gson=new Gson();
                CartData cartData=gson.fromJson(json,CartData.class);
                if (TextUtils.equals(cartData.code,"0")){//此时购物车有数据
                    cartView.showCart(cartData.data);
                }else {
                    cartView.showError(cartData.msg);//非0,就是异常
                }
            }

            @Override
            public void onFailed(String json) {
                //TODO 当错时，展示信息
                cartView.showError(json);
            }
        });

    }

    @Override
    public void deleteCart() {
        Map<String, String> map = new HashMap<>();
        cartTask.deleteCart(Constants.DEL_CART, map, new OkCallback() {
            @Override
            public void onUI(String json) {

            }

            @Override
            public void onFailed(String json) {

            }
        });
    }

    @Override
    public void onDestroy() {
        //释放内存防止内存泄漏
    }
}
