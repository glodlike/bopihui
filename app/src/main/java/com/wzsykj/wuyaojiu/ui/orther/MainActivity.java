package com.wzsykj.wuyaojiu.ui.orther;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.umeng.socialize.UMShareAPI;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.service.LocationService;
import com.wzsykj.wuyaojiu.ui.tab.CarFragment;
import com.wzsykj.wuyaojiu.ui.tab.MainFragment;
import com.wzsykj.wuyaojiu.ui.tab.TypeFragment;
import com.wzsykj.wuyaojiu.ui.tab.UserFragment;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.MainViewPagerAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.utils.AppUtils;
import com.wzsykj.wuyaojiu.utils.BracastUtil;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.UpdateService;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.widget.BadgeView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {
    private TabLayout mTablayout;
    public ViewPager mViewPager;
    private List<BadgeView> badgeViews;
    private ArrayList<String> userList;
    private String[] mTitles = new String[]{"首页", "分类", "购物车", "我的"};
    private int[] mImgs = new int[]{R.drawable.main_tab_item_home_img, R.drawable.main_tab_item_type_img, R.drawable.main_tab_item_car_img, R.drawable.main_tab_item_user_img};
    private int id;
    private LocationService locationService;
    public  boolean flag = true;
    private BroadcastReceiver broadcastReceiver ;
    private BroadcastReceiver CountFresh ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        id = getIntent().getIntExtra("id",0);
        initView();
        updateAPK();
    }
    /**
     * 显示请求字符串
     * @param str
     */
    public void logMsg(String str) {
        try {
            ToastUtils.show(MainActivity.this,str);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //子fragment 回调使用 UM
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }
    @Override
    public void onResume() {
        //locationService = ((Application) getApplication()).locationService;
        locationService = new LocationService(this);
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BracastUtil.ACTION_FINNISH1);
        filter.addAction(BracastUtil.COUNT_REFRESH);
        filter.addAction(BracastUtil.LOGINOUT);
        registerReceiver(this.broadcastReceiver, filter); // 注
        super.onResume();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    protected void onDestroy() {
        locationService.stop();
        super.onDestroy();
    }
    private void initView() {

        userList =new SharePerfenceUtils(MainActivity.this).getUserInfo();
        badgeViews = new ArrayList<>();
        mTablayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> viewList = new ArrayList<>();
        viewList.add(new MainFragment());
        viewList.add(new TypeFragment());
        viewList.add(new CarFragment());
        viewList.add(new UserFragment());
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), mTitles, viewList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mTitles.length);
        mTablayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            final View view = this.getLayoutInflater().inflate(R.layout.main_tab_item, null);
            ImageView img = (ImageView) view.findViewById(R.id.img);
            TextView title = (TextView) view.findViewById(R.id.title);
            img.setImageResource(mImgs[i]);
            title.setText(mTitles[i]);
            tab.setCustomView(view);
            final BadgeView badgeView = new BadgeView(this);
            badgeView.setBackground(10, getResources().getColor(R.color.colorPrimary));
            badgeView.setTargetView(view);
            if (i==2){
               if (flag){
                   addBadgeView(badgeView,view);
                   flag = false;
               }
               broadcastReceiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        if (intent.getAction().equals(BracastUtil.ACTION_FRESH)){
                            mViewPager.setCurrentItem(2);
                        }else if (intent.getAction().equals(BracastUtil.COUNT_REFRESH)){
                            addBadgeView(badgeView,view);
                        }else if (intent.getAction().equals(BracastUtil.LOGINOUT)){
                           RemoveBadgeView(badgeView,view);
                        }
                    }
                  };
              }
           }
        mViewPager.setCurrentItem(1);
        mViewPager.setCurrentItem(0);
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public  void addBadgeView(final BadgeView badgeView, View view){
        badgeView.setBackground(10, getResources().getColor(R.color.colorPrimary));
        badgeView.setTargetView(view);
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=cart_count&email="+userList.get(0)+"&pwd="+userList.get(1)+"&city_id="+new SharePerfenceUtils(MainActivity.this).getShop_id()[0]+"",null,new MyCallBack<String>(){
                @Override
                public void onSuccess(String result) {
                    try {
                        JSONObject js = new JSONObject(StringUtils.base64ToString(result));
                        int count = js.getInt("count");
                        if (count<10){
                            badgeView.setBadgeMargin(0,0,5,-2);
                        }else {
                            badgeView.setBadgeMargin(0,0,2,10);
                        }
                        badgeView.setBadgeCount(count);
                        badgeViews.add(badgeView);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    super.onSuccess(result);
                }
            });
        }

    public  void RemoveBadgeView(final BadgeView badgeView, View view){
        badgeView.setBackground(10, getResources().getColor(R.color.colorPrimary));
        badgeView.setTargetView(view);
        badgeView.setBadgeCount(0);
        badgeViews.add(badgeView);
    }
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                new SharePerfenceUtils(MainActivity.this).keepUserAddress(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),location.getCity());
                XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=city&act=get_city&city_name="+StringUtils.StringToGBK(location.getCity())+"",null,new MyCallBack<String>(){
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                    }
                });
            }
        }
    };
    public  void updateAPK(){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=version&dev_type=android&version="+ AppUtils.getAppVersionName(MainActivity.this)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                //materialishProgress.show();
                try {
                    final JSONObject js = new JSONObject(StringUtils.base64ToString(result));
                    //if (){
                    if ((!AppUtils.getAppVersionName(MainActivity.this).equals(js.getString("serverVersion"))&&(js.getInt("hasfile")==1))){
                        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                        alert.setTitle("软件升级")
                                .setMessage(js.getString("info"))
                                .setPositiveButton("立即更新",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                dialog.dismiss();
                                                Intent intent = new Intent(MainActivity.this, UpdateService.class);
                                                try {
                                                    intent.putExtra("apkUrl", js.getString("filename"));
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                                startService(intent);
                                            }
                                        })
                                .setNegativeButton("下次再说",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int which) {
                                                try {
                                                    if (js.getInt("forced_upgrade")==1){
                                                       finish();
                                                    }else {
                                                        dialog.dismiss();
                                                    }
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                        alert.create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                //materialishProgress.dismiss();
                super.onFinished();
            }
        });
    }


}
