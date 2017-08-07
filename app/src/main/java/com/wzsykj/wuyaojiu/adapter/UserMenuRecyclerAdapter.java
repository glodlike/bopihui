package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.entity.UserMenuEntity;

import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class UserMenuRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<UserMenuEntity> data;
    private OnRecyclerViewItemClickListener listener;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<UserMenuEntity> getData() {
        return data;
    }

    public void setData(List<UserMenuEntity> data) {
        this.data = data;
    }

    public UserMenuRecyclerAdapter(Context context, List<UserMenuEntity> data, OnRecyclerViewItemClickListener listener) {
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
        if (data.get(position).getTiitle().length() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 渲染具体的ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = null;
        switch (viewType) {
            case 0:
                contentView = mLayoutInflater.inflate(R.layout.line_h_10dp_view, parent, false);
                return new LineItemViewHolder(contentView);
            case 1:
                contentView = mLayoutInflater.inflate(R.layout.user_menu_item, parent, false);
                return new ItemViewHolder(contentView);
            default:
                return null;

        }

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
        if (holder instanceof LineItemViewHolder) {
            LineItemViewHolder lineItemViewHolder = (LineItemViewHolder) holder;

        } else if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.title.setText(data.get(position).getTiitle());
            itemViewHolder.img.setImageResource(data.get(position).getIcon());
            itemViewHolder.info.setText(data.get(position).getInfo());
        }
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
     * 空行视图
     */
    public class LineItemViewHolder extends RecyclerView.ViewHolder {

        public LineItemViewHolder(View contentView) {
            super(contentView);
        }
    }


    /**
     * 菜单视图
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title;
        public TextView info;

        public ItemViewHolder(View contentView) {
            super(contentView);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            info = (TextView) contentView.findViewById(R.id.info);
        }
    }


}
