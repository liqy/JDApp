package com.liqy.jdapp.mocktest;

/**
 * @file FileName
 * 抽取P层基类，固定模式
 * Copyright 星期五 YourCompany.
 */
public interface BasePresent {
    /**
     * 防止内存泄漏回调，只写这一个方法
     */
    public  void onDestroy();
}
