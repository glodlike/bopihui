package com.wzsykj.wuyaojiu.ui.good;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.google.gson.Gson;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.wzsykj.wuyaojiu.Bean.Collect;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.openutil.UMUtil;
import com.wzsykj.wuyaojiu.ui.login.LoginActivity;
import com.wzsykj.wuyaojiu.ui.orther.MainActivity;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.GoodInfo;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.GoodInfoPJRecyclerAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.ui.order.AddGoodActivity;
import com.wzsykj.wuyaojiu.utils.DensityUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iwgang.view.countdownview.CountdownView;
import zhy.view.flowlayout.FlowLayout;
import zhy.view.flowlayout.TagAdapter;
import zhy.view.flowlayout.TagFlowLayout;

/**
 * Created by chen on 16/8/19.
 */
public class GoodInfoActivity extends BaseActivity implements View.OnClickListener {
    private ArrayList<String> userList;
    private ImageButton back, share;
    private LinearLayout view, ll_daojishi;
    private SliderLayout sliderLayout;
    private PagerIndicator pagerIndicator;
    private CountdownView time;
    private TagFlowLayout tagFlowLayout;
    private TagFlowLayout pjFlowLayout;
    private RecyclerView pjRecyclerView;
    private TextView all_pj;
    private TextView current_price, old_price;
    private RecyclerView mdRecyclerView;
    private WebView webview;
    private TextView buy_count;
    private TextView goodsName, daojishi;
    private TextView guige;
    private GoodInfo goodInfo;
    private JSONObject jsonObject;
    private LinearLayout ll_collect;
    private ImageView collectImg, Car;
    private TextView addCar, addOrder;
    private String type = "";
    private LinearLayout ll_all, ll_tuodong, ll_allpj;
    private TextView zuwu, koubei, kucunTV;
    private View line;
    private String webHead = "<html>\n" +
            "<head>\n" +
            "<meta content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;\" name=\"viewport\">\n" +
            "<style>img{max-width: 100%;}</style>\n" +
            "</head>\n" +
            "<body>\n";
    private String footStr = "</body>\n" +
            "</html>\n";
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            unregisterReceiver(this);
            ((Activity) context).finish();
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        // 在当前的activity中注册广播
      /*  IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH);
        registerReceiver(this.broadcastReceiver, filter); // 注册*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.goodinfo_activity, null);
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
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(false);
        // User settings
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放


        webSettings.setLoadWithOverviewMode(true);

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }

    public void close() {
        Intent intent = new Intent();
        //intent.setAction(BracastUtil.ACTION_FINNISH);
        intent.setAction(BracastUtil.ACTION_FINNISH1); // 说明动作
        sendBroadcast(intent);// 该函数用于发送广播
    }

    private void initView() {
        kucunTV = (TextView) findViewById(R.id.kucun);
        line = findViewById(R.id.line);
        ll_allpj = (LinearLayout) findViewById(R.id.ll_allpj);
        zuwu = (TextView) findViewById(R.id.zanwu);
        koubei = (TextView) findViewById(R.id.koupei);
        ll_all = (LinearLayout) findViewById(R.id.ll_all);
        ll_tuodong = (LinearLayout) findViewById(R.id.tuodong);
        ll_tuodong.setVisibility(View.GONE);
        ll_all.setVisibility(View.GONE);
        type = getIntent().getStringExtra("come");
        //ToastUtils.show(GoodInfoActivity.this,getIntent().getStringExtra("goodID"));
        Car = (ImageView) findViewById(R.id.car);
        Car.setOnClickListener(this);
        daojishi = (TextView) findViewById(R.id.daojishi);
        ll_daojishi = (LinearLayout) findViewById(R.id.ll_daojishi);
        collectImg = (ImageView) findViewById(R.id.collectImg);
        userList = new SharePerfenceUtils(this).getUserInfo();
        ll_collect = (LinearLayout) findViewById(R.id.collect);
        ll_collect.setOnClickListener(this);
        guige = (TextView) findViewById(R.id.guige);
        goodsName = (TextView) findViewById(R.id.goodName);
        buy_count = (TextView) findViewById(R.id.buy_count);
        current_price = (TextView) findViewById(R.id.current_price);
        old_price = (TextView) findViewById(R.id.old_price);
        old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        share = (ImageButton) findViewById(R.id.share);
        share.setOnClickListener(this);
        view = (LinearLayout) findViewById(R.id.view);
        view.setVisibility(View.INVISIBLE);
        sliderLayout = (SliderLayout) findViewById(R.id.slider_view);
        pagerIndicator = (PagerIndicator) findViewById(R.id.custom_indicator_view);
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderLayout.setCustomIndicator(pagerIndicator);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.stopAutoCycle();
        FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (DensityUtil.getWindowsWidth(GoodInfoActivity.this)));
        sliderLayout.setLayoutParams(p);

        time = (CountdownView) findViewById(R.id.time);

        tagFlowLayout = (TagFlowLayout) findViewById(R.id.tag_flowlayout);

        pjRecyclerView = (RecyclerView) findViewById(R.id.pj_recyclerview);
        pjRecyclerView.setLayoutManager(new LinearLayoutManager(GoodInfoActivity.this, LinearLayoutManager.VERTICAL, false));
        pjRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        pjRecyclerView.setNestedScrollingEnabled(false);
        all_pj = (TextView) findViewById(R.id.all_pj);
        all_pj.setOnClickListener(this);

        mdRecyclerView = (RecyclerView) findViewById(R.id.md_recyclerview);
        mdRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mdRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mdRecyclerView.setNestedScrollingEnabled(false);

        webview = (WebView) findViewById(R.id.webview);
        webview.setInitialScale(100);

        addCar = (TextView) findViewById(R.id.add_car);
        addCar.setOnClickListener(this);
        addOrder = (TextView) findViewById(R.id.add_order);
        addOrder.setOnClickListener(this);
        laodData();
    }

    private void laodData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showData();
            }
        }, 1000);
    }

    private void showData() {
        view.setVisibility(View.VISIBLE);
        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=deal&id=" + getIntent().getStringExtra("goodID") + "&city_id=" + new SharePerfenceUtils(this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            HashMap<String, String> url_maps = new HashMap<>();
            List<String> tagList = new ArrayList<>();
            List<String> data = new ArrayList<>();         // 評分
            List<String> headUrl = new ArrayList<>();      // 頭像
            List<String> phone = new ArrayList<>();        // 電話
            List<String> content = new ArrayList<>();      // 評論
            List<String> adressList = new ArrayList<>();   // 店铺地址
            List<String> lianxiList = new ArrayList<>();   // 客服电话

            @Override
            public void onSuccess(final String result) {
                try {
                    System.out.println(AppHttp.BASE_URL_NEW + "ctl=deal&id=" + getIntent().getStringExtra("goodID") + "&city_id=" + new SharePerfenceUtils(GoodInfoActivity.this).getShop_id()[0] + "");
                    jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    if (jsonObject.getInt("status") == 1) {
                        for (int i = 0; i < jsonObject.getJSONArray("images").length(); i++) {
                            url_maps.put(String.valueOf(i), jsonObject.getJSONArray("images").getString(i));
                        }
                        //輪播
                        sliderLayout.removeAllSliders();
                        for (String name : url_maps.keySet()) {
                            DefaultSliderView defaultSliderView = new DefaultSliderView(GoodInfoActivity.this);
                            // initialize a SliderLayout
                            defaultSliderView
                                    .image(url_maps.get(name))
                                    .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                        @Override
                                        public void onSliderClick(BaseSliderView slider) {
                                        }
                                    });
                            defaultSliderView.bundle(new Bundle());
                            defaultSliderView.getBundle()
                                    .putString("extra", name);
                            sliderLayout.addSlider(defaultSliderView);
                        }
                        //倒计时
                        if ((Long.parseLong(jsonObject.getString("end_time")) - Long.parseLong(String.valueOf(jsonObject.getString("now_time")))) > 0) {
                            time.start((Long.parseLong(jsonObject.getString("end_time")) - Long.parseLong(String.valueOf(jsonObject.getString("now_time")))) * 1000); // Millisecond
                            daojishi.setText("距离结束 :");

                        } else if ((Long.parseLong(jsonObject.getString("end_time")) - Long.parseLong(String.valueOf(jsonObject.getString("now_time")))) < 0) {
                            ll_daojishi.setVisibility(View.GONE);
                        } else {
                            time.setVisibility(View.GONE);
                            daojishi.setText("活动已经结束");
                        }
                        if ((Long.parseLong(jsonObject.getString("begin_time")) - Long.parseLong(String.valueOf(jsonObject.getString("now_time")))) > 0) {
                            time.start((Long.parseLong(jsonObject.getString("begin_time")) - Long.parseLong(String.valueOf(jsonObject.getString("now_time")))) * 1000); // Millisecond
                            daojishi.setText("距离开始 :");
                        }
                        current_price.setText(jsonObject.getString("current_price"));
                        old_price.setText("￥" + jsonObject.getString("origin_price"));
                        buy_count.setText(jsonObject.getString("buy_count") + " 人已购买");
                        kucunTV.setText("库存 :" + jsonObject.getString("max_bought") + " 件");
                        guige.setText(jsonObject.getString("brief"));
                        goodsName.setText(jsonObject.getString("name"));
                        //标签
                        if (jsonObject.getJSONArray("deal_tags").length() < 1) {
                            line.setVisibility(View.GONE);
                        }
                        for (int i = 0; i < jsonObject.getJSONArray("deal_tags").length(); i++) {
                            tagList.add(jsonObject.getJSONArray("deal_tags").getJSONObject(i).getString("v"));
                        }
                        tagFlowLayout.setAdapter(new TagAdapter<String>(tagList) {
                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                View view = LayoutInflater.from(GoodInfoActivity.this).inflate(R.layout.goodingo_tag_flowlayout_tag_item,
                                        tagFlowLayout, false);
                                TextView tv = (TextView) view.findViewById(R.id.title);
                                tv.setText(s);
                                return view;
                            }
                        });
                        //详情
                        webview.loadDataWithBaseURL(null, webHead + jsonObject.getString("description") + footStr, "text/html", "utf-8", null);
                        //评价
                        if (jsonObject.getJSONArray("dp_list").length() < 1) {
                            all_pj.setVisibility(View.GONE);
                            koubei.setVisibility(View.GONE);
                            ll_allpj.setVisibility(View.GONE);
                            zuwu.setVisibility(View.VISIBLE);
                        }
                        for (int i = 0; i < jsonObject.getJSONArray("dp_list").length(); i++) {
                            data.add(jsonObject.getJSONArray("dp_list").getJSONObject(i).getString("point"));
                            headUrl.add(jsonObject.getJSONArray("dp_list").getJSONObject(i).getString("user_avatar"));
                            phone.add(jsonObject.getJSONArray("dp_list").getJSONObject(i).getString("user_name"));
                            content.add(jsonObject.getJSONArray("dp_list").getJSONObject(i).getString("content"));
                        }
                        if (jsonObject.getInt("is_collect") == 0) {
                            collectImg.setImageResource(R.mipmap.wodeshoucang_icon);
                        } else if (jsonObject.getInt("is_collect") == 1) {
                            collectImg.setImageResource(R.drawable.staron);
                        }
                        pjRecyclerView.setAdapter(new GoodInfoPJRecyclerAdapter(GoodInfoActivity.this, data, headUrl, phone, content, new GoodInfoPJRecyclerAdapter.OnRecyclerViewItemClickListener() {
                            @Override
                            public void onItemClick(int position) {

                            }
                        }));
                        /**
                         * 添加到購物車
                         */
                        addCar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent addCarIntent = new Intent(GoodInfoActivity.this, AddGoodActivity.class);
                                addCarIntent.putExtra("goodinfo", result);
                                addCarIntent.putExtra("type", 0);
                                toLeftStartActivity(addCarIntent);
                            }
                        });
                        addOrder.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent addOrderIntent = new Intent(GoodInfoActivity.this, AddGoodActivity.class);
                                addOrderIntent.putExtra("type", 1);
                                addOrderIntent.putExtra("goodinfo", result);
                                toLeftStartActivity(addOrderIntent);
                            }
                        });
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinished() {
                try {
                    materialishProgress.dismiss();
                    if (jsonObject.getInt("status") == 0) {
                        ToastUtils.show(GoodInfoActivity.this, jsonObject.getString("info"));
                    } else {
                        ll_all.setVisibility(View.VISIBLE);
                        ll_tuodong.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onFinished();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                if (type.equals("welcome")) {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                finish();
                break;
            case R.id.share:
                ShareGoods();
                break;

            case R.id.all_pj:
                Intent intent = new Intent(GoodInfoActivity.this, EvaluateActivity.class);
                try {
                    intent.putExtra("goodId", jsonObject.getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                toLeftStartActivity(intent);
                break;
            case R.id.car:
                close();
                finish();
                break;
            case R.id.add_car:
                /*Intent addCarIntent = new Intent(GoodInfoActivity.this, AddGoodActivity.class);
                //addCarIntent.putExtra("good",goodInfo);
                addCarIntent.putExtra("type", 0);
                toLeftStartActivity(addCarIntent);*/
                break;
            case R.id.add_order:
               /* Intent addOrderIntent = new Intent(GoodInfoActivity.this, AddGoodActivity.class);
                addOrderIntent.putExtra("type", 1);
                toLeftStartActivity(addOrderIntent);*/
                break;
            case R.id.collect:
                try {
                    ll_collect(jsonObject.getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 分享
     */
    public void ShareGoods() {
        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=deal&id=" + getIntent().getStringExtra("goodID") + "&city_id=" + new SharePerfenceUtils(this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                goodInfo = new Gson().fromJson(StringUtils.base64ToString(result), GoodInfo.class);
                try {
                    jsonObject = new JSONObject(StringUtils.base64ToString(result));
                    UMUtil.shareUM(GoodInfoActivity.this, jsonObject.getString("name"), jsonObject.getString("brief"), jsonObject.getString("share_url"),
                            new UMImage(GoodInfoActivity.this, jsonObject.getString("icon")), new UMShareListener() {
                                @Override
                                public void onResult(SHARE_MEDIA share_media) {


                                }

                                @Override
                                public void onError(SHARE_MEDIA share_media, Throwable throwable) {


                                }

                                @Override
                                public void onCancel(SHARE_MEDIA share_media) {

                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                super.onSuccess(result);
            }
        });
    }

    /**
     * 收藏商品
     */
    public void ll_collect(final String id) {
        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=deal&act=add_collect&id=" + id + "&email=" + userList.get(0) + "pwd=" + userList.get(1) + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Collect collect = new Gson().fromJson(StringUtils.base64ToString(result), Collect.class);
                if (collect.getStatus() == 1) {
                    if (collect.getIs_collect() == 1) {
                        collectImg.setImageResource(R.drawable.staron);
                    } else if (collect.getIs_collect() == 0) {
                        collectImg.setImageResource(R.mipmap.wodeshoucang_icon);
                    }
                    ToastUtils.show(GoodInfoActivity.this, collect.getInfo());
                } else {
                    Intent intent = new Intent(GoodInfoActivity.this, LoginActivity.class);
                    startActivity(intent);
                    ToastUtils.show(GoodInfoActivity.this, collect.getInfo());
                }
                super.onSuccess(result);
            }

            @Override
            public void onFinished() {

                super.onFinished();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && type.equals("welcome")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
