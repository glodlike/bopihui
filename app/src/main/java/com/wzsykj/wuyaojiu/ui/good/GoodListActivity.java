package com.wzsykj.wuyaojiu.ui.good;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Good;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.GoodsListRecyclerAdapter;
import com.wzsykj.wuyaojiu.adapter.PopListAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.DensityUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.BadgeView;
import com.wzsykj.wuyaojiu.widget.GridDividerItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cundong.view.recycyler.EndlessRecyclerOnScrollListener;
import cundong.view.recycyler.HeaderAndFooterRecyclerViewAdapter;
import cundong.view.recycyler.HeaderSpanSizeLookup;
import cundong.view.recycyler.RecyclerViewStateUtils;
import cundong.view.recycyler.RecyclerViewUtils;
import cundong.view.recycyler.weight.LoadingFooter;

/**
 * Created by chen on 16/8/10.
 */
public class GoodListActivity extends BaseActivity implements View.OnClickListener {
    private int page = 2;
    private RecyclerView recyclerView;
    private HeaderAndFooterRecyclerViewAdapter headerAndFooterRecyclerViewAdapter;
    private GoodsListRecyclerAdapter goodListRecyclerAdapter;
    private Button sureBtn, resetBtn; //確定和重置按鈕
    private Boolean AscFlag = true;
    private ArrayList<String> count = new ArrayList<>();
    private ArrayList<String> old_price = new ArrayList<>();
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> priceList = new ArrayList<>();
    private ArrayList<String> imageList = new ArrayList<>();
    private Map<String, ArrayList<String>> map = new HashMap<>();
    private ArrayList<String> typeList = new ArrayList<>();
    private ArrayList<String> goodsIdList = new ArrayList<>();    //商品ID
    private ArrayList<String> itemList;
    private TextView AscTextView;
    private Good goods;
    private ImageButton back, car;
    private BadgeView carBadgeView;
    private LinearLayout radioGroup;
    private LinearLayout shaixuan;
    private ImageView shaixuanImg;
    private PopupWindow popupWindow;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView AscImage;
    private ListView shaixuanListView;
    private String cate_id;  //分类ID
    private String Findtype;
    private ArrayList<String> idList = new ArrayList<>();
    private TextView newGoodsRbtn, buyCountRbnt;
    private String shaixunStr = "";
    private String loadType = "default";
    private static final int MESSAGE_FLAG = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_FLAG:
                    goodListRecyclerAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
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
        View contentView = this.getLayoutInflater().inflate(R.layout.goodlist_activity, null);
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
        //Toast.makeText(this,getIntent().getStringExtra("id"), Toast.LENGTH_LONG).show();
        cate_id = getIntent().getStringExtra("id");
        Findtype = getIntent().getStringExtra("type");
        GetGoodsByType(cate_id, "default");
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH1);
        registerReceiver(this.broadcastReceiver, filter); // 注册
    }

    private void initView() {
        AscImage = (ImageView) findViewById(R.id.asc_img);
        AscTextView = (TextView) findViewById(R.id.priceText);
        AscTextView.setOnClickListener(this);
        radioGroup = (LinearLayout) findViewById(R.id.radio_group);
        newGoodsRbtn = (TextView) findViewById(R.id.newgoodRBtn);
        newGoodsRbtn.setOnClickListener(this);
        buyCountRbnt = (TextView) findViewById(R.id.renqiRBtn);
        buyCountRbnt.setOnClickListener(this);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        car = (ImageButton) findViewById(R.id.car);
        car.setOnClickListener(this);
        carBadgeView = new BadgeView(this);
        carBadgeView.setTargetView(car);
        carBadgeView.setTextColor(getResources().getColor(R.color.black));
        carBadgeView.setBackground(10, getResources().getColor(R.color.white));

        shaixuan = (LinearLayout) findViewById(R.id.shaixuan);
        shaixuan.setOnClickListener(this);
        shaixuan.setTag(false);
        shaixuanImg = (ImageView) findViewById(R.id.shaixuan_img);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 2;
                showData();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new GridDividerItemDecoration(DensityUtil.dp2px(this, 5), 2, false));
        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadNextPage(View view) {
                super.onLoadNextPage(view);
                super.onLoadNextPage(view);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LoadMoreData(loadType, cate_id, shaixunStr, page++);
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
        // loadData(page);
    }

    private void showData() {
        swipeRefreshLayout.setRefreshing(false);
        GetGoodsByType(cate_id, loadType);
    }

    private void showPopwindows() {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(
                    R.layout.goodlist_popwindows_view, null);
            popupWindow = new PopupWindow(contentView,
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
            popupWindow.setTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setOutsideTouchable(true);
            shaixuanListView = (ListView) contentView.findViewById(R.id.popListView);
            sureBtn = (Button) contentView.findViewById(R.id.sureBtn);
            resetBtn = (Button) contentView.findViewById(R.id.resetBtn);
            //AppHttp.BASE_URL + "index.php?ctl=goods&" + Findtype + "=" + cate_id + "&f=" + shaixunStr + "&shop_id="+new SharePerfenceUtils(this).getShop_id()[0]+""
            XUtils.Get(AppHttp.BASE_URL + "index.php?ctl=goods&order_type=" + loadType + "&" + Findtype + "=" + cate_id + "&f=" + shaixunStr + "&page=1&city_id=" + new SharePerfenceUtils(this).getShop_id()[0] + "", null, new MyCallBack<String>() {
                @Override
                public void onSuccess(String result) {
                    // AppHttp.BASE_URL + "index.php?ctl=goods&order_type=" + type + "&" + Findtype + "=" + id + "&f=" + shaixunStr + "&page=1&shop_id="+new SharePerfenceUtils(this).getShop_id()[0]+""
                    goods = new Gson().fromJson(StringUtils.base64ToString(result), Good.class);
                    for (int i = 0; i < goods.getFilter_group().size(); i++) {
                        idList.add(goods.getFilter_group().get(i).getId());
                        typeList.add(goods.getFilter_group().get(i).getName());
                        itemList = new ArrayList<>();
                        for (int j = 0; j < goods.getFilter_group().get(i).getFilter_list().size(); j++) {
                            itemList.add(goods.getFilter_group().get(i).getFilter_list().get(j).getName());
                        }
                        map.put(String.valueOf(i), itemList);
                    }
                    PopListAdapter adapter = new PopListAdapter(typeList, map, GoodListActivity.this);
                    shaixuanListView.setAdapter(adapter);
                    super.onSuccess(result);
                }
            });
            //確定按鈕的監聽事件
            sureBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                    if (idList.size() == 1) {
                        shaixunStr = "{\"" + idList.get(0) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken() + "\"}";
                    } else if (idList.size() == 2) {
                        shaixunStr = "{\"" + idList.get(0) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken() + "\",\"" + idList.get(1) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken1() + "\"}";
                    } else if (idList.size() == 3) {
                        shaixunStr = "{\"" + idList.get(0) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken() + "\",\"" + idList.get(1) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken1() + "\",\"" + idList.get(2) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken2() + "\"}";
                    } else if (idList.size() == 4) {
                        shaixunStr = "{\"" + idList.get(0) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken() + "\",\"" + idList.get(1) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken1() + "\",\"" + idList.get(2) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken2() + "\",\"" + idList.get(3) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken3() + "\"}";
                    } else if (idList.size() == 5) {
                        shaixunStr = "{\"" + idList.get(0) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken() + "\",\"" + idList.get(1) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken1() + "\",\"" + idList.get(2) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken2() + "\",\"" + idList.get(3) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken3() + "\",\"" + idList.get(4) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken4() + "\"}";
                    } else if (idList.size() == 6) {
                        shaixunStr = "{\"" + idList.get(0) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken() + "\",\"" + idList.get(1) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken1() + "\",\"" + idList.get(2) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken2() + "\",\"" + idList.get(3) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken3() + "\",\"" + idList.get(4) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken4() + "\",\"" + idList.get(5) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken5() + "\"}";
                    } else if (idList.size() == 7) {
                        shaixunStr = "{\"" + idList.get(0) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken() + "\",\"" + idList.get(1) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken1() + "\",\"" + idList.get(2) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken2() + "\",\"" + idList.get(3) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken3() + "\",\"" + idList.get(4) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken4() + "\",\"" + idList.get(5) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken5() + "\",\"" + idList.get(6) + "\":\"" + new SharePerfenceUtils(GoodListActivity.this).getToken6() + "\"}";
                    }
                    FindGoodsByType(cate_id, shaixunStr);
                    cleanSharePerfence();
                }
            });
            resetBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    popupWindow.dismiss();
                    cleanSharePerfence();
                }
            });
            popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {

                    shaixuanImg.setImageResource(R.mipmap.bottom_black_icon);
                    shaixuan.setTag(false);
                }
            });
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }
        popupWindow.showAsDropDown(shaixuan);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.car:
                break;
            case R.id.shaixuan:
                if ((boolean) shaixuan.getTag()) {
                    shaixuanImg.setImageResource(R.mipmap.bottom_black_icon);
                    shaixuan.setTag(false);
                    popupWindow.dismiss();
                } else {
                    shaixuanImg.setImageResource(R.mipmap.top_black_icon);
                    shaixuan.setTag(true);
                    showPopwindows();
                }
                break;
            case R.id.priceText:
                newGoodsRbtn.setTextColor(Color.parseColor("#363636"));
                buyCountRbnt.setTextColor(Color.parseColor("#363636"));
                AscTextView.setTextColor(Color.parseColor("#EF5350"));
                if (AscFlag) {
                    AscImage.setImageResource(R.mipmap.top_1);
                    GetGoodsByType(cate_id, "price_asc");//
                    loadType = "price_asc";
                    page = 2;
                    AscFlag = false;
                } else {
                    AscImage.setImageResource(R.mipmap.bottom);
                    GetGoodsByType(cate_id, "price_desc");//
                    loadType = "price_desc";
                    page = 2;
                    AscFlag = true;
                }
                break;
            case R.id.renqiRBtn:
                AscTextView.setTextColor(Color.parseColor("#363636"));
                buyCountRbnt.setTextColor(Color.parseColor("#EF5350"));
                newGoodsRbtn.setTextColor(Color.parseColor("#363636"));
                GetGoodsByType(cate_id, "buy_count");
                loadType = "buy_count";
                page = 2;
                break;
            case R.id.newgoodRBtn:
                AscTextView.setTextColor(Color.parseColor("#363636"));
                newGoodsRbtn.setTextColor(Color.parseColor("#EF5350"));
                buyCountRbnt.setTextColor(Color.parseColor("#363636"));
                GetGoodsByType(cate_id, "newest");
                loadType = "newest";
                page = 2;
                break;
            default:
                break;
        }
    }

    public void GetGoodsByType(final String id, final String type) {
        priceList.clear();
        data.clear();
        imageList.clear();
        goodsIdList.clear();
        XUtils.Get(AppHttp.BASE_URL + "index.php?ctl=goods&order_type=" + type + "&" + Findtype + "=" + id + "&f=" + shaixunStr + "&page=1&city_id=" + new SharePerfenceUtils(this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                //System.out.println(AppHttp.BASE_URL + "index.php?ctl=goods&order_type=" + type + "&" + Findtype + "=" + id + "&f=" + shaixunStr + "&page=1&shop_id="+new SharePerfenceUtils(GoodListActivity.this).getShop_id()[0]+"");
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    JSONArray goodsList = jsonObject.getJSONArray("item");
                    for (int i = 0; i < goodsList.length(); i++) {
                        count.add(goodsList.getJSONObject(i).getString("buy_count"));
                        goodsIdList.add(goodsList.getJSONObject(i).getString("id"));
                        data.add(goodsList.getJSONObject(i).getString("name"));
                        priceList.add(goodsList.getJSONObject(i).getString("current_price"));
                        imageList.add(goodsList.getJSONObject(i).getString("image"));
                        old_price.add(goodsList.getJSONObject(i).getString("origin_price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (headerAndFooterRecyclerViewAdapter == null) {
                    goodListRecyclerAdapter = new GoodsListRecyclerAdapter(GoodListActivity.this, data, priceList, imageList, old_price, count, new GoodsListRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(GoodListActivity.this, GoodInfoActivity.class);
                            intent.putExtra("goodID", goodsIdList.get(position));
                            intent.putExtra("come", "other");
                            startActivity(intent);
                        }
                    });
                    headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(goodListRecyclerAdapter);
                    recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
                    GridLayoutManager manager = new GridLayoutManager(GoodListActivity.this, 2);
                    recyclerView.setLayoutManager(manager);
                    manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) recyclerView.getAdapter(), manager.getSpanCount()));
                    RecyclerViewUtils.setFooterView(recyclerView, new LoadingFooter(GoodListActivity.this));
                } else {
                    goodListRecyclerAdapter.setData(data, priceList, imageList, old_price, count);
                    headerAndFooterRecyclerViewAdapter.notifyDataSetChanged();
                }
                super.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
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

    public void FindGoodsByType(final String type, final String f) {
        materialishProgress.show();
        priceList.clear();
        data.clear();
        imageList.clear();
        goodsIdList.clear();
        XUtils.Get(AppHttp.BASE_URL + "index.php?ctl=goods&" + Findtype + "=" + type + "&f=" + f + "&city_id=" + new SharePerfenceUtils(this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    JSONArray goodsList = jsonObject.getJSONArray("item");
                    for (int i = 0; i < goodsList.length(); i++) {
                        goodsIdList.add(goodsList.getJSONObject(i).getString("id"));
                        data.add(goodsList.getJSONObject(i).getString("name"));
                        priceList.add(goodsList.getJSONObject(i).getString("current_price"));
                        imageList.add(goodsList.getJSONObject(i).getString("image"));
                        old_price.add(goodsList.getJSONObject(i).getString("origin_price"));
                        count.add(goodsList.getJSONObject(i).getString("buy_count"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (headerAndFooterRecyclerViewAdapter == null) {
                    goodListRecyclerAdapter = new GoodsListRecyclerAdapter(GoodListActivity.this, data, priceList, imageList, old_price, count, new GoodsListRecyclerAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            Intent intent = new Intent(GoodListActivity.this, GoodInfoActivity.class);
                            intent.putExtra("goodID", goodsIdList.get(position));
                            intent.putExtra("come", "other");
                            startActivity(intent);
                        }
                    });
                    headerAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(goodListRecyclerAdapter);
                    recyclerView.setAdapter(headerAndFooterRecyclerViewAdapter);
                    GridLayoutManager manager = new GridLayoutManager(GoodListActivity.this, 2);
                    recyclerView.setLayoutManager(manager);
                    manager.setSpanSizeLookup(new HeaderSpanSizeLookup((HeaderAndFooterRecyclerViewAdapter) recyclerView.getAdapter(), manager.getSpanCount()));
                    RecyclerViewUtils.setFooterView(recyclerView, new LoadingFooter(GoodListActivity.this));
                } else {
                    goodListRecyclerAdapter.setData(data, priceList, imageList, old_price, count);
                    headerAndFooterRecyclerViewAdapter.notifyDataSetChanged();
                }
                super.onSuccess(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
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

    public void cleanSharePerfence() {
        new SharePerfenceUtils(GoodListActivity.this).keepToken("");
        new SharePerfenceUtils(GoodListActivity.this).keepToken1("");
        new SharePerfenceUtils(GoodListActivity.this).keepToken2("");
        new SharePerfenceUtils(GoodListActivity.this).keepToken3("");
        new SharePerfenceUtils(GoodListActivity.this).keepToken4("");
        new SharePerfenceUtils(GoodListActivity.this).keepToken5("");
        new SharePerfenceUtils(GoodListActivity.this).keepToken6("");
    }

    /**
     * 加载更多
     *
     * @param type
     * @param f
     */
    public void LoadMoreData(final String fenlei, final String type, final String f, final int page) {
        //XUtils.Get(AppHttp.BASE_URL + "index.php?ctl=goods&order_type=" + type + "&" + Findtype + "=" + id + "&f=" + shaixunStr + "&page=1&shop_id="+new SharePerfenceUtils(this).getShop_id()[0]+"", null, new MyCallBack<String>() {
        XUtils.Get(AppHttp.BASE_URL + "index.php?ctl=goods&order_type=" + fenlei + "&" + Findtype + "=" + type + "&f=" + f + "&page=" + page + "&city_id=" + new SharePerfenceUtils(this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    // System.out.println(AppHttp.BASE_URL + "index.php?ctl=goods&order_type="+fenlei+"&" + Findtype + "=" + type + "&f=" + f + "&page="+page+"&city_id="+new SharePerfenceUtils(GoodListActivity.this).getShop_id()[0]+"");
                    JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    JSONArray goodsList = jsonObject.getJSONArray("item");
                    if (goodsList.length() > 1) {
                        for (int i = 0; i < goodsList.length(); i++) {
                            goodsIdList.add(goodsList.getJSONObject(i).getString("id"));
                            data.add(goodsList.getJSONObject(i).getString("name"));
                            priceList.add(goodsList.getJSONObject(i).getString("current_price"));
                            imageList.add(goodsList.getJSONObject(i).getString("image"));
                            old_price.add(goodsList.getJSONObject(i).getString("origin_price"));
                            count.add(goodsList.getJSONObject(i).getString("buy_count"));
                        }
                        RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.Loading);
                    } else {
                        RecyclerViewStateUtils.setFooterViewState(recyclerView, LoadingFooter.State.TheEnd);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
        });
    }
}
//http://www.bphapp.com/bopihui/cxb_api/index.php?ctl=goods&order_type=default&cate_id=2&f=&page=1&shop_id=1
//http://www.bphapp.com/bopihui/cxb_api/index.php?ctl=goods&order_type=default&cate_id=2&f=&page=2&shop_id=1
//http://www.bphapp.com/bopihui/cxb_api/index.php?ctl=goods&order_type=default&cate_id=2&f=&page=1&shop_id=1
//http://www.bphapp.com/bopihui/cxb_api/index.php?ctl=goods&order_type=buy_count&cate_id=2&f=&page=1&shop_id=1
//http://www.bphapp.com/bopihui/cxb_api/index.php?ctl=goods&order_type=buy_count&cate_id=2&f=&page=1&shop_id=1
//http://www.bphapp.com/bopihui/cxb_api/index.php?ctl=goods&order_type=newest   &cate_id=2&f=&page=1&shop_id=1

