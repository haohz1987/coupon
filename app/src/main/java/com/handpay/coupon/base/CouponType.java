package com.handpay.coupon.base;

import com.handpay.coupon.R;

/**
 * 卡券类型。
        通用券：GENERAL_COUPON;
        团购券：GROUPON;
        折扣券：DISCOUNT;
        礼品券：GIFT;
        代金券：CASH;
        会员卡：MEMBER_CARD;
        门票：SCENIC_TICKET；
        电影票：MOVIE_TICKET；
        飞机票：BOARDING_PASS；
        红包: LUCKY_MONEY；
        可拓展图标
 */
public enum CouponType {
    CouponType01("通用券", "GENERAL_COUPON", R.mipmap.address)
    ,CouponType02("团购券", "GROUPON", R.mipmap.address)
    ,CouponType03("折扣券", "DISCOUNT", R.mipmap.address)
    ,CouponType04("礼品券", "GIFT", R.mipmap.address)
    ,CouponType05("代金券", "CASH", R.mipmap.address)
    ,CouponType06("会员卡", "MEMBER_CARD", R.mipmap.address)
    ,CouponType07("门票", "SCENIC_TICKET", R.mipmap.address)
    ,CouponType08("电影票", "MOVIE_TICKET", R.mipmap.address)
    ,CouponType09("飞机票", "BOARDING_PASS", R.mipmap.address)
    ,CouponType10("红包", "LUCKY_MONEY", R.mipmap.address),;

    // 成员变量
    private String typeInfo;
    private String typeId;
    private int resId;

    // 构造方法
    CouponType(String typeInfo, String typeId) {
        this.typeInfo = typeInfo;
        this.typeId = typeId;
    }

    CouponType(String typeInfo, String typeId, int resId) {
        this.typeInfo = typeInfo;
        this.typeId = typeId;
        this.resId = resId;
    }

    // 普通方法
    public static String getID(String typeInfo) {
        for (CouponType c : CouponType.values()) {
            if (c.getTypeInfo().equals(typeInfo)) {
                return c.typeId;
            }
        }
        return null;
    }
    public static String getTextInfo(String typeId){

        for (CouponType c : CouponType.values()) {
            if (c.getTypeId().equals(typeId)) {
                return c.typeInfo;
            }
        }
        return null;
    }
    // 普通方法
    public static int getResID(String typeInfo) {
        for (CouponType c : CouponType.values()) {
            if (c.getTypeInfo() == typeInfo) {
                return c.resId;
            }
        }
        return 0;
    }

    // 普通方法
    public static String getTypeinfoByID(String typeID) {
        for (CouponType c : CouponType.values()) {
            if (c.getTypeId().equals(typeID)) {
                return c.typeInfo;
            }
        }
        return null;
    }


    public String getTypeInfo() {
        return typeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        this.typeInfo = typeInfo;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}