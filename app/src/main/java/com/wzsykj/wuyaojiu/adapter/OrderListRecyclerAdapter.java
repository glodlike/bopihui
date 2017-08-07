package com.wzsykj.wuyaojiu.adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.OrderListInfo;
import com.wzsykj.wuyaojiu.ui.user.PingjiaActivity;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Refund;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.order.OrderPayActivity;
import com.wzsykj.wuyaojiu.ui.user.RefusedPayActivity;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chen on 16/6/16.
 */
public class OrderListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<OrderListInfo.ItemBean> ItemBeanList;
    private OnRecyclerViewItemClickListener listener;
    private ArrayList<String> userList;
    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<OrderListInfo.ItemBean> getData() {
        return ItemBeanList;
    }

    public void setData(List<OrderListInfo.ItemBean> data) {
        this.ItemBeanList = data;
    }

    public OrderListRecyclerAdapter(Context context, List<OrderListInfo.ItemBean> data, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.ItemBeanList = data;
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
        contentView = mLayoutInflater.inflate(R.layout.orderlist_recycler_item, parent, false);
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
        final OrderListInfo.ItemBean itemBean = ItemBeanList.get(position);
        List<OrderListInfo.ItemBean.DealOrderItemBean> detailItem = itemBean.getDeal_order_item();

        itemViewHolder.time.setText(itemBean.getCreate_time());
        itemViewHolder.price.setText("￥ "+itemBean.getTotal_price());
        itemViewHolder.start.setText(itemBean.getStatus());
        //itemBean.
        if (itemBean.getItems_refund_status()==1&&itemBean.getPay_status().equals("2")&&itemBean.getOrder_status().equals("0")){
            itemViewHolder.qpj.setVisibility(View.VISIBLE);
            itemViewHolder.qpj.setText("我要退款");

        }
        if (itemBean.getPay_status().equals("0")){
            itemViewHolder.ljfk.setVisibility(View.VISIBLE);
            itemViewHolder.scdd.setVisibility(View.VISIBLE);
        }
        if (itemBean.isKeyi_dianpin()&&itemBean.getOrder_status().equals("0")){
            itemViewHolder.qpj.setVisibility(View.VISIBLE);
        }
        itemViewHolder.recyclerView.setAdapter(new OrderListGoodRecyclerAdapter(context, detailItem, new OrderListGoodRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int subPosition) {
                listener.onItemClick(position);
            }
        }));
        /**
         * 去付款
         */
        itemViewHolder.ljfk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pay(Integer.parseInt(itemBean.getId()));
            }
        });
        /**
         * 删除订单
         */
        itemViewHolder.scdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("确定要删除该订单吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                               ItemBeanList.remove(position);
                               OrderListRecyclerAdapter.this.notifyDataSetChanged();
                               deleteOrder(itemBean.getId());
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
         /**
         * 退款
         */
         if (itemViewHolder.qpj.getText().equals("我要退款")){
         itemViewHolder.qpj.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 refund(itemBean.getId(),itemBean);
                /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
                 builder.setMessage("支付宝退款将扣除0.6%的手续费,您是否确定退款？")
                         .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int index) {

                             }
                         }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {

                     }
                 });
                 AlertDialog dlg = builder.create();
                 dlg.show();*/
             }
         });
     }else if (itemViewHolder.qpj.getText().equals("去评价")){
             itemViewHolder.qpj.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     //refund(itemBean.getId(),itemBean);
                     GoPingjia(itemBean.getId(),itemBean);
                 }
             });
         }
    }
    @Override
    public int getItemCount() {
        return ItemBeanList.size();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView start;
        public TextView time;
        public TextView price;
        public TextView ljfk, scdd, qpj;
        public RecyclerView recyclerView;
        public ItemViewHolder(View contentView) {
            super(contentView);
            time = (TextView) contentView.findViewById(R.id.time);
            start = (TextView) contentView.findViewById(R.id.start);
            price = (TextView) contentView.findViewById(R.id.price);

            ljfk = (TextView) contentView.findViewById(R.id.ljfk);
            scdd = (TextView) contentView.findViewById(R.id.scdd);
            qpj = (TextView) contentView.findViewById(R.id.qpj);

            recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            //recyclerView.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.VERTICAL_LIST));
        }
    }
    /**
     * 继续付款
     */
    public void  Pay(int OrderId ){
        Intent intent = new Intent(context, OrderPayActivity.class);
        intent.putExtra("orderId",OrderId);
        context.startActivity(intent);
      }
     /**
     * 删除订单
     */
    public  void  deleteOrder(final String OrderId){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=cancel&id="+OrderId+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
            }
        });
    }
    /**
     * 申请退款
     * @param OrderId
     * @param itemBean
     */
    public void  refund(final String OrderId, final OrderListInfo.ItemBean itemBean){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=refund&order_id="+OrderId+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                Refund refund = new Gson().fromJson(StringUtils.base64ToString(result),Refund.class);
                if (refund.getStatus()==1){
                    Intent intent = new Intent(context,RefusedPayActivity.class);
                    intent.putExtra("refund",refund);
                    intent.putExtra("itemData",itemBean);
                    context.startActivity(intent);
                }else{
                   Toast.makeText(context,refund.getInfo(),Toast.LENGTH_LONG).show();
                }
                super.onSuccess(result);
            }
        });
    }
    /**
     * 去評價
     */
     public  void  GoPingjia(final String orderId,final OrderListInfo.ItemBean itemBean){
         XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=refund&order_id="+orderId+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>() {
             @Override
             public void onSuccess(String result) {
                 Refund refund = new Gson().fromJson(StringUtils.base64ToString(result),Refund.class);
                 if (refund.getStatus()==1){
                     Intent intent = new Intent(context,PingjiaActivity.class);
                     intent.putExtra("refund",refund);
                     intent.putExtra("itemData",itemBean);
                     context.startActivity(intent);
                 }else{
                     Toast.makeText(context,refund.getInfo(),Toast.LENGTH_LONG).show();
                 }
                 super.onSuccess(result);
                 }
            });
         }
     }
