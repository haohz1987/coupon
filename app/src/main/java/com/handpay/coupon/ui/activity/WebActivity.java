package com.handpay.coupon.ui.activity;

import android.os.Bundle;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseActivity;
import com.handpay.coupon.base.ProgressWebView;
import com.handpay.coupon.databinding.ActivityWebBinding;
import com.handpay.coupon.utils.LogT;

public class WebActivity extends BaseActivity<ActivityWebBinding> {
    private String strurl;
    private String title;
    private ProgressWebView mProgressWebView;
    private String mFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_web);
//        AndroidBug5497WorkaroundWeb.assistActivity(this);
        Bundle bundle = getIntent().getExtras();
        //通过flag判断是否是充值进入的,退出时要返回到零钱界面
        if (null != bundle.getString("TITLE")) {
            title = (String) bundle.get("TITLE");
            setTitle(title);
        }
        showContentView();
        if (null != bundle.getString("FLAG")) {
            mFlag = (String) bundle.get("FLAG");
            LogT.w("mFlag: " + mFlag);
        }

        if (null != bundle.get("URL_WEB")) {
            strurl = (String) bundle.get("URL_WEB");
            LogT.d(title + "::webview网址=" + strurl);
        }

        mProgressWebView = new ProgressWebView(getApplicationContext(), null);
        bindingView.webviewContainer.addView(mProgressWebView);
        if (strurl.startsWith("http:") || strurl.startsWith("https:")) {
            mProgressWebView.loadUrl(strurl);
        }
    }

    @Override
    public void onBackPressed() {
        if (mProgressWebView.canGoBack()) {
            mProgressWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != mProgressWebView) {
            mProgressWebView.removeAllViews();
            mProgressWebView.destroyView();
            mProgressWebView = null;
        }
    }
}
