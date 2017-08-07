package com.wzsykj.wuyaojiu.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.CollectList;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.ui.good.GoodInfoActivity;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.GridDividerItemDecoration;
import com.wzsykj.wuyaojiu.adapter.GoodListRecyclerAdapter;
import com.wzsykj.wuyaojiu.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import cundong.view.recycyler.EndlessRecyclerOnScrollListener;
import cundong.view.recycyler.HeaderAndFooterRecyclerViewAdapter;
import cundong.view.recycyler.HeaderSpanSizeLookup;
import cundong.view.recycyler.RecyclerViewStateUtils;
import cundong.view.recycyler.RecyclerViewUtils;
import cundong.view.recycyler.weight.LoadingFooter;
/**
 * Created by chen on 16/8/10.
 */
public class CollectionActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private ArrayList<String> userList;
    private int page = 2;
    private List<CollectList.GoodsListBean> listBeen ;

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;
    private GoodListRecyclerAdapter goodListRecyclerAdapter;


    private static final int MESSAGE_FLAG = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case MESSAGE_FLAG:
                    goodListRecyclerAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.collection_activity, null);
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

        userList   =new SharePerfenceUtils(this).getUserInfo();

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              loadData();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new GridDividerItemDecoration(DensityUtil.dp2px(this, 5), 2, false));
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {

            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GetCollectList(page++);
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
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_collect&email="+userList.get(0)+"&pwd="+userList.get(1)+"&page="+1+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                listBeen = new ArrayList<>();
                CollectList collectList =new Gson().fromJson(StringUtils.base64ToString(result),CollectList.class);
                for (int i = 0;i< collectList.getGoods_list().size();i++){
                    listBeen.add(collectList.getGoods_list().get(i));
                }
                    goodListRecyclerAdapter = new GoodListRecyclerAdapter(CollectionActivity.this, listBeen, new GoodListRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(CollectionActivity.this, GoodInfoActivity.class);
                            intent.putExtra("goodID",listBeen.get(position).getId());
                            intent.putExtra("come","other");
                            startActivity(intent);
                        }
                    });
                    headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(goodListRecyclerAdapter);
                    recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
                    GridLayoutManager manager = new GridLayoutManager(CollectionActivity.this, 2);
                    recyclerView.setLayoutManager(manager);
                    manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) recyclerView.getAdapter(), manager.getSpanCount()));
                    RecyclerViewUtils.setFooterView(recyclerView, new LoadingFooter(CollectionActivity.this));
                    super.onSuccess(result);
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
            default:
                break;
        }
    }
   public  void  GetCollectList(int page){
       XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_collect&email="+userList.get(0)+"&pwd="+userList.get(1)+"&page="+page+"",null,new MyCallBack<String>(){
           @Override
           public void onSuccess(String result) {
               CollectList collectList =new Gson().fromJson(StringUtils.base64ToString(result),CollectList.class);
               if (collectList.getGoods_list().size()>1){
               for (int i = 0;i< collectList.getGoods_list().size();i++){
                    listBeen.add(collectList.getGoods_list().get(i));
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
