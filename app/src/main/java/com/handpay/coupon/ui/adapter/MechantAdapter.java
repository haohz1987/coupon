package com.handpay.coupon.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.handpay.coupon.R;
import com.handpay.coupon.bean.ManageBeans;
import com.handpay.coupon.databinding.ItemMechantBinding;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.PerfectClickListener;
import com.handpay.coupon.utils.glide.GlideUtils;

/**
 * Created by haohz on 2018/2/5.
 */

public class MechantAdapter extends BaseRecyclerViewAdapter<ManageBeans.ResultBean> {

    private ACache mACache;
    private Activity activity;

    public MechantAdapter(Activity activity) {
        this.activity = activity;
        mACache = ACache.get(activity);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_mechant);
    }

    private class ViewHolder extends BaseRecyclerViewHolder<ManageBeans.ResultBean, ItemMechantBinding> {

        ViewHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final ManageBeans.ResultBean resultBean, final int position) {

            if (resultBean == null) return;
            binding.setResultBean(resultBean);
            if(resultBean.getStoreQueue()<=0){
                binding.tvQueue.setText("无需排队");
            }else{
                binding.tvQueue.setText(resultBean.getStoreQueue()+"人在等");
            }
            GlideUtils.readCache(activity,
                    binding.ivStoreLogo,            //图片容器
                    resultBean.getStoreIcon(),      //网址
                    mACache,                        //缓存工具
                    resultBean.getStoreName());     //缓存名称-商户名

            //  图片点击后放大的动画
//                ViewHelper.setScaleX(itemView,0.8f);
//                ViewHelper.setScaleY(itemView,0.8f);
//                ViewPropertyAnimator.animate(itemView).scaleX(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();
//                ViewPropertyAnimator.animate(itemView).scaleY(1).setDuration(350).setInterpolator(new OvershootInterpolator()).start();

            binding.llMembership.setOnClickListener(new PerfectClickListener() {
                @Override
                protected void onNoDoubleClick(View v) {
                    MechantDetail.start(activity,resultBean,binding.ivStoreLogo);
                }
            });
        }
    }
}
