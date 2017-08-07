package com.wzsykj.wuyaojiu.ui.user;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.OrderListInfo;
import com.wzsykj.wuyaojiu.adapter.OrderListRecyclerAdapter;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.order.OrderDetailActivity;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseFragment;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;

import java.util.ArrayList;
import java.util.List;

import cundong.view.recycyler.EndlessRecyclerOnScrollListener;
import cundong.view.recycyler.HeaderAndFooterRecyclerViewAdapter;
import cundong.view.recycyler.RecyclerViewStateUtils;
import cundong.view.recycyler.RecyclerViewUtils;
import cundong.view.recycyler.weight.LoadingFooter;

/**
 * Created by chen on 16/6/7.
 */
public class FalliyFragment extends BaseFragment {
    private List<OrderListInfo.ItemBean> itemBeanList;
    private int page = 2;
    private SwipeRefreshLayout swipeLayout;
    private RecyclerView recyclerView;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;
    private OrderListRecyclerAdapter orderListRecyclerAdapter;
    private ArrayList<String> userList;

    private static final int MESSAGE_FLAG = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_FLAG:
                    orderListRecyclerAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
    public static AllOrderFragment newInstance(Bundle args) {
        AllOrderFragment f = new AllOrderFragment();
        f.setArguments(args);
        return f;
    }
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.order_type_fragment, null);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //              hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        materialishProgress.show();
        initView(contentView);
        return contentView;
    }
    @Override
    public void onResume() {
        showData();
        super.onResume();
    }
    private void initView(View contentView) {
        userList = new SharePerfenceUtils(getContext()).getUserInfo();
        swipeLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe_layout);
        swipeLayout.setProgressBackgroundColor(R.color.white);
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderListRecyclerAdapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        });
        recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getMoreData(page++);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.sendEmptyMessage(MESSAGE_FLAG);
                    }
                }).start();
            }
        });
        showData();
    }
    private void showData() {
        swipeLayout.setRefreshing(false);
        getAllOrder();
    }
    public  void  getAllOrder( ){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&status=4&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(getContext()).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                itemBeanList = new ArrayList<>();
                OrderListInfo orderListInfo = new Gson().fromJson(StringUtils.base64ToString(result),OrderListInfo.class);
                for(int i=0;i<orderListInfo.getItem().size();i++){
                    itemBeanList.add(orderListInfo.getItem().get(i));
                }
                orderListRecyclerAdapter = new OrderListRecyclerAdapter(getActivity(), itemBeanList, new OrderListRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent toOC = new Intent(getActivity(), OrderDetailActivity.class);
                        toOC.putExtra("orderId", itemBeanList.get(position).getId());
                        toOC.putExtra("orderItem",itemBeanList.get(position));
                        toLeftStartActivity(toOC);
                    }
                });
                headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(orderListRecyclerAdapter);
                recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
                RecyclerViewUtils.setFooterView(recyclerView, new LoadingFooter(getActivity()));
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                materialishProgress.dismiss();
                super.onFinished();
            }
        });
    }
    /**
     * 加载更多
     * @param pages
     */
    public  void  getMoreData(int pages){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_order&status=4&page="+pages+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&shop_id="+new SharePerfenceUtils(getContext()).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                OrderListInfo orderListInfo = new Gson().fromJson(StringUtils.base64ToString(result),OrderListInfo.class);
                if(orderListInfo.getItem().size()>1){
                    for(int i=0;i<orderListInfo.getItem().size();i++){
                        itemBeanList.add(orderListInfo.getItem().get(i));
                    }
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Loading);
                }else{
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.TheEnd);
                }
                super.onSuccess(result);
            }
        });
    }
}