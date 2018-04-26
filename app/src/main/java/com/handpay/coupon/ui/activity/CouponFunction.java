package com.handpay.coupon.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.databinding.ActivityCouponFunctionBinding;
import com.handpay.coupon.utils.DateUtils;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.view.CalendarDialogView;
import com.handpay.coupon.view.CalendarView;
import com.handpay.coupon.view.SelectCouponsSheet;
import com.handpay.coupon.view.SwitchView;
import com.handpay.coupon.view.flowtag.FlowTagLayout;
import com.handpay.coupon.view.flowtag.OnTagSelectListener;
import com.handpay.coupon.view.flowtag.TagAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CouponFunction extends BaseActivity<ActivityCouponFunctionBinding>  implements SelectCouponsSheet.OnSheetSelected {

    private static final String[] dataType = {"固定日期区间", "固定时长"};
    private String[] flt1Str = {"外卖", "在线预订", "立即使用", "在线预约", "在线兑换", "在线商城", "车辆信息"};
    private String[] flt2Str = {"URL_NAME_TYPE_TAKE_AWAY", "URL_NAME_TYPE_RESERVATION", "URL_NAME_TYPE_USE_IMMEDIATELY", "URL_NAME_TYPE_APPOINTMENT"
            , "URL_NAME_TYPE_EXCHANGE", "URL_NAME_TYPE_MALL", "URL_NAME_TYPE_VEHICLE_INFORMATION"};
    private List<String> ftl1List = new ArrayList<>();
    private TagAdapter<String> ftl1Adapter;
    private ArrayAdapter<String> adapter;
    private boolean isDatePickerDialog4StartDate = false;
    private static CalendarDialogView mCalendarDialog;
    private Dialog mDialog;
    // 起始日期
    private Date startDate;
    // 结束日期
    private Date endDate;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_function);
        setTitle("创建优惠券-功能设置");

        mDialog = new Dialog(this, R.style.dialog);
        mCalendarDialog = new CalendarDialogView(this);
        mDialog.setContentView(mCalendarDialog);

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

        startDate = DateUtils.formatStringToDate(DateUtils.formatDateToString(new Date(), "yyyyMMdd"), "yyyyMMdd");
        endDate = new Date(startDate.getTime() + (24 * 60 * 60 * 1000) - 1);
        bindingView.tvBeginTimestamp.setText(DateUtils.formatDateToString(startDate, "yyyy-MM-dd"));
        bindingView.tvEndTimestamp.setText(DateUtils.formatDateToString(endDate, "yyyy-MM-dd"));

        mCalendarDialog.getCalendar().setOnItemClickListener(new CalendarView.OnItemClickListener() {
            @Override
            public void OnItemClick(Date selectedStartDate, Date selectedEndDate, Date downDate) {
                mDialog.dismiss();
                if (isDatePickerDialog4StartDate) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(downDate);
                    startDate = calendar.getTime();
                    bindingView.tvBeginTimestamp.setText(DateUtils.formatDateToString(startDate, "yyyy-MM-dd"));
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(downDate);
                    endDate = new Date(calendar.getTimeInMillis() + (24 * 60 * 60 * 1000) - 1);
                    if (!DateUtils.isOkDate(CouponFunction.this, startDate, endDate)) return;
                    bindingView.tvEndTimestamp.setText(DateUtils.formatDateToString(endDate, "yyyy-MM-dd"));
                }
            }
        });

        showContentView();
        adapter = new ArrayAdapter<String>(this, R.layout.myspinnerdropdown, dataType);
        bindingView.spDateInf.setAdapter(adapter);
        bindingView.spDateInf.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    LogT.w("选择了固定日期区间");
                    bindingView.trBeginTimestamp.setVisibility(View.VISIBLE);
                    bindingView.trEndTimestamp.setVisibility(View.VISIBLE);
                    bindingView.trFixedTerm.setVisibility(View.GONE);
                    bindingView.trFixedBeginTerm.setVisibility(View.GONE);
                } else if (position == 1) {
                    LogT.w("选择了固定时长");
                    bindingView.trBeginTimestamp.setVisibility(View.GONE);
                    bindingView.trEndTimestamp.setVisibility(View.GONE);
                    bindingView.trFixedTerm.setVisibility(View.VISIBLE);
                    bindingView.trFixedBeginTerm.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        bindingView.trBeginTimestamp.setOnClickListener(dbOnClickedListener);
        bindingView.trEndTimestamp.setOnClickListener(dbOnClickedListener);
        bindingView.btnNext.setOnClickListener(dbOnClickedListener);
    }

    private DebouncingOnClickListener dbOnClickedListener = new DebouncingOnClickListener() {
        @Override
        public void doClick(View v) {
            switch (v.getId()) {
                case R.id.tr_begin_timestamp:
                    isDatePickerDialog4StartDate = true;
                    mCalendarDialog.setCalendarData(startDate);
                    mDialog.show();

                    break;
                case R.id.tr_end_timestamp:
                    isDatePickerDialog4StartDate = false;
                    mCalendarDialog.setCalendarData(endDate);
                    mDialog.show();
                    break;
                case R.id.btn_next:
                    SelectCouponsSheet.showSheet(CouponFunction.this, CouponFunction.this, new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {

                        }
                    });
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
    public void onSheetClick(int whichBtn) {
        LogT.w("whickBtn="+whichBtn);
        String type="";
        if (whichBtn == R.id.btn_general) {
            type = "GENERAL_COUPON";
        } else if (whichBtn == R.id.btn_groupon) {
            type = "GROUPON";
        } else if (whichBtn == R.id.btn_discount) {
            type = "DISCOUNT";
        } else if (whichBtn == R.id.btn_gift) {
            type = "GIFT";
        } else if (whichBtn == R.id.btn_cash) {
            type = "CASH";
        } else if (whichBtn == R.id.btn_member_card) {
            type = "MEMBER_CARD";
        } else if (whichBtn == R.id.btn_scenic_ticket) {
            type = "SCENIC_TICKET";
        } else if (whichBtn == R.id.btn_movie_ticket) {
            type = "MOVIE_TICKET";
        } else if (whichBtn == R.id.btn_boarding_pass) {
            type = "BOARDING_PASS";
        } else if (whichBtn == R.id.btn_lucky_money) {
            type = "LUCKY_MONEY";
        } else if (whichBtn == R.id.cancel) {}
        if(!TextUtils.isEmpty(type)){
            Intent intent = new Intent(CouponFunction.this,CouponDetail.class);
            intent.putExtra("type",type);
            startActivity(intent);
        }
    }
}
