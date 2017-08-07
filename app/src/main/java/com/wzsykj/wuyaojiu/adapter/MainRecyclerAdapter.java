package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.wzsykj.wuyaojiu.ui.good.GoodInfoActivity;
import com.wzsykj.wuyaojiu.ui.good.GoodListActivity;
import com.wzsykj.wuyaojiu.ui.good.SelectActivity;
import com.wzsykj.wuyaojiu.ui.good.TimeGoodActivity;
import com.wzsykj.wuyaojiu.ui.good.WebActivity;
import com.wzsykj.wuyaojiu.ui.orther.CityListActivity;
import com.wzsykj.wuyaojiu.utils.DensityUtil;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.widget.GridDividerItemDecoration;
import com.wzsykj.wuyaojiu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iwgang.view.countdownview.CountdownView;

/**
 * Created by cheng on 16/12/16.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private JSONObject mianInfo;
    private OnRecyclerViewItemClickListener listener;
    private  HashMap<String, String> url_maps;
    private ArrayList<String>  url_img ;
    private Boolean flag = true;       // 用于判断是否第一次
    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);

        void onItemItemClick(int position, int subPosition);

        void toIntent(Intent intent);
    }
    public JSONObject  getData() {
        return mianInfo;
    }
    public void setData(JSONObject mianInfo) {
        this.mianInfo = mianInfo;
    }
    public MainRecyclerAdapter(Context context,JSONObject mianInfo, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.mianInfo = mianInfo;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }
    /**
     * 决定元素的布局使用哪种类型
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    /**
     * 渲染具体的ViewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = null;
        switch (viewType) {
            case 0:
                contentView = mLayoutInflater.inflate(R.layout.main_recycler_lunbo_item, parent, false);
                return new LunBoViewHolder(contentView);
            case 1:
                contentView = mLayoutInflater.inflate(R.layout.main_recycler_nav_item, parent, false);
                return new NavViewHolder(contentView);
            case 2:
                contentView = mLayoutInflater.inflate(R.layout.main_recycler_time_item, parent, false);
                return new TimeViewHolder(contentView);

            case 3:
                contentView = mLayoutInflater.inflate(R.layout.main_lunbo_recycler_item, parent, false);
                return new LunBo1ViewHolder(contentView);

            case 4:
                contentView = mLayoutInflater.inflate(R.layout.main_recycler_mai_item, parent, false);
                return new MaiViewHolder(contentView);

            case 5:
                contentView = mLayoutInflater.inflate(R.layout.main_recycler_webadv_item, parent, false);
                return new WebAdvViewHolder(contentView);

            case 6:
                contentView = mLayoutInflater.inflate(R.layout.main_recycler_rec_item, parent, false);
                return new RecViewHolder(contentView);

            case 7:
                contentView = mLayoutInflater.inflate(R.layout.main_lunbo_recycler_item, parent, false);
                return new LunBo2ViewHolder(contentView);

            default:
                contentView = mLayoutInflater.inflate(R.layout.main_recycler_hot_item, parent, false);
                return new HotViewHolder(contentView);
        }
    }
    /**
     * 绑定ViewHolder的数据。
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        url_maps = new HashMap<>();
        url_img   = new ArrayList<>();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });
        if (holder instanceof LunBoViewHolder) {
                    HashMap<String, String> url_maps = new HashMap<>();
                    final ArrayList<String> type     = new ArrayList<>();
                    final ArrayList<String> tid     = new ArrayList<>();
                    LunBoViewHolder lunBoViewHolder = (LunBoViewHolder) holder;
            try {
                JSONArray  img = mianInfo.getJSONArray("advs");
                for (int i = 0;i<img.length();i++){
                    url_maps.put(i+"",img.getJSONObject(i).getString("img"));
                    type.add(img.getJSONObject(i).getString("type"));
                }
                for (int i = 0 ;i<type.size();i++){
                    if (type.get(i).equals("12")){                                          //商品列表
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("cate_id"));
                    }else if (type.get(i).equals("0")){                                    //自定义URL
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("url"));
                    }else if (type.get(i).equals("21")){                                   //商品详情
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("item_id"));
                    }else if (type.get(i).equals("11")){                                   //团购
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("tid"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
                    lunBoViewHolder.sliderLayout.removeAllSliders();
                    for (String name : url_maps.keySet()) {
                        DefaultSliderView defaultSliderView = new DefaultSliderView(context);
                        defaultSliderView
                                .image(url_maps.get(name))
                                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                                .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                    @Override
                                    public void onSliderClick(BaseSliderView slider) {
                                        if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("12")){
                                            Intent intent = new Intent(context,GoodListActivity.class);//商品列表
                                            intent.putExtra("id",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                            intent.putExtra("type","cate_id");
                                            context.startActivity(intent);
                                        }else if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("0")){   // web url
                                            Intent intent = new Intent(context, WebActivity.class);
                                            intent.putExtra("url",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                            context.startActivity(intent);
                                        }
                                        else if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("21")){
                                            Intent intent = new Intent(context, GoodInfoActivity.class);
                                            intent.putExtra("goodID",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                            intent.putExtra("come","other");
                                            context.startActivity(intent);
                                        }
                                        else {

                                        }
                                    }
                                });
                        defaultSliderView.bundle(new Bundle());
                        defaultSliderView.getBundle()
                                .putString("extra", name);
                        lunBoViewHolder.sliderLayout.addSlider(defaultSliderView);
                    }
                    FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) (DensityUtil.getWindowsWidth(context) * 0.5633));
                    lunBoViewHolder.sliderLayout.setLayoutParams(p);
            try {
                lunBoViewHolder.Shop_Address.setText(mianInfo.getString("city_name"));
                lunBoViewHolder.ll_shop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                           Intent intent = new Intent(context, CityListActivity.class);
                           context.startActivity(intent);
                          }
                      });
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lunBoViewHolder.select.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent toInfo = new Intent(context, SelectActivity.class);
                            listener.toIntent(toInfo);
                        }
                    });
                   }
         /**
          * 分類
          */
        else if (holder instanceof NavViewHolder) {
                    ArrayList<String> imgdata = new ArrayList<>();
                    final ArrayList<String> tid     = new ArrayList<>();
                    final ArrayList<String> type     = new ArrayList<>();
                    NavViewHolder navViewHolder = (NavViewHolder) holder;
                    final List<String> data = new ArrayList<>();
            try {
                final JSONArray jsonArray  = mianInfo.getJSONArray("indexs");
                for (int i = 0;i<jsonArray.length();i++){
                    data.add(jsonArray.getJSONObject(i).getString("name"));
                    imgdata.add(jsonArray.getJSONObject(i).getString("img"));
                    type.add(jsonArray.getJSONObject(i).getString("type"));
                    //ToastUtils.show(context,imgdata.get(i));
                    //ToastUtils.show(context,data.get(i));
                 }
                for (int i = 0 ;i<type.size();i++){
                    if (type.get(i).equals("12")){                                          //商品列表
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("cate_id"));
                    }else if (type.get(i).equals("0")){                                    //自定义URL
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("url"));
                    }else if (type.get(i).equals("21")){                                   //商品详情
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("item_id"));
                    }else if (type.get(i).equals("11")){                                   //团购
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("tid"));
                    }
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                navViewHolder.recyclerView.setLayoutParams(params);
                navViewHolder.recyclerView.setAdapter(new MainRecyclerNavAdapter(context, data,imgdata, new MainRecyclerNavAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int subPosition) {
                        if (type.get(subPosition).equals("12")){
                            Intent intent = new Intent(context,GoodListActivity.class);//商品列表
                            intent.putExtra("id",tid.get(subPosition));
                            intent.putExtra("type","cate_id");
                            context.startActivity(intent);
                        }else if (type.get(subPosition).equals("0")){   // web url
                            Intent intent = new Intent(context, WebActivity.class);
                            intent.putExtra("url",tid.get(subPosition));
                            context.startActivity(intent);
                          }
                        else if (type.get(subPosition).equals("21")){
                            Intent intent = new Intent(context, GoodInfoActivity.class);
                            intent.putExtra("goodID",tid.get(subPosition));
                            intent.putExtra("come","other");
                            context.startActivity(intent);
                            }
                        else {

                              }
                             }
                           }));
                        } catch (JSONException e) {
                     e.printStackTrace();
                     }
                 }
        /**
         * 限时抢购
         */
        else if (holder instanceof TimeViewHolder) {
                    TimeViewHolder timeViewHolder = (TimeViewHolder) holder;
                    List<String> data = new ArrayList<>();
                    ArrayList<String> imageList = new ArrayList<>();
                    ArrayList<String> priceList = new ArrayList<>();
                    ArrayList<String> buy_count = new ArrayList<>();
                    final ArrayList<String> idList = new ArrayList<>();
            try {
                JSONArray jsonArray = mianInfo.getJSONArray("qianggou_deal_list");
                  if (jsonArray.length()<1){
                      timeViewHolder.more.setVisibility(View.GONE);
                      timeViewHolder.line.setVisibility(View.GONE);
                  }else {
                      timeViewHolder.more.setVisibility(View.VISIBLE);
                      timeViewHolder.line.setVisibility(View.VISIBLE);
                  }
                 for (int i=0;i<jsonArray.length();i++){
                     imageList.add(jsonArray.getJSONObject(i).getString("icon"));
                     data.add(jsonArray.getJSONObject(i).getString("name"));
                     priceList.add(jsonArray.getJSONObject(i).getString("current_price"));
                     idList.add(jsonArray.getJSONObject(i).getString("id"));
                     buy_count.add(jsonArray.getJSONObject(i).getString("buy_count"));
                 }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                timeViewHolder.recyclerView.setLayoutParams(params);
                timeViewHolder.recyclerView.setAdapter(new MainRecyclerTimeAdapter(context, data,imageList,priceList,buy_count,new MainRecyclerTimeAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int subPosition) {
                        Intent intent = new Intent(context, GoodInfoActivity.class);
                        intent.putExtra("goodID",idList.get(subPosition));
                        intent.putExtra("come","other");
                        context.startActivity(intent);
                    }
                }));
                long  Good_status =Long.parseLong(mianInfo.getJSONArray("qianggou_deal_list").getJSONObject(0).getString("end_time")) - Long.parseLong(mianInfo.getString("nowtime"));
                long  good_status =Long.parseLong(mianInfo.getJSONArray("qianggou_deal_list").getJSONObject(0).getString("begin_time")) - Long.parseLong(mianInfo.getString("nowtime"));
                if (Good_status>0){
                    timeViewHolder.time.start(Good_status*1000);
                    timeViewHolder.time.setVisibility(View.VISIBLE);
                    timeViewHolder.status.setText("距离结束  ");
                }else {
                    timeViewHolder.time.setVisibility(View.GONE);
                    timeViewHolder.status.setText("    已结束    ");
                }
                if (good_status>0){
                    timeViewHolder.status.setText("距开始  ");
                    timeViewHolder.time.start(good_status*1000);

                }
                timeViewHolder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.toIntent(new Intent(context, TimeGoodActivity.class));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        /**
         * 輪播廣告
         */
        else if (holder instanceof LunBo1ViewHolder) {
            HashMap<String, String> url_maps = new HashMap<>();
            final ArrayList<String> type     = new ArrayList<>();
            final ArrayList<String> tid     = new ArrayList<>();
            LunBo1ViewHolder lunBoViewHolder = (LunBo1ViewHolder) holder;
            try {
                JSONArray  img = mianInfo.getJSONArray("adv_4");
                for (int i = 0;i<img.length();i++){
                    url_maps.put(i+"",img.getJSONObject(i).getString("img"));
                    type.add(img.getJSONObject(i).getString("type"));
                }
                for (int i = 0 ;i<type.size();i++){
                    if (type.get(i).equals("12")){                                          //商品列表
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("cate_id"));
                    }else if (type.get(i).equals("0")){                                    //自定义URL
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("url"));
                    }else if (type.get(i).equals("21")){                                   //商品详情
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("item_id"));
                    }else if (type.get(i).equals("11")){                                   //团购
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("tid"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lunBoViewHolder.sliderLayout.removeAllSliders();
            for (String name : url_maps.keySet()) {
                DefaultSliderView defaultSliderView = new DefaultSliderView(context);
                defaultSliderView
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                //Toast.makeText(context, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                                if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("12")){
                                    Intent intent = new Intent(context,GoodListActivity.class);//商品列表
                                    intent.putExtra("id",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                    intent.putExtra("type","cate_id");
                                    context.startActivity(intent);
                                }else if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("0")){   // web url
                                    Intent intent = new Intent(context, WebActivity.class);
                                    intent.putExtra("url",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                    context.startActivity(intent);
                                }
                                else if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("21")){
                                    Intent intent = new Intent(context, GoodInfoActivity.class);
                                    intent.putExtra("goodID",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                    intent.putExtra("come","other");
                                    context.startActivity(intent);
                                }
                                else {

                                }
                            }
                        });
                defaultSliderView.bundle(new Bundle());
                defaultSliderView.getBundle()
                        .putString("extra", name);
                lunBoViewHolder.sliderLayout.addSlider(defaultSliderView);
            }
            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,(int) (DensityUtil.getWindowsWidth(context) * 0.3));
            lunBoViewHolder.sliderLayout.setLayoutParams(p);
           }
        /**
         * web
         */
        else if (holder instanceof WebAdvViewHolder) {

                    WebAdvViewHolder webAdvViewHolder = (WebAdvViewHolder) holder;
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.getWindowsWidth(context) / 2);
                    webAdvViewHolder.webView.setLayoutParams(params);
                    //webAdvViewHolder.webView.loadUrl(mianInfo.getZt_html());
            try {
                String html = mianInfo.getString("zt_html");
                webAdvViewHolder.webView.loadDataWithBaseURL(null,html,"text/html", "utf-8", null);

                  webAdvViewHolder.webView.setWebViewClient(new WebViewClient(){
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        if (url.contains("ctl=item")){                                //商品详情
                            Intent intent = new Intent(context, GoodInfoActivity.class);
                            intent.putExtra("come","other");
                            intent.putExtra("goodID",url.split("=")[2]);
                            context.startActivity(intent);
                          }else if (url.contains("ctl=list")){
                            Intent intent = new Intent(context,WebActivity.class);//商品列表
                            intent.putExtra("url",url);
                            intent.putExtra("type","main");
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, WebActivity.class);    //网页
                            intent.putExtra("url",url);
                            context.startActivity(intent);
                        }
                        return true;
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }
                    //System.out.println(mianInfo.getZt_html());
        }
         /**
         * web
         */
        /**
         * 輪播廣告
         */
        else if (holder instanceof LunBo2ViewHolder) {
            HashMap<String, String> url_maps = new HashMap<>();
            final ArrayList<String> type     = new ArrayList<>();
            final ArrayList<String> tid     = new ArrayList<>();
            LunBo2ViewHolder lunBoViewHolder = (LunBo2ViewHolder) holder;
            try {
                JSONArray  img = mianInfo.getJSONArray("adv_5");
                for (int i = 0;i<img.length();i++){
                    url_maps.put(i+"",img.getJSONObject(i).getString("img"));
                    type.add(img.getJSONObject(i).getString("type"));
                }
                for (int i = 0 ;i<type.size();i++){
                    if (type.get(i).equals("12")){                                          //商品列表
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("cate_id"));
                    }else if (type.get(i).equals("0")){                                    //自定义URL
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("url"));
                    }else if (type.get(i).equals("21")){                                   //商品详情
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("item_id"));
                    }else if (type.get(i).equals("11")){                                   //团购
                        tid.add(img.getJSONObject(i).getJSONObject("data").getString("tid"));
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            lunBoViewHolder.sliderLayout.removeAllSliders();
            for (String name : url_maps.keySet()) {
                DefaultSliderView defaultSliderView = new DefaultSliderView(context);
                defaultSliderView
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                //Toast.makeText(context, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                                if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("12")){
                                    Intent intent = new Intent(context,GoodListActivity.class);//商品列表
                                    intent.putExtra("id",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                    intent.putExtra("type","cate_id");
                                    context.startActivity(intent);
                                }else if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("0")){   // web url
                                    Intent intent = new Intent(context, WebActivity.class);
                                    intent.putExtra("url",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                    context.startActivity(intent);
                                }
                                else if (type.get(Integer.parseInt(slider.getBundle().get("extra").toString())).equals("21")){
                                    Intent intent = new Intent(context, GoodInfoActivity.class);
                                    intent.putExtra("goodID",tid.get(Integer.parseInt(slider.getBundle().get("extra").toString())));
                                    intent.putExtra("come","other");
                                    context.startActivity(intent);
                                }
                                else {

                                }
                            }
                        });
                defaultSliderView.bundle(new Bundle());
                defaultSliderView.getBundle()
                        .putString("extra", name);
                lunBoViewHolder.sliderLayout.addSlider(defaultSliderView);
            }
            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,(int) (DensityUtil.getWindowsWidth(context) * 0.3));
            lunBoViewHolder.sliderLayout.setLayoutParams(p);
        }
        /**
         *滾動廣澳
         */
        else if (holder instanceof RecViewHolder) {
                    RecViewHolder recViewHolder = (RecViewHolder) holder;
                    final ArrayList<String> tid     = new ArrayList<>();
                    final ArrayList<String> type     = new ArrayList<>();
                    List<String> data = new ArrayList<>();
            try {
                JSONArray jsonArray = mianInfo.getJSONArray("adv_gundong");
                for (int i = 0;i<jsonArray.length();i++){
                    data.add(jsonArray.getJSONObject(i).getString("img"));
                    type.add(jsonArray.getJSONObject(i).getString("type"));
                }
                for (int i = 0 ;i<type.size();i++){
                    if (type.get(i).equals("12")){                                          //商品列表
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("cate_id"));
                    }else if (type.get(i).equals("0")){                                    //自定义URL
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("url"));
                    }else if (type.get(i).equals("21")){                                   //商品详情
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("item_id"));
                    }else if (type.get(i).equals("11")){                                   //团购
                        tid.add(jsonArray.getJSONObject(i).getJSONObject("data").getString("tid"));
                    }
                }
                recViewHolder.recyclerView.setAdapter(new MainRecyclerRecAdapter(context, data, new MainRecyclerRecAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int subPosition) {
                        if (type.get(subPosition).equals("12")){
                            Intent intent = new Intent(context,GoodListActivity.class);//商品列表
                            intent.putExtra("id",tid.get(subPosition));
                            intent.putExtra("type","cate_id");
                            context.startActivity(intent);
                        }else if (type.get(subPosition).equals("0")){   // web url
                            Intent intent = new Intent(context, WebActivity.class);
                            intent.putExtra("url",tid.get(subPosition));
                            context.startActivity(intent);
                        }
                        else if (type.get(subPosition).equals("21")){
                            Intent intent = new Intent(context, GoodInfoActivity.class);
                            intent.putExtra("goodID",tid.get(subPosition));
                            intent.putExtra("come","other");
                            context.startActivity(intent);
                            intent.putExtra("come","other");
                        }
                        else {

                        }
                    }
                }));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //买赠
        else if (holder instanceof MaiViewHolder){
            final MaiViewHolder hotViewHolder = (MaiViewHolder) holder;
            ArrayList<String> data  = new ArrayList<>();
            ArrayList<String> nameList = new ArrayList<>();
            ArrayList<String> priceList = new ArrayList<>();
            ArrayList<String> oldpriceList = new ArrayList<>();
            ArrayList<String> buy_count = new ArrayList<>();
            final ArrayList<String> IdList = new ArrayList<>();
            if (mianInfo.toString().contains("best_deal_list")){
            try {
                JSONArray jsonArray = mianInfo.getJSONArray("best_deal_list");
                //ToastUtils.show(context,jsonArray.length()+"qq");
                if (jsonArray.length()<1){
                    hotViewHolder.name.setVisibility(View.GONE);
                    hotViewHolder.line.setVisibility(View.GONE);
                }else {
                    hotViewHolder.name.setVisibility(View.VISIBLE);
                    hotViewHolder.line.setVisibility(View.VISIBLE);
                }
                hotViewHolder.name.setOnClickListener(null);
                for (int i=0;i<jsonArray.length();i++){
                    data.add(jsonArray.getJSONObject(i).getString("icon"));
                    nameList.add(jsonArray.getJSONObject(i).getString("name"));
                    priceList.add(jsonArray.getJSONObject(i).getString("current_price"));
                    oldpriceList.add(jsonArray.getJSONObject(i).getString("origin_price"));
                    buy_count.add(jsonArray.getJSONObject(i).getString("buy_count"));
                    IdList.add(jsonArray.getJSONObject(i).getString("id"));
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                hotViewHolder.recyclerView.setLayoutParams(params);
                hotViewHolder.recyclerView.setAdapter(new GoodsListRecyclerAdapter(context,nameList,priceList,data,oldpriceList,buy_count,new GoodsListRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int subPosition) {
                        Intent intent = new Intent(context, GoodInfoActivity.class);
                        intent.putExtra("goodID",IdList.get(subPosition));
                        intent.putExtra("come","other");
                        context.startActivity(intent);
                    }
                }));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }else {
                hotViewHolder.name.setVisibility(View.GONE);
                hotViewHolder.line.setVisibility(View.GONE);
            }
        }
         /**
          * 爆款推薦
          */
        else {
            final HotViewHolder hotViewHolder = (HotViewHolder) holder;
                    ArrayList<String> data  = new ArrayList<>();
                    ArrayList<String> nameList = new ArrayList<>();
                    ArrayList<String> priceList = new ArrayList<>();
                    ArrayList<String> oldpriceList = new ArrayList<>();
                    ArrayList<String> buy_count = new ArrayList<>();
                    final ArrayList<String> IdList = new ArrayList<>();
            try {
                JSONArray jsonArray = mianInfo.getJSONArray("supplier_deal_list");
                if (jsonArray.length()<1){
                    hotViewHolder.name.setVisibility(View.GONE);
                    hotViewHolder.line.setVisibility(View.GONE);
                }else {
                    hotViewHolder.name.setVisibility(View.VISIBLE);
                    hotViewHolder.line.setVisibility(View.VISIBLE);
                }
                hotViewHolder.name.setOnClickListener(null);
                for (int i=0;i<jsonArray.length();i++){
                     data.add(jsonArray.getJSONObject(i).getString("icon"));
                     nameList.add(jsonArray.getJSONObject(i).getString("name"));
                     priceList.add(jsonArray.getJSONObject(i).getString("current_price"));
                     oldpriceList.add(jsonArray.getJSONObject(i).getString("origin_price"));
                     buy_count.add(jsonArray.getJSONObject(i).getString("buy_count"));
                     IdList.add(jsonArray.getJSONObject(i).getString("id"));
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                hotViewHolder.recyclerView.setLayoutParams(params);
                //goodListRecyclerAdapter = new GoodsListRecyclerAdapter(GoodListActivity.this, data,priceList,imageList,old_price,count, new GoodsListRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    //Context context, ArrayList<String> nameList, ArrayList<String> priceList, ArrayList<String> imageList,ArrayList<String> old_price,ArrayList<String> count
                    hotViewHolder.recyclerView.setAdapter(new GoodsListRecyclerAdapter(context,nameList,priceList,data,oldpriceList,buy_count,new GoodsListRecyclerAdapter.OnRecyclerViewItemClickListener() {
                    @Override
                    public void onItemClick(int subPosition) {
                        Intent intent = new Intent(context, GoodInfoActivity.class);
                        intent.putExtra("goodID",IdList.get(subPosition));
                        intent.putExtra("come","other");
                        context.startActivity(intent);
                    }
                }));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int getItemCount() {
        return 9;
    }
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    /**
     * 轮播视图
     */
    public class LunBoViewHolder extends RecyclerView.ViewHolder {
        public SliderLayout sliderLayout;
        public PagerIndicator pagerIndicator;
        public LinearLayout select;
        public TextView Shop_Address;
        public  LinearLayout ll_shop;

        public LunBoViewHolder(View contentView) {
            super(contentView);
            sliderLayout = (SliderLayout) contentView.findViewById(R.id.slider_view);
            pagerIndicator = (PagerIndicator) contentView.findViewById(R.id.custom_indicator_view);
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
            sliderLayout.setCustomIndicator(pagerIndicator);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.setDuration(4000);
            select = (LinearLayout) contentView.findViewById(R.id.select);
            Shop_Address = (TextView) contentView.findViewById(R.id.shop_name);
            ll_shop     = (LinearLayout) contentView.findViewById(R.id.ll_location);
        }
    }

    public class LunBo1ViewHolder extends RecyclerView.ViewHolder {
        public SliderLayout sliderLayout;
        public PagerIndicator pagerIndicator;
        public LunBo1ViewHolder(View contentView) {
            super(contentView);
            sliderLayout = (SliderLayout) contentView.findViewById(R.id.slider_view);
            pagerIndicator = (PagerIndicator) contentView.findViewById(R.id.custom_indicator_view);
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
            sliderLayout.setCustomIndicator(pagerIndicator);
            sliderLayout.setDuration(3000);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
        }
    }
    public class LunBo2ViewHolder extends RecyclerView.ViewHolder {
        public SliderLayout sliderLayout;
        public PagerIndicator pagerIndicator;
        public LunBo2ViewHolder(View contentView) {
            super(contentView);
            sliderLayout = (SliderLayout) contentView.findViewById(R.id.slider_view);
            pagerIndicator = (PagerIndicator) contentView.findViewById(R.id.custom_indicator_view);
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
            sliderLayout.setCustomIndicator(pagerIndicator);
            sliderLayout.setDuration(3000);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
        }
    }
    /**
     * 导航视图
     */
    public class NavViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;

        public NavViewHolder(View contentView) {
            super(contentView);
            recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
        }
    }
    /**
     * 限时抢购视图
     */
    public class TimeViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public CountdownView time;
        public  LinearLayout timeTv;
        public LinearLayout more;
        public View line;
        public TextView buy_count;
        private TextView status;
        public TimeViewHolder(View contentView) {
            super(contentView);
            recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            //recyclerView.addItemDecoration(new GridDividerItemDecoration(DensityUtil.dp2px(context, 5), 3, false));
            time = (CountdownView) contentView.findViewById(R.id.time);
            more = (LinearLayout) contentView.findViewById(R.id.more);
            timeTv = (LinearLayout) contentView.findViewById(R.id.timeTv);
            line   = contentView.findViewById(R.id.line);
            buy_count  = (TextView) contentView.findViewById(R.id.buy_count);
            status    =  (TextView) contentView.findViewById(R.id.status);
        }
    }
     /**
     * web 广告视图
     */
    public class WebAdvViewHolder extends RecyclerView.ViewHolder {
        public WebView webView;

        public WebAdvViewHolder(View contentView) {
            super(contentView);
            webView = (WebView) contentView.findViewById(R.id.web_view);
        }
    }
    /**
     * 推荐视图
     */
    public class RecViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public RecViewHolder(View contentView) {
            super(contentView);
            recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }
    }
    /**
     * 买赠
     */
    public class MaiViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public LinearLayout name;
        public View line;
        public MaiViewHolder(View contentView) {
            super(contentView);
            recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            recyclerView.addItemDecoration(new GridDividerItemDecoration(DensityUtil.dp2px(context, 5), 2, false));
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            name  = (LinearLayout) contentView.findViewById(R.id.name);
            line  = contentView.findViewById(R.id.line);
        }
    }
    /**
     * 热销
     */
    public class HotViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView recyclerView;
        public LinearLayout name;
        public View line;
        public HotViewHolder(View contentView) {
            super(contentView);
            recyclerView = (RecyclerView) contentView.findViewById(R.id.recycler_view);
            recyclerView.addItemDecoration(new GridDividerItemDecoration(DensityUtil.dp2px(context, 5), 2, false));
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            name  = (LinearLayout) contentView.findViewById(R.id.name);
            line  = contentView.findViewById(R.id.line);
        }
    }
}
