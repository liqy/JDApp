package com.liqy.jdapp.cart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.liqy.jdapp.R;
import com.liqy.jdapp.cart.model.bean.Seller;
import com.liqy.jdapp.cart.present.CartPresent;
import com.liqy.jdapp.cart.view.CartAdapter;
import com.liqy.jdapp.cart.view.ICartView;

import java.util.List;

public class CartActivity extends AppCompatActivity implements ICartView{

    CartAdapter cartAdapter;
    ExpandableListView listView;

    CartPresent present;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();

        present=new CartPresent(this);
        present.getCarts();
    }

    private void initView() {
        listView=(ExpandableListView)findViewById(R.id.cartList);
        cartAdapter=new CartAdapter(this);
        listView.setAdapter(cartAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showCart(List<Seller> sellers) {
            cartAdapter.addData(sellers);
            cartAdapter.expandGroup(listView);

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }
}
