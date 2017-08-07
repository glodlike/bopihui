package com.wzsykj.wuyaojiu.ui.tab;


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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.CartInfo;
import com.wzsykj.wuyaojiu.Bean.PayforCart;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.CarRecyclerAdapter;
import com.wzsykj.wuyaojiu.base.BaseFragment;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.good.GoodListActivity;
import com.wzsykj.wuyaojiu.ui.order.OrderConfirmActivity;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
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
 * Created by chen on 16/6/7.
 */
public class CarFragment extends BaseFragment implements View.OnClickListener {
    private TextView goGoodList;
    private TextView edit;
    private boolean isEdit = false;
    private ArrayList<String> userList;
    private int page = 2;
    private List <CartInfo.CartListBean > listBeen;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;
    private CarRecyclerAdapter carRecyclerAdapter;
    private TextView all_price;
    private LinearLayout ll_jiesuan,ll_car;
    private TextView pay;
    private static final int MESSAGE_FLAG = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_FLAG:
                    carRecyclerAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
    protected View getContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.main_car_fragment, null);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
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

        goGoodList = (TextView) contentView.findViewById(R.id.goGoodList);
        goGoodList.setOnClickListener(this);

        ll_jiesuan = (LinearLayout) contentView.findViewById(R.id.ll_jiesuan);
        ll_car     = (LinearLayout) contentView.findViewById(R.id.ll_car);

        userList =new SharePerfenceUtils(getContext()).getUserInfo();
        edit = (TextView) contentView.findViewById(R.id.edit);
        edit.setOnClickListener(this);
        swipeRefreshLayout = (SwipeRefreshLayout) contentView.findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();

            }
        });
        all_price = (TextView) contentView.findViewById(R.id.all_price);
        recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), getResources().getDrawable(R.drawable.divider_10dp_gray), DividerItemDecoration.VERTICAL_LIST));
        pay = (TextView) contentView.findViewById(R.id.pay);
        pay.setOnClickListener(this);
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //getMoreData(page++);
                        GetCarList(page++);
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
    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
        showData();
    }
    private void showData() {
        /*Intent intent = new Intent();
        intent.setAction(BracastUtil.COUNT_REFRESH); // 说明动作
        getActivity().sendBroadcast(intent);// 该函数用于发送广播*/
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=index&email="+new SharePerfenceUtils(getContext()).getUserInfo().get(0)+"&pwd="+new SharePerfenceUtils(getContext()).getUserInfo().get(1)+"&city_id="+new SharePerfenceUtils(getContext()).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                //System.out.println(AppHttp.BASE_URL_NEW+"ctl=cart&act=index&email="+userList.get(0)+"&pwd="+userList.get(1)+"&shop_id="+new SharePerfenceUtils(getContext()).getShop_id()[0]+"");
                listBeen = new ArrayList<>();
                //System.out.println(new SharePerfenceUtils(getContext()).getUserInfo().get(0)+"--------"+ new SharePerfenceUtils(getContext()).getUserInfo().get(1));
                if (new SharePerfenceUtils(getContext()).getUserInfo().get(0).equals("false") || new SharePerfenceUtils(getContext()).getUserInfo().get(1).equals("false")) {
                    recyclerView.setVisibility(View.GONE);
                    ll_jiesuan.setVisibility(View.GONE);
                    ll_car.setVisibility(View.VISIBLE);
                }else {
                    recyclerView.setVisibility(View.VISIBLE);
                    ll_jiesuan.setVisibility(View.VISIBLE);
                }
                CartInfo cartInfo = new Gson().fromJson(StringUtils.base64ToString(result),CartInfo.class);
                for (int i = 0;i<cartInfo.getCart_list().size();i++){
                    listBeen.add(cartInfo.getCart_list().get(i));
                }
                if (listBeen.size()>0){
                    ll_jiesuan.setVisibility(View.VISIBLE);
                    ll_car.setVisibility(View.GONE);
                    edit.setVisibility(View.VISIBLE);
                }else {
                    ll_jiesuan.setVisibility(View.GONE);
                    edit.setVisibility(View.GONE);
                }
                carRecyclerAdapter = new CarRecyclerAdapter(getActivity(), listBeen,cartInfo,all_price, ll_jiesuan,ll_car,edit,new CarRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                             /*Intent intent = new Intent(getContext(), GoodInfoActivity.class);
                             intent.putExtra("goodID",listBeen.get(position).getId());
                             startActivity(intent);*/
                    }
                });
                headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(carRecyclerAdapter);
                recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
                RecyclerViewUtils.setFooterView(recyclerView, new LoadingFooter(getActivity()));
                all_price.setText(String.valueOf(cartInfo.getTotal_data().getTotal_price()));
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                materialishProgress.dismiss();
                super.onFinished();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:
                if (carRecyclerAdapter != null) {
                    if (isEdit) {
                        carRecyclerAdapter.setEdit(false);
                        carRecyclerAdapter.notifyDataSetChanged();
                        isEdit = false;
                        edit.setText("编辑");
                        showData();
                    } else {
                        carRecyclerAdapter.setEdit(true);
                        carRecyclerAdapter.notifyDataSetChanged();
                        isEdit = true;
                        edit.setText("保存");
                    }
                }
                break;
            case R.id.pay:
                //toLeftStartActivity(new Intent(getActivity(), OrderPayActivity.class));
                CartPay();
                break;
            case R.id.goGoodList:
                Intent intent = new Intent(getContext(),GoodListActivity.class);
                intent.putExtra("id","1");
                intent.putExtra("type","cate_id");
                startActivity(intent);
                break;
            default:

                break;
        }
    }
    public  void  GetCarList(int page){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=index&email="+new SharePerfenceUtils(getContext()).getUserInfo().get(0)+"&pwd="+new SharePerfenceUtils(getContext()).getUserInfo().get(1)+"page="+page+"&city_id="+new SharePerfenceUtils(getContext()).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                CartInfo cartInfo = new Gson().fromJson(StringUtils.base64ToString(result),CartInfo.class);
                if (cartInfo.getCart_list().size()<1){
                    for (int i = 0;i<cartInfo.getCart_list().size();i++){
                        listBeen.add(cartInfo.getCart_list().get(i));
                    }
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Loading);
                }else{
                    RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.TheEnd);
                }
                super.onSuccess(result);
            }
        });
    }
    public  void  CartPay(){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=check&email="+new SharePerfenceUtils(getContext()).getUserInfo().get(0)+"&pwd="+new SharePerfenceUtils(getContext()).getUserInfo().get(1)+"&city_id="+new SharePerfenceUtils(getContext()).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    if (jsonObject.getInt("status")==1){
                        Intent intent = new Intent(getContext(), OrderConfirmActivity.class);
                        intent.putExtra("payInfo",result);
                        intent.putExtra("type",0);
                        startActivity(intent);
                        listBeen.clear();
                        carRecyclerAdapter.notifyDataSetChanged();
                        ll_jiesuan.setVisibility(View.GONE);
                        ll_car.setVisibility(View.VISIBLE);
                    }else {
                        ll_jiesuan.setVisibility(View.VISIBLE);
                        ll_car.setVisibility(View.GONE);
                        ToastUtils.show(getContext(),jsonObject.getString("info"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {

                super.onFinished();
            }
        });
    }
}