package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.Bean.CollectList;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.DensityUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by chen on 16/6/16.
 */
public class GoodListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<CollectList.GoodsListBean> data;
    private OnRecyclerViewItemClickListener listener;
    private ArrayList<String> userList;
    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }
   /* public List<CollectList.GoodsListBean> getData() {
        return data;
    }
    public void setData(List<CollectList.GoodsListBean> data) {
        this.data = data;
    }*/

    public GoodListRecyclerAdapter(Context context, List<CollectList.GoodsListBean> entitys, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.data = entitys;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
        userList =new SharePerfenceUtils(context).getUserInfo();
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
        return new ItemViewHolder(mLayoutInflater.inflate(R.layout.collectgoodlist_recycler_item, parent, false));
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
        itemViewHolder.title.setText(data.get(position).getSub_name());
        itemViewHolder.price.setText("￥" + data.get(position).getCurrent_price());
        ImageLoaderUtils.LoadImage( data.get(position).getIcon(),itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());

        final String[] id = {""};
        itemViewHolder.car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确定要将该商品从您的收藏列表中删除吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                id[0] = data.get(position).getId();
                                //Toast.makeText(context,+"-----",Toast.LENGTH_LONG).show();
                                data.remove(position);
                                DeleteFromCollect(id[0]);
                                GoodListRecyclerAdapter.this.notifyDataSetChanged();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dlg = builder.create();
                dlg.show();
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

    /**
     * 热销
     */
    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout linearLayout;
        public ImageView img;
        public TextView title;
        public TextView price;
        public ImageView car;

        public ItemViewHolder(View contentView) {
            super(contentView);
            linearLayout = (LinearLayout) contentView.findViewById(R.id.layout);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            price = (TextView) contentView.findViewById(R.id.price);
            car = (ImageView) contentView.findViewById(R.id.car);
            }
      }
    /**
     *
     */
    public  void  DeleteFromCollect(String id){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=deal&act=add_collect&id="+id+"&email="+userList.get(0)+"pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                }
            });
        }
}
