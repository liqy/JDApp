package com.liqy.jdapp.mocktest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liqy.jdapp.R;
import com.liqy.jdapp.cart.CartActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.Arrays;
import java.util.List;

public class InfoActivity extends AppCompatActivity {

    Button button;
    TextView txt_p_name;

    Banner banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        button=(Button)findViewById(R.id.btn_add_cart);
        txt_p_name=(TextView)findViewById(R.id.txt_p_name);
        banner=(Banner)findViewById(R.id.banner);


        String title=getIntent().getStringExtra("title");
        txt_p_name.setText(title);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InfoActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        String images=getIntent().getStringExtra("images");
        List<String> stringB = Arrays.asList(images.split("\\|"));

//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new MyLoader());
        banner.setImages(stringB);
//        banner.setBannerAnimation(Transformer.Default);
//        banner.setBannerTitles(list_title);
        banner.setDelayTime(1000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.start();
    }


    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }
}
