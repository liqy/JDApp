package com.liqy.jdapp.cart.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
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

    //全选标志
    private boolean isCheckAll = false;

    public CartAdapter(Context context) {
        this.sellers = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
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
     *
     * @param listView
     */
    public void expandGroup(ExpandableListView listView) {
        for (int i = 0; i < getGroupCount(); i++) {
            listView.expandGroup(i);
        }
    }

    /**
     * 默认执行一次全选，在第一次加载完数据时
     *
     * @param img_check_all
     */
    public void checkAll(ImageView img_check_all) {

        isCheckAll = !isCheckAll;//取反

        //遍历店铺
        for (Seller seller : sellers) {
            seller.isCheck = isCheckAll;

            //遍历产品
            List<Product> products=seller.list;
            for (Product product: products) {
               if (isCheckAll){
                   product.selected=1;
               }else {
                   product.selected=0;
               }
            }
        }

        //全选按钮的处理
        if (isCheckAll) {
            img_check_all.setImageResource(R.drawable.ic_checked);
        } else {
            img_check_all.setImageResource(R.drawable.ic_uncheck);
        }
        notifyDataSetChanged();
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

        if (view == null) {
            view = inflater.inflate(R.layout.item_cart_seller, viewGroup, false);
            holder = new SellerViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (SellerViewHolder) view.getTag();
        }

        Seller seller = getGroup(i);
        holder.sellerName.setText(seller.sellerName);

        // 择
        if (seller.isCheck) {
            holder.sellerCheck.setImageResource(R.drawable.ic_checked);
        } else {
            holder.sellerCheck.setImageResource(R.drawable.ic_uncheck);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ProductViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.item_cart_product, viewGroup, false);
            holder = new ProductViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ProductViewHolder) view.getTag();
        }

        Product product = getChild(i, i1);
        holder.productName.setText(product.title);

        if (product.selected == 1) {//选中状态
            holder.productCheck.setImageResource(R.drawable.ic_checked);
        } else {
            holder.productCheck.setImageResource(R.drawable.ic_uncheck);
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 商家
     */
    static class SellerViewHolder {
        TextView sellerName;
        ImageView sellerCheck;

        public SellerViewHolder(View view) {
            sellerName = (TextView) view.findViewById(R.id.txt_seller_name);
            sellerCheck = (ImageView) view.findViewById(R.id.img_select);
        }
    }

    /**
     * 产品
     */
    static class ProductViewHolder {
        TextView productName;
        ImageView productCheck;


        public ProductViewHolder(View view) {
            productName = (TextView) view.findViewById(R.id.txt_product_name);
            productCheck = (ImageView) view.findViewById(R.id.img_select);

        }

    }
}
