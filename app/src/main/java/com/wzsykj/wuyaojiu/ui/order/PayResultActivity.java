package com.wzsykj.wuyaojiu.ui.order;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.ui.user.OrderListActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;

/**
 * Created by chen on 16/8/10.
 */
public class PayResultActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private TextView payResult;
    private TextView finishBtn;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this);     // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.payresult_activity, null);
        setContentView(contentView);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//           hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        materialishProgress.show();
        initView();
        }
    private void initView() {
        finishBtn = (TextView) findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);
        payResult = (TextView) findViewById(R.id.payResult);
        payResult.setText(getIntent().getStringExtra("totle"));
        back = (ImageButton) findViewById(R.id.back);
        loadData();
    }
    private void loadData() {
        showData();
    }
    private void showData() {
//        swipeLayout.setRefreshing(false);
        materialishProgress.dismiss();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                  //toRightFinish();
                finish();
                break;
            case  R.id.finishBtn:
                   close();
                   Intent intent = new Intent(this, OrderListActivity.class);
                   startActivity(intent);
                   finish();
                 break;
            default:
                break;
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

    public void close() {
        Intent intent = new Intent();
        intent.setAction(BracastUtil.ACTION_FINNISH); // 说明动作
        sendBroadcast(intent);// 该函数用于发送广播
    }
}
