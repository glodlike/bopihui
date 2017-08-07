package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.Bean.Payment;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.List;
import java.util.Map;


/**
 * Created by chen on 16/6/16.
 */
public class OrderConfirmPayRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Payment.PaymentListBean> data;
    private OnRecyclerViewItemClickListener listener;
    private Map<Integer,Boolean> map;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<Payment.PaymentListBean> getData() {
        return data;
    }

    public void setData(List<Payment.PaymentListBean> data) {
        this.data = data;
    }

    public OrderConfirmPayRecyclerAdapter(Context context, List<Payment.PaymentListBean> data,Map<Integer,Boolean> map, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = data;
        this.map  = map;
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
        contentView = mLayoutInflater.inflate(R.layout.orderconfirm_pay_recycler_item, parent, false);
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

        ImageLoaderUtils.LoadImage(data.get(position).getLogo(), itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
        itemViewHolder.title.setText(data.get(position).getName());
        itemViewHolder.check.setChecked(map.get(position));
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
        public CheckBox check;

        public ItemViewHolder(View contentView) {
            super(contentView);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            check = (CheckBox) contentView.findViewById(R.id.check);
        }
    }
}
