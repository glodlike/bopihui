package com.wzsykj.wuyaojiu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.Bean.Pingjia;
import com.wzsykj.wuyaojiu.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/14 0014.
 */

public class GoodSPJAdapter extends BaseAdapter {
    private Context context;
    private List<Pingjia.ItemBean> itemBeanList;

    public GoodSPJAdapter(Context context, List<Pingjia.ItemBean> itemBeanList) {
        this.context = context;
        this.itemBeanList = itemBeanList;
    }
    @Override
    public int getCount() {
        return itemBeanList.size();
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
            convertView = inflater.inflate(R.layout.goodinfo_all_pj_recycler_item, null);
            horder.name  = (TextView) convertView.findViewById(R.id.nameTV);
            horder.createTime = (TextView) convertView.findViewById(R.id.time);
            horder.point      = (RatingBar) convertView.findViewById(R.id.ratingBar);
            horder.content    = (TextView) convertView.findViewById(R.id.context);
            convertView.setTag(horder);
        }
        horder = (viewHorder) convertView.getTag();
        Pingjia.ItemBean  itemBean = itemBeanList.get(i);
        horder.content.setText(itemBean.getContent());
        horder.name.setText(itemBean.getUser_name());
        horder.point.setRating(Float.parseFloat(itemBean.getPoint()));
        horder.createTime.setText(itemBean.getCreate_time());
        return convertView;
    }
    static class viewHorder {
        public  TextView name;
        public  RatingBar point;
        public  TextView createTime;
        private TextView content;
    }
}
