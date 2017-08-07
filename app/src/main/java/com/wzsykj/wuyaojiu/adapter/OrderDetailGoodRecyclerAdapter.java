package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.Bean.OrderDetailBean;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class OrderDetailGoodRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<OrderDetailBean.DealOrderItemBean> data;
    private OnRecyclerViewItemClickListener listener;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<OrderDetailBean.DealOrderItemBean> getData() {
        return data;
    }

    public void setData(List<OrderDetailBean.DealOrderItemBean> data) {
        this.data = data;
    }
    public OrderDetailGoodRecyclerAdapter(Context context, List<OrderDetailBean.DealOrderItemBean> data, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = data;
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
        contentView = mLayoutInflater.inflate(R.layout.orderlist_good_recycler_item, parent, false);
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
        ImageLoaderUtils.LoadImage(data.get(position).getDeal_icon(), itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
        itemViewHolder.title.setText(data.get(position).getSub_name());
        itemViewHolder.number.setText("X"+data.get(position).getNumber());
        itemViewHolder.info.setText(data.get(position).getAttr_str());
        itemViewHolder.price.setText("￥"+data.get(position).getUnit_price());
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
        public ImageView img;
        public TextView title;
        public TextView info;
        public TextView price;
        public TextView number;

        public ItemViewHolder(View contentView) {
            super(contentView);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            info = (TextView) contentView.findViewById(R.id.info);
            price = (TextView) contentView.findViewById(R.id.price);
            number = (TextView) contentView.findViewById(R.id.number);
        }
    }
}
