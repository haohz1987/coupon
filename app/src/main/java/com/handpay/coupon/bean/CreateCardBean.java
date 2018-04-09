package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/3/30.
 */

public class CreateCardBean{

    /**
     * card : {"card_type":"GROUPON","groupon":{"base_info":{"logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","brand_name":"海底捞","code_type":"CODE_TYPE_TEXT","title":"132 元双人火锅套餐","sub_title":"","color":"Color010","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票,请在消费时向商户提出\n 店内均可使用，仅限堂食\n 餐前不可打包,餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用,超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料","date_info":{"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400},"sku":{"quantity":50000000},"use_limit":1,"get_limit":3,"use_custom_code":false,"bind_openid":false,"can_share":true,"can_give_friend":true,"location_id_list":[123,12321,345345],"url_name_type":"URL_NAME_TYPE_RESERVATION","custom_url":"http://www.qq.com","source":"大众点评"},"deal_detail":"以下锅底2 选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）：\n 大锅1 份12 元\n 小锅2 份16 元\n 以下菜品2 选1\n 特级肥牛1 份30 元\n 洞庭鮰鱼卷1 份20 元\n 其他\n 鲜菇猪肉滑1 份18 元\n 金针菇1 份16 元\n 黑木耳1 份9 元\n 娃娃菜1 份8 元\n 冬瓜1 份6 元\n 火锅面2 个6 元\n 欢乐畅饮2 位12 元\n 自助酱料2 位10 元"}}
     */

    private CardBean card;

    public CardBean getCard() {
        return card;
    }

    public void setCard(CardBean card) {
        this.card = card;
    }

    public static class CardBean {
        /**
         * card_type : GROUPON
         * groupon : {"base_info":{"logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","brand_name":"海底捞","code_type":"CODE_TYPE_TEXT","title":"132 元双人火锅套餐","sub_title":"","color":"Color010","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票,请在消费时向商户提出\n 店内均可使用，仅限堂食\n 餐前不可打包,餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用,超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料","date_info":{"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400},"sku":{"quantity":50000000},"use_limit":1,"get_limit":3,"use_custom_code":false,"bind_openid":false,"can_share":true,"can_give_friend":true,"location_id_list":[123,12321,345345],"url_name_type":"URL_NAME_TYPE_RESERVATION","custom_url":"http://www.qq.com","source":"大众点评"},"deal_detail":"以下锅底2 选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）：\n 大锅1 份12 元\n 小锅2 份16 元\n 以下菜品2 选1\n 特级肥牛1 份30 元\n 洞庭鮰鱼卷1 份20 元\n 其他\n 鲜菇猪肉滑1 份18 元\n 金针菇1 份16 元\n 黑木耳1 份9 元\n 娃娃菜1 份8 元\n 冬瓜1 份6 元\n 火锅面2 个6 元\n 欢乐畅饮2 位12 元\n 自助酱料2 位10 元"}
         */

        private String card_type;
        private GrouponBean groupon;

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public GrouponBean getGroupon() {
            return groupon;
        }

        public void setGroupon(GrouponBean groupon) {
            this.groupon = groupon;
        }

        public static class GrouponBean {
            /**
             * base_info : {"logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","brand_name":"海底捞","code_type":"CODE_TYPE_TEXT","title":"132 元双人火锅套餐","sub_title":"","color":"Color010","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票,请在消费时向商户提出\n 店内均可使用，仅限堂食\n 餐前不可打包,餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用,超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料","date_info":{"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400},"sku":{"quantity":50000000},"use_limit":1,"get_limit":3,"use_custom_code":false,"bind_openid":false,"can_share":true,"can_give_friend":true,"location_id_list":[123,12321,345345],"url_name_type":"URL_NAME_TYPE_RESERVATION","custom_url":"http://www.qq.com","source":"大众点评"}
             * deal_detail : 以下锅底2 选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）：
             大锅1 份12 元
             小锅2 份16 元
             以下菜品2 选1
             特级肥牛1 份30 元
             洞庭鮰鱼卷1 份20 元
             其他
             鲜菇猪肉滑1 份18 元
             金针菇1 份16 元
             黑木耳1 份9 元
             娃娃菜1 份8 元
             冬瓜1 份6 元
             火锅面2 个6 元
             欢乐畅饮2 位12 元
             自助酱料2 位10 元
             */

            private BaseInfoBean base_info;
            private String deal_detail;

            public BaseInfoBean getBase_info() {
                return base_info;
            }

            public void setBase_info(BaseInfoBean base_info) {
                this.base_info = base_info;
            }

            public String getDeal_detail() {
                return deal_detail;
            }

            public void setDeal_detail(String deal_detail) {
                this.deal_detail = deal_detail;
            }

            public static class BaseInfoBean {
                /**
                 * logo_url : http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg
                 * brand_name : 海底捞
                 * code_type : CODE_TYPE_TEXT
                 * title : 132 元双人火锅套餐
                 * sub_title :
                 * color : Color010
                 * notice : 使用时向服务员出示此券
                 * service_phone : 020-88888888
                 * description : 不可与其他优惠同享
                 如需团购券发票,请在消费时向商户提出
                 店内均可使用，仅限堂食
                 餐前不可打包,餐后未吃完，可打包
                 本团购券不限人数，建议2 人使用,超过建议人数须另收酱料费5 元/位
                 本单谢绝自带酒水饮料
                 * date_info : {"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400}
                 * sku : {"quantity":50000000}
                 * use_limit : 1
                 * get_limit : 3
                 * use_custom_code : false
                 * bind_openid : false
                 * can_share : true
                 * can_give_friend : true
                 * location_id_list : [123,12321,345345]
                 * url_name_type : URL_NAME_TYPE_RESERVATION
                 * custom_url : http://www.qq.com
                 * source : 大众点评
                 */

                private String logo_url;
                private String brand_name;
                private String code_type;
                private String title;
                private String sub_title;
                private String color;
                private String notice;
                private String service_phone;
                private String description;
                private DateInfoBean date_info;
                private SkuBean sku;
                private int use_limit;
                private int get_limit;
                private boolean use_custom_code;
                private boolean bind_openid;
                private boolean can_share;
                private boolean can_give_friend;
                private String url_name_type;
                private String custom_url;
                private String source;
                private List<Integer> location_id_list;

                public String getLogo_url() {
                    return logo_url;
                }

                public void setLogo_url(String logo_url) {
                    this.logo_url = logo_url;
                }

                public String getBrand_name() {
                    return brand_name;
                }

                public void setBrand_name(String brand_name) {
                    this.brand_name = brand_name;
                }

                public String getCode_type() {
                    return code_type;
                }

                public void setCode_type(String code_type) {
                    this.code_type = code_type;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getSub_title() {
                    return sub_title;
                }

                public void setSub_title(String sub_title) {
                    this.sub_title = sub_title;
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

                public DateInfoBean getDate_info() {
                    return date_info;
                }

                public void setDate_info(DateInfoBean date_info) {
                    this.date_info = date_info;
                }

                public SkuBean getSku() {
                    return sku;
                }

                public void setSku(SkuBean sku) {
                    this.sku = sku;
                }

                public int getUse_limit() {
                    return use_limit;
                }

                public void setUse_limit(int use_limit) {
                    this.use_limit = use_limit;
                }

                public int getGet_limit() {
                    return get_limit;
                }

                public void setGet_limit(int get_limit) {
                    this.get_limit = get_limit;
                }

                public boolean isUse_custom_code() {
                    return use_custom_code;
                }

                public void setUse_custom_code(boolean use_custom_code) {
                    this.use_custom_code = use_custom_code;
                }

                public boolean isBind_openid() {
                    return bind_openid;
                }

                public void setBind_openid(boolean bind_openid) {
                    this.bind_openid = bind_openid;
                }

                public boolean isCan_share() {
                    return can_share;
                }

                public void setCan_share(boolean can_share) {
                    this.can_share = can_share;
                }

                public boolean isCan_give_friend() {
                    return can_give_friend;
                }

                public void setCan_give_friend(boolean can_give_friend) {
                    this.can_give_friend = can_give_friend;
                }

                public String getUrl_name_type() {
                    return url_name_type;
                }

                public void setUrl_name_type(String url_name_type) {
                    this.url_name_type = url_name_type;
                }

                public String getCustom_url() {
                    return custom_url;
                }

                public void setCustom_url(String custom_url) {
                    this.custom_url = custom_url;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public List<Integer> getLocation_id_list() {
                    return location_id_list;
                }

                public void setLocation_id_list(List<Integer> location_id_list) {
                    this.location_id_list = location_id_list;
                }

                public static class DateInfoBean {
                    /**
                     * type : 1
                     * begin_timestamp : 1397577600
                     * end_timestamp : 1399910400
                     */

                    private int type;
                    private int begin_timestamp;
                    private int end_timestamp;

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public int getBegin_timestamp() {
                        return begin_timestamp;
                    }

                    public void setBegin_timestamp(int begin_timestamp) {
                        this.begin_timestamp = begin_timestamp;
                    }

                    public int getEnd_timestamp() {
                        return end_timestamp;
                    }

                    public void setEnd_timestamp(int end_timestamp) {
                        this.end_timestamp = end_timestamp;
                    }
                }

                public static class SkuBean {
                    /**
                     * quantity : 50000000
                     */

                    private int quantity;

                    public int getQuantity() {
                        return quantity;
                    }

                    public void setQuantity(int quantity) {
                        this.quantity = quantity;
                    }
                }
            }
        }
    }
}
