package com.handpay.coupon.utils.glide;

public interface GlideCacheListener {

    void success(String path);

    void error(Exception e);
}
