package com.wzsykj.wuyaojiu.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.google.gson.Gson;
import com.wzsykj.wuyaojiu.Bean.Shop;
import com.wzsykj.wuyaojiu.adapter.DeliverAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.service.LocationService;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import java.util.ArrayList;

/**
 * 配送门店
 */

public class DeliveryActivity extends BaseActivity {
    private ListView mendianListView;
    private ArrayList<String> address;
    private ImageButton back;
    private LocationService locationService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_delivery);
          back = (ImageButton) findViewById(R.id.back);
          mendianListView = (ListView) findViewById(R.id.mendian);
          address = new SharePerfenceUtils(this).getUserAddress();
          address = new SharePerfenceUtils(DeliveryActivity.this).getUserAddress();
          materialishProgress.show();
           XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=cart&act=select_shop&city_id="+new SharePerfenceUtils(this).getShop_id()[0]+"&xpoint="+new SharePerfenceUtils(this).getUserAddress().get(1)+"&ypoint="+new SharePerfenceUtils(this).getUserAddress().get(0)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=cart&act=select_shop&city_id="+new SharePerfenceUtils(DeliveryActivity.this).getShop_id()[0]+"&xpoint="+new SharePerfenceUtils(DeliveryActivity.this).getUserAddress().get(1)+"&ypoint="+new SharePerfenceUtils(DeliveryActivity.this).getUserAddress().get(0)+"");
                final Shop shop = new Gson().fromJson(StringUtils.base64ToString(result),Shop.class);
                DeliverAdapter adapter = new DeliverAdapter(DeliveryActivity.this,shop.getShop_list());
                mendianListView.setAdapter(adapter);
                super.onSuccess(result);
                mendianListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        /* new SharePerfenceUtils(DeliveryActivity.this).Keepshop_id(shop.getShop_list().get(i).getId(),shop.getShop_list().get(i).getName());
                        //ToastUtils.show(DeliveryActivity.this,"您选择了"+shop.getShop_list().get(i).getName());
                        new SharePerfenceUtils(DeliveryActivity.this).KeepisEdit(true);
                        finish();*/
                        Intent intent = new Intent();
                        //把返回数据存入Intent
                        intent.putExtra("id",shop.getShop_list().get(i).getId());
                        intent.putExtra("name",shop.getShop_list().get(i).getName());
                        //设置返回数据
                        setResult(0x01, intent);
                        //关闭Activity
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
        locationService = new LocationService(DeliveryActivity.this); /*((Application) getApplication()).locationService;*/
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
                new SharePerfenceUtils(DeliveryActivity.this).keepUserAddress(String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),location.getCity());
             }
        }
    };
}
