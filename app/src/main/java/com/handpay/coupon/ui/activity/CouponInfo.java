package com.handpay.coupon.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.base.CouponColorType;
import com.handpay.coupon.databinding.ActivityCouponInfoBinding;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.RxToast;
import com.handpay.coupon.view.ColorSelectDialog;
import com.handpay.coupon.view.SwitchView;
import com.handpay.coupon.view.flowtag.FlowTagLayout;
import com.handpay.coupon.view.flowtag.OnTagSelectListener;
import com.handpay.coupon.view.flowtag.TagAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CouponInfo extends BaseActivity<ActivityCouponInfoBinding> implements ColorSelectDialog.OnColorSelected {
    private ACache mACache;
    private String[] flt1Str = {"文本", "一维码", "二维码"};
    private String[] flt2Str = {"CODE_TYPE_TEXT", "CODE_TYPE_BARCODE", "CODE_TYPE_QRCODE"};
    private List<String> ftl1List = new ArrayList<>();
    private TagAdapter<String> ftl1Adapter;
    private StringBuilder sb = new StringBuilder();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_info);
        setTitle("创建优惠券-基本信息");
        showContentView();
        bindingView.ftl1.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_SINGLE);
        ftl1Adapter = new TagAdapter<>(this);
        bindingView.ftl1.setAdapter(ftl1Adapter);
        Collections.addAll(ftl1List, flt1Str);
        ftl1Adapter.onlyAddAll(ftl1List);

        bindingView.ftl1.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    LogT.w("当前选择的值为：" + flt1Str[selectedList.get(0)] + ",type=" + flt2Str[selectedList.get(0)]);
                } else {
                    LogT.w("没有选择标签");
                }
            }
        });
        bindingView.llColor.setOnClickListener(dbOnClickedListener);
        bindingView.etBranchName.setSelection(bindingView.etBranchName.length());
        if (bindingView.etBranchName.length() > 0) {
            bindingView.tvNumBranchName.setText(bindingView.etBranchName.length() + "/12");
        }

        bindingView.etBranchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int t = s.length();
                if (t >= 0 && t <= 12) {
                    bindingView.tvNumBranchName.setTextColor(getResources().getColor(R.color.black));
                    bindingView.tvNumBranchName.setText(t + "/12");
                } else {
                    RxToast.warning("文字长度超限");
                    bindingView.tvNumBranchName.setTextColor(getResources().getColor(R.color.light_red));
                    bindingView.tvNumBranchName.setText(t + "/12");
                }
            }
        });
        bindingView.btnNext.setOnClickListener(dbOnClickedListener);

//    bindingView..setOnStateChangedListener(stateChangedListener);
    }

    private DebouncingOnClickListener dbOnClickedListener = new DebouncingOnClickListener() {
        @Override
        public void doClick(View v) {
            switch (v.getId()) {
                case R.id.ll_color:
                    ColorSelectDialog.selectColor(CouponInfo.this, CouponInfo.this);
                    break;
                case R.id.btn_next:
                    startActivity(new Intent(CouponInfo.this, CouponFunction.class));
                    break;

            }
        }
    };

    private SwitchView.OnStateChangedListener stateChangedListener = new SwitchView.OnStateChangedListener() {
        @Override
        public void toggleToOn(SwitchView view) {
            switch (view.getId()) {
//                case R.id.tv_log_state:
//                    view.toggleSwitch(true);
//                    break;
            }
        }

        @Override
        public void toggleToOff(SwitchView view) {
            switch (view.getId()) {
//                case R.id.tv_log_state:
//                    view.toggleSwitch(false);
//
//                    break;
            }
        }
    };

    @Override
    public void onColorSelected(int position) {
        bindingView.ivSelect.setImageResource(CouponColorType.getResID("" + (position + 1)));
    }
}
