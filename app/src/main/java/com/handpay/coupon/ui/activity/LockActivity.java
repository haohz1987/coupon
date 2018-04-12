package com.handpay.coupon.ui.activity;

import android.os.Bundle;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.databinding.ActivityMapBinding;
import com.handpay.coupon.utils.LogT;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by haohz on 2018/4/10.
 */
public class LockActivity extends BaseActivity<ActivityMapBinding> {

private Lock lock = new ReentrantLock();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

    }
    //同步方法1
    private void method(Thread thread){
        lock.lock();
        LogT.w("线程"+thread.getName()+"获得锁");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            LogT.w("线程"+thread.getName()+"释放锁");
            lock.unlock();
        }

    }

}
