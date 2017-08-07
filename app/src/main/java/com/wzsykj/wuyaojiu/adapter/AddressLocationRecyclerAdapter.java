package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.wzsykj.wuyaojiu.R;

import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class AddressLocationRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<PoiInfo> data;
    private OnRecyclerViewItemClickListener listener;
    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }
    public List<PoiInfo> getData() {
        return data;
    }
    public void setData(List<PoiInfo> data) {
        this.data = data;
    }
    public AddressLocationRecyclerAdapter(Context context, List<PoiInfo> entitys, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = entitys;
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
        contentView = mLayoutInflater.inflate(R.layout.address_location_recycler_item, parent, false);
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

        PoiInfo itemPoiInfo = data.get(position);
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        String tag = "";
        if (position == 0) {
            tag = "[当前]";
            itemViewHolder.name.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            tag = "";
            itemViewHolder.name.setTextColor(context.getResources().getColor(R.color.colorFour));
        }
        itemViewHolder.name.setText(tag + itemPoiInfo.name);
        itemViewHolder.address.setText(itemPoiInfo.address);

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
        public TextView name, address;

        public ItemViewHolder(View contentView) {
            super(contentView);
            contentView.setBackgroundResource(R.color.clearColor);
            name = (TextView) contentView.findViewById(R.id.name);
            address = (TextView) contentView.findViewById(R.id.address);
        }
    }


}
