package com.handpay.coupon.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.databinding.ActivityBusinessBinding;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.DebouncingOnClickListener;

public class BusinessInfo extends BaseActivity<ActivityBusinessBinding>{

    private ACache mACache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        setTitle("店铺信息");
        showContentView();
        mACache = ACache.get(this);

        bindingView.btnUpload.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
//                startActivity(new Intent(BusinessInfo.this,BusinessInfo.class));

            }
        });
    }


}
