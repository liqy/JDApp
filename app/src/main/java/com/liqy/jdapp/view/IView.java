package com.liqy.jdapp.view;

import com.liqy.jdapp.model.SetMeal;

import java.util.List;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public interface IView {
    /**
     * 展示数据
     * @param meals
     */
    void showData(List<SetMeal> meals);
}
