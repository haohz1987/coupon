package com.handpay.coupon.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.databinding.ActivityCouponCreateInfoBinding;
import com.handpay.coupon.entity.GetCardData;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.glide.GlideUtils;

public class CouponCreateInfo extends BaseActivity<ActivityCouponCreateInfoBinding> {

    private ACache mACache;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_create_info);
        setTitle("发券");
        mACache = ACache.get(this);
        showContentView();

        if (getIntent() == null)
            return;
        String card_id = getIntent().getStringExtra("card_id");

        //自定义code
        if (getIntent().getBooleanExtra("user_custom_code", false))
            bindingView.llCode.setVisibility(View.VISIBLE);
        bindingView.btnNext.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                getTicket();
            }
        });
    }

    private void getTicket() {
        String ticket = "gQEr8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xLy1YV21CQi1sc3dUS0p1M3NjRjVuAAIE3kqwVQMEgDPhAQ==";
        GlideUtils.readCache(CouponCreateInfo.this,
                bindingView.ivQr,            //图片容器
                "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + ticket,      //网址
                mACache,                        //缓存工具
                ticket);     //缓存名称-商户名
    }


}
