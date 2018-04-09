package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class UpdateUserMemberCardBean {
    /**
     * code : 12312313
     * card_id : p1Pj9jr90_SQRaVqYI239Ka1erkI
     * record_bonus : 消费30元，获得3积分
     * add_bonus : 3
     * add_balance : -3000
     * record_balance : 购买焦糖玛琪朵一杯，扣除金额30元。
     */

    private String code;
    private String card_id;
    private String record_bonus;
    private int add_bonus;
    private int add_balance;
    private String record_balance;

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

    public String getRecord_bonus() {
        return record_bonus;
    }

    public void setRecord_bonus(String record_bonus) {
        this.record_bonus = record_bonus;
    }

    public int getAdd_bonus() {
        return add_bonus;
    }

    public void setAdd_bonus(int add_bonus) {
        this.add_bonus = add_bonus;
    }

    public int getAdd_balance() {
        return add_balance;
    }

    public void setAdd_balance(int add_balance) {
        this.add_balance = add_balance;
    }

    public String getRecord_balance() {
        return record_balance;
    }

    public void setRecord_balance(String record_balance) {
        this.record_balance = record_balance;
    }
}
