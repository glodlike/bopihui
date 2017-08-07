package com.wzsykj.wuyaojiu.ui.user;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.wzsykj.wuyaojiu.adapter.OrderListViewPageAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.BracastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chen on 16/8/10.
 */
public class OrderListActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;

    private SmartTabLayout tabLayout;
    public ViewPager viewPager;
    private String[] tabList = new String[]{"全部", "待付款", "待配送", "待评价", "售后"};
    private List<Fragment> viewList;
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
        View contentView = this.getLayoutInflater().inflate(R.layout.orderlist_activity, null);
        setContentView(contentView);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        initView();
    }
    @Override
    public void onResume() {
        super.onResume();
        // 在当前的activity中注册广播
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH);
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }

    private void initView() {
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);

        tabLayout = (SmartTabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

             viewList = new ArrayList<>();

        //for (int i = 0; i <= tabList.length; i++) {
        //Bundle bundle = new Bundle();
        //bundle.putInt("type", i);

        viewList.add(new AllOrderFragment());
             viewList.add(new UnpayOrderFragment());
             viewList.add(new UnDeliverOrderFragment());
             viewList.add(new UnPingjiaOrderFragment());
             viewList.add(new FalliyFragment());
        //}
        OrderListViewPageAdapter adapter = new OrderListViewPageAdapter(getSupportFragmentManager(), tabList, viewList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(tabList.length);
        tabLayout.setViewPager(viewPager);
        loadData();
    }

    private void loadData() {


        showData();
    }

    private void showData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            default:
                break;
        }
    }
}
