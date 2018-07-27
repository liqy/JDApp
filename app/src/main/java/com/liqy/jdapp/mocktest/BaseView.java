package com.liqy.jdapp.mocktest;

/**
 * @file FileName
 * V 层基类  固定模式，注意 泛型
 * Copyright 星期五 YourCompany.
 */
public interface BaseView<T> {//TODO
    /**
     * 设置M层
     * @param t
     */
    void setPresent(T t);
}
