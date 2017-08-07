package com.wzsykj.wuyaojiu.ui.good;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.TimeGood;
import com.wzsykj.wuyaojiu.adapter.TimeGoodRecyclerAdapter;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cundong.view.recycyler.EndlessRecyclerOnScrollListener;
import cundong.view.recycyler.HeaderAndFooterRecyclerViewAdapter;
import cundong.view.recycyler.RecyclerViewStateUtils;
import cundong.view.recycyler.RecyclerViewUtils;
import cundong.view.recycyler.weight.LoadingFooter;


/**
 * Created by chen on 16/8/10.
 */
public class TimeGoodActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private List<String> data = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;
    private TimeGoodRecyclerAdapter timeGoodRecyclerAdapter;
    private List<TimeGood.QianggouDealListBean> itemBeanList;
    private int page = 2;
    private static final int MESSAGE_FLAG = 1;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this); // 这句话必须要写要不会报错，不写虽然能关闭，会报一堆错
            ((Activity) context).finish();
        }
    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_FLAG:
                    timeGoodRecyclerAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH1);
        registerReceiver(this.broadcastReceiver, filter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.timegood_activity, null);
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
        itemBeanList = new ArrayList<>();
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //  page = 1;
                // loadData(page);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
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
        //loadData(page);
    }

    /* private void loadData(final int page) {
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 if (page >= 3) {
                     RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.TheEnd);
                 } else {
                     data = new ArrayList<>();
                     for (int i = 1; i < page * 10; i++) {
                         data.add("i=" + i);
                     }
                     RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Normal);
                 }
                 showData();
             }
         }, 2000);
     }*/
    private void showData() {

        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=miaosha&page=1&city_id=" + new SharePerfenceUtils(TimeGoodActivity.this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onFinished() {
                super.onFinished();
                materialishProgress.dismiss();
            }

            @Override
            public void onSuccess(String result) {
                try {
                    final JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    TimeGood timeGood = new Gson().fromJson(StringUtils.base64ToString(result), TimeGood.class);
                    for (int i = 0; i < timeGood.getQianggou_deal_list().size(); i++) {
                        itemBeanList.add(timeGood.getQianggou_deal_list().get(i));
                    }
                    if (headerAndFooterRecyclerViewAdapter == null) {

                        timeGoodRecyclerAdapter = new TimeGoodRecyclerAdapter(TimeGoodActivity.this, itemBeanList, String.valueOf(timeGood.getNowtime()), new TimeGoodRecyclerAdapter.OnRecyclerViewItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                try {
                                    Intent intent = new Intent(TimeGoodActivity.this, GoodInfoActivity.class);
                                    intent.putExtra("goodID", jsonObject.getJSONArray("qianggou_deal_list").getJSONObject(position).getString("id"));
                                    intent.putExtra("come", "other");

                                    startActivity(intent);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(timeGoodRecyclerAdapter);
                        recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
                        RecyclerViewUtils.setFooterView(recyclerView, new LoadingFooter(TimeGoodActivity.this));
                    } else {
                        timeGoodRecyclerAdapter.setData(itemBeanList);
                        headerAndFooterRecyclerViewAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
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
            default:
                break;
        }
    }

    /**
     * 加载更多
     *
     * @param pages
     */
    public void getMoreData(int pages) {
        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=miaosha&page=" + pages + "&shop_id=" + new SharePerfenceUtils(TimeGoodActivity.this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {

                TimeGood orderListInfo = new Gson().fromJson(StringUtils.base64ToString(result), TimeGood.class);
                if (orderListInfo.getQianggou_deal_list().size() > 1) {
                    for (int i = 0; i < orderListInfo.getQianggou_deal_list().size(); i++) {
                        itemBeanList.add(orderListInfo.getQianggou_deal_list().get(i));
                    }
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Loading);
                } else {
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.TheEnd);
                }
                super.onSuccess(result);
            }
        });
    }

}
