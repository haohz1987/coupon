package com.handpay.coupon.base;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.handpay.coupon.R;
import com.handpay.coupon.databinding.ActivityBaseBinding;
import com.handpay.coupon.utils.CommonUtils;
import com.handpay.coupon.utils.PerfectClickListener;
import com.handpay.coupon.view.CustomDialog;
import com.handpay.coupon.view.statusbar.StatusBarUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by jingbin on 16/12/10.
 */
public class BaseActivity<SV extends ViewDataBinding> extends AppCompatActivity {

    // 布局view
    protected SV bindingView;
    private LinearLayout llProgressBar;
    private View refresh;
    private ActivityBaseBinding mBaseBinding;
    private AnimationDrawable mAnimationDrawable;
    private CompositeSubscription mCompositeSubscription;
    public BaseActivity mContext;
    protected static CustomDialog dialog;

    protected <T extends View> T getView(int id) {
        return (T) findViewById(id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        mBaseBinding = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.activity_base, null, false);
        bindingView = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, null, false);

        // content
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        bindingView.getRoot().setLayoutParams(params);
        RelativeLayout mContainer = (RelativeLayout) mBaseBinding.getRoot().findViewById(R.id.container);
        mContainer.addView(bindingView.getRoot());
        getWindow().setContentView(mBaseBinding.getRoot());

        // 设置透明状态栏
        StatusBarUtil.setColor(this, CommonUtils.getColor(R.color.login_blue_nomal_top),0);
        llProgressBar = getView(R.id.ll_progress_bar);
        refresh = getView(R.id.ll_error_refresh);
        ImageView img = getView(R.id.img_progress);

        // 加载动画
        mAnimationDrawable = (AnimationDrawable) img.getDrawable();
        // 默认进入页面就开启动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }

        setToolBar();
        // 点击加载失败布局
        refresh.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                showLoading();
                onRefresh();
            }
        });
        bindingView.getRoot().setVisibility(View.GONE);
    }

    /**
     * 设置titlebar
     */
    protected void setToolBar() {
        if(null!=mBaseBinding.toolBar){
            setSupportActionBar(mBaseBinding.toolBar);
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                //去除默认Title显示
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setHomeAsUpIndicator(R.mipmap.back_arrow);
            }
            mBaseBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

    }

    public void setTitle(CharSequence text) {
        mBaseBinding.toolBar.setTitle(text);
        mBaseBinding.toolBar.setTitleTextColor(getResources().getColor(R.color.white));
    }

    protected void showLoading() {
        if (llProgressBar.getVisibility() != View.VISIBLE) {
            llProgressBar.setVisibility(View.VISIBLE);
        }
        // 开始动画
        if (!mAnimationDrawable.isRunning()) {
            mAnimationDrawable.start();
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
    }

    protected void showContentView() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.GONE) {
            refresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

    protected void showError() {
        if (llProgressBar.getVisibility() != View.GONE) {
            llProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (refresh.getVisibility() != View.VISIBLE) {
            refresh.setVisibility(View.VISIBLE);
        }
        if (bindingView.getRoot().getVisibility() != View.GONE) {
            bindingView.getRoot().setVisibility(View.GONE);
        }
    }

    /**
     * 失败后点击刷新
     */
    protected void onRefresh() {

    }

    public void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }

    }

    public void removeSubscription() {
        if (this.mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            this.mCompositeSubscription.unsubscribe();
        }
    }
    public final void showAlertDialog(Context context, String title, String message, boolean cancelable, DialogInterface.OnClickListener oclOK) {
        showAlertDialog(context, title, message, cancelable, oclOK, null);
    }
    public final void showAlertDialog(Context context, String title, String message, boolean cancelable, DialogInterface.OnClickListener oclOK, DialogInterface.OnClickListener oclCancel) {
        showAlertDialog(context, title, message, cancelable, oclOK, oclCancel, null, null);
    }
    /**
     * @param context
     * @param title        标题
     * @param message      提示信息
     * @param cancelable   是否可取消
     * @param oclOK        确定事件
     * @param oclCancel    取消事件
     * @param middleButton 中间按钮文字
     * @param oclMiddle    中间按钮事件
     */
    public final void showAlertDialog(Context context, String title, String message, boolean cancelable, DialogInterface.OnClickListener oclOK, DialogInterface.OnClickListener oclCancel, String middleButton, DialogInterface.OnClickListener oclMiddle) {
        showAlertDialog(context, title, message, cancelable, null, oclOK, null, oclCancel, middleButton, oclMiddle);
    }
    // 对话框
    public final void showAlertDialog(Context context, String title, String message, boolean cancelable, String okString, DialogInterface.OnClickListener oclOK, String cancelString, DialogInterface.OnClickListener oclCancel, String middleButton, DialogInterface.OnClickListener oclMiddle) {
        class RunnableShowAlertDialog implements Runnable {
            private Context context;
            private String title, message, neutral, mOKString, mCancelString;
            private boolean cancelable;
            private DialogInterface.OnClickListener oclPositive, oclNeutral, oclNegative;
            private CustomDialog.Builder builder;

            public RunnableShowAlertDialog(Context context, String title, String message, boolean cancelable, String okString, DialogInterface.OnClickListener oclOK, String cancelString, DialogInterface.OnClickListener oclCancel, String middleButton, DialogInterface.OnClickListener oclMiddle) {
                this.context = context;
                this.title = title;
                this.message = message;
                this.mOKString = okString;
                this.mCancelString = cancelString;
                this.cancelable = cancelable;
                oclPositive = oclOK;
                oclNegative = oclCancel;
                neutral = middleButton;
                oclNeutral = oclMiddle;
            }

            @Override
            public void run() {
                try {
                    if (dialog != null && dialog.isShowing()) {
                        if (builder == null) {
                            builder = setAlertBuilder(new CustomDialog.Builder(context));
                        } else {
                            builder = setAlertBuilder(builder);
                        }
                    } else {
                        builder = setAlertBuilder(new CustomDialog.Builder(context));
                    }
                    dialog = builder.create();
                    dialog.setCanceledOnTouchOutside(cancelable);
                    dialog.setCancelable(cancelable);
                    dialog.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            /**
             * 设置Builder
             */
            private CustomDialog.Builder setAlertBuilder(CustomDialog.Builder builder) {
                builder.setTitle(title);
                builder.setMessage(stringFilter(message));
                // 按钮文字为空时，不显示按钮
                if (TextUtils.isEmpty(mOKString)) {
                    builder.setPositiveButton(android.R.string.ok, oclPositive);
                } else {
                    builder.setPositiveButton(mOKString, oclPositive);
                }
                if (oclNeutral != null) {
                    builder.setNeutralButton(neutral, oclNeutral);
                }
                if (oclNegative != null) {
                    // 取消事件为空，认为是没有取消按钮
                    if (TextUtils.isEmpty(mCancelString)) {
                        builder.setNegativeButton(android.R.string.cancel, oclNegative);
                    } else {
                        builder.setNegativeButton(mCancelString, oclNegative);
                    }
                }
                return builder;
            }
        }
        this.runOnUiThread(new RunnableShowAlertDialog(context, title, message, cancelable, okString, oclOK, cancelString, oclCancel, middleButton, oclMiddle));
    }
    /**
     * 替换、过滤特殊字符
     */
    public static String stringFilter(String str) throws PatternSyntaxException {
        str = str.replaceAll("【", "[").replaceAll("】", "]").replaceAll("！", "!").replaceAll("，", ",").replaceAll("&", "\n");//替换中文标号
        String regEx = "[『』]"; // 清除掉特殊字符
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
