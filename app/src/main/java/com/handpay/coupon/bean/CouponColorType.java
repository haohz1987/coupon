package com.handpay.coupon.bean;

import com.handpay.coupon.R;

public enum CouponColorType {
    CouponColorType01("1", R.drawable.shape_corner_bg01),
    CouponColorType02("2", R.drawable.shape_corner_bg02),
    CouponColorType03("3",R.drawable.shape_corner_bg03),
    CouponColorType04("4", R.drawable.shape_corner_bg04),
    CouponColorType05("5", R.drawable.shape_corner_bg05),
    CouponColorType06("6", R.drawable.shape_corner_bg06),
    CouponColorType07("7", R.drawable.shape_corner_bg07),
    CouponColorType08("8", R.drawable.shape_corner_bg08),
    CouponColorType09("9", R.drawable.shape_corner_bg09),
    CouponColorType10("10", R.drawable.shape_corner_bg10);
    // 成员变量
    private String typeId;
    private int resId;

    CouponColorType(String typeId, int resId) {
        this.typeId = typeId;
        this.resId = resId;
    }

    public static int getResID(String typeId) {
        for (CouponColorType c : CouponColorType.values()) {
            if (c.getTypeId().equals(typeId) ) {
                return c.resId;
            }
        }
        return 0;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
