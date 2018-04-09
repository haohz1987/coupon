package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class QrCodeCreateBackBean {
    /**
     * errcode : 0
     * errmsg : ok
     * ticket : gQG28DoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL0FuWC1DNmZuVEhv MVp4NDNMRnNRAAIEesLvUQMECAcAAA==
     */

    private int errcode;
    private String errmsg;
    private String ticket;

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

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
