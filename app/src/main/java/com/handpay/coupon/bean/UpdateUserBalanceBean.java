package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class UpdateUserBalanceBean {
    /**
     * code : 12312313
     * card_id : xxxx_card_id
     * balance : 1231231
     */

    private String code;
    private String card_id;
    private int balance;

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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
