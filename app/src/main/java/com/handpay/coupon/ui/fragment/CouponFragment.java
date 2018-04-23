package com.handpay.coupon.ui.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.google.gson.Gson;
import com.handpay.coupon.R;
import com.handpay.coupon.base.BaseFragment;
import com.handpay.coupon.bean.BatchGetCardBackBean;
import com.handpay.coupon.bean.GetCardBackBean;
import com.handpay.coupon.dao.GetCardDataDao;
import com.handpay.coupon.databinding.FragmentCouponBinding;
import com.handpay.coupon.db.DbUtil;
import com.handpay.coupon.db.GetCardDataHelper;
import com.handpay.coupon.entity.GetCardData;
import com.handpay.coupon.ui.adapter.MyFragmentPagerAdapter;
import com.handpay.coupon.utils.AssetsUtil;
import com.handpay.coupon.utils.DebouncingOnClickListener;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.view.SelectCouponsSheet;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haohz on 2018/2/2.
 */

public class CouponFragment extends BaseFragment<FragmentCouponBinding> implements SelectCouponsSheet.OnSheetSelected {

    private ArrayList<String> mTitleList = new ArrayList<>(4);
    private ArrayList<Fragment> mFragments = new ArrayList<>(4);
    private ArrayList<String> cardList;
    private List<GetCardData> cardDataList = new ArrayList<>();//操作的数据
    private GetCardBackBean.CardBean.GrouponBean.BaseInfoBean baseInfoBean;
    private GetCardBackBean getCardBackBean;
    private GetCardBackBean.CardBean.GrouponBean grouponBean;
    private Query<GetCardData> query;
    private Activity mActivity;

    @Override
    public int setContent() {
        return R.layout.fragment_coupon;
    }
//    setUserVisibleHint


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){//onresume
//            batchGetCardBack();
//            getCardBack();
//        }else{//onpause
//
//        }
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
        showLoading();
        //获取数据
        batchGetCardBack();
        getCardBack();

        initFragmentList();
        MyFragmentPagerAdapter myAdapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, mTitleList);
        bindingView.vpCoupon.setAdapter(myAdapter);
        // 左右预加载页面的个数
        bindingView.vpCoupon.setOffscreenPageLimit(2);
        myAdapter.notifyDataSetChanged();
        bindingView.tabCoupon.setTabMode(TabLayout.MODE_FIXED);
        bindingView.tabCoupon.setupWithViewPager(bindingView.vpCoupon);
        showContentView();
//        // item点击跳转
//        initRxBus();
        bindingView.btnCreate.setOnClickListener(new DebouncingOnClickListener() {
            @Override
            public void doClick(View v) {
                SelectCouponsSheet.showSheet(getActivity(), CouponFragment.this, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                    }
                });
            }
        });

    }

    /* 获取卡列表 */
    private void batchGetCardBack() {
        String temp = AssetsUtil.loadlocalData(mActivity, "batchGetCardBack.json");
        if (TextUtils.isEmpty(temp)) return;
        BatchGetCardBackBean batchGetCardBackBean = new Gson().fromJson(temp, BatchGetCardBackBean.class);
        cardList = new ArrayList<>(batchGetCardBackBean.getCard_id_list());
        int totalNum = batchGetCardBackBean.getTotal_num();
        LogT.w("totalNum=" + totalNum + ",cardList=" + cardList.toString());
    }

    /* 获取卡列表 */
    private void getCardBack() {
        String temp = AssetsUtil.loadlocalData(mActivity, "getCardBack.json");
        if (TextUtils.isEmpty(temp)) return;
        getCardBackBean = new Gson().fromJson(temp, GetCardBackBean.class);
        baseInfoBean = getCardBackBean.getCard().getGroupon().getBase_info();
        grouponBean = getCardBackBean.getCard().getGroupon();
        saveDb();
    }

    /**
     * 保存数据库
     */
    private void saveDb() {
        //获取操作类
        GetCardDataHelper getCardDataHelper = DbUtil.getGetCardDataHelper();
        cardDataList = getCardDataHelper.queryAll();
        GetCardData getCardData = new GetCardData();
        getCardData.setCard_id(baseInfoBean.getId());// 卡券唯一编号
        getCardData.setLocal_id(baseInfoBean.getId());
        getCardData.setCard_type(getCardBackBean.getCard().getCard_type());
        getCardData.setDefault_detail(grouponBean.getDefault_detail());
        getCardData.setDeal_detail(grouponBean.getDeal_detail());
        getCardData.setGift(grouponBean.getGift());
        getCardData.setLeast_cost(grouponBean.getLeast_cost());
        getCardData.setReduce_cost(grouponBean.getReduce_cost());
        getCardData.setDiscount(grouponBean.getDiscount());
        getCardData.setSupply_bonus(grouponBean.getSupply_bonus());
        getCardData.setSupply_balance(grouponBean.getSupply_balance());
        getCardData.setBonus_cleared(grouponBean.getBonus_cleared());
        getCardData.setBonus_rules(grouponBean.getBonus_rules());
        getCardData.setBalance_rules(grouponBean.getBalance_rules());
        getCardData.setPrerogative(grouponBean.getPrerogative());
        getCardData.setBind_old_card_url(grouponBean.getBind_old_card_url());
        getCardData.setActivate_url(grouponBean.getActivate_url());
        getCardData.setTicket_class(grouponBean.getTicket_class());
        getCardData.setGuide_url(grouponBean.getGuide_url());
        getCardData.setDetail(grouponBean.getDetail());
        getCardData.setFrom(grouponBean.getFrom());
        getCardData.setTo(grouponBean.getTo());
        getCardData.setFlight(grouponBean.getFlight());
        getCardData.setDeparture_time(grouponBean.getDeparture_time());
        getCardData.setLanding_time(grouponBean.getLanding_time());
        getCardData.setCheck_in_url(grouponBean.getCheck_in_url());
        getCardData.setAir_model(grouponBean.getAir_model());
        getCardData.setLogo_url(baseInfoBean.getLogo_url());
        getCardData.setCode_type(baseInfoBean.getCode_type());
        getCardData.setBranch_name(baseInfoBean.getBrand_name());
        getCardData.setTitle(baseInfoBean.getTitle());
        getCardData.setColor(baseInfoBean.getColor());
        getCardData.setNotice(baseInfoBean.getNotice());
        getCardData.setService_phone(baseInfoBean.getService_phone());
        getCardData.setDescription(baseInfoBean.getDescription());
        getCardData.setUse_limit(baseInfoBean.getUse_limit());
        getCardData.setGet_limit(baseInfoBean.getGet_limit());
        getCardData.setUse_custom_code(baseInfoBean.isUse_custom_code());
        getCardData.setBind_openid(baseInfoBean.isBind_openid());
        getCardData.setCan_share(baseInfoBean.isCan_share());
        getCardData.setCan_give_friend(baseInfoBean.isCan_give_friend());
        getCardData.setLocation_id_list(baseInfoBean.getLocation_id_list().get(0) + "," + baseInfoBean.getLocation_id_list().get(1));
        getCardData.setType(baseInfoBean.getDate_info().getType());
        getCardData.setBegin_timestamp(baseInfoBean.getDate_info().getBegin_timestamp());
        getCardData.setEnd_timestamp(baseInfoBean.getDate_info().getEnd_timestamp());
        getCardData.setFixed_term(baseInfoBean.getFixed_term());
        getCardData.setFixed_begin_term(baseInfoBean.getFixed_begin_term());
        getCardData.setQuantity(baseInfoBean.getSku().getQuantity());
        getCardData.setUrl_name_type(baseInfoBean.getUrl_name_type());
        getCardData.setCustom_url(baseInfoBean.getCustom_url());
        getCardData.setSource(baseInfoBean.getSource());
        getCardData.setStatus(baseInfoBean.getStatus());

        //如果当前数据库非空，查询到当前卡Id已有记录，默认更新所有的数据；无记录或未查询到，新增当前卡Id记录
        if (cardDataList.size() > 0) {
            List<GetCardData> queryList = new ArrayList<>();
            List<GetCardData> cardQueryList = new ArrayList<>();
            for (int i = 0; i < cardList.size(); i++) {
                query = getCardDataHelper.queryBuilder()
                        .where(GetCardDataDao.Properties.Card_id.eq(baseInfoBean.getId())).build();
                cardQueryList = query.list();
            }
            queryList.addAll(cardQueryList);
            LogT.w("更新的卡id：baseInfoBean.getId()=" + baseInfoBean.getId());
            if (queryList.size() > 0) {
                LogT.w("数据库_getCardData_更新");
                getCardDataHelper.update(getCardData);
            } else {
                LogT.w("数据库_getCardData_新增");
                getCardDataHelper.save(getCardData);
            }
            queryList.clear();
        } else {
            LogT.w("数据库_getCardData_新增");
            getCardDataHelper.save(getCardData);
        }
        cardDataList.clear();
        cardDataList = getCardDataHelper.queryAll();
        LogT.w("db操作完毕_查询数据库_getCardData_列表：" + cardDataList.toString());
    }

    private void initFragmentList() {
        mTitleList.add("未使用");
        mTitleList.add("已使用");
        mTitleList.add("已核销");
        mTitleList.add("审核中");

        mFragments.add(new UnusedCoupon());
        mFragments.add(new UnusedCoupon());
        mFragments.add(new UsedCoupon());
        mFragments.add(new VerifiedCoupon());
    }

    @Override
    public void onSheetClick(int whichBtn) {
        if (whichBtn == R.id.btn_general) {

        } else if (whichBtn == R.id.btn_groupon) {

        } else if (whichBtn == R.id.btn_discount) {

        } else if (whichBtn == R.id.btn_gift) {

        } else if (whichBtn == R.id.btn_cash) {

        } else if (whichBtn == R.id.btn_member_card) {

        } else if (whichBtn == R.id.btn_scenic_ticket) {

        } else if (whichBtn == R.id.btn_movie_ticket) {

        } else if (whichBtn == R.id.btn_lucky_money) {

        } else if (whichBtn == R.id.cancel) {

        }
    }

}
