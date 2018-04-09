package com.handpay.coupon.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.View;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseFragment;
import com.handpay.coupon.databinding.FragmentCouponBinding;
import com.handpay.coupon.ui.adapter.MyFragmentPagerAdapter;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.view.SelectCouponsSheet;

import java.util.ArrayList;

/**
 * Created by haohz on 2018/2/2.
 */

public class CouponFragment extends BaseFragment<FragmentCouponBinding> implements  SelectCouponsSheet.OnSheetSelected {

    private ArrayList<String> mTitleList = new ArrayList<>(3);
    private ArrayList<Fragment> mFragments = new ArrayList<>(3);

    @Override
    public int setContent() {
        return R.layout.fragment_coupon;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLoading();
        initFragmentList();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        bindingView.vpCoupon.setAdapter(myAdapter);
        // 左右预加载页面的个数
        bindingView.vpCoupon.setOffscreenPageLimit(2);
        myAdapter.notifyDataSetChanged();
        bindingView.tabCoupon.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabCoupon.setupWithViewPager(bindingView.vpCoupon);
        showContentView();
//        // item点击跳转
//        initRxBus();
        bindingView.btnCreate.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                SelectCouponsSheet.showSheet(getActivity(), CouponFragment.this, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {}
                });
            }
        });

    }
    private void initFragmentList() {
        mTitleList.add("未使用");
        mTitleList.add("已使用");
        mTitleList.add("已核销");
        mFragments.add(new UnusedCoupon());
        mFragments.add(new UsedCoupon());
        mFragments.add(new VerifiedCoupon());
    }

    @Override
    public void onSheetClick(int whichBtn) {
        //        if (whichBtn == R.id.btn_cashcoupon) {
//            startActivity(new Intent(CouponsActivity.this, CreateCashCouponsActivity.class));
//        } else if (whichBtn == R.id.btn_discount) {
//            startActivity(new Intent(CouponsActivity.this, CreateCouponActivity.class));
//        } else if (whichBtn == R.id.btn_exchange) {
//            startActivity(new Intent(CouponsActivity.this, CreateExchangeActivity.class));
//        }
    }
}
