package com.liqy.jdapp.cart.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.liqy.jdapp.R;
import com.liqy.jdapp.cart.model.bean.Product;
import com.liqy.jdapp.cart.model.bean.Seller;

import java.util.ArrayList;
import java.util.List;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期一 YourCompany.
 */
public class CartAdapter extends BaseExpandableListAdapter {

    List<Seller> sellers;
    private Context context;
    LayoutInflater inflater;

    public CartAdapter(Context context) {
        this.sellers = new ArrayList<>();
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    /**
     * 添加数据
     *
     * @param list
     */
    public void addData(List<Seller> list) {
        sellers.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 默认展开
     * @param listView
     */
    public void expandGroup(ExpandableListView listView){
        for (int i = 0; i < getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }

    @Override
    public int getGroupCount() {
        return sellers.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return sellers.get(i).list.size();
    }

    @Override
    public Seller getGroup(int i) {
        return sellers.get(i);
    }

    @Override
    public Product getChild(int i, int i1) {
        return sellers.get(i).list.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        Seller seller = getGroup(i);
        return seller.sellerid;
    }

    @Override
    public long getChildId(int i, int i1) {
        Product product = getChild(i, i1);
        return product.pid;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        SellerViewHolder holder;

        if (view==null){
            view=inflater.inflate(R.layout.item_cart_seller,viewGroup,false);
            holder=new SellerViewHolder(view);
            view.setTag(holder);
        }else {
            holder=(SellerViewHolder)view.getTag();
        }

        Seller seller=getGroup(i);
        holder.sellerName.setText(seller.sellerName);

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ProductViewHolder holder;

        if (view==null){
            view=inflater.inflate(R.layout.item_cart_product,viewGroup,false);
            holder=new ProductViewHolder(view);
            view.setTag(holder);
        }else {
            holder=(ProductViewHolder)view.getTag();
        }

        Product product=getChild(i,i1);
        holder.productName.setText(product.title);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 商家
     */
    static class SellerViewHolder{
        TextView sellerName;

        public SellerViewHolder(View view) {
            sellerName=(TextView)view.findViewById(R.id.txt_seller_name);
        }
    }

    /**
     * 产品
     */
    static class ProductViewHolder{
        TextView productName;
        public ProductViewHolder(View view) {
            productName=(TextView)view.findViewById(R.id.txt_product_name);
        }

    }
}
