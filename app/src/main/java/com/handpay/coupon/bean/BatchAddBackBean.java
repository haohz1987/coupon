package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class BatchAddBackBean {

    /**
     * errcode : 0
     * errmsg : ok
     * location_id_list : [271262077,271262079]
     */

    private int errcode;
    private String errmsg;
    private List<Integer> location_id_list;

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

    public List<Integer> getLocation_id_list() {
        return location_id_list;
    }

    public void setLocation_id_list(List<Integer> location_id_list) {
        this.location_id_list = location_id_list;
    }
}
