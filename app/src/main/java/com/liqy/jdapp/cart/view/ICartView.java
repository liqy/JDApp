package com.liqy.jdapp.cart.view;

import com.liqy.jdapp.cart.model.bean.Seller;

import java.util.List;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public interface ICartView {
    void showCart(List<Seller> sellers);
    void showError(String error);
}
