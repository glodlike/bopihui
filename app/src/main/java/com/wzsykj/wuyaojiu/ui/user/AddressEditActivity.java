package com.wzsykj.wuyaojiu.ui.user;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.wzsykj.wuyaojiu.utils.StringUtils;
import com.wzsykj.wuyaojiu.utils.ToastUtils;
import com.wzsykj.wuyaojiu.utils.XUtils;
import com.wzsykj.wuyaojiu.Bean.Address;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.network.AppHttp;
import com.wzsykj.wuyaojiu.utils.MyCallBack;
import com.wzsykj.wuyaojiu.utils.SharePerfenceUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chen on 16/8/10.
 */
public class AddressEditActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private LinearLayout selectLocation;
    private TextView btn;
    private String privince;
    private String city;
    private String block;
    private EditText nameET,phoneET,addressET;
    private ArrayList<String> userList;
    private Address.ConsigneeListBean bean = null;
    private Intent intent;
    private CheckBox checkBox;
    private Map<Integer,String> map = new HashMap<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.address_edit_activity, null);
        setContentView(contentView);
        initErrorView(contentView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                hiddeErrorView();
//                materialishProgress.show();
//                laodData();
            }
        });
        initView();
    }
    private void initView() {
        checkBox  = (CheckBox) findViewById(R.id.checkbox);
        userList =new SharePerfenceUtils(this).getUserInfo();
        nameET  =   (EditText)   findViewById(R.id.nameET);
        phoneET  =  (EditText)  findViewById(R.id.phoneET);
        addressET = (EditText) findViewById(R.id.detailadress);
        textView = (TextView) findViewById(R.id.adress);
        btn = (TextView) findViewById(R.id.addAddress);
        btn.setOnClickListener(this);
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        selectLocation = (LinearLayout) findViewById(R.id.select_location);
        selectLocation.setOnClickListener(this);
        intent  = getIntent();
        if (intent.getAction().equals("edit")){
            btn.setText("保存地址");
            bean = (Address.ConsigneeListBean) intent.getSerializableExtra("data");
            phoneET.setText(bean.getMobile());
            nameET.setText(bean.getConsignee());
            textView.setText(bean.getRegion_lv2_name()+" "+bean.getRegion_lv3_name()+" "+bean.getRegion_lv4_name());
            addressET.setText(bean.getAddress());
            privince = bean.getRegion_lv2_name();
            city     = bean.getRegion_lv3_name();
            block    = bean.getRegion_lv4_name();
            if (bean.getIs_default().equals("1")){
                checkBox.setChecked(true);
            }else{
                checkBox.setChecked(false);
            }
        }
        loadData();
    }
    private void loadData() {

        showData();
    }
    private void showData() {
        //materialishProgress.dismiss();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                //toRightFinish();
                finish();
                break;
            case R.id.select_location:
                CityPicker cityPicker = new CityPicker.Builder(AddressEditActivity.this).textSize(18)
                        .title("选择地址")
                        .titleBackgroundColor("#c20009")
                        .confirTextColor("#ffffff")
                        .cancelTextColor("#ffffff")
                        .province("浙江")
                        .city("温州")
                        .district("鹿城区")
                        .textColor(Color.parseColor("#000000"))
                        .provinceCyclic(false)
                        .cityCyclic(false)
                        .districtCyclic(false)
                        .visibleItemsCount(7)
                        .itemPadding(10)
                        .build();
                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... citySelected) {
                         textView.setText(citySelected[0]+" "+citySelected[1]+" "+citySelected[2]);
                         privince = citySelected[0];
                         city     = citySelected[1];
                         block     = citySelected[2];
                        try {
                            map.put(0, URLEncoder.encode(privince, "GBK"));
                            map.put(1,URLEncoder.encode(city, "GBK"));
                            map.put(2,URLEncoder.encode(block, "GBK"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case R.id.addAddress:
                if (intent.getAction().equals("edit")){

                    if (checkBox.isChecked()){
                        uploadAddress(bean.getId(),"1");
                    }else{
                        uploadAddress(bean.getId(),"0");
                    }

                }else {
                    if (checkBox.isChecked()) {
                        uploadAddress("1");
                    } else {
                        uploadAddress("0");
                    }
                }
                break;
            default:
                break;
        }
    }
    public void uploadAddress(final String is_default){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=save&is_default="+is_default+"&region_lv2_str="+map.get(0)+"&region_lv3_str="+map.get(1)+"&region_lv4_str="+map.get(2)+"&consignee="+StringUtils.StringToGBK(nameET.getText().toString().trim())+"&address="+StringUtils.StringToGBK(addressET.getText().toString().trim())+"&mobile="+phoneET.getText().toString().trim()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&shop_id="+new SharePerfenceUtils(AddressEditActivity.this).getShop_id()[0]+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println("===="+AppHttp.BASE_URL_NEW+"ctl=uc_address&act=save&is_default="+is_default+"&region_lv2_str="+map.get(0)+"&region_lv3_str="+map.get(1)+"&region_lv4_str="+map.get(2)+"&consignee="+StringUtils.StringToGBK(nameET.getText().toString().trim())+"&address="+StringUtils.StringToGBK(addressET.getText().toString().trim())+"&mobile="+phoneET.getText().toString().trim()+"&email="+userList.get(0)+"&pwd="+userList.get(1)+"&shop_id="+new SharePerfenceUtils(AddressEditActivity.this).getShop_id()[0]+"");
                try {
                    JSONObject js = new JSONObject(StringUtils.base64ToString(result));
                    if (js.getInt("add_status")==1){
                        ToastUtils.show(AddressEditActivity.this,js.getString("info"));
                        finish();
                    }else {
                        Toast.makeText(AddressEditActivity.this,js.getString("info"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }
    public void uploadAddress(final String id, final String is_default){
        XUtils.Get(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=save&id="+id+"&is_default="+is_default+"&region_lv2_str="+StringUtils.StringToGBK(privince)+"&region_lv3_str="+StringUtils.StringToGBK(city)+"&region_lv4_str="+StringUtils.StringToGBK(block)+"&" +
                "consignee="+StringUtils.StringToGBK(nameET.getText().toString().trim())+"&address="+StringUtils.StringToGBK(addressET.getText().toString().trim())+"&mobile="+phoneET.getText().toString().trim()
                +"&email="+userList.get(0)+"&pwd="+userList.get(1)+"",null,new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                System.out.println(AppHttp.BASE_URL_NEW+"ctl=uc_address&act=save&id="+id+"&is_default="+is_default+"&region_lv2_str="+StringUtils.StringToGBK(privince)+"&region_lv3_str="+StringUtils.StringToGBK(city)+"&region_lv4_str="+StringUtils.StringToGBK(block)+"&" +
                        "consignee="+StringUtils.StringToGBK(nameET.getText().toString().trim())+"&address="+StringUtils.StringToGBK(addressET.getText().toString().trim())+"&mobile="+phoneET.getText().toString().trim()
                        +"&email="+userList.get(0)+"&pwd="+userList.get(1)+"");
                try {
                    JSONObject js = new JSONObject(StringUtils.base64ToString(result));
                    if (js.getInt("add_status")==1){
                        ToastUtils.show(AddressEditActivity.this,js.getString("info"));
                        finish();
                    }else {
                        Toast.makeText(AddressEditActivity.this,js.getString("info"),Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                super.onSuccess(result);
            }
            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }
}
