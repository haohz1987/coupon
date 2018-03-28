package com.handpay.coupon.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseFragment;
import com.handpay.coupon.databinding.FragmentCouponSelectBinding;
import com.handpay.coupon.utils.CommonUtils;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.RxToast;

/**
 * Created by haohz on 2018/2/2.
 */

public class ScanFragment extends BaseFragment<FragmentCouponSelectBinding> implements View.OnClickListener {

    public static final String MONEY_ZERO = "0.00";
    public static final int MONEY_MAX_LEN = 6;
    /**
     * 记录当前输入金额的字符串。通过CommonUtils.formatMoney设置到mInputMoneyet
     */
    private String mMoneyText = MONEY_ZERO;
    private EditText etAmount = null;
    private EditText etCoupon = null;

    @Override
    public int setContent() {
        return R.layout.fragment_coupon_select;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();

        initView();

//        preferentialAmount = bindingView.etPreferentialAmount.getText().toString().trim();
//        bindingView.etPreferentialAmount.setText("2.00");

    }

    /**
     * 从后台返回前台依然会弹出系统键盘
     */
    @Override
    public void onResume() {
        super.onResume();
        LogT.w("屏蔽系统键盘");
        etAmount.clearFocus();
        CommonUtils.disableShowSoftInput(etAmount);
        CommonUtils.disableShowSoftInput(etCoupon);
//        etCoupon = bindingView.etPreferentialAmount;
//        etCoupon.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//        etCoupon.setOnTouchListener(new View.OnTouchListener() {
//            @SuppressLint("ClickableViewAccessibility")
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                int inType = etCoupon.getInputType();
//                etCoupon.setInputType(InputType.TYPE_NULL);
//                etCoupon.onTouchEvent(event);
//                etCoupon.setInputType(inType);
//                return true;
//            }
//        });
    }

    private void initView() {
        bindingView.rfHpKbCancle.setOnClickListener(this);
        bindingView.rfHpKbDot.setOnClickListener(this);
        bindingView.rfHpKbNum0.setOnClickListener(this);
        bindingView.rfHpKbNum1.setOnClickListener(this);
        bindingView.rfHpKbNum2.setOnClickListener(this);
        bindingView.rfHpKbNum3.setOnClickListener(this);
        bindingView.rfHpKbNum4.setOnClickListener(this);
        bindingView.rfHpKbNum5.setOnClickListener(this);
        bindingView.rfHpKbNum6.setOnClickListener(this);
        bindingView.rfHpKbNum7.setOnClickListener(this);
        bindingView.rfHpKbNum8.setOnClickListener(this);
        bindingView.rfHpKbNum9.setOnClickListener(this);
        bindingView.rfWechatBtn.setOnClickListener(this);
        bindingView.rfHpKbOk.setOnClickListener(this);
        bindingView.rfHpKbCancle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                restZero();
                return true;
            }
        });
        etAmount = bindingView.etOrderAmount;
        etCoupon = bindingView.etPreferentialAmount;

    }

    private void moneyText(String money) {
        mMoneyText = money;
        etAmount.setText(CommonUtils.formatMoneyDouble(Double.parseDouble(mMoneyText)));
        etAmount.setSelection(etAmount.getText().length());
    }

    private String dealInput(char c) {
        String money = mMoneyText;
        // 如果输入删除
        if ('-' == c) {
            if (MONEY_ZERO.equals(money) || money.length() == 1) {
                return MONEY_ZERO;
            }
            if ('.' == money.charAt(money.length() - 1)) {
                if (2 == money.length()) {
                    return MONEY_ZERO;
                } else {
                    return money.substring(0, money.length() - 2);
                }
            }
            return money.substring(0, money.length() - 1);
        }

        int indexOfDot = money.indexOf(".");
        // 如果当前没有输入小数点，且要输入的不是小数点的时候
        if (-1 == indexOfDot && '.' != c) {
            if (money.length() >= MONEY_MAX_LEN) {
                return money;
            }
        }
        // 如果遇到输入0。注意输入00.相当于调用两次输入0
        if ('0' == c) {
            if (MONEY_ZERO.equals(money)) {
                return MONEY_ZERO;
            }
            // 如果没有小数点，或者没有输入到小数点后两位，仍然可以输入
            if (indexOfDot == -1 || indexOfDot >= (money.length() - 2)) {
                return money + c;
            }
        }
        // 如果遇到输入1~9
        if ('1' <= c && '9' >= c) {
            if (MONEY_ZERO.equals(money)) {
                return c + "";
            }
            // 如果没有小数点，或者没有输入到小数点后两位，仍然可以输入
            if (indexOfDot == -1 || indexOfDot >= (money.length() - 2)) {
                return money + c;
            }
        }
        // 如果遇到输入点
        if ('.' == c) {
            if (MONEY_ZERO.equals(money)) {
                return "0.";
            }
            // 如果当前money没有'.'
            if (!money.contains(".")) {
                return (money + ".");
            }
        }
        return money;
    }

    @Override
    public void onClick(View v) {
        char c;
        int i = v.getId();
        if (i == R.id.rf_hpKbNum1) {
            c = '1';
            moneyText(dealInput(c));
        } else if (i == R.id.rf_hpKbNum2) {
            c = '2';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum3) {
            c = '3';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum4) {
            c = '4';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum5) {
            c = '5';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum6) {
            c = '6';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum7) {
            c = '7';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum8) {
            c = '8';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum9) {
            c = '9';
            moneyText(dealInput(c));

        } else if (i == R.id.rf_hpKbNum0) {
            c = '0';
            moneyText(dealInput(c));
        } else if (i == R.id.rf_hpKbDot) {
            c = '.';
            moneyText(dealInput(c));
        } else if (i == R.id.rf_hpKbCancle) {
            c = '-';
            moneyText(dealInput(c));
        } else if (i == R.id.rf_wechat_btn) {
            // TODO: 2018/3/2  更换扫码基类
//            startActivity(new Intent(getActivity(), CaptureActivity.class));
        } else if (i == R.id.rf_hpKbOk) {
            showNumerousMultiChoiceDialog();
        }
    }

    private void showNumerousMultiChoiceDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("优惠券:");
        builder.setIcon(R.mipmap.ic_launcher);
        final String[] items = new String[]{"满100减10", "满200减30", "9折", "8折"};/*设置多选的内容*/
        final boolean[] checkedItems = new boolean[]{false, false, false, false};/*设置多选默认状态*/

        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {/*设置多选的点击事件*/
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
//                if((checkedItems[0] && which==1) ||(checkedItems[1] && which==0) || (checkedItems[2] && which==3) || (checkedItems[3] && which==2)){
//                    RxToast.info("相同类型的优惠券不能同时使用");
//                }
                checkedItems[which] = isChecked;
            }
        });
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RxToast.info("跳转智能pos付款页面");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RxToast.info("取消");
            }
        });
        builder.setCancelable(true);
        builder.show();
    }

    protected void restZero() {
        bindingView.etOrderAmount.setText(MONEY_ZERO);
        bindingView.etPreferentialAmount.setText(MONEY_ZERO);
        mMoneyText = MONEY_ZERO;
    }
}
