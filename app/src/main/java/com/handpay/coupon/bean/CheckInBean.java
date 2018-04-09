package com.handpay.coupon.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by haohz on 2018/4/2.
 */

public class CheckInBean {
    /**
     * code : 198374613512
     * card_id : p1Pj9jr90_SQRaVqYI239Ka1erkI
     * passenger_name : 乘客姓名
     * class : 舱等
     * seat : 座位号
     * boarding_time : 1409137710
     * gate : 登机口
     * etkt_bnr : 电子客票号
     * qrcode_data : 二维码数据
     * is_cancel  : false
     */

    private String code;
    private String card_id;
    private String passenger_name;
    @SerializedName("class")
    private String classX;
    private String seat;
    private int boarding_time;
    private String gate;
    private String etkt_bnr;
    private String qrcode_data;
    private boolean is_cancel;

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

    public String getPassenger_name() {
        return passenger_name;
    }

    public void setPassenger_name(String passenger_name) {
        this.passenger_name = passenger_name;
    }

    public String getClassX() {
        return classX;
    }

    public void setClassX(String classX) {
        this.classX = classX;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getBoarding_time() {
        return boarding_time;
    }

    public void setBoarding_time(int boarding_time) {
        this.boarding_time = boarding_time;
    }

    public String getGate() {
        return gate;
    }

    public void setGate(String gate) {
        this.gate = gate;
    }

    public String getEtkt_bnr() {
        return etkt_bnr;
    }

    public void setEtkt_bnr(String etkt_bnr) {
        this.etkt_bnr = etkt_bnr;
    }

    public String getQrcode_data() {
        return qrcode_data;
    }

    public void setQrcode_data(String qrcode_data) {
        this.qrcode_data = qrcode_data;
    }

    public boolean isIs_cancel() {
        return is_cancel;
    }

    public void setIs_cancel(boolean is_cancel) {
        this.is_cancel = is_cancel;
    }
}
