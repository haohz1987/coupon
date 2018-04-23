package com.handpay.coupon.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.handpay.coupon.R;
import com.handpay.coupon.base.CouponType;
import com.handpay.coupon.databinding.ItemCouponBinding;
import com.handpay.coupon.entity.GetCardData;
import com.handpay.coupon.ui.activity.CouponInfo;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.glide.GlideUtils;

/**
 * Created by haohz on 2018/2/5.
 */

public class CouponAdapter extends BaseRecyclerViewAdapter<GetCardData> {

    private ACache mACache;
    private Activity activity;

    public CouponAdapter(Activity activity) {
        this.activity = activity;
        mACache = ACache.get(activity);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
        return new ViewHolder(parent, R.layout.item_coupon);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<GetCardData, ItemCouponBinding> {

        ViewHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final GetCardData resultBean, final int position) {

            if (resultBean == null) return;
            binding.setResultBean(resultBean);
            GlideUtils.readCache(activity,
                    binding.ivStoreLogo,            //图片容器
                    resultBean.getLogo_url(),      //网址
                    mACache,                        //缓存工具
                    resultBean.getLogo_url());     //缓存名称-商户名

            binding.tvCardType.setText(CouponType.getTextInfo(resultBean.getCard_type()));
            binding.llMembership.setBackgroundColor(Color.parseColor(resultBean.getColor()));
            binding.llMembership.setOnClickListener(new DebouncingOnClickListener() {
                @Override
                public void doClick(View v) {
                    LogT.w("点击llMembership的位置_bean：" + resultBean.toString() + ",点击的位置：" + position);
                    activity.startActivity(new Intent(activity, CouponInfo.class));
                }
            });
            binding.llGrant.setOnClickListener(new DebouncingOnClickListener() {
                @Override
                public void doClick(View v) {
                    LogT.w("点击llGrant的位置_bean：" + resultBean.toString() + ",点击的位置：" + position);

                }
            });

        }
    }
}
