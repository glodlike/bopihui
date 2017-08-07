package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.graphics.Paint;
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
public class MainRecyclerHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> data;              // 图片
    private ArrayList<String> priceList;    //单价
    private ArrayList<String> nameList;     //商品名
    private ArrayList<String> oldpriceList; //原价
    private ArrayList<String> buy_count;    //购买量
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

    public MainRecyclerHotAdapter(Context context, List<String> entitys,ArrayList<String> nameList,ArrayList<String> priceList,ArrayList<String> oldpriceList,ArrayList<String> buy_count, OnRecyclerViewItemClickListener listener) {
        this.context      = context;
        this.data         = entitys;
        this.priceList    = priceList;
        this.oldpriceList = oldpriceList;
        this.buy_count    = buy_count;
        this.nameList     = nameList;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener     = listener;
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
        contentView = mLayoutInflater.inflate(R.layout.main_recycler_hot_item_item, parent, false);
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.getWindowsWidth(context) / 2, ViewGroup.LayoutParams.MATCH_PARENT);
        itemViewHolder.linearLayout.setLayoutParams(params);
        itemViewHolder.title.setText(nameList.get(position));
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams((DensityUtil.getWindowsWidth(context) / 2) - DensityUtil.dp2px(context, 30), (DensityUtil.getWindowsWidth(context) / 2) - DensityUtil.dp2px(context, 30));
        itemViewHolder.img.setLayoutParams(params1);
        ImageLoaderUtils.LoadImage(data.get(position),itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
        itemViewHolder.price.setText("￥"+priceList.get(position));
        itemViewHolder.old_price.setText("￥"+oldpriceList.get(position));
        itemViewHolder.buy_count.setText("已售 "+buy_count.get(position)+" 件");
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
        public TextView price,old_price,buy_count;
        public ImageView car;

        public ItemViewHolder(View contentView) {
            super(contentView);
            linearLayout = (LinearLayout) contentView.findViewById(R.id.layout);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            price  = (TextView) contentView.findViewById(R.id.price);
            old_price  = (TextView) contentView.findViewById(R.id.old_price);
            old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            buy_count   = (TextView) contentView.findViewById(R.id.buy_count);
        }
    }
}
