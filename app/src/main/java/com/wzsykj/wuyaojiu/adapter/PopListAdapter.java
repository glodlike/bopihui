package com.wzsykj.wuyaojiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import zhy.view.flowlayout.FlowLayout;
import zhy.view.flowlayout.TagAdapter;
import zhy.view.flowlayout.TagFlowLayout;

/**
 * Created by Administrator on 2016/11/29 0029.
 */

public class PopListAdapter extends BaseAdapter {
    private String data = "";
    private ArrayList<String> typeList;
    private Map<String,ArrayList<String>> itemList;
    private Context context;
    private ListView listView;

    public PopListAdapter(ArrayList<String> typeList, Map<String, ArrayList<String>> itemList, Context context) {
        this.typeList = typeList;
        this.itemList = itemList;
        this.context = context;
    }

    public PopListAdapter(ArrayList<String> typeList, Map<String, ArrayList<String>> itemList, Context context, ListView listView) {
        this.typeList = typeList;
        this.itemList = itemList;
        this.context = context;
        this.listView = listView;
    }

    @Override
    public int getCount() {
        return typeList.size();
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
            convertView = inflater.inflate(R.layout.shaixuan_pop_item_layout, null);
            horder.title = (TextView) convertView.findViewById(R.id.title);
            horder.tagFlowLayout = (TagFlowLayout) convertView.findViewById(R.id.flowlayout);
            convertView.setTag(horder);
        }
        horder = (viewHorder) convertView.getTag();
        horder.title.setText(typeList.get(i));
        final viewHorder finalHorder = horder;
        horder.tagFlowLayout.setAdapter(new TagAdapter<String>(itemList.get(String.valueOf(i))) {
            @Override
            public View getView(zhy.view.flowlayout.FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.select_flowlayout_tag_item_shaixuan,
                       finalHorder.tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        horder.tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (i==0){
                    new  SharePerfenceUtils(context).keepToken(itemList.get(String.valueOf(0)).get(position));
                }else if (i==1){
                    new  SharePerfenceUtils(context).keepToken1(itemList.get(String.valueOf(1)).get(position));
                }else if (i==2){
                    new  SharePerfenceUtils(context).keepToken2(itemList.get(String.valueOf(2)).get(position));
                }else if (i==3){
                    new  SharePerfenceUtils(context).keepToken3(itemList.get(String.valueOf(3)).get(position));
                }else if (i==4){
                    new  SharePerfenceUtils(context).keepToken4(itemList.get(String.valueOf(4)).get(position));
                }else if (i==5){
                    new  SharePerfenceUtils(context).keepToken5(itemList.get(String.valueOf(5)).get(position));
                }else if (i==6){
                    new  SharePerfenceUtils(context).keepToken6(itemList.get(String.valueOf(6)).get(position));
                }
                return false;
                }
          });
        horder.tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

               }
           });
        return convertView;
    }
    static class viewHorder {
        public TextView title;
        private TagFlowLayout tagFlowLayout;
    }
}
