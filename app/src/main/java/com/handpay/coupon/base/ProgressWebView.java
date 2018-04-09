package com.handpay.coupon.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.handpay.coupon.R;
import com.handpay.coupon.utils.LogT;


public class ProgressWebView extends RelativeLayout {
    private WebView web_view_;
    private Context current_context_;
    private final ProgressBar progressbar;

    public ProgressWebView(Context aContext, AttributeSet attrs) {
        super(aContext, attrs);
        LayoutInflater.from(aContext).inflate(R.layout.progress_webview, this);
        current_context_ = aContext;
        progressbar = findViewById(R.id.progressbar);
    }

    /**
     * 将页面信息缓存到文件夹
     *
     * @param aContext current context
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(Context aContext) {
        RelativeLayout webview_container = findViewById(R.id.webview_container);
        web_view_ = new MyWebView(aContext, null);

        web_view_.requestFocus();
        web_view_.getSettings().setSupportZoom(true);//可缩放
        web_view_.getSettings().setJavaScriptEnabled(true);
        web_view_.getSettings().setBlockNetworkImage(false);//把图片加载放在最后来加载渲染
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            web_view_.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        web_view_.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web_view_.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        web_view_.getSettings().setDomStorageEnabled(true);
        web_view_.getSettings().setDatabaseEnabled(true);
        web_view_.getSettings().setUseWideViewPort(true);
        String cacheDirPath = aContext.getFilesDir().getAbsolutePath() + "InteractionWebView";
        LogT.d("WebView缓存目录=" + cacheDirPath);
        web_view_.getSettings().setDatabasePath(cacheDirPath);
        web_view_.getSettings().setAppCachePath(cacheDirPath);
        web_view_.getSettings().setAppCacheEnabled(true);
        web_view_.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        webview_container.addView(web_view_);
    }

    public void loadUrl(String url) {
        if (web_view_ == null && current_context_ != null) {
            initWebView(current_context_);
        }
        if (url != null && !url.equals("")) {
            web_view_.loadUrl(url);
        } else {
            LogT.e("Url navigating is NULL or empty");
        }
    }

    public boolean canGoBack() {
        return web_view_.canGoBack();
    }

    /**
     * Description: webView's goBack
     */
    public void goBack() {
        if (canGoBack()) {
            web_view_.goBack();
        } else {
            LogT.d("Can NOT go back anymore");
        }
    }

    public void destroyView() {
        web_view_.destroy();
    }

    public class MyWebView extends WebView {

        public MyWebView(Context context) {
            this(context, null);
        }

        public MyWebView(Context context, AttributeSet attrs) {
            this(context, attrs, Resources.getSystem().getIdentifier("webViewStyle", "attr", "android"));
        }

        @SuppressLint("SetJavaScriptEnabled")
        public MyWebView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            getSettings().setJavaScriptEnabled(true);
            setWebViewClient(new MyWebViewClient());
            setWebChromeClient(new WebChromeClient());
            this.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return true;
                }
            });
        }

        //实时更新进度的样式
        public class WebChromeClient extends android.webkit.WebChromeClient {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 100) {
                    progressbar.setVisibility(GONE);
                } else {
                    if (progressbar.getVisibility() == GONE)
                        progressbar.setVisibility(VISIBLE);
                    progressbar.setProgress(newProgress);
                }

                super.onProgressChanged(view, newProgress);
            }
        }
    }
    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
//            LogT.w("onPageStarted"+view+";"+url);
//            sslClient(view.getUrl());
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
//            LogT.w("onPageFinished"+view+";"+url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            LogT.w("sslError="+error.toString());
        }
    }
}