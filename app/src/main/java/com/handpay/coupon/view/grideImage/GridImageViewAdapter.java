package com.handpay.coupon.view.grideImage;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.handpay.coupon.R;

import java.util.List;

/**
 * @param <TImage> 指定类型
 */
public abstract class GridImageViewAdapter<TImage> {
    protected abstract void onDisplayImage(Context context, ImageView imageView, TImage path);

    protected abstract void onAddClick(Context context, List<TImage> list);

    protected void onItemImageClick(Context context, int index, List<TImage> list) {

    }

    protected int getShowStyle() {
        return GridImageView.STYLE_GRID;
    }

    protected
    @DrawableRes
    int generateAddIcon() {
        return R.mipmap.add_image;
    }
}