package com.handpay.coupon.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.databinding.ActivityQueueBinding;
import com.handpay.coupon.utils.RxToast;

/**
 * Created by ggao
 * from on 2018/1/5.
 */

public class MechantQueue extends BaseActivity<ActivityQueueBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        setTitle("排号提醒");
        showContentView();
        bindingView.btnQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkSwiperAndPrintBill(null);
                RxToast.info("暂不支持打印");
            }
        });
    }

}