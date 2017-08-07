package com.wzsykj.wuyaojiu.ui.order;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.wzsykj.wuyaojiu.Bean.Alipay;
import com.wzsykj.wuyaojiu.Bean.WXinpay;
import com.wzsykj.wuyaojiu.adapter.OrderConfirmGoodRecyclerAdapter;
import com.wzsykj.wuyaojiu.adapter.OrderConfirmPayRecyclerAdapter;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.PayResult;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Payment;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.openutil.OpenConfig;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chen on 16/8/10.
 */
public class OrderPayActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private TextView pay;
    private ArrayList<String> userList;
    private LinearLayout ll_all;
    private TextView orderIdTV,orderFreeTV,emailFreeTV,totleFree;
    private Map<Integer,Boolean> map;
    private Map<Integer,String> PayId;
    private IWXAPI weixinApi;
    private BroadcastReceiver wxPayReceiver;
    private JSONArray goodList;

    private int  orderId;
    private int   type;
    private  OrderConfirmGoodRecyclerAdapter orderConfirmGoodRecyclerAdapter;
    private RecyclerView payRecyclerView,goodsRecyclerView;
    private OrderConfirmPayRecyclerAdapter orderConfirmPayRecyclerAdapter;

    private static final int SDK_PAY_FLAG = 1;

    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    //PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                            Intent intent1 = new Intent(OrderPayActivity.this,PayResultActivity.class);
                            intent1.putExtra("totle",totleFree.getText().toString());
                            startActivity(intent1);
                            finish();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(OrderPayActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderPayActivity.this, "取消支付", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //break;
                }
            }
        }
    };
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this); // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH);
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.orderpay_activity, null);
        setContentView(contentView);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        materialishProgress.show();
        initView();
    }
    private void initView() {

        goodsRecyclerView = (RecyclerView) findViewById(R.id.goods_recycler_view);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        goodsRecyclerView.setNestedScrollingEnabled(false);

        ll_all = (LinearLayout) findViewById(R.id.ll_all);
        ll_all.setVisibility(View.GONE);
        //微信支付
        weixinApi = WXAPIFactory.createWXAPI(OrderPayActivity.this, OpenConfig.WXAppID);
        weixinApi.registerApp(OpenConfig.WXAppID);

        wxPayReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getIntExtra(OpenConfig.ERROR_CODE, -3) == 0){
                    Intent intent1 = new Intent(OrderPayActivity.this,PayResultActivity.class);
                    intent1.putExtra("totle",totleFree.getText().toString());
                    startActivity(intent1);
                    finish();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(OpenConfig.ACTION_WEIXIN_PAY);
        registerReceiver(wxPayReceiver, intentFilter);
        map   = new HashMap<>();
        PayId = new HashMap<>();
        //订单Id
        orderId  = getIntent().getIntExtra("orderId",0);
        type     = getIntent().getIntExtra("type",0);
        //Toast.makeText(this,orderId+"",Toast.LENGTH_SHORT).show();

        orderIdTV = (TextView) findViewById(R.id.orderId);
        orderFreeTV = (TextView) findViewById(R.id.orderFree);
        emailFreeTV  = (TextView) findViewById(R.id.emailFree);
        totleFree    = (TextView) findViewById(R.id.totleFree);


        userList =  new SharePerfenceUtils(this).getUserInfo();
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);

        pay = (TextView) findViewById(R.id.pay);
        pay.setOnClickListener(this);

        payRecyclerView = (RecyclerView) findViewById(R.id.pay_recycler_view);
        payRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        payRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        payRecyclerView.setNestedScrollingEnabled(false);
        loadData();
    }
    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showData();
            }
        }, 1000);
    }
    private void showData() {
        //swipeLayout.setRefreshing(false);
        getPayforCartinfo();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                AlertDialog.Builder alert = new AlertDialog.Builder(OrderPayActivity.this);
                alert.setTitle("确定放弃支付？")
                        .setMessage("放弃支付可以在: 我的订单—待付款—点击支付按钮继续支付")
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which)   {
                                        if (type == 1 ){
                                            close();
                                        }else {
                                            finish();
                                        }
                                     }
                                })
                        .setNegativeButton("继续支付",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        dialog.dismiss();
                                    }
                                });
                alert.create().show();

                break;
            case R.id.pay:
               for (int i= 0;i<map.size();i++){
                   if (map.get(i)){
                       if (PayId.get(i).equals("17")){
                           YuePay();
                       }else  if (PayId.get(i).equals("24")){
                           AliPay();
                       }else if( PayId.get(i).equals("25")){
                           WXinPay();
                       }else {
                           ToastUtils.show(OrderPayActivity.this,"该支付方式暂未开通");
                       }
                   }
               }
                break;
            default:
                break;
        }
    }
    public  void  getPayforCartinfo(){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=payment&act=done&id="+orderId+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(StringUtils.base64ToString(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                double orderPay = 0;
                Payment payment = new Gson().fromJson(StringUtils.base64ToString(result),Payment.class);
                orderIdTV.setText(payment.getOrder_sn());
                for (int i = 0;i<payment.getCart_list().size();i++){
                    orderPay = orderPay+payment.getCart_list().get(i).getTotal_price();
                }
                orderFreeTV.setText("￥"+orderPay);
                emailFreeTV.setText("￥"+payment.getDelivery_fee());
                totleFree.setText  ("￥"+payment.getTotal_price());
                List<Payment.PaymentListBean> data =payment.getPayment_list();
                for (int i=0;i<data.size();i++){
                    map.put(i,false);
                    PayId.put(i,data.get(i).getId());
                }

                if (orderConfirmGoodRecyclerAdapter == null) {
                    try {
                        goodList = jsonObject.getJSONArray("cart_list");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    orderConfirmGoodRecyclerAdapter = new OrderConfirmGoodRecyclerAdapter(OrderPayActivity.this, goodList, new OrderConfirmGoodRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(int position) {



                        }
                    });
                    goodsRecyclerView.setAdapter(orderConfirmGoodRecyclerAdapter);
                } else {
                    orderConfirmGoodRecyclerAdapter.setData(goodList);
                    orderConfirmGoodRecyclerAdapter.notifyDataSetChanged();
                }



                if (orderConfirmPayRecyclerAdapter == null) {
                    orderConfirmPayRecyclerAdapter = new OrderConfirmPayRecyclerAdapter(OrderPayActivity.this, data,map,
                            new OrderConfirmPayRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            if (map.get(position)){
                                map.put(position,false);
                                orderConfirmPayRecyclerAdapter.notifyDataSetChanged();
                            }else{
                                map.put(position,true);
                                for (int i= 0;i<map.size();i++){
                                    if (i!=position){
                                        map.put(i,false);
                                    }
                                }
                                orderConfirmPayRecyclerAdapter.notifyDataSetChanged();
                            }
                                orderConfirmPayRecyclerAdapter.notifyDataSetChanged();
                        }
                    });
                    payRecyclerView.setAdapter(orderConfirmPayRecyclerAdapter);
                } else {
                    orderConfirmPayRecyclerAdapter.setData(data);
                    orderConfirmPayRecyclerAdapter.notifyDataSetChanged();
                }
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                materialishProgress.dismiss();
                ll_all.setVisibility(View.VISIBLE);
                super.onFinished();
            }
        });
    }

    public void close() {
        Intent intent = new Intent();
        intent.setAction(BracastUtil.ACTION_FINNISH); // 说明动作
        sendBroadcast(intent);// 该函数用于发送广播
    }

    /**
     * 支付宝支付
     */
     public  void  AliPay(){
         XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=payment&act=get_payment_code&order_id="+orderId+"&payment_id=24",null,new MyCallBack<String>(){
             @Override
             public void onSuccess(String result) {
                 //System.out.println(AppHttp.BASE_URL_NEW+"ctl=payment&act=get_payment_code&order_id="+orderId+"&payment_id="+24+"");
                 final Alipay alipay1 = new Gson().fromJson(StringUtils.base64ToString(result),Alipay.class);
                 if (alipay1.getStatus() ==1){
                     Runnable payRunnable = new Runnable() {
                         @Override
                         public void run() {
                             // 构造PayTask 对象
                             //index = position;
                             String order_spec = alipay1.getPayment_code().getConfig().getOrder_spec()+"&sign=\""+alipay1.getPayment_code().getConfig().getSign()+"\"&sign_type=\""+alipay1.getPayment_code().getConfig().getSign_type()+"\"";
                             PayTask alipay = new PayTask(OrderPayActivity.this);
                             String result = alipay.pay(order_spec,true);
                             Message msg = new Message();
                             msg.what = SDK_PAY_FLAG;
                             msg.obj = result;
                             mHandler.sendMessage(msg);
                         }

                     };
                     // 必须异步调用
                     Thread payThread = new Thread(payRunnable);
                     payThread.start();
                 }else {
                     ToastUtils.show(OrderPayActivity.this,alipay1.getInfo());
                 }

                 super.onSuccess(result);
             }
         });
     }
     /**
      *微信支付
      */
   public  void  WXinPay(){

        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=payment&act=get_payment_code&order_id="+orderId+"&payment_id=25",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                //System.out.println(AppHttp.BASE_URL_NEW+"ctl=payment&act=get_payment_code&order_id="+orderId+"&payment_id=25");
                WXinpay wXinpay = new Gson().fromJson(StringUtils.base64ToString(result),WXinpay.class);
                if (wXinpay.getStatus() == 1){
                    PayReq payReq = new PayReq();
                    WXinpay.PaymentCodeBean.ConfigBean configBean  = wXinpay.getPayment_code().getConfig();
                    payReq.appId        =     configBean.getAppid();
                    payReq.partnerId    =     configBean.getPartnerid();
                    payReq.prepayId     =     configBean.getPrepayid();
                    payReq.packageValue =     configBean.getPackagevalue();
                    payReq.nonceStr     =     configBean.getNoncestr();
                    payReq.timeStamp    =     String.valueOf(configBean.getTimestamp());
                    payReq.sign         =     configBean.getSign();
                    weixinApi.sendReq(payReq);
                }else {
                    ToastUtils.show(OrderPayActivity.this,wXinpay.getInfo());
                }

                super.onSuccess(result);
            }
        });
   }
    /**
     * 余额支付
     */
    public  void  YuePay(){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=payment&act=get_payment_code&order_id="+orderId+"&payment_id=17",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject obj = new JSONObject(StringUtils.base64ToString(result));
                     if (obj.getInt("status")==1){
                            Intent intent = new Intent(OrderPayActivity.this,PayResultActivity.class);
                            intent.putExtra("totle",totleFree.getText().toString());
                            startActivity(intent);
                            finish();
                            }else {
                             ToastUtils.show(OrderPayActivity.this,obj.getString("info"));
                            }
                         } catch (JSONException e) {
                    e.printStackTrace();
                    }
                super.onSuccess(result);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ExitDialog(OrderPayActivity.this).show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Dialog ExitDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("确定放弃支付？");
        builder.setMessage("放弃支付可以在: 我的订单—待付款—点击支付按钮继续支付");
        builder.setPositiveButton("确定",
        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (type == 1 ){
                    close();
                }else {
                    finish();
                }
            }
        });
        builder.setNegativeButton("继续支付",
        new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        return builder.create();
    }
}
