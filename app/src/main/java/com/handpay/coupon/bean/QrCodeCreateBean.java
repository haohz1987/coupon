package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class QrCodeCreateBean {

    /**
     * action_name : QR_CARD
     * card : {"card_id":"pFS7Fjg8kV1IdDz01r4SQwMkuCKc","code":"198374613512","openid":"oFS7Fjl0WsZ9AMZqrI80nbIq8xrA"," expire_seconds":"1800"," is_unique_code":false}
     */

    private String action_name;
    private ActionInfoBean action_info;
    public static class ActionInfoBean{
        private CardBean card;
        public CardBean getCard() {
            return card;
        }

        public void setCard(CardBean card) {
            this.card = card;
        }
        public static class CardBean {
            /**
             * card_id : pFS7Fjg8kV1IdDz01r4SQwMkuCKc
             * code : 198374613512
             * openid : oFS7Fjl0WsZ9AMZqrI80nbIq8xrA
             *  expire_seconds : 1800
             *  is_unique_code : false
             */

            private String card_id;
            private String code;
            private String openid;
            private String expire_seconds;
            private boolean is_unique_code;

            public String getCard_id() {
                return card_id;
            }

            public void setCard_id(String card_id) {
                this.card_id = card_id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }
        }
    }

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public ActionInfoBean getAction_info() {
        return action_info;
    }

    public void setAction_info(ActionInfoBean action_info) {
        this.action_info = action_info;
    }


}
