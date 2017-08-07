package com.wzsykj.wuyaojiu.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.wzsykj.wuyaojiu.Bean.TimeGood;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.ui.good.GoodInfoActivity;
import com.wzsykj.wuyaojiu.widget.ImageLoaderUtils;

import org.json.JSONObject;

import java.util.List;

import iwgang.view.countdownview.CountdownView;


/**
 * Created by chen on 16/6/16.
 */
public class TimeGoodRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private LayoutInflater mLayoutInflater;
    private JSONObject data;
    private OnRecyclerViewItemClickListener listener;
    private List<TimeGood.QianggouDealListBean> itemBeanList;
    private String nowTime;
    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    public List<TimeGood.QianggouDealListBean> getData() {
        return itemBeanList;
    }

    public void setData(List<TimeGood.QianggouDealListBean> itemBeanList ) {
       this.itemBeanList = itemBeanList;
    }

    public TimeGoodRecyclerAdapter(Context context, List<TimeGood.QianggouDealListBean> itemBeanList ,String nowTime, OnRecyclerViewItemClickListener listener) {
        this.context = context;
        this.itemBeanList = itemBeanList;
        this.nowTime      = nowTime;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }
    /**
     * 鍐冲畾鍏冪礌鐨勫竷灞�浣跨敤鍝绫诲瀷
     */
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    /**
     * 娓叉煋鍏蜂綋鐨刅iewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView = null;
        switch (viewType) {
           /* case 0:
                contentView = mLayoutInflater.inflate(R.layout.timegood_recycler_lunbo_item, parent, false);
                return new LunBoViewHolder(contentView);*/
            case 0:
                contentView = mLayoutInflater.inflate(R.layout.timegood_recycler_time_item, parent, false);
                return new TimeViewHolder(contentView);

            default:
                contentView = mLayoutInflater.inflate(R.layout.timegood_recycler_good_item, parent, false);
                return new ItemViewHolder(contentView);
        }

    }

    /**
     * 缁戝畾ViewHolder鐨勬暟鎹��
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
   /*     holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });

        if (holder instanceof LunBoViewHolder) {
            LunBoViewHolder lunBoViewHolder = (LunBoViewHolder) holder;

            HashMap<String, String> url_maps = new HashMap<String, String>();
            url_maps.put("1", "http://pic.3h3.com/up/2014-6/20146611111432254789.jpg");
            url_maps.put("2", "http://pic.3h3.com/up/2014-6/2014661111143227131.jpg");
            url_maps.put("3", "http://pic.3h3.com/up/2014-6/20146611111432289210.jpg");
            lunBoViewHolder.sliderLayout.removeAllSliders();
            for (String name : url_maps.keySet()) {
                DefaultSliderView defaultSliderView = new DefaultSliderView(context);
                // initialize a SliderLayout
                defaultSliderView
                        .image(url_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                        .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                            @Override
                            public void onSliderClick(BaseSliderView slider) {
                                Toast.makeText(context, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
                            }
                        });
                //add your extra information
                defaultSliderView.bundle(new Bundle());
                defaultSliderView.getBundle()
                        .putString("extra", name);
                lunBoViewHolder.sliderLayout.addSlider(defaultSliderView);
            }

            FrameLayout.LayoutParams p = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, (int) (DensityUtil.getWindowsWidth(context) * 0.5));
            lunBoViewHolder.sliderLayout.setLayoutParams(p);
        } else if */
        if (holder instanceof TimeViewHolder) {
            TimeViewHolder timeViewHolder = (TimeViewHolder) holder;
            //timeViewHolder.time.start(995550000); // Millisecond
                timeViewHolder.time.start((Long.parseLong(itemBeanList.get(0).getEnd_time())-Long.parseLong(nowTime))*1000);
        } else {
            final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            final TimeGood.QianggouDealListBean bean = itemBeanList.get(position-1);
            itemViewHolder.title.setText(bean.getName());
            ImageLoaderUtils.LoadImage(bean.getIcon(),itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
            itemViewHolder.price.setText("￥"+bean.getCurrent_price());
            itemViewHolder.oldPrice.setText("￥"+bean.getOrigin_price());
            itemViewHolder.number.setText("限量"+bean.getMax_bought());
            itemViewHolder.progressbar.setMax(bean.getBuyper()+Integer.parseInt(bean.getMax_bought()));
            itemViewHolder.progressbar.setProgress(bean.getBuyper());
            itemViewHolder.progressbarNumber.setText(bean.getBuyper()+"%");
            itemViewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, GoodInfoActivity.class);
                        intent.putExtra("goodID",bean.getId());
                        intent.putExtra("come","other");
                        context.startActivity(intent);
                }
            });
            /*try {
                if (data.toString().contains("qianggou_deal_list")){
                    itemViewHolder.title.setText(data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("name"));
                    ImageLoaderUtils.LoadImage(data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("image"), itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
                    itemViewHolder.price.setText("￥"+data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("current_price"));
                    itemViewHolder.oldPrice.setText("￥"+data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("origin_price"));
                    itemViewHolder.number.setText("仅剩"+data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("max_bought")+"件");
                    itemViewHolder.progressbar.setMax(Integer.parseInt(data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("buyper"))+Integer.parseInt(data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("max_bought")));
                    itemViewHolder.progressbar.setProgress(Integer.parseInt(data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("buyper")));
                    itemViewHolder.progressbarNumber.setText(data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("buyper")+"%");
                    itemViewHolder.btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, GoodInfoActivity.class);
                            try {
                                intent.putExtra("goodID",data.getJSONArray("qianggou_deal_list").getJSONObject(position-1).getString("id"));
                                intent.putExtra("come","other");
                                context.startActivity(intent);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }*/
           // ImageLoaderUtils.LoadImage("http://img.519wz.cn/public/attachment/201604/09/16/5708bdf5ef18c.jpg", itemViewHolder.img, ImageLoaderUtils.getOptionsDefault());
          }
    }
    @Override
    public int getItemCount() {

        return itemBeanList.size()+1;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
    public class LunBoViewHolder extends RecyclerView.ViewHolder {

        public SliderLayout sliderLayout;
        public PagerIndicator pagerIndicator;

        public LunBoViewHolder(View contentView) {
            super(contentView);
            sliderLayout = (SliderLayout) contentView.findViewById(R.id.slider_view);
            pagerIndicator = (PagerIndicator) contentView.findViewById(R.id.custom_indicator_view);
            sliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
            sliderLayout.setCustomIndicator(pagerIndicator);
            sliderLayout.setCustomAnimation(new DescriptionAnimation());
            sliderLayout.stopAutoCycle();
        }
    }
    public class TimeViewHolder extends RecyclerView.ViewHolder {
        public CountdownView time;

        public TimeViewHolder(View contentView) {
            super(contentView);
            time = (CountdownView) contentView.findViewById(R.id.time);
        }
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView title, number, progressbarNumber, price, oldPrice, btn;
        public ProgressBar progressbar;
        public ItemViewHolder(View contentView) {
            super(contentView);
            img = (ImageView) contentView.findViewById(R.id.img);
            title = (TextView) contentView.findViewById(R.id.title);
            number = (TextView) contentView.findViewById(R.id.number);
            progressbarNumber = (TextView) contentView.findViewById(R.id.progressbar_number);
            price = (TextView) contentView.findViewById(R.id.price);
            oldPrice = (TextView) contentView.findViewById(R.id.old_price);
            progressbar = (ProgressBar) contentView.findViewById(R.id.progressbar);
            btn = (TextView) contentView.findViewById(R.id.btn);
            oldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG );
        }
    }
}
