package com.handpay.coupon.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.Poi;
import com.handpay.coupon.CpApp;
import com.handpay.coupon.R;
import com.handpay.coupon.baidu.LocationService;
import com.handpay.coupon.base.BaseKey;
import com.handpay.coupon.databinding.ActivityMainBinding;
import com.handpay.coupon.rx.RxBus;
import com.handpay.coupon.rx.RxBusBaseMessage;
import com.handpay.coupon.rx.RxCodeConstants;
import com.handpay.coupon.ui.adapter.MyFragmentPagerAdapter;
import com.handpay.coupon.ui.fragment.CouponFragment;
import com.handpay.coupon.ui.fragment.MechantFragment;
import com.handpay.coupon.ui.fragment.ScanFragment;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.CommonUtils;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.RxToast;
import com.handpay.coupon.view.statusbar.StatusBarUtil;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ActivityMainBinding mBinding;
    private Toolbar toolbar;
    private FrameLayout llTitleMenu;
    private ViewPager vpContent;
    private ImageView ivCoupon;
    private ImageView ivMechant;
    private ImageView ivUserInfo;
    private ACache mACache;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mACache = ACache.get(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        // 设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.login_blue_nomal_top), 0);
//        initStatusView();
        initId();
        initRxBus();//顶部标题栏切换
        initContentFragment();
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {

                    }
                });

    }

    private LocationService locationService;

    @Override
    protected void onResume() {
        super.onResume();
        locationService = ((CpApp) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        if (locationService != null && !locationService.isStart()) {
            locationService.start();
        }
//        locationService.start();// 定位SDK,start之后会默认发起一次定位请求，开发者无须判断isstart并主动调用request
    }

    @Override
    protected void onStop() {
        if (locationService.isStart())
            locationService.stop(); //停止定位服务
        locationService.unregisterListener(mListener); //注销掉监听
        super.onStop();
    }

    private void initId() {
        toolbar = mBinding.toolbar;
        vpContent = mBinding.vpContent;
        ivMechant = mBinding.ivMechant;
        ivCoupon = mBinding.ivCoupon;
        ivUserInfo = mBinding.ivUserInfo;

        ivMechant.setOnClickListener(this);
        ivCoupon.setOnClickListener(this);
        ivUserInfo.setOnClickListener(this);
    }

    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new MechantFragment());
        mFragmentList.add(new CouponFragment());
        mFragmentList.add(new ScanFragment());
        // 注意使用的是：getSupportFragmentManager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗最少为2页)
        vpContent.setOffscreenPageLimit(3);
        vpContent.addOnPageChangeListener(MainActivity.this);
        //初始化商户列表为第一页
        mBinding.ivMechant.setSelected(true);
        vpContent.setCurrentItem(0);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.titlebar_local));//
            toolbar.setNavigationOnClickListener(new DebouncingOnClickListener() {
                @Override
                public void doClick(View v) {
                    //http://api.map.baidu.com/geocoder?address=北京市海淀区上地信息路9号奎科科技大厦&output=html&src=yourCompanyName|yourAppName
                    String url = BaseKey.BAIDU_MAP_MARKER_URL + "location=" + mACache.getAsObject(BaseKey.KEY_LATITUDE) + "," +
                            mACache.getAsObject(BaseKey.KEY_LONGTITUDE) + "&title=" + mACache.getAsString(BaseKey.KEY_POSITION) + "&content=" + mACache.getAsString(BaseKey.KEY_ADDRESS) +
                            "&output=html&src=" + getString(R.string.app_name);
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    /* 原生位置 */
                    intent.putExtra("KEY_LATITUDE",(double) mACache.getAsObject(BaseKey.KEY_LATITUDE));
                    intent.putExtra("KEY_LONGTITUDE",(double) mACache.getAsObject(BaseKey.KEY_LONGTITUDE));
                    intent.setClass(MainActivity.this,MapActivity.class);

                    /* H5网页位置 */
//                    intent.putExtra("URL_WEB", url);
//                    intent.putExtra("TITLE", "我的位置");
//                    intent.setClass(MainActivity.this, WebActivity.class);

                    startActivityForResult(intent, RESULT_OK);
                }
            });
        }
    }

    private void initRxBus() {
        RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE_TO_ONE, RxBusBaseMessage.class)
                .subscribe(new Action1<RxBusBaseMessage>() {
                    @Override
                    public void call(RxBusBaseMessage integer) {
                        mBinding.vpContent.setCurrentItem(1);
                    }
                });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                ivMechant.setSelected(true);
                ivCoupon.setSelected(false);
                ivUserInfo.setSelected(false);
                break;
            case 1:
                ivCoupon.setSelected(true);
                ivMechant.setSelected(false);
                ivUserInfo.setSelected(false);
                break;
            case 2:
                ivUserInfo.setSelected(true);
                ivCoupon.setSelected(false);
                ivMechant.setSelected(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_mechant:
                if (vpContent.getCurrentItem() != 0) {//不然cpu会有损耗
                    ivMechant.setSelected(true);
                    ivCoupon.setSelected(false);
                    ivUserInfo.setSelected(false);
                    vpContent.setCurrentItem(0);
                }
                break;
            case R.id.iv_coupon:
                if (vpContent.getCurrentItem() != 1) {
                    ivCoupon.setSelected(true);
                    ivMechant.setSelected(false);
                    ivUserInfo.setSelected(false);
                    vpContent.setCurrentItem(1);
                }
                break;
            case R.id.iv_user_info:
                if (vpContent.getCurrentItem() != 2) {
                    ivUserInfo.setSelected(true);
                    ivMechant.setSelected(false);
                    ivCoupon.setSelected(false);
                    vpContent.setCurrentItem(2);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
//                RxToast.info("绑定微信公众号");
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuilder sb = new StringBuilder(256);
                //时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                //location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                sb.append("time : ").append(location.getTime());
                sb.append("\nlocType : ").append(location.getLocType());// 定位类型
                sb.append("\nlocType description : ").append(location.getLocTypeDescription());// *****对应的定位类型说明*****
                sb.append("\nlatitude : ").append(location.getLatitude()); // 纬度
                sb.append("\nlontitude : ").append(location.getLongitude());// 经度
                sb.append("\nradius : ").append(location.getRadius());// 半径
                sb.append("\nCountryCode : ").append(location.getCountryCode());// 国家码
                sb.append("\nCountry : ").append(location.getCountry());// 国家名称
                sb.append("\ncityscape : ").append(location.getCityCode());// 城市编码
                sb.append("\ncity : ").append(location.getCity());// 城市
                sb.append("\nDistrict : ").append(location.getDistrict());// 区
                sb.append("\nStreet : ").append(location.getStreet());// 街道
                sb.append("\naddi : ").append(location.getAddrStr());// 地址信息
                sb.append("\nUserIndoorState: ").append(location.getUserIndoorState());// *****返回用户室内外判断结果*****
                sb.append("\nDirection(not all devices have value): ").append(location.getDirection());// 方向
                sb.append("\nlocations: ").append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName()).append(";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ").append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ").append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ").append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ").append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ").append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果      // 运营商信息
                    if (location.hasAltitude()) // *****如果有海拔高度*****
                        sb.append("\nheight : ").append(location.getAltitude());// 单位：米
                    sb.append("\noperationers : ").append(location.getOperators());// 运营商信息
                    sb.append("\ndescribe : ").append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ").append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                logMsg(location.getAddrStr(), sb.toString());
//                ClientEngine.getInstance().saveGlobal(BaseKey.KEY_ADDRESS,location.getAddrStr());
//                ClientEngine.getInstance().saveGlobal(BaseKey.KEY_LATITUDE,location.getLatitude());
//                ClientEngine.getInstance().saveGlobal(BaseKey.KEY_LONGTITUDE,location.getLongitude());
                mACache.put(BaseKey.KEY_ADDRESS, location.getAddrStr());
                mACache.put(BaseKey.KEY_POSITION, location.getPoiList().get(0).getName());
                mACache.put(BaseKey.KEY_LATITUDE, location.getLatitude());
                mACache.put(BaseKey.KEY_LONGTITUDE, location.getLongitude());
                LogT.w(location.getAddrStr() + ",Latitude=" + location.getLatitude() + ",Longitude=" + location.getLongitude());
                // http://api.map.baidu.com/marker?location=39.916979519873,116.41004950566&title=我的位置&content=百度奎科大厦&output=html
                locationService.stop(); //停止定位服务


            }
        }

    };

    public void logMsg(String address, String sb) {
        final String s = sb;
        final String addre = address;
        try {
            if (toolbar != null) {
                RxToast.info("当前位置：" + addre);
//                LogT.w("定位信息：" + s);
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        toolbar.post(new Runnable() {
//                            @Override
//                            public void run() {
//
//                            }
//                        });
//                    }
//                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
