package com.handpay.coupon.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseFragment;
import com.handpay.coupon.bean.GetPoiBackBean;
import com.handpay.coupon.bean.PoiListBean;
import com.handpay.coupon.databinding.FragmentMechantBinding;
import com.handpay.coupon.ui.activity.MainActivity;
import com.handpay.coupon.ui.activity.BranchInfo;
import com.handpay.coupon.ui.adapter.MechantDetail;
import com.handpay.coupon.ui.adapter.PoiListAdapter;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.AssetsUtil;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.RxToast;

import java.util.ArrayList;

/**
 * Created by haohz on 2018/2/2.
 */

public class MechantFragment extends BaseFragment<FragmentMechantBinding> {

    // 初始化完成后加载数据
    private boolean isPrepared = false;
    // 第一次显示时加载数据，第二次不显示
    private boolean isFirst = true;
    // 是否正在刷新（用于刷新数据时返回页面不再刷新）
    private boolean mIsLoading = false;
    private ACache aCache;
    private MainActivity activity;
    private PoiListAdapter poiListAdapter;
    //    private HotMovieBean mHotMovieBean;
    private View mHeaderView = null;

    @Override
    public int setContent() {
        return R.layout.fragment_mechant;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showContentView();
        aCache = ACache.get(getActivity());
        poiListAdapter = new PoiListAdapter(activity) {
            @Override
            public void doStoreClick(int available_state,String poi_id) {
                if (available_state == 3){
                    RxToast.info("根据商户号，查询商户详情:"+poi_id);
                    getPoiBack();
                }else
                    RxToast.info("修改或查看门店信息");
            }
        };
        setAdapter();
//      mHotMovieBean = (HotMovieBean) aCache.getAsObject(Constants.ONE_HOT_MOVIE); 有网络后将数据缓存到acache
//      isPrepared = true;
        bindingView.btnCreate.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                activity.startActivity(new Intent(activity, BranchInfo.class));
            }
        });
    }
    //懒加载
    @Override
    protected void loadData() {
        if (!isPrepared || !mIsVisible) {
            return;
        }
        if (!mIsLoading) {//如果网络缓存时间戳判断，在次数添加
            showLoading();
            postDelayLoad();
        } else {
            if (!isFirst) return;
            bindingView.listOne.postDelayed(new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        setAdapter();
                        showContentView();
                    }
                }
            }, 150);
        }
    }

    private void setAdapter() {

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        bindingView.listOne.setLayoutManager(mLayoutManager);

        // 下拉出提示不会产生出现刷新头的bug，不加拉不下来
        bindingView.listOne.setPullRefreshEnabled(false);
        bindingView.listOne.clearHeader();

        bindingView.listOne.setLoadingMoreEnabled(false);
        // 不加滑动不流畅
        bindingView.listOne.setNestedScrollingEnabled(false);
        bindingView.listOne.setHasFixedSize(false);

//        if (mHeaderView == null) {
//            mHeaderView = View.inflate(getContext(), R.layout.layout_mechant_search, null);
////            View llMovieTop = mHeaderView.findViewById(R.id.ll_movie_top);
////            ImageView ivImg = (ImageView) mHeaderView.findViewById(R.id.iv_img);
////            ImgLoadUtil.displayRandom(3, ConstantsImageUrl.ONE_URL_01,ivImg);
////            llMovieTop.setOnClickListener(new PerfectClickListener() {
////                @Override
////                protected void onNoDoubleClick(View v) {
////                    DoubanTopActivity.start(v.getContext());
////                }
////            });
//        }
//        bindingView.listOne.addHeaderView(mHeaderView);
        getMechantList();
        bindingView.listOne.setAdapter(poiListAdapter);
        poiListAdapter.notifyDataSetChanged();

        isFirst = false;
    }

    /**
     * 延迟执行，避免卡顿
     * 加同步锁，避免重复加载
     */
    private void postDelayLoad() {
        synchronized (this) {
            if (!mIsLoading) {
                mIsLoading = true;
                bindingView.listOne.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setAdapter();
                    }
                }, 150);
            }
        }
    }

    private void getMechantList() {
        String temp = AssetsUtil.loadlocalData(activity, "getPoiList.json");
        if (TextUtils.isEmpty(temp)) {
            RxToast.info("获取temp失败："+temp);
            return;
        }
        PoiListBean poiListBean = new Gson().fromJson(temp,PoiListBean.class);
        poiListAdapter.clear();
        poiListAdapter.addAll(poiListBean.getBusiness_list());

    }

    private void getPoiBack() {
        String temp = AssetsUtil.loadlocalData(activity, "getPoiBack.json");
        if (TextUtils.isEmpty(temp)) {
            RxToast.info("获取temp失败："+temp);
            return ;
        }
        ArrayList<GetPoiBackBean>  arrayList = new ArrayList<>();
        GetPoiBackBean getPoiBackBean = new Gson().fromJson(temp,GetPoiBackBean.class);
        arrayList.add(getPoiBackBean);
//        LogT.w("arrayList="+arrayList.toString());
        Intent intent = new Intent(activity, MechantDetail.class);
        intent.putExtra("getPoiBack",getPoiBackBean);
//        intent.putParcelableArrayListExtra("getPoiBack",arrayList);
        activity.startActivity(intent);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }

    /**
     * 加载完成的状态
     */
    protected void showContentView() {
        if (mLlProgressBar.getVisibility() != View.GONE) {
            mLlProgressBar.setVisibility(View.GONE);
        }
        // 停止动画
        if (mAnimationDrawable.isRunning()) {
            mAnimationDrawable.stop();
        }
        if (mRefresh.getVisibility() != View.GONE) {
            mRefresh.setVisibility(View.GONE);
        }
        if (bindingView.getRoot().getVisibility() != View.VISIBLE) {
            bindingView.getRoot().setVisibility(View.VISIBLE);
        }
    }

//    public void intentDetail(){
//        Intent intent = new Intent(activity,MainActivity.class);
//        startActivity(intent);
//    }

}
