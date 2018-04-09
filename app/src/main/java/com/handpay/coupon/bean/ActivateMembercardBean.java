package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class ActivateMembercardBean {
    /**
     * init_bonus : 100
     * init_balance : 200
     * membership_number : AAA00000001
     * code : 12312313
     * card_id : xxxx_card_id
     */

    private int init_bonus;
    private int init_balance;
    private String membership_number;
    private String code;
    private String card_id;

    public int getInit_bonus() {
        return init_bonus;
    }

    public void setInit_bonus(int init_bonus) {
        this.init_bonus = init_bonus;
    }

    public int getInit_balance() {
        return init_balance;
    }

    public void setInit_balance(int init_balance) {
        this.init_balance = init_balance;
    }

    public String getMembership_number() {
        return membership_number;
    }

    public void setMembership_number(String membership_number) {
        this.membership_number = membership_number;
    }

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
}
