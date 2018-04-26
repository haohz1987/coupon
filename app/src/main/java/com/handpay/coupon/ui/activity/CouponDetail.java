package com.handpay.coupon.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.base.CouponType;
import com.handpay.coupon.databinding.ActivityCouponDetailBinding;

public class CouponDetail extends BaseActivity<ActivityCouponDetailBinding>{


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_detail);
        if(getIntent()==null) return;
        setTitle("创建优惠券-"+ CouponType.getTextInfo(getIntent().getStringExtra("type")));

        showContentView();
        detailType(getIntent().getStringExtra("type"));
    }
    private void detailType(String type) {
       if(type.equals("GENERAL_COUPON")){
           bindingView.tvDetail.setText("描述文本");
           bindingView.etDetail.setHint("请输入描述文本");
       }else if(type.equals("GROUPON")){
           bindingView.tvDetail.setText("团购详情");
           bindingView.etDetail.setHint("请输入团购详情");
       }else if(type.equals("GIFT")){
           bindingView.tvDetail.setText("礼品名称");
           bindingView.etDetail.setHint("请输入礼品名称");
       }else if(type.equals("CASH")){
           bindingView.llDetail1.setVisibility(View.VISIBLE);
           bindingView.tvDetail.setText("起用金额");
           bindingView.etDetail.setHint("请输入起用金额,单位为分");
           bindingView.tvDetail1.setText("减免金额");
           bindingView.etDetail1.setHint("请输入减免金额,单位为分");
       }else if(type.equals("DISCOUNT")){
           bindingView.tvDetail.setText("打折额度");
           bindingView.etDetail.setMinLines(1);
           bindingView.etDetail.setInputType(InputType.TYPE_CLASS_NUMBER);
           bindingView.etDetail.setHint("请输入打折额度(百分比)，例如30 表示七折");
       }else if(type.equals("MEMBER_CARD")){


       }else if(type.equals("SCENIC_TICKET")){

       }else if(type.equals("MOVIE_TICKET")){

       }else if(type.equals("BOARDING_PASS")){

       }else if(type.equals("LUCKY_MONEY")){

       }
    }

}
