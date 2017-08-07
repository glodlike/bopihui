package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;

import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class GoodInfoMDRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> data;
    private List<String> tel;
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

    public GoodInfoMDRecyclerAdapter(Context context, List<String> entitys,List<String> tel, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = entitys;
        this.tel =tel;
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
        contentView = mLayoutInflater.inflate(R.layout.goodinfo_md_recycler_item, parent, false);
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
        itemViewHolder.addressTextView.setText(data.get(position));
        itemViewHolder.telTextView.setText(tel.get(position));

        itemViewHolder.phoneImg.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"+tel.get(position)+""));
                context.startActivity(intent);
            }
        });
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
        public TextView addressTextView;
        public TextView telTextView;
        public  ImageView phoneImg;
        public ItemViewHolder(View contentView) {
            super(contentView);
            contentView.setBackgroundResource(R.color.clearColor);
            addressTextView = (TextView) contentView.findViewById(R.id.name);
            telTextView     = (TextView) contentView.findViewById(R.id.phone);
            phoneImg         = (ImageView) contentView.findViewById(R.id.phoneImg);
        }
    }
}
