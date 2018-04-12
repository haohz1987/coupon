package com.handpay.coupon.ui.activity;

import android.os.Bundle;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.databinding.ActivityMapBinding;
import com.handpay.coupon.utils.LogT;

/**
 * Created by haohz on 2018/4/10.
 */
public class MapActivity extends BaseActivity<ActivityMapBinding> {

    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private BitmapDescriptor bitmap;
    private String address;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SDKInitializer.initialize(getApplicationContext());
        showContentView();
        setTitle("手动定位");
        // 获取地图控件引用
        mMapView = findViewById(R.id.bmapView);
        bindingView.bmapView.getMap();
        mBaiduMap = mMapView.getMap();
        //设置是否显示比例尺控件
        mMapView.showScaleControl(true);
        //设置是否显示缩放控件
        mMapView.showZoomControls(true);
        // 删除百度地图LoGo
        mMapView.removeViewAt(1);
        // 设置marker图标
        bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.maker);

        if(getIntent()!=null){
            //先清除图层
            mBaiduMap.clear();
            latitude = getIntent().getDoubleExtra("KEY_LATITUDE",31.253898);
            longitude = getIntent().getDoubleExtra("KEY_LONGTITUDE",121.45987);
            LogT.w("当前默认的额经纬度为，（"+latitude+","+longitude+")");
            // 定义Maker坐标点
            LatLng point = new LatLng(latitude, longitude);
            // 构建MarkerOption，用于在地图上添加Marker
            MarkerOptions options = new MarkerOptions().position(point)
                    .icon(bitmap);
            // 在地图上添加Marker，并显示
            mBaiduMap.addOverlay(options);
            //设置默认的显示比例
            MapStatusUpdate newLatLngZoom = MapStatusUpdateFactory.newLatLngZoom(point,mBaiduMap.getMaxZoomLevel() -4);
            mBaiduMap.animateMapStatus(newLatLngZoom);
            //设置经纬度（参数一是纬度，参数二是经度）
            MapStatusUpdate mapstatusupdate =  MapStatusUpdateFactory.newLatLng(new LatLng(latitude,longitude));
            mBaiduMap.setMapStatus(mapstatusupdate); //对地图的中心点进行更新，

        }

        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {

            @Override
            public boolean onMapPoiClick(MapPoi arg0) {
                // TODO Auto-generated method stub
//                RxToast.info("onMapPoiClick:"+arg0);
                return false;
            }

            //此方法就是点击地图监听
            @Override
            public void onMapClick(LatLng latLng) {
                //获取经纬度
                latitude = latLng.latitude;
                longitude = latLng.longitude;
                LogT.w("点击地图监听:latitude="+ latitude +",longitude="+ longitude);
                mBaiduMap.clear();
                LatLng point = new LatLng(latitude, longitude);
                MarkerOptions options = new MarkerOptions().position(point)
                        .icon(bitmap);
                mBaiduMap.addOverlay(options);
//                //实例化一个地理编码查询对象
//                GeoCoder geoCoder = GeoCoder.newInstance();
//                //设置反地理编码位置坐标
//                ReverseGeoCodeOption op = new ReverseGeoCodeOption();
//                op.location(point);
//                //发起反地理编码请求(经纬度->地址信息)
//                geoCoder.reverseGeoCode(op);
//                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
//                    @Override
//                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
//                        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//                            //没有检索到结果
//                            RxToast.info("未检测到定位信息");
//                        }
//                        //获取地理编码结果
//                        //获取点击的坐标地址
//                        address = result.getAddress();
//                        RxToast.info("onGetReverseGeoCodeResult_当前位置："+address);
//                        LogT.w("点击处的位置："+address+",latitude="+ latitude +",longitude="+ longitude);
//                    }
//
//                    @Override
//                    public void onGetGeoCodeResult(GeoCodeResult result) {
//                        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//                            //没有检索到结果
//                            RxToast.info("未检测到定位信息");
//                        }
//                        RxToast.info("onGetReverseGeoCodeResult_当前位置："+ result.getAddress());
//                    }
//                });
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBaiduMap.clear();
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
}
