package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class GoodInfoPJRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> data;
    private        List<String> headUrl ; // 頭像
    private    List<String> phone;        // 電話
    private   List<String> pingjia ;      //評論
    private OnRecyclerViewItemClickListener listener;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
    public GoodInfoPJRecyclerAdapter(Context context, List<String> entitys,List<String> headUrl,List<String> phone,List<String> pingjia, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = entitys;
        this.headUrl =headUrl;
        this.phone   = phone;
        this.pingjia  = pingjia;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }
    /**
     * 决定元素的布局使用哪种类型
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    /**
     * 渲染具体的ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = null;
        contentView = mLayoutInflater.inflate(R.layout.goodinfo_pj_recycler_item, parent, false);
        return new ItemViewHolder(contentView);
    }

    /**
     * 绑定ViewHolder的数据。
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });

        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.userTV.setText(phone.get(position));
        itemViewHolder.textView.setText(pingjia.get(position));
        itemViewHolder.ratingBar.setRating(Float.parseFloat(data.get(position)));
        ImageLoaderUtils.LoadImage(headUrl.get(position), itemViewHolder.img, ImageLoaderUtils.getOptionsRounde(context, itemViewHolder.img.getLayoutParams().width / 2));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public  ImageView img;
        public  RatingBar ratingBar;
        public  TextView textView;
        private TextView userTV;

        public ItemViewHolder(View contentView) {
            super(contentView);
            contentView.setBackgroundResource(R.color.clearColor);
            img = (ImageView) contentView.findViewById(R.id.img);
            ratingBar = (RatingBar) contentView.findViewById(R.id.ratingBar);
            textView  = (TextView) contentView.findViewById(R.id.context);
            userTV     = (TextView) contentView.findViewById(R.id.name);
        }
    }


}
