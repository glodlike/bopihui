package com.wzsykj.wuyaojiu.ui.tab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Shop;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.orther.MainActivity;
import com.wzsykj.wuyaojiu.ui.good.GoodInfoActivity;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.MainRecyclerAdapter;
import com.wzsykj.wuyaojiu.base.BaseFragment;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cundong.view.recycyler.RecyclerViewStateUtils;
import cundong.view.recycyler.weight.LoadingFooter;

/**
 * Created by chen on 16/6/7.
 */
public class MainFragment extends BaseFragment {
    private SwipeRefreshLayout swipeLayout;
    private RecyclerView recyclerView;
    private MainRecyclerAdapter mainRecyclerAdapter;
    private ArrayList<String> address;
    private MainActivity activity;
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.main_home_fragment, null);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
//                materialishProgress.show();
//                loadData();
            }
        });
        materialishProgress.show();
        activity = (MainActivity) getActivity();
        initView(contentView);
        address = new SharePerfenceUtils(getContext()).getUserAddress();
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=city&act=index&xpoint="+address.get(1)+"&ypoint="+address.get(0)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                 System.out.println("=============="+AppHttp.BASE_URL_NEW+"ctl=city&act=index&xpoint="+address.get(1)+"&ypoint="+address.get(0)+"");
                 Shop shop = new Gson().fromJson(StringUtils.base64ToString(result),Shop.class);
                 new SharePerfenceUtils(getContext()).Keepshop_id(shop.getShop_list().get(0).getId(),shop.getShop_list().get(0).getName());
                 super.onSuccess(result);
            }
        });
        return contentView;
    }
    private void initView(View contentView) {

        activity.mViewPager.setCurrentItem(getActivity().getIntent().getIntExtra("index",0));
        swipeLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe_layout);
        swipeLayout.setProgressBackgroundColor(R.color.white);
        swipeLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));
        loadData();
    }
    @Override
    public void onResume() {

        if (new SharePerfenceUtils(getContext()).getIsEdit()){
            materialishProgress.show();
            loadData();
            new SharePerfenceUtils(getContext()).KeepisEdit(false);
        }
        super.onResume();
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
        ///**/
        swipeLayout.setRefreshing(false);
        XUtils.Get(AppHttp.BASE_URL+"index.php?ctl=index&city_id="+new SharePerfenceUtils(getContext()).get_city_id()+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                 System.out.println("==============="+AppHttp.BASE_URL+"index.php?ctl=index&city_id="+new SharePerfenceUtils(getContext()).get_city_id()+"");
                 try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Normal);
                    if (mainRecyclerAdapter == null) {
                        mainRecyclerAdapter = new MainRecyclerAdapter(getActivity(), jsonObject, new MainRecyclerAdapter.OnRecyclerViewItemClickListener() {
                            @Override
                            public void onItemClick(int position) {
                                toLeftStartActivity(new Intent(getActivity(), GoodInfoActivity.class));
                            }
                            @Override
                            public void onItemItemClick(int position, int subPosition) {
                            }
                            @Override
                            public void toIntent(Intent intent) {
                                toLeftStartActivity(intent);
                            }
                        });
                        recyclerView.setAdapter(mainRecyclerAdapter);
                    } else {
                        mainRecyclerAdapter.setData(jsonObject);
                        mainRecyclerAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                 materialishProgress.dismiss();
                super.onFinished();
            }
        });
    }


}