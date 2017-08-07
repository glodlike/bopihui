package com.wzsykj.wuyaojiu.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.Bean.Address;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.user.AddressEditActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class AddressRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<Address.ConsigneeListBean> data;
    private OnRecyclerViewItemClickListener listener;
    private ArrayList<String> userList;

    private static final int MESSAGE_FLAG = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_FLAG:
                    AddressRecyclerAdapter.this.notifyDataSetChanged();
                    //orderListRecyclerAdapter.notifyDataSetChanged();
                    //Toast.makeText(context,"设置成功",Toast.LENGTH_SHORT).show();
                   /* Intent intent = new Intent();
                    intent.setAction(BracastUtil.ACTION_FRESH); // 说明动作
                    context.sendBroadcast(intent);// 该函数用于发送广播*/
                    break;
                default:
                    break;
            }
        }
    };


    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);

        void toIntent(Intent intent);
    }

    public List<Address.ConsigneeListBean> getData() {
        return data;
    }

    public void setData(List<Address.ConsigneeListBean> data) {
        this.data = data;
    }

    public AddressRecyclerAdapter(Context context, List<Address.ConsigneeListBean> entitys, OnRecyclerViewItemClickListener listener) {
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
        View contentView = null;
        contentView = mLayoutInflater.inflate(R.layout.address_recycler_item, parent, false);
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
        final  Address.ConsigneeListBean bean   = data.get(position);
        itemViewHolder.address.setText(bean.getRegion_lv2_name()+" "+bean.getRegion_lv3_name()+" "+ bean.getRegion_lv4_name()+" "+bean.getAddress());
        itemViewHolder.phone.setText(bean.getMobile());
        itemViewHolder.name.setText(bean.getConsignee());
        if (bean.getIs_default().equals("1")){
            itemViewHolder.deflute.setBackgroundResource(R.drawable.my_button1);
            itemViewHolder.deflute.setTextColor(Color.WHITE);
            itemViewHolder.deflute.setText("默认");
        }
        itemViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddressEditActivity.class);
                intent.setAction("edit");
                intent.putExtra("data", bean);
                context.startActivity(intent);
            }
        });
         /**
         * 设置为默认地址
         */
        itemViewHolder.deflute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //getMoreData(page++);
                        for (int i=0;i<data.size();i++){
                            data.get(i).setIs_default("0");
                            if (i==position){
                                data.get(position).setIs_default("1");
                            }
                        }
                        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=set_default&id="+bean.getId()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
                            @Override
                            public void onSuccess(String result) {
                                AddressRecyclerAdapter.this.notifyDataSetChanged();
                                System.out.println(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=set_default&id="+bean.getId()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"");
                                super.onSuccess(result);
                            }

                            @Override
                            public void onFinished() {
                                Intent intent = new Intent();
                                intent.setAction(BracastUtil.ACTION_FRESH); // 说明动作
                                context.sendBroadcast(intent);// 该函数用于发送广播
                                ToastUtils.show(context,"设置成功");
                                super.onFinished();
                            }
                        });

                    }
                }).start();
            }
        });
        /**
         * 删除地址
         */
        itemViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确定删除改地址？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=del&id="+bean.getId()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
                                    @Override
                                    public void onSuccess(String result) {
                                        System.out.println(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=set_default&id="+bean.getId()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"");
                                        super.onSuccess(result);
                                    }
                                    @Override
                                    public void onFinished() {
                                        super.onFinished();
                                    }
                                });
                                data.remove(position);
                                AddressRecyclerAdapter.this.notifyDataSetChanged();
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
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name,address;
        private TextView phone;
        private ImageView edit;
        private ImageView delete;
        private TextView  deflute; //设为默认
        public ItemViewHolder(View contentView) {
            super(contentView);
            contentView.setBackgroundResource(R.color.clearColor);
            name = (TextView) contentView.findViewById(R.id.name);
            phone = (TextView) contentView.findViewById(R.id.phone);
            delete   = (ImageView) contentView.findViewById(R.id.delete);
            address = (TextView) contentView.findViewById(R.id.address);
            edit = (ImageView) contentView.findViewById(R.id.edit);
            deflute = (TextView) contentView.findViewById(R.id.deflute);
        }
    }
    public  void  deieteAddress(){

    }
    public void  setDefalute(){

    }
}
