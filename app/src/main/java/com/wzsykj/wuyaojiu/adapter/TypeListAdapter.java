package com.wzsykj.wuyaojiu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.ui.good.GoodListActivity;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.Map;

import zhy.view.flowlayout.FlowLayout;
import zhy.view.flowlayout.TagAdapter;
import zhy.view.flowlayout.TagFlowLayout;

/**
 * Created by Administrator on 2016/11/28 0028.
 */

public class TypeListAdapter extends BaseAdapter {
    private ArrayList<String> imgList;
    private ArrayList<String> nameList;
    private Map<String,ArrayList<String>> typeList;
    private Context context;
    private Map<String,ArrayList<String>> typeIdList;

    public TypeListAdapter(ArrayList<String> imgList, ArrayList<String> nameList, Map<String, ArrayList<String>> typeList,Map<String, ArrayList<String>> typeIdList, Context context) {
        this.imgList = imgList;
        this.nameList = nameList;
        this.typeList = typeList;
        //this.cateIdList = cateIdList;
        this.typeIdList   = typeIdList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return nameList.size();
    }
    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
       viewHorder horder;
        if (convertView == null) {
            horder = new viewHorder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.type_items_layout, null);
            horder.img = (ImageView)convertView.findViewById(R.id.img);
            horder.name = (TextView) convertView.findViewById(R.id.title);
            horder.type = (TagFlowLayout) convertView.findViewById(R.id.flowlayout);
            convertView.setTag(horder);
        }
        horder = (viewHorder) convertView.getTag();
        horder.name.setText(nameList.get(i));
        ImageLoaderUtils.LoadImage(imgList.get(i),horder.img, ImageLoaderUtils.getOptionsDefault());
        final viewHorder finalHorder = horder;
        horder.type.setAdapter(new TagAdapter<String>(typeList.get(String.valueOf(i))) {
            @Override
            public View getView(zhy.view.flowlayout.FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.type_flowlayout_tag_item,
                        finalHorder.type, false);
                tv.setText(s);
                return tv;
            }
        });
        horder.type.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Intent intent = new Intent(context,GoodListActivity.class);
                intent.putExtra("type","cate_id");
                intent.putExtra("id",typeIdList.get(String.valueOf(i)).get(position));
                context.startActivity(intent);
                return false;
            }
        });
        return convertView;
    }
    static class viewHorder {
        public ImageView img;
        public TextView name;
        public TagFlowLayout type;
    }
}
