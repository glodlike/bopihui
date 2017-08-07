package com.wzsykj.wuyaojiu.ui.orther;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.CityList;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.service.LocationService;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;

import java.util.ArrayList;

public class CityListActivity extends BaseActivity {
    private ListView mendianListView;
    private ArrayList<String> address;
    private ImageButton back;
    private LocationService locationService;
    private ArrayList<String> CitynameList,CityIdList;
    private TextView nowCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        back = (ImageButton) findViewById(R.id.back);
        mendianListView = (ListView) findViewById(R.id.mendian);
        nowCity    = (TextView) findViewById(R.id.nowCity);
        address = new SharePerfenceUtils(this).getUserAddress();
        CityIdList = new ArrayList<>();
        CitynameList = new ArrayList<>();
        address = new SharePerfenceUtils(CityListActivity.this).getUserAddress();
        nowCity.setText(address.get(2));
        materialishProgress.show();
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=city&act=index&xpoint="+address.get(1)+"&ypoint="+address.get(0)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=city&act=index&xpoint="+address.get(1)+"&ypoint="+address.get(0)+"");
                final CityList cityList = new Gson().fromJson(StringUtils.base64ToString(result),CityList.class);
                for (int i = 0;i<cityList.getCity_list().size();i++){
                    if (!cityList.getCity_list().get(i).getPid().equals("0")){
                        CitynameList.add(cityList.getCity_list().get(i).getName());
                        CityIdList.add(cityList.getCity_list().get(i).getId());
                    }
                }
                ArrayAdapter adapter = new ArrayAdapter(CityListActivity.this,android.R.layout.simple_list_item_1,CitynameList);

                mendianListView.setAdapter(adapter);
                mendianListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        new SharePerfenceUtils(CityListActivity.this).Keepshop_id(CityIdList.get(i),CitynameList.get(i));
                        new SharePerfenceUtils(CityListActivity.this).keep_city_id(CityIdList.get(i));
                        new SharePerfenceUtils(CityListActivity.this).KeepisEdit(true);
                        finish();
                    }
                });
            }
            @Override
            public void onFinished() {
                super.onFinished();
                materialishProgress.dismiss();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        locationService = new LocationService(CityListActivity.this); /*((Application) getApplication()).locationService;*/
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        locationService.start();
        super.onResume();
    }
    private BDLocationListener mListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                new SharePerfenceUtils(CityListActivity.this).keepUserAddress(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),location.getCity());
            }
        }
    };
}
