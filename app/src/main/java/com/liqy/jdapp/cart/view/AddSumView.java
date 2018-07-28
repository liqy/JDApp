package com.liqy.jdapp.cart.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liqy.jdapp.R;

/**
 * @file FileName
 * 增加减少自定义控件
 * Copyright 星期二 YourCompany.
 */
public class AddSumView extends LinearLayout {

    private Context context;

    private ImageView add;//增加产品数量按钮
    private ImageView reduce;//减少产品数量按钮
    private TextView sum;//展示产品数量

    private int totalSum = 1;//记录商品数量
    private int limitSum = 5;//最多添加几件商品

    //定义点击事件
    private SumClickListener listener;


    /**
     * 绑定点击事件
     *
     * @param listener
     */
    public void setSumClickListener(SumClickListener listener) {
        this.listener = listener;
    }

    public void setSum(int num) {
        totalSum = num;//设置初试数量
        this.sum.setText("" + num);
    }

    /**
     * 设置限购的数量
     *
     * @param limitSum
     */
    public void setLimitSum(int limitSum) {
        if (limitSum > 0) {
            this.limitSum = limitSum;
        } else {
            this.limitSum = 5;
        }
    }

    /**
     * 构造函数
     *
     * @param context
     * @param attrs
     */
    public AddSumView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //初始化UI
        initView(context);

    }

    /**
     * 舒适化视图
     *
     * @param context
     */
    private void initView(Context context) {
        View view = View.inflate(context, R.layout.layout_add_sum_view, this);
        add = (ImageView) view.findViewById(R.id.add);
        reduce = (ImageView) view.findViewById(R.id.reduce);
        sum = (TextView) view.findViewById(R.id.txt_sum);

        //添加点击事件
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addClick();
            }
        });

        //减少
        reduce.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reduceClick();
            }
        });
    }

    /**
     * 增加商品数量
     */
    public void addClick() {
        totalSum++;
        if (totalSum >= limitSum) {
            totalSum = limitSum;//TODO
            Toast.makeText(context, "每人限购" + limitSum + "件", Toast.LENGTH_SHORT).show();
        }

        //回调事件
        if (listener != null) {
            listener.sumClick(totalSum);
        }

        sum.setText(totalSum + "");
    }

    /**
     * 减少商品数量
     */
    public void reduceClick() {
        totalSum--;
        if (totalSum > 0) {
            sum.setText(totalSum + "");
        } else {
            totalSum = 1;//TODO
            Toast.makeText(context, "不能再减少了", Toast.LENGTH_SHORT).show();
            sum.setText(totalSum + "");
        }

        //回调事件
        if (listener != null) {
            listener.sumClick(totalSum);
        }
    }

    /**
     * 回调商品数量点击事件
     */
    interface SumClickListener {
        void sumClick(int psum);
    }


}


