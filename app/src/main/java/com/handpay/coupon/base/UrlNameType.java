package com.handpay.coupon.base;

import com.handpay.coupon.R;

/**
 * 商户自定义cell 名称
 "URL_NAME_TYPE_TAKE_AWAY"，外卖
 "URL_NAME_TYPE_RESERVATION"，在线预订
 "URL_NAME_TYPE_USE_IMMEDIATELY"，立即使用
 "URL_NAME_TYPE_APPOINTMENT”,在线预约
 URL_NAME_TYPE_EXCHANGE,在线兑换
 URL_NAME_TYPE_MALL,在线商城
 "URL_NAME_TYPE_VEHICLE_INFORMATION，车辆信息
 */
public enum UrlNameType {

    UrlNameType01("外卖", "URL_NAME_TYPE_TAKE_AWAY", R.mipmap.address)
    ,UrlNameType02("在线预订", "URL_NAME_TYPE_RESERVATION", R.mipmap.address)
    ,UrlNameType03("立即使用", "URL_NAME_TYPE_USE_IMMEDIATELY", R.mipmap.address)
    ,UrlNameType04("在线预约", "URL_NAME_TYPE_APPOINTMENT", R.mipmap.address)
    ,UrlNameType05("在线兑换", "URL_NAME_TYPE_EXCHANGE", R.mipmap.address)
    ,UrlNameType06("在线商城", "URL_NAME_TYPE_MALL", R.mipmap.address)
    ,UrlNameType07("车辆信息", "URL_NAME_TYPE_VEHICLE_INFORMATION", R.mipmap.address)
    ;

    // 成员变量
    private String typeInfo;
    private String typeId;
    private int resId;

    // 构造方法
    UrlNameType(String typeInfo, String typeId) {
        this.typeInfo = typeInfo;
        this.typeId = typeId;
    }

    UrlNameType(String typeInfo, String typeId, int resId) {
        this.typeInfo = typeInfo;
        this.typeId = typeId;
        this.resId = resId;
    }

    // 普通方法
    public static String getID(String typeInfo) {
        for (UrlNameType c : UrlNameType.values()) {
            if (c.getTypeInfo().equals(typeInfo)) {
                return c.typeId;
            }
        }
        return null;
    }
    public static String getTextInfo(String typeId){

        for (UrlNameType c : UrlNameType.values()) {
            if (c.getTypeId().equals(typeId)) {
                return c.typeInfo;
            }
        }
        return null;
    }
    // 普通方法
    public static int getResID(String typeInfo) {
        for (UrlNameType c : UrlNameType.values()) {
            if (c.getTypeInfo() == typeInfo) {
                return c.resId;
            }
        }
        return 0;
    }

    // 普通方法
    public static String getTypeinfoByID(String typeID) {
        for (UrlNameType c : UrlNameType.values()) {
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