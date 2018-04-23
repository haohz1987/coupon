package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class BatchGetCardBackBean {
    /**
     * errcode : 0
     * errmsg : ok
     * card_id_list : ["ph_gmt7cUVrlRk8swPwx7aDyF-pg"]
     * total_num : 1
     */

    private int errcode;
    private String errmsg;
    private int total_num;
    private List<String> card_id_list;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"total_num\":")
                .append(total_num);
        sb.append(",\"errmsg\":\"")
                .append(errmsg).append('\"');
        sb.append(",\"errcode\":")
                .append(errcode);
        sb.append(",\"card_id_list\":")
                .append(card_id_list);
        sb.append('}');
        return sb.toString();
    }

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

    public int getTotal_num() {
        return total_num;
    }

    public void setTotal_num(int total_num) {
        this.total_num = total_num;
    }

    public List<String> getCard_id_list() {
        return card_id_list;
    }

    public void setCard_id_list(List<String> card_id_list) {
        this.card_id_list = card_id_list;
    }
}
