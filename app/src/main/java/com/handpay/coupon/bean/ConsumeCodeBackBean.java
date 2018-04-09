package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class ConsumeCodeBackBean {
    /**
     * errcode : 0
     * errmsg : ok
     * card : {"card_id":"pFS7Fjg8kV1IdDz01r4SQwMkuCKc"}
     * openid : oFS7Fjl0WsZ9AMZqrI80nbIq8xrA
     */

    private int errcode;
    private String errmsg;
    private CardBean card;
    private String openid;

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

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public static class CardBean {
        /**
         * card_id : pFS7Fjg8kV1IdDz01r4SQwMkuCKc
         */

        private String card_id;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }
    }
}
