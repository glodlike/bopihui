package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.DensityUtil;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class MainRecyclerTimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> data;
    private OnRecyclerViewItemClickListener listener;
    private ArrayList<String> imageList;
    private ArrayList<String> priceList;
    private ArrayList<String> buy_count;
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

    public MainRecyclerTimeAdapter(Context context, List<String> entitys, ArrayList<String> imageList ,ArrayList<String> priceList,ArrayList<String> buy_count, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = entitys;
        this.imageList = imageList;
        this.priceList = priceList;
        this.buy_count = buy_count;
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
        contentView = mLayoutInflater.inflate(R.layout.main_recycler_time_item_item, parent, false);
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
        ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((DensityUtil.getWindowsWidth(context) / 3)-20, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemViewHolder.linearLayout.setLayoutParams(params);
        itemViewHolder.title.setText(data.get(position));
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams((DensityUtil.getWindowsWidth(context) / 3) - DensityUtil.dp2px(context, 30), (DensityUtil.getWindowsWidth(context) / 3) - DensityUtil.dp2px(context, 30));
        itemViewHolder.img.setLayoutParams(params1);
        ImageLoaderUtils.LoadImage(imageList.get(position), itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
        itemViewHolder.price.setText("￥"+priceList.get(position));
        itemViewHolder.buy_count.setText(buy_count.get(position)+" 人购买");
    }
    @Override
    public int getItemCount() {
        return data.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * 导航视图
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public ImageView img;
        public TextView title;
        public TextView price;
        public TextView buy_count;
        public ItemViewHolder(View contentView) {
            super(contentView);
            linearLayout = (LinearLayout) contentView.findViewById(R.id.layout);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            price = (TextView) contentView.findViewById(R.id.price);
            buy_count = (TextView) contentView.findViewById(R.id.buy_count);
        }
    }


}
