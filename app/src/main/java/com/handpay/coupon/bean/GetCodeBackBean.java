package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class GetCodeBackBean {
    /**
     * errcode : 0
     * errmsg : ok
     * openid : oFS7Fjl0WsZ9AMZqrI80nbIq8xrA
     * card : {"card_id":"pFS7Fjg8kV1IdDz01r4SQwMkuCKc","begin_time":1404205036,"end_time":1404205036}
     */

    private int errcode;
    private String errmsg;
    private String openid;
    private CardBean card;

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

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public static class CardBean {
        /**
         * card_id : pFS7Fjg8kV1IdDz01r4SQwMkuCKc
         * begin_time : 1404205036
         * end_time : 1404205036
         */

        private String card_id;
        private int begin_time;
        private int end_time;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public int getBegin_time() {
            return begin_time;
        }

        public void setBegin_time(int begin_time) {
            this.begin_time = begin_time;
        }

        public int getEnd_time() {
            return end_time;
        }

        public void setEnd_time(int end_time) {
            this.end_time = end_time;
        }
    }
}
