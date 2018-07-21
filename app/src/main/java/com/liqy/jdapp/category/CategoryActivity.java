package com.liqy.jdapp.category;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.liqy.jdapp.R;
import com.liqy.jdapp.category.model.SetMeal;
import com.liqy.jdapp.category.present.IPresent;
import com.liqy.jdapp.category.present.MyPresent;
import com.liqy.jdapp.category.view.IView;
import com.liqy.jdapp.category.view.adapter.LeftAdapter;
import com.liqy.jdapp.category.view.adapter.RightAdapter;

import java.util.List;

public class CategoryActivity extends AppCompatActivity implements IView {

    //注入P层
    IPresent present;

    //左边
    RecyclerView leftRecycler;
    LeftAdapter leftAdapter;

    //右边
    RecyclerView rightRecycler;
    RightAdapter rightAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initView();

        //初始化
        present = new MyPresent(this);
        present.getData();
    }

    private void initView() {
        //左边
        leftRecycler = (RecyclerView) findViewById(R.id.left_recycler);
        final LinearLayoutManager leftManager = new LinearLayoutManager(this);
        leftRecycler.setLayoutManager(leftManager);
        leftRecycler.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));
        leftAdapter = new LeftAdapter();
        leftRecycler.setAdapter(leftAdapter);

        leftAdapter.setListener(new LeftAdapter.ItemClickListener() {
            @Override
            public void onItemClick(SetMeal meal) {
                Toast.makeText(CategoryActivity.this, meal.name, Toast.LENGTH_SHORT).show();
                rightAdapter.addData(meal.spus);
            }
        });


        //右边
        rightRecycler = (RecyclerView) findViewById(R.id.right_recycler);
        rightAdapter = new RightAdapter();

        final LinearLayoutManager rightManager = new LinearLayoutManager(this);
        rightRecycler.setLayoutManager(rightManager);
        rightRecycler.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));

        rightRecycler.setAdapter(rightAdapter);

    }

    /**
     * 销毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        present.onDestroy();//调用防止内存泄漏
    }

    @Override
    public void showData(List<SetMeal> meals) {
        //初始化数据
        leftAdapter.addData(meals);
        SetMeal meal = meals.get(0);
        meal.isClick = true;//设置默认点击
        rightAdapter.addData(meal.spus);
    }
}
