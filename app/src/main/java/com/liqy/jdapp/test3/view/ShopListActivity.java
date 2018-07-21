package com.liqy.jdapp.test3.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqy.jdapp.R;
import com.liqy.jdapp.network.OK;
import com.liqy.jdapp.network.OkCallback;
import com.liqy.jdapp.test3.model.RootData;

public class ShopListActivity extends AppCompatActivity {

    XRecyclerView xRecycler;
    int page =0;

    ShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoplist);
        initView();
        getData(page);
    }

    private void initView() {
        xRecycler=(XRecyclerView)findViewById(R.id.xRecycler);

        xRecycler.setLoadingMoreEnabled(true);//加载更多
        xRecycler.setPullRefreshEnabled(true);//下拉刷新
        //监听
        xRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=0;
                getData(page);
            }

            @Override
            public void onLoadMore() {
                page++;
                getData(page);
            }
        });

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecycler.setLayoutManager(layoutManager);

        //分割线
        xRecycler.addItemDecoration(new DividerItemDecoration(this, OrientationHelper.VERTICAL));

        //适配器
        adapter=new ShopAdapter(this);
        xRecycler.setAdapter(adapter);
    }

    private void getData(int page) {
        String url = "http://39.108.3.12:3000/v1/restaurants?offset="+page+"&limit=10&lng=116.29845&lat=39.95933";
        OK.getOk().doGet(url, new OkCallback() {
            @Override
            public void onUI(String json) {
                Gson gson=new Gson();
                RootData data=gson.fromJson(json,RootData.class);
                adapter.addData(data.data);
                //加载完成
                xRecycler.refreshComplete();
                xRecycler.loadMoreComplete();
            }

            @Override
            public void onFailed(String json) {
                //加载完成
                xRecycler.refreshComplete();
                xRecycler.loadMoreComplete();
            }
        });
    }
}
