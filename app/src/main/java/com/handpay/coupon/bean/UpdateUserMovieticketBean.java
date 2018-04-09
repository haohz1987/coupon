package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class UpdateUserMovieticketBean {
    /**
     * code : 277217129962
     * card_id : p1Pj9jr90_SQRaVqYI239Ka1erkI
     * ticket_class : 4D
     * show_time : 1408493192
     * duration : 120
     * screening_room : 5 号影厅
     * seat_number : ["5 排14 号","5 排15 号"]
     */

    private String code;
    private String card_id;
    private String ticket_class;
    private int show_time;
    private int duration;
    private String screening_room;
    private List<String> seat_number;

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

    public String getTicket_class() {
        return ticket_class;
    }

    public void setTicket_class(String ticket_class) {
        this.ticket_class = ticket_class;
    }

    public int getShow_time() {
        return show_time;
    }

    public void setShow_time(int show_time) {
        this.show_time = show_time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getScreening_room() {
        return screening_room;
    }

    public void setScreening_room(String screening_room) {
        this.screening_room = screening_room;
    }

    public List<String> getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(List<String> seat_number) {
        this.seat_number = seat_number;
    }
}
