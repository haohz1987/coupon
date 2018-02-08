package com.handpay.coupon.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.handpay.coupon.R;
import com.handpay.coupon.databinding.ActivityMainBinding;
import com.handpay.coupon.rx.RxBus;
import com.handpay.coupon.rx.RxBusBaseMessage;
import com.handpay.coupon.rx.RxCodeConstants;
import com.handpay.coupon.ui.adapter.MyFragmentPagerAdapter;
import com.handpay.coupon.ui.fragment.CouponFragment;
import com.handpay.coupon.ui.fragment.MechantFragment;
import com.handpay.coupon.ui.fragment.ScanFragment;
import com.handpay.coupon.utils.RxToast;

import java.util.ArrayList;

import rx.functions.Action1;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private ActivityMainBinding mBinding;
    private Toolbar toolbar;
    private FrameLayout llTitleMenu;
    private ViewPager vpContent;
    private ImageView ivCoupon;
    private ImageView ivMechant;
    private ImageView ivUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
//        initStatusView();
        initId();
        initRxBus();//顶部标题栏切换
        initContentFragment();
    }
//    private void initStatusView() {
//        ViewGroup.LayoutParams layoutParams = mBinding.viewStatus.getLayoutParams();
//        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
//        mBinding.viewStatus.setLayoutParams(layoutParams);
//    }

    private void initId() {
        toolbar = mBinding.toolbar;
        vpContent = mBinding.vpContent;
        ivMechant = mBinding.ivMechant;
        ivCoupon=mBinding.ivCoupon;
        ivUserInfo = mBinding.ivUserInfo;

        ivMechant.setOnClickListener(this);
        ivCoupon.setOnClickListener(this);
        ivUserInfo.setOnClickListener(this);
    }

    private void initContentFragment() {
        ArrayList<Fragment> mFragmentList = new ArrayList<>();
        mFragmentList.add(new MechantFragment());
        mFragmentList.add(new CouponFragment());
        mFragmentList.add(new ScanFragment());
        // 注意使用的是：getSupportFragmentManager
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);
        vpContent.setAdapter(adapter);
        // 设置ViewPager最大缓存的页面个数(cpu消耗最少为2页)
        vpContent.setOffscreenPageLimit(3);
        vpContent.addOnPageChangeListener(MainActivity.this);
        //初始化商户列表为第一页
        mBinding.ivMechant.setSelected(true);
        vpContent.setCurrentItem(0);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }
    /**
     * Rxbus跳转
     */
    private void initRxBus() {
        RxBus.getDefault().toObservable(RxCodeConstants.JUMP_TYPE_TO_ONE, RxBusBaseMessage.class)
                .subscribe(new Action1<RxBusBaseMessage>() {
                    @Override
                    public void call(RxBusBaseMessage integer) {
                        mBinding.vpContent.setCurrentItem(1);
                    }
                });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                ivMechant.setSelected(true);
                ivCoupon.setSelected(false);
                ivUserInfo.setSelected(false);
                break;
            case 1:
                ivCoupon.setSelected(true);
                ivMechant.setSelected(false);
                ivUserInfo.setSelected(false);
                break;
            case 2:
                ivUserInfo.setSelected(true);
                ivCoupon.setSelected(false);
                ivMechant.setSelected(false);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_mechant:
                if (vpContent.getCurrentItem() != 0) {//不然cpu会有损耗
                    ivMechant.setSelected(true);
                    ivCoupon.setSelected(false);
                    ivUserInfo.setSelected(false);
                    vpContent.setCurrentItem(0);
                }
                break;
            case R.id.iv_coupon:
                if (vpContent.getCurrentItem() != 1) {
                    ivCoupon.setSelected(true);
                    ivMechant.setSelected(false);
                    ivUserInfo.setSelected(false);
                    vpContent.setCurrentItem(1);
                }
                break;
            case R.id.iv_user_info:
                if (vpContent.getCurrentItem() != 2) {
                    ivUserInfo.setSelected(true);
                    ivMechant.setSelected(false);
                    ivCoupon.setSelected(false);
                    vpContent.setCurrentItem(2);
                }
                break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                RxToast.info("绑定微信公众号");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
