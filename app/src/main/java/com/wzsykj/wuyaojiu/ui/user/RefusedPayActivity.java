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

import com.wzsykj.wuyaojiu.Bean.OrderListInfo;
import com.wzsykj.wuyaojiu.adapter.RefundOrderListGoodRecyclerAdapter;
import com.wzsykj.wuyaojiu.Bean.Refund;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

public class RefusedPayActivity extends BaseActivity implements View.OnClickListener {
    private TextView Submit;
    private RecyclerView recyclerView;
    private OrderListInfo.ItemBean itemBean;
    private Refund refund;
    private RefundOrderListGoodRecyclerAdapter adapter;
    private Map<Integer,Boolean> map;
    private EditText content;
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
        setContentView(R.layout.activity_refused_pay);
        InitData();
        InitView();
        InitClick();
    }
    private void InitClick() {
        Submit.setOnClickListener(this);
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
        back.setOnClickListener(this);
        content      = (EditText) findViewById(R.id.content);
        Submit       = (TextView) findViewById(R.id.submit);

        recyclerView = (RecyclerView) findViewById(R.id.good_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setNestedScrollingEnabled(false);

        adapter = new RefundOrderListGoodRecyclerAdapter(RefusedPayActivity.this,refund ,itemBean.getDeal_order_item(), map,Submit,content,new RefundOrderListGoodRecyclerAdapter.OnRecyclerViewItemClickListener() {
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
        for (int i=0;i<itemBean.getDeal_order_item().size();i++){
            map.put(i,false);
        }
    }
    @Override
    public void onClick(View view) {
          switch (view.getId()){
              case R.id.back:
                  finish();
                  break;
          }
     }
}
