package com.liqy.jdapp.mocktest.present;

import com.liqy.jdapp.mocktest.BasePresent;

/**
 * @file FileName
 * P层 继承基类
 * Copyright 星期五 YourCompany.
 */
public interface IProductPresent extends BasePresent{
    /**
     * P层
     *
     *  此方法实现，实际是去调用M层中的实际网络请求
     * @param key 搜索的关键词
     * @param page 页数
     */
    void getSearchData(String key,int page);
}
