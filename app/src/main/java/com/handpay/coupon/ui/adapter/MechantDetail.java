package com.handpay.coupon.ui.adapter;

import android.annotation.SuppressLint;
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
import com.handpay.coupon.dao.StoreDataDao;
import com.handpay.coupon.databinding.ActivityMechantDetailBinding;
import com.handpay.coupon.databinding.HeaderSlideBinding;
import com.handpay.coupon.db.DbUtil;
import com.handpay.coupon.db.StoreDataHelper;
import com.handpay.coupon.entity.StoreData;
import com.handpay.coupon.ui.activity.BranchInfo;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.LogT;
import com.handpay.coupon.utils.RxToast;
import com.handpay.coupon.utils.glide.GlideUtils;
import com.handpay.coupon.view.banner.AutoSwitchAdapter;
import com.handpay.coupon.view.banner.AutoSwitchView;

import org.greenrobot.greendao.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MechantDetail extends BaseHeaderActivity<HeaderSlideBinding, ActivityMechantDetailBinding> {
    private ACache mACache;
    private GetPoiBackBean.BusinessBean.BaseInfoBean baseInfoBean;
    private StoreDataHelper storeDataHelper;
    private List<StoreData> storeDataList = new ArrayList<>();//操作的数据

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechant_detail);
        mACache = ACache.get(this);
        showContentView();
        if (getIntent() == null) return;
        if (getIntent().getSerializableExtra("getPoiBack") == null) return;
        GetPoiBackBean getPoiBackBean = (GetPoiBackBean) getIntent().getSerializableExtra("getPoiBack");
        baseInfoBean = getPoiBackBean.getBusiness().getBase_info();
        saveDb();
//        LogT.w("getPoiBack:" + getPoiBackBean.toString());
        setTitle("我的门店");
        GlideUtils.readCache(this, bindingHeaderView.ivStoreLogo,            //图片容器
                getPoiBackBean.getBusiness().getBase_info().getPhoto_list().get(0).getPhoto_url(),      //网址
                mACache, "head_image");
        bindingHeaderView.setBaseInfoBean(baseInfoBean);
        bindingContentView.setBaseInfoBean(baseInfoBean);
        bindingContentView.tvAvgPrice.setText("￥" + baseInfoBean.getAvg_price() + "/人");//人均价格

        setSubTitle(baseInfoBean.getBranch_name());//可选副标题
        initSlideShapeTheme(setHeaderImgUrl(), setHeaderImageView(), setSlideTitle());
        bindingHeaderView.executePendingBindings();
        initAdapter(baseInfoBean.getPhoto_list());
    }

    /**
     * 保存数据库
     */
    private void saveDb() {
        //获取操作类
        storeDataHelper = DbUtil.getStoreDataHelper();
        storeDataList = storeDataHelper.queryAll();
        StoreData storeData = new StoreData();
        storeData.setSid(baseInfoBean.getSid());
        storeData.setBusinessName(baseInfoBean.getBusiness_name());
        storeData.setBranchName(baseInfoBean.getBranch_name());
        storeData.setProvince(baseInfoBean.getProvince());
        storeData.setCity(baseInfoBean.getCity());
        storeData.setAddress(baseInfoBean.getAddress());
        storeData.setTelephone(baseInfoBean.getTelephone());
        storeData.setCategories(baseInfoBean.getCategoriesString());
        storeData.setOffsetType(baseInfoBean.getOffset_type());
        storeData.setLongitude(baseInfoBean.getLongitude());
        storeData.setLatitude(baseInfoBean.getLatitude());
        storeData.setPhotoListHeadUrl(baseInfoBean.getPhoto_list().get(0).getPhoto_url());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < baseInfoBean.getPhoto_list().size(); i++) {
            if (i == baseInfoBean.getPhoto_list().size()-1) {
                sb.append(baseInfoBean.getPhoto_list().get(i).getPhoto_url());
            } else {
                sb.append(baseInfoBean.getPhoto_list().get(i).getPhoto_url()).append(",");
            }
        }
        storeData.setPhotoListContentUrl(sb.toString());
        storeData.setRecommend(baseInfoBean.getRecommend());
        storeData.setSpecial(baseInfoBean.getSpecial());
        storeData.setIntroduction(baseInfoBean.getIntroduction());
        storeData.setOpenTime(baseInfoBean.getOpen_time());
        storeData.setAvgPrice(baseInfoBean.getAvg_price());
        if(storeDataList.size()>0){
            List<StoreData> queryList= new ArrayList<>();
            Query<StoreData> query = storeDataHelper.queryBuilder()
                    .where(StoreDataDao.Properties.Sid.eq(baseInfoBean.getSid())).build();
            queryList = query.list();
            if(queryList.size()>0){
                LogT.w("数据库_storeData_更新");
                storeDataHelper.update(storeData);
                queryList.clear();
            }else{
                LogT.w("数据库_storeData_新增");
                storeDataHelper.save(storeData);
            }
        }else{
            LogT.w("数据库_storeData_新增");
            storeDataHelper.save(storeData);
        }
        storeDataList = storeDataHelper.queryAll();
        LogT.w("db取_查询数据库_storeData_列表："+storeDataList.toString());
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
        return baseInfoBean.getBusiness_name();
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
                        MechantDetail.this.finish();
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
                if (baseInfoBean != null) {
                    startActivity(new Intent(MechantDetail.this, BranchInfo.class).putExtra("baseInfoBean", baseInfoBean));
                } else {
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        storeDataHelper=null;
    }
}
