package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/3/30.
 */

public class CreateCardBackBean {

    /**
     * errcode : 0
     * errmsg : ok
     * card_id : p1Pj9jr90_SQRaVqYI239Ka1erkI
     */

    private int errcode;
    private String errmsg;
    private String card_id;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"errmsg\":\"")
                .append(errmsg).append('\"');
        sb.append(",\"errcode\":")
                .append(errcode);
        sb.append(",\"card_id\":\"")
                .append(card_id).append('\"');
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

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
}
