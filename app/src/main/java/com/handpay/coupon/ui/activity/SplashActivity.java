package com.handpay.coupon.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.handpay.coupon.R;
import com.handpay.coupon.databinding.ActivitySplashBinding;
import com.handpay.coupon.utils.CommonUtils;
import com.handpay.coupon.utils.PerfectClickListener;

/**
 * Created by haohz on 2018/4/2.
 */

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding mBinding;
    private boolean animationEnd;
    private boolean isIn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        // 先显示默认图
        mBinding.ivDefultPic.setImageDrawable(CommonUtils.getDrawable(R.mipmap.default_homebg));
        // TODO: 2018/4/10 正式版改成1500 
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mBinding.ivDefultPic.setVisibility(View.GONE);
            }
        }, 100);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
            }
        }, 500);
        mBinding.tvJump.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                toMain();
            }
        });
    }

    private void toMain() {
        if (isIn) return;
        Intent intent = new Intent(this, MainActivity.class);//LoginActivity
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
        isIn = true;
    }

}
