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

    private List<Seller> sellers;
    private LayoutInflater inflater;
    private Context context;

    private TextView txt_total_price;//合计总价
    private ImageView img_check_all;//全选状态UI更新

    //全选标志
    private boolean isCheckAll = false;//全选状态

    public CartAdapter(Context context) {
        this.sellers = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setTotalPrice(TextView txt_total_price) {
        this.txt_total_price = txt_total_price;
    }

    public void setCheckAll(ImageView img_check_all) {
        this.img_check_all = img_check_all;
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
     * @param
     */
    public void checkAll() {

        isCheckAll = !isCheckAll;//取反

        //遍历店铺
        for (Seller seller : sellers) {
            seller.isCheck = isCheckAll;

            //遍历产品
            List<Product> products = seller.list;
            for (Product product : products) {
                if (isCheckAll) {
                    product.selected = 1;
                } else {
                    product.selected = 0;
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

    /**
     * 计算所选产品价格
     */
    public void sumPrice() {
        int total = 0;//计算总价
        int uncheck = 0;

        int psum = 0;//购物车中商品数量

        for (Seller seller : sellers) {
            psum += seller.list.size();
            if (seller.isCheck) {//选中的店铺
                for (Product product : seller.list) {
                    if (product.selected == 1) {//选中的产品的价格计算
                        total = total + product.price * product.num;
                    } else {
                        uncheck += 1;
                    }
                }
            } else {
                uncheck += seller.list.size();//未选中的加1
            }
        }

        if (uncheck < psum) {//判断是否全选
            isCheckAll = false;
        } else {
            isCheckAll = true;
        }

        if (isCheckAll) {//全选的UI更新
            img_check_all.setImageResource(R.drawable.ic_checked);
        } else {
            img_check_all.setImageResource(R.drawable.ic_uncheck);
        }

        txt_total_price.setText("合计￥" + total);
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
        return true;
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

        final Seller seller = getGroup(i);
        holder.sellerName.setText(seller.sellerName);

        // 选择
        if (seller.isCheck) {
            holder.sellerCheck.setImageResource(R.drawable.ic_checked);
        } else {
            holder.sellerCheck.setImageResource(R.drawable.ic_uncheck);
        }

        holder.sellerCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Product> products = seller.list;

                if (seller.isCheck == true) {//修改店铺的选中状态
                    seller.isCheck = false;
                } else {
                    seller.isCheck = true;
                }

                for (Product product : products) {//修改产品的选中状态
                    if (seller.isCheck) {
                        product.selected = 1;
                    } else {
                        product.selected = 0;
                    }
                }

                //计算价格
                sumPrice();

                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public View getChildView(final int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        ProductViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.item_cart_product, viewGroup, false);
            holder = new ProductViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ProductViewHolder) view.getTag();
        }

        final Product product = getChild(i, i1);

        holder.productName.setText(product.title);
        holder.productPrice.setText("￥" + product.price);

        //选中状态UI更新
        if (product.selected == 1) {
            holder.productCheck.setImageResource(R.drawable.ic_checked);
        } else {
            holder.productCheck.setImageResource(R.drawable.ic_uncheck);
        }

        //设置选中监听
        holder.productCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickProductCheck(product, i);
            }
        });

        holder.addSumView.setSumClickListener(new AddSumView.SumClickListener() {
            @Override
            public void sumClick(int psum) {
                product.num = psum;
                sumPrice();
            }
        });


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 点击产品选中按钮
     *
     * @param product 产品
     * @param spos    商家位置
     */
    private void clickProductCheck(Product product, int spos) {
        //设置选中状态
        if (product.selected == 1) {
            product.selected = 0;
        } else {
            product.selected = 1;
        }

        //获取当前商品的店铺信息
        Seller seller = getGroup(spos);

        int sum = 0;//统计产品选中数量

        for (Product p : seller.list) {
            if (p.selected == 1) {//判断当前店铺有无选中产品
                sum = +1;//统计数量
            }
        }

        if (sum == 0) {//无产品选中
            seller.isCheck = false;
        } else {//有产品选中
            seller.isCheck = true;
        }

        //计算价格
        sumPrice();

        notifyDataSetChanged();
    }

    /**
     * 商家
     */
    static class SellerViewHolder {
        TextView sellerName;//商家名称
        ImageView sellerCheck;//选中按钮

        public SellerViewHolder(View view) {
            sellerName = (TextView) view.findViewById(R.id.txt_seller_name);
            sellerCheck = (ImageView) view.findViewById(R.id.img_select);
        }
    }

    /**
     * 产品
     */
    static class ProductViewHolder {
        TextView productName;//产品名称
        TextView productPrice;//产品价格
        ImageView productCheck;//选中按钮

        AddSumView addSumView;

        public ProductViewHolder(View view) {
            productName = (TextView) view.findViewById(R.id.txt_product_name);
            productPrice = (TextView) view.findViewById(R.id.txt_product_price);
            productCheck = (ImageView) view.findViewById(R.id.img_select);
            addSumView = (AddSumView) view.findViewById(R.id.add_sum);
        }

    }
}
