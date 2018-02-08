package com.handpay.coupon.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.widget.ImageView;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseHeaderActivity;
import com.handpay.coupon.bean.ManageBeans;
import com.handpay.coupon.databinding.ActivityMechantDetailBinding;
import com.handpay.coupon.databinding.HeaderSlideBinding;
import com.handpay.coupon.ui.activity.MechantQueue;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.PerfectClickListener;
import com.handpay.coupon.utils.glide.GlideUtils;

public class MechantDetail extends BaseHeaderActivity<HeaderSlideBinding, ActivityMechantDetailBinding> {
    ManageBeans.ResultBean resultBean;
    private ACache mACache;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechant_detail);
        if (getIntent() != null) {
            resultBean = (ManageBeans.ResultBean) getIntent().getSerializableExtra("bean");
        }
        mACache = ACache.get(this);
        bindingHeaderView.setResultBean(resultBean);
        setTitle("商户详情");
//        setSubTitle("");//可选副标题
        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView(),setSlideTitle());
        GlideUtils.readCache(this,
                bindingHeaderView.ivStoreLogo,            //图片容器
                resultBean.getStoreIcon(),      //网址
                mACache,                        //缓存工具
                resultBean.getStoreName());     //缓存名称-商户名

        bindingHeaderView.executePendingBindings();
        showContentView();
        bindingContentView.rlQueue.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                startActivity(new Intent(MechantDetail.this, MechantQueue.class));

            }
        });
        bindingContentView.btnExit.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.handpay.zztong.smartpos.n900");
                startActivity(intent);
            }
        });
    }

    /**
     * a. 布局高斯透明图 header布局
     */
    @Override
    protected int setHeaderLayout() {
        return R.layout.header_slide;
    }

    /**
     * b. 设置头部header高斯背景 imgUrl
     */
    @Override
    protected String setHeaderImgUrl() {
        if(resultBean==null) return "";
        return resultBean.getStoreIcon();
    }

    /* 设置头部header布局 高斯背景ImageView控件 */
    @Override
    protected ImageView setHeaderImageView() {
        return bindingHeaderView.imgItemBg;
    }

    /* 滑动后的标题 */
    @Override
    protected String setSlideTitle() {
        if(resultBean==null) return "";
        return resultBean.getStoreName();
    }

    public static void start(Activity context, ManageBeans.ResultBean positionData, ImageView imageView) {
        Intent intent = new Intent(context, MechantDetail.class);
        intent.putExtra("bean", positionData);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(context,
                        imageView, "transition_header_img");//与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
}
