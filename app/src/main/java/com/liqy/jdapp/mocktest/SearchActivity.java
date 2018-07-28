package com.liqy.jdapp.mocktest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liqy.jdapp.R;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    EditText edit_key;
    Button btn_search;
    TagFlowLayout last_tag;
    TagFlowLayout hot_tag;

    KeyAdapter lastAdapter;
    KeyAdapter hotAdapter;

    List<String> list;

    Button btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();

        list = new ArrayList<>();
        list.add("冰激淋");
        list.add("麻辣小龙虾");
        list.add("汉堡");
        list.add("煎饼");
        list.add("手机");
        list.add("电脑");

        lastAdapter = new KeyAdapter(list);
        hotAdapter = new KeyAdapter(list);

        last_tag.setAdapter(lastAdapter);
        hot_tag.setAdapter(hotAdapter);
    }

    private void initView() {
        edit_key = (EditText) findViewById(R.id.edit_key);
        btn_search = (Button) findViewById(R.id.btn_search);
        last_tag = (TagFlowLayout) findViewById(R.id.last_tag);
        hot_tag = (TagFlowLayout) findViewById(R.id.hot_tag);

        btn_clear=(Button)findViewById(R.id.btn_clear);

        //搜索按钮的点击事件
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = edit_key.getText().toString();
                if (!TextUtils.isEmpty(key)) {//判断一下
                    list.add(key);
                    lastAdapter.notifyDataChanged();

                    //TODO 跳转
                    Intent intent = new Intent(SearchActivity.this, ProductListActivity.class);
                    intent.putExtra("key", key);
                    startActivity(intent);
                }
            }
        });

        //点击事件，流式布局
        last_tag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String key = lastAdapter.getItem(position);//获取点击项目的值
                //TODO 跳转到产品列表页面
                Intent intent = new Intent(SearchActivity.this, ProductListActivity.class);
                intent.putExtra("key", key);
                startActivity(intent);
                return true;
            }
        });

        //清空历史记录
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               list.clear();
               lastAdapter.notifyDataChanged();
            }
        });
    }

    /**
     * 适配器
     */
    class KeyAdapter extends TagAdapter<String> {

        public KeyAdapter(List<String> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, String s) {
            TextView textView = new TextView(SearchActivity.this);
            textView.setText(s);
            return textView;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
