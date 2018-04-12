package com.handpay.coupon.view.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.handpay.coupon.bean.GetPoiBackBean.BusinessBean.BaseInfoBean.PhotoListBean;
import com.handpay.coupon.utils.ACache;
import com.handpay.coupon.utils.glide.GlideUtils;

import java.util.List;

public class AutoSwitchAdapter extends AutoLoopSwitchBaseAdapter {
    private LayoutInflater inflater;
    private Context cxt;
    private ACache mACache;
    public static String imageUrl[];//设置一个数组，用来保存所有图片的URL
//    public static String bannersUrl[];//点击图片后的网址,预留
    private List<PhotoListBean> bannerdata;

    public AutoSwitchAdapter(Context cxt, List<PhotoListBean> bannerdata,
                             AutoSwitchView autoSwitchView) {
        inflater = LayoutInflater.from(cxt);
        this.cxt = cxt;
        this.bannerdata = bannerdata;
        mACache = ACache.get(cxt);
        if (bannerdata!= null) {
            imageUrl = new String[bannerdata.size()];
//            bannersUrl = new String[bannerdata.size()];
            for (int i = 1; i < bannerdata.size(); i++) {
                imageUrl[i] = bannerdata.get(i).getPhoto_url();//将data中的每一个URL赋值给数组
            }
        }
    }
    @Override
    public int getDataCount() {
        return bannerdata == null ? 0 : bannerdata.size();
    }

    @Override
    public View getView(int position) {
        inflater = LayoutInflater.from(cxt);
        ImageView imageView = new ImageView(cxt);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
        PhotoListBean model = (PhotoListBean) getItem(position);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        GlideUtils.readCache(cxt,imageView,model.getPhoto_url(),mACache,model.getPhoto_url());
        return imageView;
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position < getDataCount()) {
            return bannerdata.get(position);
        }
        return null;
    }

    @Override
    public View getEmptyView() {
        return null;
    }

    @Override
    public void updateView(View view, int position) {

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }


}
