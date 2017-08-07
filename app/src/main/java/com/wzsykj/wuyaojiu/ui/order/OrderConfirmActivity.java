package com.wzsykj.wuyaojiu.ui.order;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.PayforCart;
import com.wzsykj.wuyaojiu.adapter.OrderConfirmGoodRecyclerAdapter;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.user.AddressActivity;
import com.wzsykj.wuyaojiu.ui.user.AddressEditActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.SelectPicPopupWindow;
import com.wzsykj.wuyaojiu.Bean.Address;
import com.wzsykj.wuyaojiu.Bean.CartDone;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created by chen on 16/8/10.
 *
 * @param "type" 1=添加订单  0=订单详情
 */
public class OrderConfirmActivity extends BaseActivity implements View.OnClickListener {
    //private PayforCart pfc;
    private CheckBox cb;
    private LinearLayout ll_address;
    private String   order_attr;
    private String   mendianId;  //门店id
    private TextView name,addressTV;  //默認地址
    private RelativeLayout ll_peisong,ll_yue;
    private TextView mendian;
    private PopupWindow menu;
    private ImageButton back;
    private TextView title;
    private TextView goods_totle_price_tv; //商品总价
    private TextView order_totle_price ;   //总价
    private TextView yunfei;
    private ScrollView view;
    //private PopupWindow popupWindow;
    private Map<String,String> map;
   /* private TextView tishiTV;*/
    private TextView account_money;
    private TextView noaddress;
    private RecyclerView payRecyclerView, goodRecyclerView;
    private OrderConfirmGoodRecyclerAdapter orderConfirmGoodRecyclerAdapter;

    private ArrayList<String> userList;
    private EditText editText;

    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;
    private LinearLayout selectTime, selectPs;
    private TextView time, ps;
    private AlertDialog.Builder psBuilder;
    private Map<String,String> pssway; //配送方式
    private String[] pss ;
    private Date date;
    private String payforCart;  // 立即购买信息
    private int type;
    private JSONArray goodList;
    private TextView Sure_order ;        //下單
    private JSONObject jsonObject;
    private ArrayList<Double> emailPree;  // 运费
    private ImageView dingwei,right;
    private PopupWindow popupWindow;
    private View contentView;
    private String[] kinds = { "今天", "明天"};
    private ArrayAdapter adapter,timeAdapter;
    private List<String> list,timeList,timeList1;
    private List<String> timeListID,timeList1ID;
    private String deTime;  //配送时间
    private String deTimeId;
    private TextView peisong;
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
        GetDefadalute();
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH);
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.orderconfirm_activity, null);
        setContentView(contentView);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        materialishProgress.show();
        new SharePerfenceUtils(OrderConfirmActivity.this).keeppeisong_id("");
        initView();
        //showPopwindow();
    }
    private void initView() {
        peisong = (TextView) findViewById(R.id.time);
        //pfc = (PayforCart) getIntent().getSerializableExtra("ob");
        //ToastUtils.show(this,pfc.getCity_name());
        mendian = (TextView) findViewById(R.id.mendian);

        noaddress = (TextView) findViewById(R.id.noaddress);

        dingwei = (ImageView) findViewById(R.id.dingwei);
        right   = (ImageView) findViewById(R.id.right);
        cb   = (CheckBox) findViewById(R.id.checkbox);

        account_money = (TextView) findViewById(R.id.much);

        /*  tishiTV = (TextView) findViewById(R.id.tishi);*/
        //tishiTV.setText("此商品只能在【"+new SharePerfenceUtils(this).getShop_id()[1]+"】提货或配送");

        ll_address = (LinearLayout) findViewById(R.id.ll_address);
        ll_address.setOnClickListener(this);

        ll_yue = (RelativeLayout) findViewById(R.id.ll_yue);

        map  = new HashMap<>();
        emailPree = new ArrayList<>();
        editText = (EditText) findViewById(R.id.beizhu);
        Sure_order = (TextView) findViewById(R.id.order_ok);
        Sure_order.setOnClickListener(this);

        userList = new SharePerfenceUtils(this).getUserInfo();

        name   = (TextView) findViewById(R.id.name);
        addressTV = (TextView) findViewById(R.id.address);

        //mendian = (TextView) findViewById(R.id.mendian);

        ll_peisong = (RelativeLayout) findViewById(R.id.ll_peisong);
        ll_peisong.setOnClickListener(this);

        goods_totle_price_tv = (TextView) findViewById(R.id.goods_totle_price_tv);
        order_totle_price   = (TextView) findViewById(R.id.order_price);
        yunfei = (TextView) findViewById(R.id.yunfei);
        payforCart    =  getIntent().getStringExtra("payInfo");
        try {
            jsonObject = new JSONObject(StringUtils.base64ToString(payforCart));
            account_money.setText("￥"+jsonObject.getString("account_money"));

            if(Double.parseDouble(jsonObject.getString("account_money"))<Double.parseDouble(jsonObject.getJSONObject("total_data").getString("total_price"))){
                          ll_yue.setVisibility(View.GONE);
            }

            goods_totle_price_tv.setText("￥"+jsonObject.getJSONObject("total_data").getString("total_price"));
            order_totle_price.setText("总计 ：" +"￥"+(Double.valueOf(jsonObject.getJSONObject("total_data").getString("total_price"))));

            pssway = new HashMap<>();
            for (int i= 0 ;i<jsonObject.getJSONArray("delivery_list").length();i++){
                pssway.put(jsonObject.getJSONArray("delivery_list").getJSONObject(i).getString("name"),jsonObject.getJSONArray("delivery_list").getJSONObject(i).getString("id"));
            }
            pss   = new String[jsonObject.getJSONArray("delivery_list").length()];
            for (int i = 0;i<jsonObject.getJSONArray("delivery_list").length();i++){
                pss[i]  = jsonObject.getJSONArray("delivery_list").getJSONObject(i).getString("name");
                emailPree.add(Double.valueOf(jsonObject.getJSONArray("delivery_list").getJSONObject(i).getString("first_fee")));
            }
            for (int i = 0;i<jsonObject.getJSONArray("delivery_list").length();i++){
                map.put(jsonObject.getJSONArray("delivery_list").getJSONObject(i).getString("name"),jsonObject.getJSONArray("delivery_list").getJSONObject(i).getString("id"));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        order_attr = getIntent().getStringExtra("order");
        type =    getIntent().getIntExtra("type",0);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);

        title = (TextView) findViewById(R.id.title);

        view = (ScrollView) findViewById(R.id.view);
        view.setVisibility(View.INVISIBLE);

        payRecyclerView = (RecyclerView) findViewById(R.id.pay_recycler_view);
        payRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        payRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        payRecyclerView.setNestedScrollingEnabled(false);

        goodRecyclerView = (RecyclerView) findViewById(R.id.good_recycler_view);
        goodRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        goodRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        goodRecyclerView.setNestedScrollingEnabled(false);

        selectTime = (LinearLayout) findViewById(R.id.select_time);
        //selectTime.setOnClickListener(this);
        selectPs = (LinearLayout) findViewById(R.id.select_ps);
        selectPs.setOnClickListener(this);
        time = (TextView) findViewById(R.id.time);
        ps = (TextView) findViewById(R.id.ps);
        date = new Date();
        psBuilder = new AlertDialog.Builder(this);
        loadData();
        GetDefadalute();
    }
    private void loadData() {
        if (getIntent().getExtras().getInt("type", 0) == 0) {
            title.setText("订单详情");
        } else {
            title.setText("确认订单");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showData();
            }
        }, 1000);
    }
    private void showData() {
        materialishProgress.dismiss();
        view.setVisibility(View.VISIBLE);
        if (orderConfirmGoodRecyclerAdapter == null) {
            try {
                goodList = jsonObject.getJSONArray("cart_list");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            orderConfirmGoodRecyclerAdapter = new OrderConfirmGoodRecyclerAdapter(this, goodList, new OrderConfirmGoodRecyclerAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
            goodRecyclerView.setAdapter(orderConfirmGoodRecyclerAdapter);
        } else {
            orderConfirmGoodRecyclerAdapter.setData(goodList);
            orderConfirmGoodRecyclerAdapter.notifyDataSetChanged();
        }
       psBuilder.setItems(pss, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    String url = null;
                    ps.setTextColor(getResources().getColor(R.color.colorFour));
                    ps.setText(pss[which]);
                    if (type == 0){
                            url =  AppHttp.BASE_URL_NEW+"ctl=cart&act=count_buy_total&delivery_id="+map.get(ps.getText().toString().trim())+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(OrderConfirmActivity.this).get_city_id()+"";
                    }else {
                        try {
                            url = AppHttp.BASE_URL_NEW+"ctl=cart&act=count_buy_total&delivery_id="+map.get(ps.getText().toString().trim())+"&buynow_id="+jsonObject.getJSONArray("cart_list").getJSONObject(0).getString("deal_id")+"&buynow_attr="+order_attr
                                    +"&buynow_number="+jsonObject.getJSONArray("cart_list").getJSONObject(0).getString("number")+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(OrderConfirmActivity.this).get_city_id()+"";
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        final String finalUrl = url;
                        XUtils.Get(url,null,new MyCallBack<String>(){
                            @Override
                            public void onSuccess(String result) {
                                try {
                                    JSONObject js = new JSONObject(StringUtils.base64ToString(result));
                                    if (js.getString("is_support").equals("-1")){
                                        ToastUtils.show(OrderConfirmActivity.this,"您的配送地址暂不支持商店配送，请重新选择配送方式");
                                    }else {
                                        yunfei.setText("运费 ：￥" + js.getJSONObject("delivery_fee_supplier").getString("sid_1"));
                                    }
                                    order_totle_price.setText("总计 ：" +"￥"+js.getString("pay_price"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                super.onSuccess(result);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        //设置总价
                        order_totle_price.setText("总计 ：" +"￥"+(Double.valueOf(jsonObject.getJSONObject("total_data").getString("total_price"))+emailPree.get(which)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.order_ok:        // 下单
                 if (cb.isChecked()){
                     if (type==0) {            //订单来自购物车用余额支付
                         CartOrderPayWithYue();
                     }else if (type==1){       //订单来自立即购买用余额支付
                         getPayIfo();
                     }
                 }else {
                     if (type==0){
                         CartOrderPay();     //订单来自购物车
                     }else if (type==1){
                         OrderPay();         //订单来自立即购买
                     }
                 }
                break;
            case R.id.select_ps:
                psBuilder.show();
                break;
            case R.id.ll_peisong:
                Intent intent1 = new Intent(this,DeliveryActivity.class);
                startActivityForResult(intent1,0);
                break;
            case R.id.ll_address:
                Intent intent = new Intent(this, AddressActivity.class);
                intent.putExtra("action",1);
                startActivity(intent);
                break;
            default:
                break;
           }
       }
     /**
      *  确认立即购买所下的订单
      */
    private void OrderPay() {
        try {
            XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=done&delivery_id="+map.get(ps.getText().toString().trim())+"&all_account_money=0&content="
                    +editText.getText().toString().trim()+"&delivery_time="+deTimeId+"&shop_id="+new SharePerfenceUtils(this).getpeisong_id()+"" +
                    "&buynow_id="+jsonObject.getJSONArray("cart_list").getJSONObject(0).getString("deal_id")+"&buynow_attr="+order_attr
                    +"&buynow_number="+jsonObject.getJSONArray("cart_list").getJSONObject(0).getString("number")+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
                @Override
                public void onSuccess(String result) {
                    CartDone cartDone  = new Gson().fromJson(StringUtils.base64ToString(result),CartDone.class);
                    if (cartDone.getStatus()==1){
                        Intent intent = new Intent(OrderConfirmActivity.this,OrderPayActivity.class);
                        intent.putExtra("orderId",cartDone.getOrder_id());
                        intent.putExtra("type",1);
                        startActivity(intent);
                    }else{
                        Toast.makeText(OrderConfirmActivity.this,cartDone.getInfo(),Toast.LENGTH_SHORT).show();
                    }
                     super.onSuccess(result);
                      }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     *  确认从购物车所下的订单
     */
    private void CartOrderPay() {
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=done&delivery_id="+map.get(ps.getText().toString().trim())+"&all_account_money=0&content="
                +editText.getText().toString().trim()+"&delivery_time="+deTimeId+"&shop_id="+new SharePerfenceUtils(this).getpeisong_id()+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"" +
                "&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                CartDone cartDone  = new Gson().fromJson(StringUtils.base64ToString(result),CartDone.class);
                if (cartDone.getStatus()==1){
                    Intent intent = new Intent(OrderConfirmActivity.this,OrderPayActivity.class);
                    intent.putExtra("orderId",cartDone.getOrder_id());
                    intent.putExtra("type",1);
                    startActivity(intent);

                    Intent intent1 = new Intent();
                    intent1.setAction(BracastUtil.COUNT_REFRESH); // 说明动作
                    sendBroadcast(intent1);                       // 该函数用于发送广播

                }else{
                    Toast.makeText(OrderConfirmActivity.this,cartDone.getInfo(),Toast.LENGTH_SHORT).show();
                }
                super.onSuccess(result);
            }
        });
    }
    /**
     * 返回數據
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 0x01:
                   mendian.setText(data.getStringExtra("name"));
                   mendian.setTextColor(getResources().getColor(R.color.colorFour));
                   mendianId = data.getStringExtra("id");
                   //ToastUtils.show(OrderConfirmActivity.this,mendianId);
                   new SharePerfenceUtils(this).keeppeisong_id(mendianId);
                break;
        }
    }
    /**
     * 默認地址
      */
    public  void  GetDefadalute(){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=Module&email="+userList.get(0)+"&pwd="+userList.get(1)+"&shop_id="+new SharePerfenceUtils(this).getpeisong_id()+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=Module&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(OrderConfirmActivity.this).getShop_id()[0]+"");
                Address address = new Gson().fromJson(StringUtils.base64ToString(result),Address.class);
                if (address.getStatus()==1){
                    if (address.getConsignee_list().size()>0){
                        noaddress.setVisibility(View.GONE);
                        dingwei.setVisibility(View.VISIBLE);
                        right.setVisibility(View.VISIBLE);
                        for (int i= 0;i<address.getConsignee_list().size();i++){
                            if (address.getConsignee_list().get(i).getIs_default().equals("1")){
                                name.setText("收  件  人 : "+ address.getConsignee_list().get(i).getConsignee()+"     "+address.getConsignee_list().get(i).getMobile());
                                addressTV.setText("送酒地址 : "+ address.getConsignee_list().get(i).getRegion_lv1_name()+" "+address.getConsignee_list().get(i).getRegion_lv2_name()
                                        +" "+address.getConsignee_list().get(i).getRegion_lv3_name()+" "+address.getConsignee_list().get(i).getRegion_lv4_name()+address.getConsignee_list().get(i).getAddress()
                                );
                            }
                        }
                    }else {
                        dingwei.setVisibility(View.GONE);
                        right.setVisibility(View.GONE);
                        noaddress.setVisibility(View.VISIBLE);
                        noaddress.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(OrderConfirmActivity.this, AddressEditActivity.class);
                                intent.setAction("add");
                                startActivity(intent);

                            }
                        });
                    }
                }
                super.onSuccess(result);
            }
        });
    }
    private void showPopwindows() {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(
                    R.layout.goodlist_popwindows_view, null);
            popupWindow = new PopupWindow(contentView,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            popupWindow.setTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                }
            });
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
        popupWindow.showAsDropDown(selectTime);
    }
    public  void  getPayIfo(){
        try {
            XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=done&delivery_id="+map.get(ps.getText().toString().trim())+"&all_account_money=0&content="
                    +editText.getText().toString().trim()+"&delivery_time="+deTimeId+"&shop_id="+new SharePerfenceUtils(this).getpeisong_id()+"" +
                    "&buynow_id="+jsonObject.getJSONArray("cart_list").getJSONObject(0).getString("deal_id")+"&buynow_attr="+order_attr
                    +"&buynow_number="+jsonObject.getJSONArray("cart_list").getJSONObject(0).getString("number")+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
                @Override
                public void onSuccess(String result) {
                    CartDone cartDone  = new Gson().fromJson(StringUtils.base64ToString(result),CartDone.class);
                    if (cartDone.getStatus()==1){
                        YuePay(String.valueOf(cartDone.getOrder_id()));
                    }else{
                        Toast.makeText(OrderConfirmActivity.this,cartDone.getInfo(),Toast.LENGTH_SHORT).show();
                    }
                    super.onSuccess(result);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * 余额支付
     */
    public  void  YuePay(String orderId){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=payment&act=get_payment_code&order_id="+orderId+"&payment_id=17&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject obj = new JSONObject(StringUtils.base64ToString(result));
                    if (obj.getInt("status")==1){
                        Intent intent = new Intent(OrderConfirmActivity.this,PayResultActivity.class);
                        intent.putExtra("totle",order_totle_price.getText().toString());
                        startActivity(intent);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
        });
    }
    /**
     * 来自购物车的余额支付
     */
    private void CartOrderPayWithYue() {
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=done&delivery_id="+map.get(ps.getText().toString().trim())+"&all_account_money=0&content="
                +editText.getText().toString().trim()+"&delivery_time="+deTimeId+"&shop_id="+new SharePerfenceUtils(this).getpeisong_id()+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"" +
                "&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                CartDone cartDone  = new Gson().fromJson(StringUtils.base64ToString(result),CartDone.class);
                if (cartDone.getStatus()==1){
                    YuePay(String.valueOf(cartDone.getOrder_id()));
                    Intent intent1 = new Intent();
                    intent1.setAction(BracastUtil.COUNT_REFRESH); // 说明动作
                    sendBroadcast(intent1);// 该函数用于发送广播
                }else{
                    Toast.makeText(OrderConfirmActivity.this,cartDone.getInfo(),Toast.LENGTH_SHORT).show();
                }
                super.onSuccess(result);
            }
        });
    }
    /**
     * 显示popupWindow
     */
   /*  private void showPopwindow() {
        Map<Integer,Map<String,ArrayList<String>>> map = new HashMap<>();
        Map<String,ArrayList<String>> map1 = null;
        //加载弹出框的布局
        contentView = LayoutInflater.from(OrderConfirmActivity.this).inflate(
                R.layout.pop, null);
        //绑定控件
        ListView day = (ListView) contentView.findViewById(R.id.day);
        final ListView time = (ListView) contentView.findViewById(R.id.time);
        final TextView sureBtn  = (TextView) contentView.findViewById(R.id.sureBtn);
        TextView cancleBtn = (TextView)contentView.findViewById(R.id.cancle);
        list = new ArrayList<>();
        timeList = new ArrayList<>();
        timeList1 = new ArrayList<>();
        timeListID = new ArrayList<>();
        timeList1ID = new ArrayList<>();
        for (int i = 0;i<pfc.getDelivery_time().get(0).size();i++)    {
            timeList.add(pfc.getDelivery_time().get(0).get(i).get(0));
            timeListID.add(pfc.getDelivery_time().get(0).get(i).get(1));
        }
        for (int i = 0;i<pfc.getDelivery_time().get(1).size();i++)    {
            timeList1.add(pfc.getDelivery_time().get(1).get(i).get(0));
            timeList1ID.add(pfc.getDelivery_time().get(1).get(i).get(1));
        }
        for (int i = 0; i < kinds.length; i++) {
            list.add(kinds[i]);
        }
        //初始化适配器
        adapter  = new ArrayAdapter(OrderConfirmActivity.this,android.R.layout.simple_list_item_1,list);
        timeAdapter  = new ArrayAdapter(OrderConfirmActivity.this,android.R.layout.simple_list_item_1,timeList);
        //添加适配器
        day.setAdapter(adapter);
        time.setAdapter(timeAdapter);
        //默认
        timeAdapter  = new ArrayAdapter(OrderConfirmActivity.this,android.R.layout.simple_list_item_1,timeList);
        time.setAdapter(timeAdapter);
        time.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                sureBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        deTime = list.get(0)+" "+timeList.get(i);
                        deTimeId =  timeListID.get(i);
                        peisong.setText(deTime);
                        peisong.setTextColor(getResources().getColor(R.color.colorFour));
                    }
                });
            }
        });
       //gridview点击的监听
        day.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int postion, long l) {
               if (postion==0){
                   timeAdapter  = new ArrayAdapter(OrderConfirmActivity.this,android.R.layout.simple_list_item_1,timeList);
                   time.setAdapter(timeAdapter);
                   time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                           sureBtn.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   popupWindow.dismiss();
                                   deTime = list.get(postion)+" "+timeList.get(i);
                                   deTimeId =  timeListID.get(i);
                                   peisong.setText(deTime);
                                   peisong.setTextColor(getResources().getColor(R.color.colorFour));
                               }
                           });
                       }
                   });
               }else if (postion==1){
                   timeAdapter  = new ArrayAdapter(OrderConfirmActivity.this,android.R.layout.simple_list_item_1,timeList1);
                   time.setAdapter(timeAdapter);
                   time.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                       @Override
                       public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                           sureBtn.setOnClickListener(new View.OnClickListener() {
                               @Override
                               public void onClick(View view) {
                                   popupWindow.dismiss();
                                   deTime = list.get(postion)+" "+timeList1.get(i);
                                   deTimeId =  timeList1ID.get(i);
                                   peisong.setText(deTime);
                                   peisong.setTextColor(getResources().getColor(R.color.colorFour));
                               }
                           });
                       }
                   });
               }
            }
        });
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        //设置弹出框的宽度和高度
        popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);
        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.AnimBottom);
    }*/
    /**
     * 按钮的监听
     */
    public void openPopWindow(View v) {
        //从底部显示
        popupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }
}