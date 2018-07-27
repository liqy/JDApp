package com.liqy.jdapp.mocktest.view;

import com.liqy.jdapp.cart.model.bean.Product;
import com.liqy.jdapp.mocktest.BaseView;
import com.liqy.jdapp.mocktest.present.IProductPresent;

import java.util.List;

/**
 * @file FileName
 * V 层 继承基类  设置泛型 ，也就是和那个P层有关，就填入那个P的接口
 * Copyright 星期五 YourCompany.
 */
public interface IProductView extends BaseView<IProductPresent> {//TODO

    /**
     * z展示数据
     * @param products
     */
    void showData(List<Product> products);

    /**
     * 错误提示
     * @param json
     */
    void showError(String json);
}
