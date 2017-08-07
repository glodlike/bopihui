package com.wzsykj.wuyaojiu.ui.order;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.AddToCart;
import com.wzsykj.wuyaojiu.Bean.PayforCart;
import com.wzsykj.wuyaojiu.adapter.AddGoodTagRecyclerAdapter;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.login.LoginActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chen on 16/8/10.
 *
 * @param "type" ,0=添加到购物车  1=立即购买
 */
public class AddGoodActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private ImageView number_remove,number_add;
    private TextView barTitle;
    private String     reslut;
    private JSONObject goodInfo;
    private ScrollView view;
    private TextView unit_price; // 单价
    private TextView all_price;  //总价
    private TextView edit_number; //數量
    private ImageView img;
    private TextView title, price, info, number;
    private int goodsNum = 1;  // 購買數量
    private TextView order_price;
    private RecyclerView recyclerView;
    private TextView order_ok;
    private ArrayList<String> userList;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
             unregisterReceiver(this); // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.addgood_activity, null);
        setContentView(contentView);
        reslut  =  getIntent().getStringExtra("goodinfo");
        try {
            goodInfo = new JSONObject(StringUtils.base64ToString(reslut));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        //materialishProgress.show();
        initView();
    }

    private void initView() {

        userList =new SharePerfenceUtils(this).getUserInfo();

        order_price = (TextView) findViewById(R.id.order_price);
        try {
        order_price.setText("￥ "+goodInfo.getString("current_price"));
        number_remove = (ImageView) findViewById(R.id.number_remove);
        number_add = (ImageView) findViewById(R.id.number_add);
        number_add.setOnClickListener(this);
        number_remove.setOnClickListener(this);

        edit_number = (TextView) findViewById(R.id.edit_number);
        goodsNum   = Integer.parseInt(edit_number.getText().toString());

        all_price  = (TextView) findViewById(R.id.all_price);
        all_price.setText(String.valueOf(goodInfo.getString("current_price")));
        unit_price = (TextView) findViewById(R.id.unit_price);
        unit_price.setText(String.valueOf(goodInfo.getString("current_price")));

        title = (TextView) findViewById(R.id.title);
        price  = (TextView) findViewById(R.id.price);
        info   = (TextView) findViewById(R.id.info);
        number = (TextView) findViewById(R.id.number);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        barTitle = (TextView) findViewById(R.id.bar_title);

        view = (ScrollView) findViewById(R.id.view);
        view.setVisibility(View.INVISIBLE);

        img = (ImageView) findViewById(R.id.img);
        ImageLoaderUtils.LoadImage(goodInfo.getString("icon"),img, ImageLoaderUtils.getOptionsDefault());
        title.setText(goodInfo.getString("name"));
        price.setText(""+goodInfo.getString("current_price"));
        info.setText(goodInfo.getString("brief"));
        number.setText(goodInfo.getString("buy_count")+" 人已购买");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, getResources().getDrawable(R.drawable.divider_10dp_gray), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setNestedScrollingEnabled(false);
        order_ok = (TextView) findViewById(R.id.order_ok);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        loadData();
    }
    private void loadData() {
        if (getIntent().getExtras().getInt("type", 0) == 0) {
            barTitle.setText("加入购物车");
            order_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if  (new SharePerfenceUtils(AddGoodActivity.this).getUserInfo().get(0).equals("false") || new SharePerfenceUtils(AddGoodActivity.this).getUserInfo().get(1).equals("false")) {
                        Intent intent = new Intent(AddGoodActivity.this, LoginActivity.class);
                        startActivity(intent);
                        ToastUtils.show(AddGoodActivity.this,"请先登录");
                    }else {
                        addToCart("");
                    }
                }
            });
        } else {
            barTitle.setText("立即购买");
            order_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if  (new SharePerfenceUtils(AddGoodActivity.this).getUserInfo().get(0).equals("false") || new SharePerfenceUtils(AddGoodActivity.this).getUserInfo().get(1).equals("false")) {

                            Intent intent = new Intent(AddGoodActivity.this, LoginActivity.class);
                            startActivity(intent);
                            ToastUtils.show(AddGoodActivity.this,"请先登录");
                        }else {
                           faseBuy("");
                    }
                }
            });
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showData();
            }
        }, 1000);
    }
    private void showData() {
        view.setVisibility(View.VISIBLE);
        List<String> data = new ArrayList<>();
        List<String> idlist = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();
        Map<String,List<String>> Idmap = new HashMap<>();
        Map<String,List<String>> priceMap = new HashMap<>();
        List<String> itemList  ;
        List<String> itemListId;
        List<String>  priceList;
        try {
            for (int i = 0;i<goodInfo.getJSONArray("deal_attr").length();i++){
                data.add(goodInfo.getJSONArray("deal_attr").getJSONObject(i).getString("name"));
                itemList = new ArrayList<>();
                itemListId  = new ArrayList<>();
                priceList  = new ArrayList<>();
               for (int j= 0;j<goodInfo.getJSONArray("deal_attr").getJSONObject(i).getJSONArray("attr_list").length();j++){
                   itemList.add(goodInfo.getJSONArray("deal_attr").getJSONObject(i).getJSONArray("attr_list").getJSONObject(i).getString("name"));
                   itemListId.add(goodInfo.getJSONArray("deal_attr").getJSONObject(i).getJSONArray("attr_list").getJSONObject(i).getString("id"));
                   priceList.add(goodInfo.getJSONArray("deal_attr").getJSONObject(i).getJSONArray("attr_list").getJSONObject(i).getString("price"));
                   priceMap.put(String.valueOf(i),priceList);
                   map.put(String.valueOf(i),itemList);
                   Idmap.put(String.valueOf(i),itemListId);
               }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /*for(int i = 0 ;i<goodInfo.getDeal_attr().size();i++){
                data.add(goodInfo.getName());
                itemList = new ArrayList<>();
                itemListId  = new ArrayList<>();
                priceList  = new ArrayList<>();
            for (int j= 0;j<goodInfo.getDeal_attr().get(i).getAttr_list().size();j++){
                itemList.add(goodInfo.getDeal_attr().get(i).getAttr_list().get(j).getName());
                itemListId.add(String.valueOf(goodInfo.getDeal_attr().get(i).getAttr_list().get(j).getId()));
                priceList.add(goodInfo.getDeal_attr().get(i).getAttr_list().get(j).getPrice());
                priceMap.put(String.valueOf(i),priceList);
                map.put(String.valueOf(i),itemList);
                Idmap.put(String.valueOf(i),itemListId);
            }
        }*/
        try {
            recyclerView.setAdapter(new AddGoodTagRecyclerAdapter(AddGoodActivity.this, data,idlist,map,Idmap,priceMap,new AddGoodTagRecyclerAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            },order_ok,order_price,all_price,unit_price,goodInfo.getString("id"),goodInfo.getDouble("current_price"),edit_number,getIntent().getExtras().getInt("type", 0)));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.number_add:
                edit_number.setText(String.valueOf(Integer.parseInt(edit_number.getText().toString())+1));
                all_price.setText(String.valueOf(Integer.parseInt(edit_number.getText().toString())*Double.valueOf(unit_price.getText().toString())));
                order_price.setText("合计 ￥"+String.valueOf(Integer.parseInt(edit_number.getText().toString())*Double.valueOf(unit_price.getText().toString())));
                //Toast.makeText(AddGoodActivity.this,"￥"+Integer.parseInt(edit_number.getText().toString())*goodInfo.getCurrent_price(),Toast.LENGTH_SHORT).show();
                //all_price.setText("￥"+(Integer.parseInt(unit_price.getText().toString())*Integer.parseInt(edit_number.getText().toString())));
                break;
            case  R.id.number_remove:
                if (Integer.parseInt(edit_number.getText().toString())>1){
                    edit_number.setText(String.valueOf(Integer.parseInt(edit_number.getText().toString())-1));
                    all_price.setText(String.valueOf(Integer.parseInt(edit_number.getText().toString())*Double.valueOf(unit_price.getText().toString())));
                    order_price.setText("合计 ￥"+String.valueOf(Integer.parseInt(edit_number.getText().toString())*Double.valueOf(unit_price.getText().toString())));
                }else{
                    Toast.makeText(AddGoodActivity.this,"亲，多少买一瓶吧",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
    /**
     * 添加到购物车
     * @param order
     */
    public  void  addToCart(final String order){
        try {
            XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=addcart&id="+goodInfo.getString("id")+"&deal_attr="+order+"&number="+edit_number.getText().toString()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
                @Override
                public void onSuccess(String result) {
                    AddToCart addToCart = new Gson().fromJson(StringUtils.base64ToString(result),AddToCart.class);
                    if (addToCart.getStatus()==1){
                        ToastUtils.show(AddGoodActivity.this,"成功添加到购物车");
                        Intent intent = new Intent();
                        intent.setAction(BracastUtil.COUNT_REFRESH); // 说明动作
                        sendBroadcast(intent);// 该函数用于发送广播
                        finish();
                    }else{
                        if (addToCart.getInfo().equals("请先登录")){
                            Intent intent = new Intent(AddGoodActivity.this, LoginActivity.class);
                            startActivity(intent);
                            ToastUtils.show(AddGoodActivity.this,addToCart.getInfo());
                        }else {
                            ToastUtils.show(AddGoodActivity.this,addToCart.getInfo());
                        }
                    }
                    super.onSuccess(result);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    /**
     * 立即购买
     * @param order
     */
    public  void  faseBuy(final String order){
        try {
            XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=buynow&buynow_id="+goodInfo.getString("id")+"&buynow_attr="+order+"&buynow_number="+edit_number.getText().toString()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
                @Override
                public void onSuccess(String result) {
                    //PayforCart payforCart = new Gson().fromJson(StringUtils.base64ToString(result),PayforCart.class);
                    try {
                        System.out.println(""+AppHttp.BASE_URL_NEW+"ctl=cart&act=buynow&buynow_id="+goodInfo.getString("id")+"&buynow_attr="+order+"&buynow_number="+edit_number.getText().toString()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(AddGoodActivity.this).getShop_id()[0]+"");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                        if (jsonObject.getInt("status") == 1) {
                            Intent toOC = new Intent(AddGoodActivity.this, OrderConfirmActivity.class);
                            toOC.putExtra("order", order);
                            toOC.putExtra("type", 1);
                            toOC.putExtra("payInfo", result);
                            //toOC.putExtra("ob",payforCart);
                            startActivity(toOC);
                        } else {
                           if (jsonObject.getString("info").equals("请先登录")){
                                Intent intent = new Intent(AddGoodActivity.this, LoginActivity.class);
                                startActivity(intent);
                                ToastUtils.show(AddGoodActivity.this,jsonObject.getString("info"));
                             }else{
                                ToastUtils.show(AddGoodActivity.this,jsonObject.getString("info"));
                                }
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH);
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }
}
