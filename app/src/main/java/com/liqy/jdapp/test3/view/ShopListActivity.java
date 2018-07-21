package com.liqy.jdapp.test3.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.view.View;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.liqy.jdapp.MainActivity;
import com.liqy.jdapp.R;
import com.liqy.jdapp.network.OK;
import com.liqy.jdapp.network.OkCallback;
import com.liqy.jdapp.test3.model.BannerData;
import com.liqy.jdapp.test3.model.RootData;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

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
        getBannerData();
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

    public void getBannerData(){
        OK.getOk().doGet("http://www.wanandroid.com/banner/json", new OkCallback() {
            @Override
            public void onUI(String json) {

                Gson gson=new Gson();

                BannerData data=gson.fromJson(json,BannerData.class);


                //添加轮播图
                View view=View.inflate(ShopListActivity.this,R.layout.layout_banner,null);
                Banner banner = view.findViewById(R.id.banner);
                //设置图片加载器
                banner.setImageLoader(new GlideImageLoader());
                //设置图片集合
//                List<String> list=new ArrayList<>();
//                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532168907905&di=24390df9eda1d5b0aac3ac4c6764aae4&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F09fa513d269759eeef490028befb43166d22df3c.jpg");
//                list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1532168907904&di=c7b09878c025ffc0f84cb2f310907a7c&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F95eef01f3a292df58538bff2b0315c6035a87359.jpg");
                banner.setImages(data.data);
                //banner设置方法全部调用完毕时最后调用
                banner.start();

                xRecycler.addHeaderView(view);
            }

            @Override
            public void onFailed(String json) {

            }
        });
    }
}
