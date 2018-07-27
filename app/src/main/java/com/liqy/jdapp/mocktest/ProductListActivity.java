package com.liqy.jdapp.mocktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqy.jdapp.R;
import com.liqy.jdapp.cart.model.bean.Product;
import com.liqy.jdapp.mocktest.model.ProductData;
import com.liqy.jdapp.mocktest.view.ProductAdapter;
import com.liqy.jdapp.network.OK;
import com.liqy.jdapp.network.OkCallback;

import java.net.URLEncoder;

public class ProductListActivity extends AppCompatActivity {


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
        key = getIntent().getStringExtra("key");
        edit_key.setText(key);

        getData(key, page);

    }

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
                intent.putExtra("images",product.images);
                startActivity(intent);
            }
        });

        //处理上拉刷新，下拉加载
        xRecyclerView.setPullRefreshEnabled(true);
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 0;
                adapter.clearData();
                getData(key, page);
            }

            @Override
            public void onLoadMore() {
                page++;
                getData(key, page);
            }
        });

    }

    public void getData(String keywords, int page) {
        String key = URLEncoder.encode(keywords);
        String url = "http://www.zhaoapi.cn/product/searchProducts?keywords=" + key + "&page=" + page + "&sort=0";
        OK.getOk().doGet(url, new OkCallback() {
            @Override
            public void onUI(String json) {
                Gson gson = new Gson();
                ProductData data = gson.fromJson(json, ProductData.class);
                adapter.addData(data.data);

                xRecyclerView.loadMoreComplete();
                xRecyclerView.refreshComplete();
            }

            @Override
            public void onFailed(String json) {

            }
        });
    }
}
