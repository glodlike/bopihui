package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.PayforCart;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.Bean.AddToCart;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.login.LoginActivity;
import com.wzsykj.wuyaojiu.ui.order.OrderConfirmActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zhy.view.flowlayout.FlowLayout;
import zhy.view.flowlayout.TagAdapter;
import zhy.view.flowlayout.TagFlowLayout;


/**
 * Created by chen on 16/6/16.
 */
public class AddGoodTagRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<String> data;
    private List<String> idList;
    private Map<String,List<String>> map;
    private Map<String,List<String>> Idmap;
    private Map<String,List<String>> priceMap;
    private OnRecyclerViewItemClickListener listener;
    private TextView SureBtn;
    private TextView order_price;
    private TextView all_price;
    private TextView danjiaTv;
    private String goodId; // 商品ID

    private double price;  // 商品单价

    private Map<String,String> orderIdList = new HashMap<>();

    private TextView number;

    private int type;

    private Map<Integer,String> addPriceMap = new HashMap<>();

    private ArrayList<String> userList = new ArrayList<>();

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

    public AddGoodTagRecyclerAdapter(Context context, List<String> entitys,
                                     List<String> idList,Map<String,List<String>> map,
                                     Map<String,List<String>> Idmap,Map<String,List<String>> priceMap,
                                     OnRecyclerViewItemClickListener listener,TextView sureBtn,TextView order_price,
                                     TextView all_price,TextView danjiaTv,String goodId,double price,TextView number,int type) {
        this.context = context;
        this.data = entitys;
        this.idList = idList;
        this.map = map;
        this.Idmap = Idmap;
        this.priceMap = priceMap;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
        this.SureBtn   = sureBtn;
        this.order_price = order_price;
        this.all_price   = all_price;
        this.danjiaTv    = danjiaTv;
        this.goodId      = goodId;
        this.price       = price;
        this.number      = number;
        this.type        = type;

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
        contentView = mLayoutInflater.inflate(R.layout.addgood_recycler_tag_item, parent, false);
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


        itemViewHolder.tagFlowLayout.setAdapter(new TagAdapter<String>(map.get(String.valueOf(position))) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.addgood_flowlayout_tag_item,
                        itemViewHolder.tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        itemViewHolder.tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int i, FlowLayout parent) {
                 double Oneprice;
                 double addPrice =0.00;
                 orderIdList.put(String.valueOf(position),Idmap.get(String.valueOf(position)).get(i));
                 addPriceMap.put(position,priceMap.get(String.valueOf(position)).get(i));
                 for (int j=0;j<addPriceMap.size();j++){
                   addPrice =  addPrice+Double.valueOf(addPriceMap.get(j));
                 }
                 Oneprice = price+addPrice;
                 all_price.setText (String.valueOf(Oneprice));
                 order_price.setText ("合计 ￥："+String.valueOf(Oneprice));
                 danjiaTv.setText(String.valueOf(Oneprice));
                 return true;
            }
        });

    SureBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String order = "";
        if(orderIdList.size()==0){
           Toast.makeText(context,"请选择商品的属性",Toast.LENGTH_SHORT).show();
        }else{
        for (int i= 0;i<orderIdList.size();i++){
            if (i==orderIdList.size()-1){
                order =order+orderIdList.get(String.valueOf(i));
            }else {
                order =order+orderIdList.get(String.valueOf(i))+",";
            }
           }
        }
        if (  type == 0 ){
            //加入购物车
            addToCart(order);

        }else if(type == 1) {
            faseBuy(order);   // 立即购买
              }
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
        public TagFlowLayout tagFlowLayout;
        public TextView title;

        public ItemViewHolder(View contentView) {
            super(contentView);
            contentView.setBackgroundResource(R.color.clearColor);
            title = (TextView) contentView.findViewById(R.id.title);
            tagFlowLayout = (TagFlowLayout) contentView.findViewById(R.id.flowlayout);
        }
    }
    /**
     * 添加到购物车
     * @param order
     */
    public  void  addToCart(final String order){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=addcart&id="+goodId+"&deal_attr="+order+"&number="+number.getText().toString()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&shop_id="+new SharePerfenceUtils(context).getShop_id()+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=cart&act=addcart&id="+goodId+"&deal_attr="+order+"&number="+number.getText().toString()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"");
                //Toast.makeText(context, StringUtils.base64ToString(result),Toast.LENGTH_SHORT).show();
                AddToCart addToCart = new Gson().fromJson(StringUtils.base64ToString(result),AddToCart.class);
                if (addToCart.getStatus()==1){
                  /* Intent toOC = new Intent(context, GoodInfoActivity.class);   //成功添加，跳到詳情
                        toOC.putExtra("type", 1);
                        context.startActivity(toOC);*/
                        Intent intent = new Intent();
                        intent.setAction(BracastUtil.ACTION_FINNISH); // 说明动作
                        context.sendBroadcast(intent);// 该函数用于发送广播
                }else {
                    if (addToCart.getInfo().equals("请先登录")){
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        ToastUtils.show(context,addToCart.getInfo());
                    }
                    ToastUtils.show(context,addToCart.getInfo());
                }
                super.onSuccess(result);
            }
        });
     }

    /**
    * 立即购买
    * @param order
    * http://116.62.28.31/bopihui/cxb_api/index.php?ctl=cart&act=buynow&buynow_id=12&buynow_attr=""&buynow_number=1&email=&pwd=
    */

    public  void  faseBuy(final String order){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=buynow&buynow_id="+goodId+"&buynow_attr="+order+"&buynow_number="+number.getText().toString()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(context).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                PayforCart payforCart = new Gson().fromJson(StringUtils.base64ToString(result),PayforCart.class);
                //try {
                    System.out.println("=========="+AppHttp.BASE_URL_NEW+"ctl=cart&act=buynow&buynow_id="+goodId+"&buynow_attr="+order+"&buynow_number="+number.getText().toString()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(context).getShop_id()[0]+"");
                //} /*catch (JSONException e) {
                 //   e.printStackTrace();
               // }*/
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    if (jsonObject.getInt("status") == 1) {
                        Intent toOC = new Intent(context, OrderConfirmActivity.class);
                        toOC.putExtra("order", order);
                        toOC.putExtra("type", 1);
                        toOC.putExtra("payInfo", result);
                        toOC.putExtra("ob",payforCart);
                        context.startActivity(toOC);
                    } else {
                        if (jsonObject.getString("info").equals("请先登录")){
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                            ToastUtils.show(context,jsonObject.getString("info"));
                        }
                        ToastUtils.show(context,jsonObject.getString("info"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

                super.onError(ex, isOnCallback);
            }
        });
    }
}
