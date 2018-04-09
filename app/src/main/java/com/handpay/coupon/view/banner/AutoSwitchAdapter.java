package com.handpay.coupon.view.banner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class AutoSwitchAdapter<T> extends AutoLoopSwitchBaseAdapter {
    private LayoutInflater inflater;
    private Context cxt;
    public static String imageUrl[];//设置一个数组，用来保存所有图片的URL
    public static String bannersUrl[];//点击图片后的网址
//    private List<FragmentHomeBean.AttrBean.BannersBean> bannerdata;
    private List<T> bannerdata;

    public AutoSwitchAdapter(Context cxt, List<T> bannerdata,
                             AutoSwitchView autoSwitchView) {
        inflater = LayoutInflater.from(cxt);
        this.cxt = cxt;
        this.bannerdata = bannerdata;
        if (bannerdata!= null) {
            imageUrl = new String[bannerdata.size()];
            bannersUrl = new String[bannerdata.size()];

//            for (int i = 0; i < bannerdata.size(); i++) {
//                imageUrl[i] = bannerdata.get(i).getImageUrl();//将data中的每一个URL赋值给数组
//            }
        }
    }
    @Override
    public int getDataCount() {
        return bannerdata == null ? 0 : bannerdata.size();
    }

    @Override
    public View getView(int position) {
        inflater = LayoutInflater.from(cxt);
//        ImageView imageView = new ImageView(cxt);
//        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));
//        T model = (T) getItem(position);
//        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//        Glide.with(cxt).load(model.getImageUrl()).thumbnail(0.1f).into(imageView);

//        return imageView;
        return null;
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
