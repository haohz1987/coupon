package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class CommonBackBean {
    /**
     * errcode : 0
     * errmsg : ok
     * code : 751234212312
     */

    private int errcode;
    private String errmsg;
    private String code;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
