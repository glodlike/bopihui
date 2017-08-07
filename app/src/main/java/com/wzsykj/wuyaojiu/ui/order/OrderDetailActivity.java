package com.wzsykj.wuyaojiu.ui.order;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.OrderListInfo;
import com.wzsykj.wuyaojiu.Bean.Refund;
import com.wzsykj.wuyaojiu.ui.user.PingjiaActivity;
import com.wzsykj.wuyaojiu.ui.user.RefusedPayActivity;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.OrderDetailBean;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.OrderListGoodRecyclerAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

public class OrderDetailActivity extends BaseActivity implements View.OnClickListener{
    private TextView orderTime,orderNo,orderDeliverWay,orderDelivTime,extreWord,orderPayTime;
    private RecyclerView recycler;  // 商品列表
    private TextView payTV,pingjiaTV,cancleTV;
    private TextView totle_price,deliveFree,goodsFree ;
    private TextView nameTV,addressTV;
    private ArrayList<String> userList;
    private String  orderId;
    private OrderListInfo.ItemBean OrderBean;
    private List<OrderDetailBean.DealOrderItemBean> beanList;
    private OrderListGoodRecyclerAdapter adapter;
    private  ScrollView view;
    private ImageButton back;

    private TextView shop_name,shop_phone,shop_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        InitData();
        InitView();
        getOrderDetailInfo(orderId);
        InitClick();
    }

    private void InitClick() {
     pingjiaTV.setOnClickListener(this);
     payTV.setOnClickListener(this);
     cancleTV.setOnClickListener(this);
     back.setOnClickListener(this);
    }
    /**
     * 初始化数据
     */
    private void InitData() {
        orderId  =  getIntent().getStringExtra("orderId");
        OrderBean = (OrderListInfo.ItemBean) getIntent().getSerializableExtra("orderItem");
        //Toast.makeText(this,OrderBean.getDeal_order_item().get(0).getAttr_str(),Toast.LENGTH_LONG).show();
        beanList = new ArrayList<>();
        userList = new SharePerfenceUtils(this).getUserInfo();
    }
    /**
     * 初始化View
     */
    private void InitView() {
        orderPayTime = (TextView) findViewById(R.id.orderPayTime);
        shop_name = (TextView) findViewById(R.id.shop_name);
        shop_phone = (TextView) findViewById(R.id.shop_phone);
        shop_address = (TextView) findViewById(R.id.shop_address);


        back = (ImageButton) findViewById(R.id.back);

        orderNo = (TextView) findViewById(R.id.orderNo);                  // 订单编号
        orderTime = (TextView) findViewById(R.id.orderTime);              //下单时间
        orderDeliverWay = (TextView) findViewById(R.id.orderDeliverWay);  // 配送方式
        orderDelivTime  = (TextView) findViewById(R.id.orderDeliverTime); // 配送时间
        extreWord       = (TextView) findViewById(R.id.extraWord);        // 备注
        //orderPayTime    = (TextView) findViewById(R.id.orderPayTime);     //付款时间

        payTV           = (TextView) findViewById(R.id.ljfk);
        pingjiaTV       = (TextView) findViewById(R.id.qpj);
        cancleTV        = (TextView) findViewById(R.id.scdd);

        recycler         = (RecyclerView) findViewById(R.id.good_recycler_view);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycler.setNestedScrollingEnabled(false);


        totle_price      = (TextView) findViewById(R.id.yifujine);
        goodsFree        = (TextView) findViewById(R.id.xufujie);
        deliveFree       = (TextView) findViewById(R.id.yunfei);

        nameTV           = (TextView) findViewById(R.id.name);
        addressTV        = (TextView) findViewById(R.id.address);

        view = (ScrollView) findViewById(R.id.view);
        view.setVisibility(View.INVISIBLE);

        adapter = new OrderListGoodRecyclerAdapter(OrderDetailActivity.this, OrderBean.getDeal_order_item(), new OrderListGoodRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
        recycler.setAdapter(adapter);

        if (OrderBean.getItems_refund_status()==1&&OrderBean.getPay_status().equals("2")&&OrderBean.getOrder_status().equals("0")){
            pingjiaTV.setVisibility(View.VISIBLE);
            pingjiaTV.setText("我要退款");
            pingjiaTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    refund(orderId,OrderBean);
                }
            });
        }

        if (OrderBean.getPay_status().equals("0")){
            payTV.setVisibility(View.VISIBLE);
            cancleTV.setVisibility(View.VISIBLE);
        }
        if (OrderBean.isKeyi_dianpin()&&OrderBean.getOrder_status().equals("0")){
            pingjiaTV.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ljfk:    // 付款
                Pay(Integer.parseInt(orderId));
                break;
            case R.id.qpj :
                // 评价
                if (pingjiaTV.getText().equals("我要退款")){
                  refund(orderId,OrderBean);
                }else if (pingjiaTV.getText().equals("去评价")){
                    GoPingjia(orderId,OrderBean);
                }
                break;
            case R.id.scdd:      // 取消订单
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("确定要删除该订单吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int index) {
                                 deleteOrder(orderId);
                                 finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dlg = builder.create();
                dlg.show();
                break;
           case  R.id.back:
              finish();
            break;
        }
    }
    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(Long srcTime){
        long targetTime=srcTime*1000-TimeZone.getDefault().getRawOffset();
        Date newDate=new Date(targetTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String str=simpleDateFormat.format(newDate);
        return str;
    }
    public  void  getOrderDetailInfo(final String id){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=view&id="+id+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
               System.out.println(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=view&id="+id+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(OrderDetailActivity.this).getShop_id()[0]+"");
                view.setVisibility(View.VISIBLE);
                OrderDetailBean bean = new Gson().fromJson(StringUtils.base64ToString(result),OrderDetailBean.class);
                orderNo.setText("订单编号 ："+bean.getOrder_sn());
                orderTime.setText("下单时间 ："+OrderBean.getCreate_time());
                if (bean.getPay_status().equals("2")){
                    orderPayTime.setText("付款时间 ："+stampToDate((Long.parseLong(bean.getPay_time())+86400)));
                }else if (bean.getPay_status().equals("0")){
                    orderPayTime.setVisibility(View.GONE);
                }
                orderDeliverWay.setText("配送方式 ："+bean.getDelivery_name());
                totle_price.setText("￥"+bean.getTotal_price());
                goodsFree.setText("￥"+bean.getDeal_total_price());
                deliveFree.setText("￥"+bean.getDelivery_fee());
                orderDelivTime.setText("配送时间 ："+stampToDate((Long.parseLong(bean.getDelivery_time())+86400)));
                orderDelivTime.setVisibility(View.GONE);

                nameTV.setText(bean.getConsignee()+" "+bean.getMobile());
                addressTV.setText(bean.getAddress());
                extreWord.setText("         备注  : "+bean.getMemo());
                shop_name.setText(bean.getShop_info().getName());
                shop_phone.setText(bean.getShop_info().getTel());
                shop_address.setText(bean.getShop_info().getAddress());

                shop_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://"+shop_phone.getText()));
                        startActivity(intent);
                    }
                });
                super.onSuccess(result);
                }
        });
    }
    /**
     * 继续付款
     */
    public void  Pay(int OrderId ){
        Intent intent = new Intent(this, OrderPayActivity.class);
        intent.putExtra("orderId",OrderId);
        startActivity(intent);
    }
    /**
     * 删除订单
     */
    public  void  deleteOrder(final String OrderId){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&act=cancel&id="+OrderId+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
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
                    Intent intent = new Intent(OrderDetailActivity.this,RefusedPayActivity.class);
                    intent.putExtra("refund",refund);
                    intent.putExtra("itemData",itemBean);
                    OrderDetailActivity.this.startActivity(intent);
                }else{
                    ToastUtils.show(OrderDetailActivity.this,refund.getInfo());
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
                    Intent intent = new Intent(OrderDetailActivity.this,PingjiaActivity.class);
                    intent.putExtra("refund",refund);
                    intent.putExtra("itemData",itemBean);
                    OrderDetailActivity.this.startActivity(intent);
                }else{
                    ToastUtils.show(OrderDetailActivity.this,refund.getInfo());
                }
                super.onSuccess(result);
            }
        });
    }
}

