package com.handpay.coupon;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.handpay.coupon.utils.CustomerExceptionHandler;
import com.handpay.coupon.utils.LogT;

/**
 * Created by haohz on 2018/2/2.
 */

public class CpApp extends Application {
    private static CpApp cpApp;
    public static Context mContext;
    public static CpApp getInstance() {
        return cpApp;
    }
    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        cpApp = this;
        mContext = getApplicationContext();
        LogT.init(true, Log.VERBOSE);//不输出到文件
        Thread.setDefaultUncaughtExceptionHandler(new CustomerExceptionHandler());
    }

}
