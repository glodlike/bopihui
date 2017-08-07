package com.wzsykj.wuyaojiu.ui.user;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.OrderListInfo;
import com.wzsykj.wuyaojiu.adapter.PJOrderListGoodRecyclerAdapter;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Dianping;
import com.wzsykj.wuyaojiu.Bean.Refund;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;
import com.wzsykj.wuyaojiu.widget.RatingBarView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PingjiaActivity extends BaseActivity {
    private TextView Submit;
    private RecyclerView recyclerView;
    private OrderListInfo.ItemBean itemBean;
    private Refund refund;
    private PJOrderListGoodRecyclerAdapter adapter;
    private EditText content;
    private ArrayList<String> userList;
    private  String ids="";
    private Map<Integer,Boolean> map;
    private  String contentStr;
    private  String points;
    private RatingBarView ratingBar;
    private ImageButton back;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this);
            ((Activity) context).finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pingjia);
        InitData();
        InitView();
       /* Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PingjiaActivity.this,contentStr,Toast.LENGTH_SHORT).show();
                SunbitPJ(ids,"\""+content.getText().toString().trim()+"\"","\""+String.valueOf(ratingBar.getStarCount())+"\"");
            }
        });*/
    }
    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH_RE);
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }
    private void InitView() {
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ratingBar   = (RatingBarView) findViewById(R.id.custom_ratingbar);
        content      = (EditText) findViewById(R.id.content);
        Submit       = (TextView) findViewById(R.id.submit);
        recyclerView = (RecyclerView) findViewById(R.id.good_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setNestedScrollingEnabled(false);
        adapter = new PJOrderListGoodRecyclerAdapter(PingjiaActivity.this,refund ,itemBean.getDeal_order_item(),map,Submit,content,new PJOrderListGoodRecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        recyclerView.setAdapter(adapter);

    }
    private void InitData() {
        map = new HashMap<>();

        itemBean = (OrderListInfo.ItemBean) getIntent().getSerializableExtra("itemData");
        refund   = (Refund) getIntent().getSerializableExtra("refund");
        userList = new  SharePerfenceUtils(this).getUserInfo();

        for (int i=0;i<itemBean.getDeal_order_item().size();i++){
            map.put(i,false);
        }

        for (int i=0;i<refund.getItems().size();i++){
            if (i==refund.getItems().size()-1){
                ids = ids+"\""+refund.getItems().get(i).getId()+"\"";
            }else{
                ids = ids+"\""+refund.getItems().get(i).getId()+"\""+",";
            }
        }
    }
    /**
     * 评论
     */
    public  void  SunbitPJ(final String itemid, final String contentStr, final String points){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=dp&act=add_order_dp&email="+userList.get(0)+"&pwd="+userList.get(1)+"&ids=["+itemid+"]&contents=["+StringUtils.StringToGBK(contentStr)+"]&points=["+points+"]&shop_id="+new SharePerfenceUtils(this).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=add_dp&act=add_order_dp&email="+userList.get(0)+"&pwd="+userList.get(1)+"&ids=["+itemid+"]&contents=["+contentStr+"]&points=["+points+"]");
                Dianping dianping = new Gson().fromJson(StringUtils.base64ToString(result),Dianping.class);
                if (dianping.getStatus()==1){
                    finish();
                }else{
                    Toast.makeText(PingjiaActivity.this,dianping.getInfo(),Toast.LENGTH_SHORT).show();
                }
                super.onSuccess(result);
            }
        });
    }
}
