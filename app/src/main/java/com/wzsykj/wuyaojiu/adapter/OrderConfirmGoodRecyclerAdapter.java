package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import org.json.JSONArray;
import org.json.JSONException;


/**
 * Created by chen on 16/6/16.
 */
public class OrderConfirmGoodRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private JSONArray data;
    private OnRecyclerViewItemClickListener listener;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public JSONArray getData() {
        return data;
    }

    public void setData(JSONArray data) {
        this.data = data;
    }

    public OrderConfirmGoodRecyclerAdapter(Context context, JSONArray data, OnRecyclerViewItemClickListener listener) {
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
        try {
            ImageLoaderUtils.LoadImage(data.getJSONObject(position).getString("icon"), itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
            itemViewHolder.title.setText(data.getJSONObject(position).getString("name"));
            itemViewHolder.number.setText("X " +data.getJSONObject(position).getString("number"));
            if (data.getJSONObject(position).getString("attr_str").equals("false")){
                itemViewHolder.info.setVisibility(View.INVISIBLE);
            }else {
                itemViewHolder.info.setText(data.getJSONObject(position).getString("attr_str"));
            }
            itemViewHolder.price.setText("￥"+data.getJSONObject(position).getString("total_price"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return data.length();
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
