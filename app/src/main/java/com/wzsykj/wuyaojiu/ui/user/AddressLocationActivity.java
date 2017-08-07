package com.wzsykj.wuyaojiu.ui.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.wzsykj.wuyaojiu.R;
import com.wzsykj.wuyaojiu.adapter.AddressLocationRecyclerAdapter;
import com.wzsykj.wuyaojiu.base.BaseActivity;
import com.wzsykj.wuyaojiu.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by chen on 16/8/10.
 */
public class AddressLocationActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton back;
    private TextView select;
    private EditText selectValue;

    private ImageView myLocation;

    // 定位相关
    private LocationClient mLocClient;
    private MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private MyLocationData locData;

    MapView mMapView;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位

    private GeoCoder mSearch;
    private PoiSearch mPoiSearch;

    private List<PoiInfo> poiInfos;

    private RecyclerView recyclerView;
    private AddressLocationRecyclerAdapter selectLocationRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = this.getLayoutInflater().inflate(R.layout.address_location_activity, null);
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
        back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(this);
        select = (TextView) findViewById(R.id.select);
        select.setOnClickListener(this);
        selectValue = (EditText) findViewById(R.id.select_value);
        myLocation = (ImageView) findViewById(R.id.my_location);
        myLocation.setOnClickListener(this);

        mMapView = (MapView) findViewById(R.id.mapview);
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
        mBaiduMap
                .setMyLocationConfigeration(new MyLocationConfiguration(
                        mCurrentMode, true, null));

        mBaiduMap.setOnMapStatusChangeListener(new MyMapStatusChangeListener());

        //地理检索
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(new MyGeoCoderResultListener());

        //poi 检索
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(new MySearchResultListener());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        poiInfos = new ArrayList<>();
    }

    private void showData() {
        materialishProgress.dismiss();

        if (selectLocationRecyclerAdapter == null) {
            selectLocationRecyclerAdapter = new AddressLocationRecyclerAdapter(this, poiInfos, new AddressLocationRecyclerAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(int position) {

                }
            });
            recyclerView.setAdapter(selectLocationRecyclerAdapter);
        } else {
            selectLocationRecyclerAdapter.setData(poiInfos);
            selectLocationRecyclerAdapter.notifyDataSetChanged();
        }

        recyclerView.scrollToPosition(0);
    }


    /**
     * 定位 回调
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude()).accuracy(100)
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    /**
     * map 移动回调
     */
    private class MyMapStatusChangeListener implements BaiduMap.OnMapStatusChangeListener {
        @Override
        public void onMapStatusChangeStart(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChange(MapStatus mapStatus) {

        }

        @Override
        public void onMapStatusChangeFinish(MapStatus mapStatus) {
            reverseGeoCode();

        }
    }

    /**
     * poi 回调
     */
    private class MySearchResultListener implements OnGetPoiSearchResultListener {
        @Override
        public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
            //获取tPoiIndoor
            Toast.makeText(AddressLocationActivity.this, "", Toast.LENGTH_LONG)
                    .show();
        }

        @Override
        public void onGetPoiResult(PoiResult poiResult) {
            //获取POI检索结果
            Toast.makeText(AddressLocationActivity.this, "", Toast.LENGTH_LONG)
                    .show();
        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
            //获取Place详情页检索结果
            Toast.makeText(AddressLocationActivity.this, "", Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * 地理编码 回调
     */
    private class MyGeoCoderResultListener implements OnGetGeoCoderResultListener {
        @Override
        public void onGetGeoCodeResult(GeoCodeResult result) {
            materialishProgress.dismiss();
            //正地理编码回调
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(AddressLocationActivity.this, "查不到哦", Toast.LENGTH_LONG)
                        .show();
                return;
            } else {
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(result.getLocation()).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            //反低地理编码回调
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                Toast.makeText(AddressLocationActivity.this, "周边没有可以标示地区的建筑物哦", Toast.LENGTH_LONG)
                        .show();
                return;
            } else {
                poiInfos = result.getPoiList();
                showData();
                //已经可以获取 周边poi 集合
                // poiSearch();
            }
        }
    }

    /**
     * 地图移动后获取 最新中心点 查询 地理编码 地址信息
     */
    private void reverseGeoCode() {
        MapStatus mmapStatus = mBaiduMap.getMapStatus();
        LatLng center = mmapStatus.target;
        mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                .location(center));
    }

    /**
     * 关键字搜索 移动地图位置
     */
    private void geocode() {
        if (selectValue.getText().toString().trim().length() == 0) {
            Toast.makeText(AddressLocationActivity.this, "未输入关键字", Toast.LENGTH_SHORT).show();
            return;
        }
        mSearch.geocode(new GeoCodeOption().city("中国").address(selectValue.getText().toString()));
    }

    /**
     * 获取地址信息后 在查询 poi 坐标转地址,列表
     */
    private void poiSearch() {
        MapStatus mmapStatus = mBaiduMap.getMapStatus();
        LatLng center = mmapStatus.target;
        PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword("").location(center)
                .radius(500).pageNum(10);//半径500米 10个信息
        mPoiSearch.searchNearby(nearbySearchOption);
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;

        mPoiSearch.destroy();
        mSearch.destroy();
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                toRightFinish();
                break;

            case R.id.select:
                materialishProgress.show();
                geocode();
                break;

            case R.id.my_location:
                LatLng cenpt = new LatLng(locData.latitude, locData.longitude);
                MapStatus mMapStatus = new MapStatus.Builder()
                        .target(cenpt)
                        .zoom(18)
                        .build();
                MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                mBaiduMap.setMapStatus(mMapStatusUpdate);
                reverseGeoCode();
                break;
            default:
                break;
        }
    }
}
