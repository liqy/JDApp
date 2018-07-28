package com.liqy.jdapp.cart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.liqy.jdapp.R;
import com.liqy.jdapp.cart.model.bean.Seller;
import com.liqy.jdapp.cart.present.CartPresent;
import com.liqy.jdapp.cart.view.CartAdapter;
import com.liqy.jdapp.cart.view.ICartView;

import java.util.List;

public class CartActivity extends AppCompatActivity implements ICartView {

    CartAdapter cartAdapter;
    ExpandableListView listView;
    CartPresent present;
    LinearLayout btn_check_all;
    ImageView img_check_all;
    TextView txt_total_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();

        present = new CartPresent(this);
        present.getCarts();
    }

    private void initView() {
        listView = (ExpandableListView) findViewById(R.id.cartList);

        //TODO 设置二级列表，不能关闭
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;//返回true,表示不可点击
            }
        });

        //TODO 去掉默认箭头
        listView.setGroupIndicator(null);

        cartAdapter = new CartAdapter(this);
        listView.setAdapter(cartAdapter);

        img_check_all = (ImageView) findViewById(R.id.img_check_all);
        btn_check_all = (LinearLayout) findViewById(R.id.btn_check_all);

        btn_check_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO 全选 反选
                if (cartAdapter.isCheckAll()){
                    cartAdapter.invertAll();//反选
                }else {
                    cartAdapter.checkAll();//全选
                }
            }
        });

        txt_total_price=(TextView)findViewById(R.id.txt_total_price);

        cartAdapter.setTotalPrice(txt_total_price);
        cartAdapter.setCheckAll(img_check_all);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showCart(List<Seller> sellers) {
        cartAdapter.addData(sellers);//添加数据
        cartAdapter.expandGroup(listView);//展开列表
        cartAdapter.checkAll();//选中计算价格

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
