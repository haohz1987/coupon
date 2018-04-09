package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class UpdateCodeBean {
    /**
     * code : 12345678
     * card_id : p1Pj9jr90_SQRaxxxxxxxx
     * new_code : 3495739475
     */

    private String code;
    private String card_id;
    private String new_code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getNew_code() {
        return new_code;
    }

    public void setNew_code(String new_code) {
        this.new_code = new_code;
    }
}
