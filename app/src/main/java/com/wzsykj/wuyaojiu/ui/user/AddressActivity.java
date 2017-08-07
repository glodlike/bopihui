package com.wzsykj.wuyaojiu.ui.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Address;
import com.wzsykj.wuyaojiu.adapter.AddressRecyclerAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chen on 16/8/10.
 */
public class AddressActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private TextView title, add;
    private ArrayList<String> userList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    //private AddressRecyclerAdapter addressRecyclerAdapter;
    private List<Address.ConsigneeListBean> listBeen;  //地址列表
    private TextView add_address;
    private static int ACTION;
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            showData();
        }
    };
    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.address_activity, null);
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
        ACTION   = getIntent().getIntExtra("action",0);
        listBeen = new ArrayList<>();
        userList =new  SharePerfenceUtils(this).getUserInfo();

        add_address = (TextView) findViewById(R.id.add);
        add_address.setOnClickListener(this);

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        title = (TextView) findViewById(R.id.title);
        add = (TextView) findViewById(R.id.add);
        add.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               showData();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
    }
    @Override
    public void onResume() {
        //loadData();
        showData();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FRESH);
        registerReceiver(this.broadcastReceiver,filter); // 注册
        super.onResume();
    }
    private void showData() {
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=module&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=module&email="+userList.get(0)+"&pwd="+userList.get(1)+"");
                final Address address = new Gson().fromJson(StringUtils.base64ToString(result),Address.class);
                for (int i = 0;i<address.getConsignee_list().size();i++){
                    listBeen = address.getConsignee_list();
                }
                AddressRecyclerAdapter addressRecyclerAdapter = new AddressRecyclerAdapter(AddressActivity.this, listBeen, new AddressRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                             if (ACTION==1){
                                 setDaflute(address.getConsignee_list().get(position));
                             }
                        }
                        @Override
                        public void toIntent(Intent intent) {
                            toLeftStartActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(addressRecyclerAdapter);
                    super.onSuccess(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(AddressActivity.this,ex.toString(),Toast.LENGTH_SHORT).show();
                super.onError(ex, isOnCallback);
            }
            @Override
            public void onFinished() {
                materialishProgress.dismiss();
                swipeRefreshLayout.setRefreshing(false);
                super.onFinished();
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
            case R.id.add:
                Intent intent = new Intent(this, AddressEditActivity.class);
                intent.setAction("add");
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public  void  setDaflute( Address.ConsigneeListBean bean ){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=set_default&id="+bean.getId()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                finish();
                super.onFinished();
            }
        });




    }


}
