package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class UpdateCardBean {
    /**
     * card_id : xxxxxxxxxxxxx
     * member_card : {"base_info":{"logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","color":"Color010","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票，请在消费时向商户提出\n店内均可使用，仅限堂食\n 餐前不可打包，餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用，超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料"},"bonus_cleared":"aaaaaaaaaaaaaa","bonus_rules":"aaaaaaaaaaaaaa","prerogative":""}
     */

    private String card_id;
    private MemberCardBean member_card;

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public MemberCardBean getMember_card() {
        return member_card;
    }

    public void setMember_card(MemberCardBean member_card) {
        this.member_card = member_card;
    }

    public static class MemberCardBean {
        /**
         * base_info : {"logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","color":"Color010","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票，请在消费时向商户提出\n店内均可使用，仅限堂食\n 餐前不可打包，餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用，超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料"}
         * bonus_cleared : aaaaaaaaaaaaaa
         * bonus_rules : aaaaaaaaaaaaaa
         * prerogative :
         */

        private BaseInfoBean base_info;
        private String bonus_cleared;
        private String bonus_rules;
        private String prerogative;

        public BaseInfoBean getBase_info() {
            return base_info;
        }

        public void setBase_info(BaseInfoBean base_info) {
            this.base_info = base_info;
        }

        public String getBonus_cleared() {
            return bonus_cleared;
        }

        public void setBonus_cleared(String bonus_cleared) {
            this.bonus_cleared = bonus_cleared;
        }

        public String getBonus_rules() {
            return bonus_rules;
        }

        public void setBonus_rules(String bonus_rules) {
            this.bonus_rules = bonus_rules;
        }

        public String getPrerogative() {
            return prerogative;
        }

        public void setPrerogative(String prerogative) {
            this.prerogative = prerogative;
        }

        public static class BaseInfoBean {
            /**
             * logo_url : http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg
             * color : Color010
             * notice : 使用时向服务员出示此券
             * service_phone : 020-88888888
             * description : 不可与其他优惠同享
             如需团购券发票，请在消费时向商户提出
             店内均可使用，仅限堂食
             餐前不可打包，餐后未吃完，可打包
             本团购券不限人数，建议2 人使用，超过建议人数须另收酱料费5 元/位
             本单谢绝自带酒水饮料
             */

            private String logo_url;
            private String color;
            private String notice;
            private String service_phone;
            private String description;

            public String getLogo_url() {
                return logo_url;
            }

            public void setLogo_url(String logo_url) {
                this.logo_url = logo_url;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getNotice() {
                return notice;
            }

            public void setNotice(String notice) {
                this.notice = notice;
            }

            public String getService_phone() {
                return service_phone;
            }

            public void setService_phone(String service_phone) {
                this.service_phone = service_phone;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
