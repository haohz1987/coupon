package com.handpay.coupon.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseFragment;
import com.handpay.coupon.bean.CouponBean;
import com.handpay.coupon.databinding.FragmentRecycleBinding;
import com.handpay.coupon.ui.activity.MainActivity;
import com.handpay.coupon.ui.adapter.CouponAdapter;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.AssetsUtil;
import com.handpay.coupon.utils.RxToast;

/**
 * Created by haohz on 2018/2/2.
 */

public class VerifiedCoupon extends BaseFragment<FragmentRecycleBinding> {

    // 初始化完成后加载数据
    private boolean isPrepared = false;
    // 第一次显示时加载数据，第二次不显示
    private boolean isFirst = true;
    // 是否正在刷新（用于刷新数据时返回页面不再刷新）
    private boolean mIsLoading = false;
    private ACache aCache;
    private MainActivity activity;
    private CouponAdapter couponAdapter;
    //    private HotMovieBean mHotMovieBean;
    private View mHeaderView = null;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        aCache = ACache.get(getActivity());
        couponAdapter = new CouponAdapter(activity);
        setAdapter();
//        mHotMovieBean = (HotMovieBean) aCache.getAsObject(Constants.ONE_HOT_MOVIE); 有网络后将数据缓存到acache
//        isPrepared = true;
    }

    //懒加载
    @Override
    protected void loadData() {
        if (!isPrepared || !mIsVisible) {
            return;
        }
        if (!mIsLoading) {//如果网络缓存时间戳判断，在次数添加
            showLoading();
            postDelayLoad();
        } else {
            if (!isFirst) return;
            bindingView.listOne.postDelayed(new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        setAdapter();
                        showContentView();
                    }
                }
            }, 150);
        }
    }

    private void setAdapter() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bindingView.listOne.setLayoutManager(mLayoutManager);

        // 下拉出提示不会产生出现刷新头的bug，不加拉不下来
        bindingView.listOne.setPullRefreshEnabled(false);
        bindingView.listOne.clearHeader();

        bindingView.listOne.setLoadingMoreEnabled(true);
        // 不加滑动不流畅
        bindingView.listOne.setNestedScrollingEnabled(false);
        bindingView.listOne.setHasFixedSize(false);
        getMechantList();
        bindingView.listOne.setAdapter(couponAdapter);
        couponAdapter.notifyDataSetChanged();
        isFirst = false;
    }

    /**
     * 延迟执行，避免卡顿
     * 加同步锁，避免重复加载
     */
    private void postDelayLoad() {
        synchronized (this) {
            if (!mIsLoading) {
                mIsLoading = true;
                bindingView.listOne.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter();
                    }
                }, 150);
            }
        }
    }

    private void getMechantList() {
        String temp = AssetsUtil.loadlocalData(activity, "getCoupList.json");
        if (TextUtils.isEmpty(temp)) {
            RxToast.info("获取temp失败："+temp);
            return;
        }
//        LogT.w(temp);
        CouponBean couponBean = new Gson().fromJson(temp, CouponBean.class);
        couponAdapter.clear();
        couponAdapter.addAll(couponBean.getResult());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    @Override
    public int setContent() {
        return R.layout.fragment_recycle;
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

}
