package com.wzsykj.wuyaojiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/25 0025.
 */

public class GoodListAdapter extends BaseAdapter {
    private ArrayList<String> nameList;
    private ArrayList<String> priceList;
    private List<String> imageList;
    private Context context;

    public GoodListAdapter(ArrayList<String> nameList, ArrayList<String> priceList, List<String> imageList, Context context) {
        this.nameList = nameList;
        this.priceList = priceList;
        this.imageList = imageList;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        viewHorder horder;
        if (convertView == null) {
            horder = new viewHorder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.good_item_layout, null);
            horder.img = (ImageView)convertView.findViewById(R.id.img);
            horder.title = (TextView) convertView.findViewById(R.id.title);
            horder.price = (TextView) convertView.findViewById(R.id.price);
            convertView.setTag(horder);
        }
        horder = (viewHorder) convertView.getTag();
        horder.title.setText(nameList.get(i));
        horder.price.setText("￥"+priceList.get(i));
        ImageLoaderUtils.LoadImage(imageList.get(i),horder.img, ImageLoaderUtils.getOptionsDefault());
        return convertView;
    }

    static class viewHorder {
        public ImageView img;
        public TextView title;
        public TextView price;
        public ImageView car;
    }
}
