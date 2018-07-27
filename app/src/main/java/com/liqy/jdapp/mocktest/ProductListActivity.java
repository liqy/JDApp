package com.liqy.jdapp.mocktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.EditText;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqy.jdapp.R;
import com.liqy.jdapp.cart.model.bean.Product;
import com.liqy.jdapp.mocktest.present.IProductPresent;
import com.liqy.jdapp.mocktest.present.ProductPresent;
import com.liqy.jdapp.mocktest.view.IProductView;
import com.liqy.jdapp.mocktest.view.ProductAdapter;

import java.util.List;

public class ProductListActivity extends AppCompatActivity implements IProductView {


    //定义P层
    IProductPresent present;

    EditText edit_key;
    XRecyclerView xRecyclerView;

    ProductAdapter adapter;
    private int page = 0;

    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        initView();

        //获取传值
        key = getIntent().getStringExtra("key");
        edit_key.setText(key);

        //初始化P层
        setPresent(new ProductPresent(this));
        //调用P层接口
        present.getSearchData(key, page);
    }

    /**
     * 初始化界面
     */
    private void initView() {

        edit_key = (EditText) findViewById(R.id.edit_key);

        xRecyclerView = (XRecyclerView) findViewById(R.id.xRecyclerView);

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        xRecyclerView.setLayoutManager(layoutManager);

        //分割线
        xRecyclerView.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));


        //添加动画
        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        xRecyclerView.setItemAnimator(defaultItemAnimator);
        adapter = new ProductAdapter(this);
        xRecyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void ItemClickListener(Product product) {
                //TODO 跳转详情页面
                Intent intent = new Intent(ProductListActivity.this, InfoActivity.class);
                intent.putExtra("pid", product.pid);
                intent.putExtra("title", product.title);
                intent.putExtra("images", product.images);
                startActivity(intent);
            }
        });

        //处理上拉刷新，下拉加载
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0; //重置页数
                adapter.clearData();//清空数据

                //调用P层接口
                present.getSearchData(key, page);
            }

            @Override
            public void onLoadMore() {
                page++;//增加页数

                //调用P层接口
                present.getSearchData(key, page);
            }
        });

    }


    /**
     * 展示数据
     * @param list
     */
    @Override
    public void showData(List list) {
        adapter.addData(list);

        //TODO 完成，记得处理
        xRecyclerView.loadMoreComplete();
        xRecyclerView.refreshComplete();
    }

    @Override
    public void showError(String json) {

    }

    /**
     * 绑定P层
     * @param iProductPresent
     */
    @Override
    public void setPresent(IProductPresent iProductPresent) {
        present=iProductPresent;
    }
}
