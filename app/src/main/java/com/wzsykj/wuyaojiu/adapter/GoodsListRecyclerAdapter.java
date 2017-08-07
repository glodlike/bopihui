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


/**
 * Created by chen on 16/6/16.
 */
public class GoodsListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<String> nameList;
    private ArrayList<String> priceList;
    private ArrayList<String> old_price;
    private ArrayList<String> count;
    private ArrayList<String> imageList;
    private OnRecyclerViewItemClickListener listener;

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public void setData(ArrayList<String> nameList, ArrayList<String> priceList, ArrayList<String> imageList,ArrayList<String> old_price,ArrayList<String> count) {
        this.nameList = nameList;
        this.priceList = priceList;
        this.imageList = imageList;
        this.old_price = old_price;
        this.count = count;

    }

    public GoodsListRecyclerAdapter(Context context, ArrayList<String> nameList, ArrayList<String> priceList, ArrayList<String> imageList,ArrayList<String> old_price,ArrayList<String> count, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.nameList = nameList;
        this.priceList = priceList;
        this.imageList = imageList;
        this.old_price = old_price;
        this.count     = count;
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
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.goodlist_recycler_item, parent, false));
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
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((DensityUtil.getWindowsWidth(context) - DensityUtil.dp2px(context, 16 * 3)) / 2, (DensityUtil.getWindowsWidth(context) - DensityUtil.dp2px(context, 16 * 3)) / 2);
        itemViewHolder.img.setLayoutParams(params);
        itemViewHolder.title.setText(nameList.get(position));
        itemViewHolder.price.setText("会员价 ￥" + priceList.get(position));
        itemViewHolder.old_price.setText("￥"+ old_price.get(position));
        itemViewHolder.buyCount.setText("已售"+count.get(position)+" 件");
        ImageLoaderUtils.LoadImage(imageList.get(position), itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * 热销
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public ImageView img;
        public TextView title;
        public TextView price,old_price;
        public  TextView buyCount;
        public ImageView car;

        public ItemViewHolder(View contentView) {
            super(contentView);
            linearLayout = (LinearLayout) contentView.findViewById(R.id.layout);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            price = (TextView) contentView.findViewById(R.id.price);
            car = (ImageView) contentView.findViewById(R.id.car);
            old_price = (TextView) contentView.findViewById(R.id.old_price);
            old_price.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
            buyCount = (TextView) contentView.findViewById(R.id.buy_count);
        }

    }

}
