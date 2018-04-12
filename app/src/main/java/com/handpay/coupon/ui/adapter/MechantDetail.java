package com.handpay.coupon.ui.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseHeaderActivity;
import com.handpay.coupon.bean.GetPoiBackBean;
import com.handpay.coupon.databinding.ActivityMechantDetailBinding;
import com.handpay.coupon.databinding.HeaderSlideBinding;
import com.handpay.coupon.ui.activity.BranchInfo;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.RxToast;
import com.handpay.coupon.utils.glide.GlideUtils;
import com.handpay.coupon.view.banner.AutoSwitchAdapter;
import com.handpay.coupon.view.banner.AutoSwitchView;

import java.util.ArrayList;
import java.util.List;

public class MechantDetail extends BaseHeaderActivity<HeaderSlideBinding, ActivityMechantDetailBinding> {
    private ACache mACache;
    private GetPoiBackBean.BusinessBean.BaseInfoBean baseInfoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechant_detail);
        mACache = ACache.get(this);
        setTitle("我的门店");
        showContentView();
        if (getIntent() == null) return;
        if (getIntent().getSerializableExtra("getPoiBack") == null) return;
        GetPoiBackBean getPoiBackBean = (GetPoiBackBean) getIntent().getSerializableExtra("getPoiBack");
        baseInfoBean = getPoiBackBean.getBusiness().getBase_info();
        LogT.w("getPoiBack:" + getPoiBackBean.toString());

        GlideUtils.readCache(this, bindingHeaderView.ivStoreLogo,            //图片容器
                getPoiBackBean.getBusiness().getBase_info().getPhoto_list().get(0).getPhoto_url(),      //网址
                mACache, "head_image");
        bindingHeaderView.setBaseInfoBean(baseInfoBean);
        bindingContentView.setBaseInfoBean(baseInfoBean);
        bindingContentView.tvAvgPrice.setText("￥" + baseInfoBean.getAvg_price() + "/人");//人均价格

////        setSubTitle("");//可选副标题
        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView(), setSlideTitle());
        bindingHeaderView.executePendingBindings();
        initAdapter(baseInfoBean.getPhoto_list());

    }

    private void initAdapter(List<GetPoiBackBean.BusinessBean.BaseInfoBean.PhotoListBean> list) {
        AutoSwitchView mAsv = findViewById(R.id.asv);
        AutoSwitchAdapter autoSwitchAdapter = new AutoSwitchAdapter(this, list, mAsv);
        mAsv.setAdapter(autoSwitchAdapter);
        //初始化banner指示器
        List<Integer> indicate = new ArrayList<>();
        indicate.clear();
        indicate.add(R.drawable.dot_normal);
        indicate.add(R.drawable.dot_focused);
        mAsv.initIndicate(indicate);
        autoSwitchAdapter.notifyDataSetChanged();
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
        if (baseInfoBean == null) return "";
        return baseInfoBean.getPhoto_list().get(0).getPhoto_url();
    }

    /* 设置头部header布局 高斯背景ImageView控件 */
    @Override
    protected ImageView setHeaderImageView() {
        return bindingHeaderView.imgItemBg;
    }

    /* 滑动后的标题 */
    @Override
    protected String setSlideTitle() {
        if (baseInfoBean == null) return "";
        return baseInfoBean.getBranch_name();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mechent_top_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                this.showAlertDialog(this, "提示", getString(R.string.delete_branch), true, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RxToast.info("调用删除门店接口");
                        dialog.dismiss();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
//                startActivity(new Intent(MechantDetail.this, LoginActivity.class));
                return true;
            case R.id.action_modify:
                if(baseInfoBean!=null){
                    startActivity(new Intent(MechantDetail.this, BranchInfo.class).putExtra("baseInfoBean",baseInfoBean));
                }else{
                    this.showAlertDialog(this, "提示", "网络异常，请稍后再试！", true, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                }
                RxToast.info("调用修改门店接口");
//                startActivity(new Intent(MechantDetail.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
