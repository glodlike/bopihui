package com.wzsykj.wuyaojiu.ui.good;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Pingjia;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.GoodSPJAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chen on 16/8/10.
 */
public class EvaluateActivity extends BaseActivity implements View.OnClickListener, AbsListView.OnScrollListener {
    private ImageButton back;

    private String goodId;
    private int page = 2;
    private GoodSPJAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView recyclerView;
    private List<Pingjia.ItemBean> itemBeamList;

    private ProgressBar wxPB, sixPB, sanxPB, lxPB, yxPB;
    private TextView wxTV, sixTV, sanxTV, lxTV, yxTV;

    private View LoadingView;
    private View EndView;

    private TextView advPoint, countPJ;
    private RatingBar ratingbar;

    private static final int MESSAGE_FLAG = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MESSAGE_FLAG:
                    adapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.evaluate_activity, null);
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

        EndView = getLayoutInflater().inflate(R.layout.sample_common_list_footer_end, null);
        LoadingView = getLayoutInflater().inflate(R.layout.sample_common_list_footer_loading, null);

        itemBeamList = new ArrayList<>();
        goodId = getIntent().getStringExtra("goodId");

        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);

        advPoint = (TextView) findViewById(R.id.advPoint);
        countPJ = (TextView) findViewById(R.id.countPJ);
        ratingbar = (RatingBar) findViewById(R.id.ratingBar);

        wxPB = (ProgressBar) findViewById(R.id.wxPB);
        sixPB = (ProgressBar) findViewById(R.id.sixPB);
        sanxPB = (ProgressBar) findViewById(R.id.sanxPB);
        lxPB = (ProgressBar) findViewById(R.id.lxPB);
        yxPB = (ProgressBar) findViewById(R.id.yxPB);

        wxTV = (TextView) findViewById(R.id.wxTV);
        sixTV = (TextView) findViewById(R.id.sixTV);
        sanxTV = (TextView) findViewById(R.id.sanxTV);
        lxTV = (TextView) findViewById(R.id.lxTV);
        yxTV = (TextView) findViewById(R.id.yxTV);

        /*swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        swipeRefreshLayout.setProgressBackgroundColor(R.color.white);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              swipeRefreshLayout.setRefreshing(false);
            }
          });*/
        recyclerView = (ListView) findViewById(R.id.recycler_view);
        recyclerView.setOnScrollListener(this);

        showData();
    }

    private void showData() {
        //swipeRefreshLayout.setRefreshing(false);
        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=dp&type=deal&data_id=" + goodId + "&page=1&city_id=" + new SharePerfenceUtils(this).getShop_id()[0] + "", null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                Pingjia pingjia = new Gson().fromJson(StringUtils.base64ToString(result), Pingjia.class);
                int length = pingjia.getItem().size();
                if (length > 4) {
                    recyclerView.addFooterView(LoadingView);
                }
                for (int i = 0; i < pingjia.getItem().size(); i++) {
                    itemBeamList.add(pingjia.getItem().get(i));
                }
                adapter = new GoodSPJAdapter(EvaluateActivity.this, itemBeamList);
                recyclerView.setAdapter(adapter);

                wxPB.setMax(Integer.parseInt(pingjia.getMessage_count()));
                wxPB.setProgress(Integer.parseInt(pingjia.getStar_5()));
                wxTV.setText((pingjia.getStar_5()));

                sixPB.setMax(Integer.parseInt(pingjia.getMessage_count()));
                sixPB.setProgress(Integer.parseInt(pingjia.getStar_4()));
                sixTV.setText(pingjia.getStar_4());

                sanxPB.setMax(Integer.parseInt(pingjia.getMessage_count()));
                sanxPB.setProgress(Integer.parseInt(pingjia.getStar_3()));
                sanxTV.setText(pingjia.getStar_3());

                lxPB.setMax(Integer.parseInt(pingjia.getMessage_count()));
                lxPB.setProgress(Integer.parseInt(pingjia.getStar_2()));
                lxTV.setText(pingjia.getStar_2());

                yxPB.setMax(Integer.parseInt(pingjia.getMessage_count()));
                yxPB.setProgress(Integer.parseInt(pingjia.getStar_1()));
                yxTV.setText(pingjia.getStar_1());
                countPJ.setText(pingjia.getMessage_count() + " 条评论");
                advPoint.setText(pingjia.getBuy_dp_avg() + "");
                ratingbar.setRating(Float.parseFloat(String.valueOf(pingjia.getBuy_dp_avg())));
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
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            default:
                break;
        }
    }

    int lastVisibleItem = 0;

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (lastVisibleItem == adapter.getCount() &&
                scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    getMoreData(page++);
                }
            }).start();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastVisibleItem = firstVisibleItem + visibleItemCount - 1;
    }

    public void getMoreData(int page) {
        XUtils.Get(AppHttp.BASE_URL_NEW + "ctl=dp&type=deal&data_id=" + goodId + "&page=" + page + "", null, new MyCallBack<String>() {
            @Override
            public void onFinished() {
                super.onFinished();
                handler.sendEmptyMessage(MESSAGE_FLAG);
            }

            @Override
            public void onSuccess(String result) {
                Pingjia pingjia = new Gson().fromJson(StringUtils.base64ToString(result), Pingjia.class);
                if (pingjia.getItem().size() > 1) {
                    for (int i = 0; i < pingjia.getItem().size(); i++) {
                        itemBeamList.add(pingjia.getItem().get(i));
                    }
                } else {
                    recyclerView.removeFooterView(LoadingView);
                    // Toast.makeText(EvaluateActivity.this,"数据已完部加载完毕",Toast.LENGTH_LONG).show();
                }
                super.onSuccess(result);
            }
        });
    }
}