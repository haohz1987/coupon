package com.handpay.coupon;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.handpay.coupon.baidu.LocationService;
import com.handpay.coupon.db.DbCore;
import com.handpay.coupon.utils.LogT;

/**
 * Created by haohz on 2018/2/2.
 */

public class CpApp extends Application {

    private static CpApp cpApp;
    public static Context mContext;
    public LocationService locationService;
    public Vibrator mVibrator;

    public static CpApp getInstance() {
        return cpApp;
    }
    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        cpApp = this;
        mContext = getApplicationContext();
        DbCore.init(this);
        LogT.init(true, Log.VERBOSE);//不输出到文件
//        Thread.setDefaultUncaughtExceptionHandler(new CustomerExceptionHandler());

        //初始化百度基础定位
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

    }
}
