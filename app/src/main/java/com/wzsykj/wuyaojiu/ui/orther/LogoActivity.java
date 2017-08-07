package com.wzsykj.wuyaojiu.ui.orther;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.CityID;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.service.LocationService;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class LogoActivity extends BaseActivity {
    private LocationService locationService;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }
            @Override
            public void onFinish() {
                XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=init",null,new MyCallBack<String>(){
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        try {
                            JSONObject jsonObject = new JSONObject(StringUtils.base64ToString(result));
                            if (jsonObject.getInt("status")==1){
                            if (jsonObject.toString().contains("start_page")||jsonObject.getJSONArray("start_page").length()==0){
                                Intent toMain = new Intent(LogoActivity.this, MainActivity.class);
                                startActivity(toMain);
                                finish();
                            }else {
                                Intent intent = new Intent(LogoActivity.this,WelcomeActivity.class);
                                intent.putExtra("start",StringUtils.base64ToString(result));
                                startActivity(intent);
                                  }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        };
        timer.start();

    }

    @Override
    public void onResume() {
        //locationService = ((Application) getApplication()).locationService;
        locationService = new LocationService(this);
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
        super.onResume();
    }
    @Override
    protected void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                new SharePerfenceUtils(LogoActivity.this).keepUserAddress(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),location.getCity());
                XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=city&act=get_city&city_name="+location.getCity()+"",null,new MyCallBack<String>(){
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(""+AppHttp.BASE_URL_NEW+"ctl=city&act=get_city&city_name="+location.getCity()+"");
                        CityID cityID  = new Gson().fromJson(StringUtils.base64ToString(result),CityID.class);
                        new SharePerfenceUtils(LogoActivity.this).Keepshop_id(cityID.getCurrent_city().getId(),location.getCity());
                        new SharePerfenceUtils(LogoActivity.this).keep_city_id(cityID.getCurrent_city().getId());
                        super.onSuccess(result);
                    }
                });
            }
        }
    };
}
