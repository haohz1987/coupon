package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class OpenCardDetailBackBean {

    private List<CardListBean> card_list;

    public List<CardListBean> getCard_list() {
        return card_list;
    }

    public void setCard_list(List<CardListBean> card_list) {
        this.card_list = card_list;
    }

    public static class CardListBean {
        /**
         * card_id : po_2Djks4-yP5PGtgGY4GkbAIIt0
         * card_ext :
         * is_succ : 1
         */

        private String card_id;
        private String card_ext;
        private int is_succ;

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getCard_ext() {
            return card_ext;
        }

        public void setCard_ext(String card_ext) {
            this.card_ext = card_ext;
        }

        public int getIs_succ() {
            return is_succ;
        }

        public void setIs_succ(int is_succ) {
            this.is_succ = is_succ;
        }
    }
}
