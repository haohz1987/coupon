package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class GetCardBackBean {
    /**
     * errcode : 0
     * errmsg : ok
     * card : {"card_type":"GROUPON","groupon":{"base_info":{"status":1,"id":"p1Pj9jr90_SQRaVqYI239Ka1erkI","logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","appid":"wx588def6b0089dd48","code_type":"CODE_TYPE_TEXT","brand_name":"海底捞","title":"132 元双人火锅套餐","sub_title":"","date_info":{"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400},"color":"#3373bb","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票，请在消费时向商户提出\n 店内均可使用，仅限堂食\n 餐前不可打包，餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用，超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料","use_limit":1,"get_limit":3,"can_share":true,"location_id_list":[123,12321,345345],"url_name_type":"URL_NAME_TYPE_RESERVATION","custom_url":"http://www.qq.com","source":"大众点评","sku":{"quantity":0}},"deal_detail":"以下锅底2 选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）：\n 大锅1 份12 元\n 小锅2 份16 元\n 以下菜品2 选1\n 特级肥牛1 份30 元\n 洞庭鮰鱼卷1 份20 元\n 其他\n 鲜菇猪肉滑1 份18 元\n 金针菇1 份16 元\n 黑木耳1 份9 元\n 娃娃菜1 份8 元\n 冬瓜1 份6 元\n 火锅面2 个6 元\n 欢乐畅饮2 位12 元\n 自助酱料2 位10 元"}}
     */

    private int errcode;
    private String errmsg;
    private CardBean card;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"errmsg\":\"")
                .append(errmsg).append('\"');
        sb.append(",\"errcode\":")
                .append(errcode);
        sb.append(",\"card\":")
                .append(card);
        sb.append('}');
        return sb.toString();
    }

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

    public static class CardBean {
        /**
         * card_type : GROUPON
         * groupon : {"base_info":{"status":1,"id":"p1Pj9jr90_SQRaVqYI239Ka1erkI","logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","appid":"wx588def6b0089dd48","code_type":"CODE_TYPE_TEXT","brand_name":"海底捞","title":"132 元双人火锅套餐","sub_title":"","date_info":{"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400},"color":"#3373bb","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票，请在消费时向商户提出\n 店内均可使用，仅限堂食\n 餐前不可打包，餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用，超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料","use_limit":1,"get_limit":3,"can_share":true,"location_id_list":[123,12321,345345],"url_name_type":"URL_NAME_TYPE_RESERVATION","custom_url":"http://www.qq.com","source":"大众点评","sku":{"quantity":0}},"deal_detail":"以下锅底2 选1（有菌王锅、麻辣锅、大骨锅、番茄锅、清补凉锅、酸菜鱼锅可选）：\n 大锅1 份12 元\n 小锅2 份16 元\n 以下菜品2 选1\n 特级肥牛1 份30 元\n 洞庭鮰鱼卷1 份20 元\n 其他\n 鲜菇猪肉滑1 份18 元\n 金针菇1 份16 元\n 黑木耳1 份9 元\n 娃娃菜1 份8 元\n 冬瓜1 份6 元\n 火锅面2 个6 元\n 欢乐畅饮2 位12 元\n 自助酱料2 位10 元"}
         */

        private String card_type;
        private GrouponBean groupon;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"groupon\":")
                    .append(groupon);
            sb.append(",\"card_type\":\"")
                    .append(card_type).append('\"');
            sb.append('}');
            return sb.toString();
        }

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
             * base_info : {"status":1,"id":"p1Pj9jr90_SQRaVqYI239Ka1erkI","logo_url":"http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg","appid":"wx588def6b0089dd48","code_type":"CODE_TYPE_TEXT","brand_name":"海底捞","title":"132 元双人火锅套餐","sub_title":"","date_info":{"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400},"color":"#3373bb","notice":"使用时向服务员出示此券","service_phone":"020-88888888","description":"不可与其他优惠同享\n 如需团购券发票，请在消费时向商户提出\n 店内均可使用，仅限堂食\n 餐前不可打包，餐后未吃完，可打包\n 本团购券不限人数，建议2 人使用，超过建议人数须另收酱料费5 元/位\n 本单谢绝自带酒水饮料","use_limit":1,"get_limit":3,"can_share":true,"location_id_list":[123,12321,345345],"url_name_type":"URL_NAME_TYPE_RESERVATION","custom_url":"http://www.qq.com","source":"大众点评","sku":{"quantity":0}}
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
            private String default_detail;
            private String gift;
            private long least_cost;
            private long reduce_cost;
            private int discount;
            private String supply_bonus;
            private String supply_balance;
            private String bonus_cleared;
            private String bonus_rules;
            private String balance_rules;
            private String prerogative;
            private String bind_old_card_url;
            private String activate_url;
            private String ticket_class;
            private String guide_url;
            private String detail;
            private String from;
            private String to;
            private String flight;
            private String departure_time;
            private String landing_time;
            private String check_in_url;
            private String air_model;

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"to\":\"")
                        .append(to).append('\"');
                sb.append(",\"ticket_class\":\"")
                        .append(ticket_class).append('\"');
                sb.append(",\"supply_bonus\":\"")
                        .append(supply_bonus).append('\"');
                sb.append(",\"supply_balance\":\"")
                        .append(supply_balance).append('\"');
                sb.append(",\"reduce_cost\":")
                        .append(reduce_cost);
                sb.append(",\"prerogative\":\"")
                        .append(prerogative).append('\"');
                sb.append(",\"least_cost\":")
                        .append(least_cost);
                sb.append(",\"landing_time\":\"")
                        .append(landing_time).append('\"');
                sb.append(",\"guide_url\":\"")
                        .append(guide_url).append('\"');
                sb.append(",\"gift\":\"")
                        .append(gift).append('\"');
                sb.append(",\"from\":\"")
                        .append(from).append('\"');
                sb.append(",\"flight\":\"")
                        .append(flight).append('\"');
                sb.append(",\"discount\":")
                        .append(discount);
                sb.append(",\"detail\":\"")
                        .append(detail).append('\"');
                sb.append(",\"departure_time\":\"")
                        .append(departure_time).append('\"');
                sb.append(",\"default_detail\":\"")
                        .append(default_detail).append('\"');
                sb.append(",\"deal_detail\":\"")
                        .append(deal_detail).append('\"');
                sb.append(",\"check_in_url\":\"")
                        .append(check_in_url).append('\"');
                sb.append(",\"bonus_rules\":\"")
                        .append(bonus_rules).append('\"');
                sb.append(",\"bonus_cleared\":\"")
                        .append(bonus_cleared).append('\"');
                sb.append(",\"bind_old_card_url\":\"")
                        .append(bind_old_card_url).append('\"');
                sb.append(",\"base_info\":")
                        .append(base_info);
                sb.append(",\"balance_rules\":\"")
                        .append(balance_rules).append('\"');
                sb.append(",\"air_model\":\"")
                        .append(air_model).append('\"');
                sb.append(",\"activate_url\":\"")
                        .append(activate_url).append('\"');
                sb.append('}');
                return sb.toString();
            }

            public BaseInfoBean getBase_info() {
                return base_info;
            }

            public String getDefault_detail() {
                return default_detail;
            }

            public void setDefault_detail(String default_detail) {
                this.default_detail = default_detail;
            }

            public String getGift() {
                return gift;
            }

            public void setGift(String gift) {
                this.gift = gift;
            }

            public long getLeast_cost() {
                return least_cost;
            }

            public void setLeast_cost(int least_cost) {
                this.least_cost = least_cost;
            }

            public long getReduce_cost() {

                return reduce_cost;
            }

            public void setReduce_cost(int reduce_cost) {
                this.reduce_cost = reduce_cost;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public String getSupply_bonus() {
                return supply_bonus;
            }

            public void setSupply_bonus(String supply_bonus) {
                this.supply_bonus = supply_bonus;
            }

            public String getSupply_balance() {
                return supply_balance;
            }

            public void setSupply_balance(String supply_balance) {
                this.supply_balance = supply_balance;
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

            public String getBalance_rules() {
                return balance_rules;
            }

            public void setBalance_rules(String balance_rules) {
                this.balance_rules = balance_rules;
            }

            public String getPrerogative() {
                return prerogative;
            }

            public void setPrerogative(String prerogative) {
                this.prerogative = prerogative;
            }

            public String getBind_old_card_url() {
                return bind_old_card_url;
            }

            public void setBind_old_card_url(String bind_old_card_url) {
                this.bind_old_card_url = bind_old_card_url;
            }

            public String getActivate_url() {
                return activate_url;
            }

            public void setActivate_url(String activate_url) {
                this.activate_url = activate_url;
            }

            public String getTicket_class() {
                return ticket_class;
            }

            public void setTicket_class(String ticket_class) {
                this.ticket_class = ticket_class;
            }

            public String getGuide_url() {
                return guide_url;
            }

            public void setGuide_url(String guide_url) {
                this.guide_url = guide_url;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public String getFlight() {
                return flight;
            }

            public void setFlight(String flight) {
                this.flight = flight;
            }

            public String getDeparture_time() {
                return departure_time;
            }

            public void setDeparture_time(String departure_time) {
                this.departure_time = departure_time;
            }

            public String getLanding_time() {
                return landing_time;
            }

            public void setLanding_time(String landing_time) {
                this.landing_time = landing_time;
            }

            public String getCheck_in_url() {
                return check_in_url;
            }

            public void setCheck_in_url(String check_in_url) {
                this.check_in_url = check_in_url;
            }

            public String getAir_model() {
                return air_model;
            }

            public void setAir_model(String air_model) {
                this.air_model = air_model;
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
                 * status : 1
                 * id : p1Pj9jr90_SQRaVqYI239Ka1erkI
                 * logo_url : http://www.supadmin.cn/uploads/allimg/120216/1_120216214725_1.jpg
                 * appid : wx588def6b0089dd48
                 * code_type : CODE_TYPE_TEXT
                 * brand_name : 海底捞
                 * title : 132 元双人火锅套餐
                 * sub_title :
                 * date_info : {"type":1,"begin_timestamp":1397577600,"end_timestamp":1399910400}
                 * color : #3373bb
                 * notice : 使用时向服务员出示此券
                 * service_phone : 020-88888888
                 * description : 不可与其他优惠同享
                 如需团购券发票，请在消费时向商户提出
                 店内均可使用，仅限堂食
                 餐前不可打包，餐后未吃完，可打包
                 本团购券不限人数，建议2 人使用，超过建议人数须另收酱料费5 元/位
                 本单谢绝自带酒水饮料
                 * use_limit : 1
                 * get_limit : 3
                 * can_share : true
                 * location_id_list : [123,12321,345345]
                 * url_name_type : URL_NAME_TYPE_RESERVATION
                 * custom_url : http://www.qq.com
                 * source : 大众点评
                 * sku : {"quantity":0}
                 */

                private int status;
                private String id;
                private String logo_url;
                private String appid;
                private String code_type;
                private String brand_name;
                private String title;
                private String sub_title;
                private DateInfoBean date_info;
                private String color;
                private String notice;
                private String service_phone;
                private String description;
                private int use_limit;
                private int get_limit;
                private boolean use_custom_code;
                private boolean bind_openid;
                private boolean can_share;
                private boolean can_give_friend;
                private String fixed_term;
                private String fixed_begin_term;

                private String url_name_type;
                private String custom_url;
                private String source;
                private SkuBean sku;
                private List<Integer> location_id_list;

                @Override
                public String toString() {
                    final StringBuilder sb = new StringBuilder("{");
                    sb.append("\"use_limit\":")
                            .append(use_limit);
                    sb.append(",\"use_custom_code\":")
                            .append(use_custom_code);
                    sb.append(",\"url_name_type\":\"")
                            .append(url_name_type).append('\"');
                    sb.append(",\"title\":\"")
                            .append(title).append('\"');
                    sb.append(",\"sub_title\":\"")
                            .append(sub_title).append('\"');
                    sb.append(",\"status\":")
                            .append(status);
                    sb.append(",\"source\":\"")
                            .append(source).append('\"');
                    sb.append(",\"sku\":")
                            .append(sku);
                    sb.append(",\"service_phone\":\"")
                            .append(service_phone).append('\"');
                    sb.append(",\"notice\":\"")
                            .append(notice).append('\"');
                    sb.append(",\"logo_url\":\"")
                            .append(logo_url).append('\"');
                    sb.append(",\"location_id_list\":")
                            .append(location_id_list);
                    sb.append(",\"id\":\"")
                            .append(id).append('\"');
                    sb.append(",\"get_limit\":")
                            .append(get_limit);
                    sb.append(",\"fixed_term\":\"")
                            .append(fixed_term).append('\"');
                    sb.append(",\"fixed_begin_term\":\"")
                            .append(fixed_begin_term).append('\"');
                    sb.append(",\"description\":\"")
                            .append(description).append('\"');
                    sb.append(",\"date_info\":")
                            .append(date_info);
                    sb.append(",\"custom_url\":\"")
                            .append(custom_url).append('\"');
                    sb.append(",\"color\":\"")
                            .append(color).append('\"');
                    sb.append(",\"code_type\":\"")
                            .append(code_type).append('\"');
                    sb.append(",\"can_share\":")
                            .append(can_share);
                    sb.append(",\"can_give_friend\":")
                            .append(can_give_friend);
                    sb.append(",\"brand_name\":\"")
                            .append(brand_name).append('\"');
                    sb.append(",\"bind_openid\":")
                            .append(bind_openid);
                    sb.append(",\"appid\":\"")
                            .append(appid).append('\"');
                    sb.append('}');
                    return sb.toString();
                }

                public boolean isCan_give_friend() {
                    return can_give_friend;
                }

                public void setCan_give_friend(boolean can_give_friend) {
                    this.can_give_friend = can_give_friend;
                }


                public String getFixed_term() {
                    return fixed_term;
                }

                public void setFixed_term(String fixed_term) {
                    this.fixed_term = fixed_term;
                }

                public String getFixed_begin_term() {
                    return fixed_begin_term;
                }

                public void setFixed_begin_term(String fixed_begin_term) {
                    this.fixed_begin_term = fixed_begin_term;
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

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLogo_url() {
                    return logo_url;
                }

                public void setLogo_url(String logo_url) {
                    this.logo_url = logo_url;
                }

                public String getAppid() {
                    return appid;
                }

                public void setAppid(String appid) {
                    this.appid = appid;
                }

                public String getCode_type() {
                    return code_type;
                }

                public void setCode_type(String code_type) {
                    this.code_type = code_type;
                }

                public String getBrand_name() {
                    return brand_name;
                }

                public void setBrand_name(String brand_name) {
                    this.brand_name = brand_name;
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

                public DateInfoBean getDate_info() {
                    return date_info;
                }

                public void setDate_info(DateInfoBean date_info) {
                    this.date_info = date_info;
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

                public boolean isCan_share() {
                    return can_share;
                }

                public void setCan_share(boolean can_share) {
                    this.can_share = can_share;
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

                public SkuBean getSku() {
                    return sku;
                }

                public void setSku(SkuBean sku) {
                    this.sku = sku;
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
                    private long begin_timestamp;
                    private long end_timestamp;

                    @Override
                    public String toString() {
                        final StringBuilder sb = new StringBuilder("{");
                        sb.append("\"type\":")
                                .append(type);
                        sb.append(",\"end_timestamp\":")
                                .append(end_timestamp);
                        sb.append(",\"begin_timestamp\":")
                                .append(begin_timestamp);
                        sb.append('}');
                        return sb.toString();
                    }

                    public int getType() {
                        return type;
                    }

                    public void setType(int type) {
                        this.type = type;
                    }

                    public long getBegin_timestamp() {
                        return begin_timestamp;
                    }

                    public void setBegin_timestamp(int begin_timestamp) {
                        this.begin_timestamp = begin_timestamp;
                    }

                    public long getEnd_timestamp() {
                        return end_timestamp;
                    }

                    public void setEnd_timestamp(int end_timestamp) {
                        this.end_timestamp = end_timestamp;
                    }
                }

                public static class SkuBean {
                    /**
                     * quantity : 0
                     */

                    private int quantity;

                    @Override
                    public String toString() {
                        final StringBuilder sb = new StringBuilder("{");
                        sb.append("\"quantity\":")
                                .append(quantity);
                        sb.append('}');
                        return sb.toString();
                    }

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
