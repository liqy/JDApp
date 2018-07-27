package com.liqy.jdapp.mocktest.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liqy.jdapp.R;
import com.liqy.jdapp.cart.model.bean.Product;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

/**
 * @file FileName
 * JDApp
 *  * liqy
 * Copyright 星期五 YourCompany.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private List<Product> list;
    private LayoutInflater inflater;

    private OnItemClickListener onItemClickListener;

    private DisplayImageOptions options;

    ImageLoader imageLoader;

    public ProductAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
         options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageForEmptyUri(R.mipmap.ic_launcher_round) // resource or drawable
                .showImageOnFail(R.mipmap.ic_launcher_round) // resource or drawable
                 .cacheInMemory(true) // default
                 .cacheOnDisk(true) // default
                 .displayer(new CircleBitmapDisplayer())
                 .build();
         imageLoader=ImageLoader.getInstance();
    }


    public void addData(List<Product> products) {
        this.list.addAll(products);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void clearData() {
        this.list.clear();
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_serach_product, viewGroup, false);

        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        final Product product = list.get(i);
        productViewHolder.txt_p_name.setText(product.title);

        String string=product.images.split("\\|")[0];

        imageLoader.displayImage(string,productViewHolder.p_imge,options);

        productViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.ItemClickListener(product);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView txt_p_name;
        ImageView p_imge;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_p_name = (TextView) itemView.findViewById(R.id.txt_p_name);
            p_imge=(ImageView)itemView.findViewById(R.id.p_imge);
        }
    }

    /**
     * 定义点击监听事件
     */
    public interface OnItemClickListener {
        void ItemClickListener(Product product);
    }
}
