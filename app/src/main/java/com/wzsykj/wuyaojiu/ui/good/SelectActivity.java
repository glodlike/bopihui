package com.wzsykj.wuyaojiu.ui.good;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;

import zhy.view.flowlayout.FlowLayout;
import zhy.view.flowlayout.TagAdapter;
import zhy.view.flowlayout.TagFlowLayout;


/**
 * Created by chen on 16/8/10.
 */
public class SelectActivity extends BaseActivity implements View.OnClickListener {
    public ImageView back,find;
    public TextView select;
    private TagFlowLayout myTagFlowLayout, otherTagFlowLayout;
    private EditText editText;
    private String[] myFind = {"茅台","商家发布","啤酒","葡萄酒","红酒"};
    private String[] ortherFind = {"百威","鸡尾酒","老酒","茅台","商家发布","啤酒","葡萄酒","红酒"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_activity);

        initView();
    }

    private void initView() {
        find = (ImageView) findViewById(R.id.find);

        editText  = (EditText) findViewById(R.id.keyword);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(this);

        select = (TextView) findViewById(R.id.select_btn);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,GoodListActivity.class);
                intent.putExtra("id",editText.getText().toString().trim());
                intent.putExtra("type","keyword");
                startActivity(intent);
            }
        });

        myTagFlowLayout = (TagFlowLayout) findViewById(R.id.my_tagflowlayout);
        otherTagFlowLayout = (TagFlowLayout) findViewById(R.id.other_tagflowlayout);
        loadData();

    }
    private void loadData() {


        showData();
    }

    private void showData() {
        myTagFlowLayout.setAdapter(new TagAdapter<String>(myFind) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView textView = (TextView) LayoutInflater.from(SelectActivity.this).inflate(R.layout.select_flowlayout_tag_item,
                        myTagFlowLayout, false);
                textView.setText(str);

                return textView;
            }
        });
        myTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent intent = new Intent(SelectActivity.this,GoodListActivity.class);
                intent.putExtra("id",myFind[position]);
                intent.putExtra("type","keyword");
                startActivity(intent);
                return false;
            }
        });

        otherTagFlowLayout.setAdapter(new TagAdapter<String>(ortherFind) {
            @Override
            public View getView(FlowLayout parent, int position, String str) {
                TextView textView = (TextView) LayoutInflater.from(SelectActivity.this).inflate(R.layout.select_flowlayout_tag_item,
                        myTagFlowLayout, false);
                textView.setText(str);

                return textView;
            }
        });
        otherTagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent intent = new Intent(SelectActivity.this,GoodListActivity.class);
                intent.putExtra("id",ortherFind[position]);
                intent.putExtra("type","keyword");
                startActivity(intent);
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.select:

                break;
            default:
                break;
        }
    }
}
