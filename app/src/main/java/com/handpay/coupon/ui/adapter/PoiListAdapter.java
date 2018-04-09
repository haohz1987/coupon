package com.handpay.coupon.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.handpay.coupon.R;
import com.handpay.coupon.bean.PoiListBean;
import com.handpay.coupon.databinding.ItemPoilistBinding;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.DebouncingOnClickListener;

/**
 * Created by haohz on 2018/2/5.
 */

public abstract class PoiListAdapter extends BaseRecyclerViewAdapter<PoiListBean.BusinessListBean> {

    private ACache mACache;
    private Activity activity;

    public PoiListAdapter(Activity activity) {
        this.activity = activity;
        mACache = ACache.get(activity);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, R.layout.item_poilist);
    }
    public abstract void doStoreClick(int available_state,String poi_id);

    private class ViewHolder extends BaseRecyclerViewHolder<PoiListBean.BusinessListBean, ItemPoilistBinding> {

        ViewHolder(ViewGroup context, int layoutId) {
            super(context, layoutId);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(final PoiListBean.BusinessListBean resultBean, final int position) {

            if (resultBean == null) return;
            binding.setBusinessListBean(resultBean);
            if (resultBean.getBase_info().getAvailable_state() == 1) {
                binding.ivCheck.setImageResource(R.mipmap.in_check_error);
                binding.ivCheck.setVisibility(View.VISIBLE);
            } else if (resultBean.getBase_info().getAvailable_state() == 2) {
                binding.ivCheck.setImageResource(R.mipmap.in_check);
                binding.ivCheck.setVisibility(View.VISIBLE);
            } else if (resultBean.getBase_info().getAvailable_state() == 3) {
                binding.ivCheck.setVisibility(View.GONE);
            } else if (resultBean.getBase_info().getAvailable_state() == 4) {
                binding.ivCheck.setImageResource(R.mipmap.in_check_error);
                binding.ivCheck.setVisibility(View.VISIBLE);
            }

            binding.llStore.setOnClickListener(new DebouncingOnClickListener() {
                @Override
                public void doClick(View v) {
                    doStoreClick(resultBean.getBase_info().getAvailable_state(),resultBean.getBase_info().getPoi_id());
                }
            });
        }
    }
}
