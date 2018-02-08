package com.handpay.coupon.utils.glide;

import com.handpay.coupon.utils.LogT;

/**
 * Created by $USER_NAME on 2017/9/5.
 */

public class MyGlideCacheListener implements GlideCacheListener {
    @Override
    public void success(String path) {
    }

    @Override
    public void error(Exception e) {
        LogT.w("网络或缓存失败:"+e.getMessage());
    }
}
