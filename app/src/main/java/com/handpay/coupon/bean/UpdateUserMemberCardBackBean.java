package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class UpdateUserMemberCardBackBean {
    /**
     * errcode : 0
     * errmsg : ok
     * result_bonus : 100
     * result_balance : 200
     * openid : oFS7Fjl0WsZ9AMZqrI80nbIq8xrA
     */

    private int errcode;
    private String errmsg;
    private int result_bonus;
    private int result_balance;
    private String openid;

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

    public int getResult_bonus() {
        return result_bonus;
    }

    public void setResult_bonus(int result_bonus) {
        this.result_bonus = result_bonus;
    }

    public int getResult_balance() {
        return result_balance;
    }

    public void setResult_balance(int result_balance) {
        this.result_balance = result_balance;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
