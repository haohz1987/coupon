package com.handpay.coupon.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by haohz on 2018/4/18.
 */
@Entity
public class GetCardData{

    @Id
    public String card_id; //卡id
    public String local_id; //本地卡id，预留，可以定义为@id，用于排序，筛选等功能
    public String card_type;   //卡券类型
    /* general_coupon */
    public String default_detail; //通用卡券描述文本
    /* groupon */
    public String deal_detail; //团购券专用，团购详情
    /* gift */
    public String gift; //礼品券专用，表示礼品名字
    /* cash */
    public long least_cost; //代金券专用，表示起用金额（单位为分）
    public long reduce_cost; //代金券专用，表示减免金额（单位为分）
    /* discount */
    public int discount; //折扣券专用，表示打折额度（百分比）。填30就是七折
    //是否支持积分，填写true 或false，如填写true，积分相关字段均为必填。填写false，积
    //分字段无需填写。储值字段处理方式相同。
    /* member_card */
    public String supply_bonus;
    public String supply_balance; //是否支持储值，填写true 或false。
    public String bonus_cleared; //积分清零规则
    public String bonus_rules; //积分规则
    public String balance_rules; //储值说明
    public String prerogative; //特权说明
    public String bind_old_card_url; //绑定旧卡的url。与“activate_url”二选一必填。
    public String activate_url; //激活会员卡的url。与“bind_old_card_url”二选一必填
    /* scenic_ticket */
    public String ticket_class; //票类型，例如平日全票，套票等。
    public String guide_url; //导览图url
    /* movie_ticket */
    public String detail; //电影票详情
    /* boarding_pass */
    public String from; //起点
    public String to; //终点
    public String flight; //航班
    public String departure_time; //起飞时间
    public String landing_time; //降落时间
    public String check_in_url; //在线值机的链接
    public String air_model; //机型

    /************* 基本的卡券数据 base_info *************/
//    public String card_id; //卡id
    public String logo_url; //卡券的商户logo
    public String code_type;// code展示类型
    public String branch_name;//商户类型
    public String title;//券名
    public String color;//券颜色
    public String notice; //使用提醒。（一句话描述，展示在首页）
    public String service_phone; // 客服电话
    public String description; //使用说明。长文本描述，可以分行。
    public int use_limit; //每人使用次数限制
    public int get_limit; //每人最大领取次数
    public boolean use_custom_code; //是否自定义code 码
    public boolean bind_openid; //是否指定用户领取
    //领取卡券原生页面是否可分享，填写true 或false，true 代表可分享。默认可分享。
    public boolean can_give_friend;
    //卡券是否可转赠，填写true 或false,true 代表可转赠。默认可转赠。
    public boolean can_share;
    public String location_id_list; //门店位置ID
    public String date_info; //使用日期，有效期的信息
    public int type; //使用时间的类型，1：固定日期区间，2：固定时长（自领取后按天算）
    public long begin_timestamp; //固定日期区间专用，表示起用时间
    public long end_timestamp; //固定日期区间专用，表示结束时间
    public String fixed_term; //固定时长专用，表示自领取后多少天内有效。（单位为天）
    public String fixed_begin_term; //固定时长专用，表示自领取后多少天开始生效。（单位为天）
    public String sku; //固定时长专用
    public int quantity; //库存数量
    public String url_name_type; //商户自定义cell 名称
    public String custom_url; //商户自定义url 地址，支持卡券页内跳转
    public String source; //第三方来源名，例如同程旅游、格瓦拉
    //1：待审核，2：审核失败，3：通过审核， 4：已删除（飞机票的status 字段为1：正常2：已删除）
    public int status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"use_limit\":")
                .append(use_limit);
        sb.append(",\"use_custom_code\":")
                .append(use_custom_code);
        sb.append(",\"url_name_type\":\"")
                .append(url_name_type).append('\"');
        sb.append(",\"type\":")
                .append(type);
        sb.append(",\"to\":\"")
                .append(to).append('\"');
        sb.append(",\"title\":\"")
                .append(title).append('\"');
        sb.append(",\"ticket_class\":\"")
                .append(ticket_class).append('\"');
        sb.append(",\"supply_bonus\":\"")
                .append(supply_bonus).append('\"');
        sb.append(",\"supply_balance\":\"")
                .append(supply_balance).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"source\":\"")
                .append(source).append('\"');
        sb.append(",\"sku\":\"")
                .append(sku).append('\"');
        sb.append(",\"service_phone\":\"")
                .append(service_phone).append('\"');
        sb.append(",\"reduce_cost\":")
                .append(reduce_cost);
        sb.append(",\"quantity\":")
                .append(quantity);
        sb.append(",\"prerogative\":\"")
                .append(prerogative).append('\"');
        sb.append(",\"notice\":\"")
                .append(notice).append('\"');
        sb.append(",\"logo_url\":\"")
                .append(logo_url).append('\"');
        sb.append(",\"location_id_list\":\"")
                .append(location_id_list).append('\"');
        sb.append(",\"local_id\":\"")
                .append(local_id).append('\"');
        sb.append(",\"least_cost\":")
                .append(least_cost);
        sb.append(",\"landing_time\":\"")
                .append(landing_time).append('\"');
        sb.append(",\"guide_url\":\"")
                .append(guide_url).append('\"');
        sb.append(",\"gift\":\"")
                .append(gift).append('\"');
        sb.append(",\"get_limit\":")
                .append(get_limit);
        sb.append(",\"from\":\"")
                .append(from).append('\"');
        sb.append(",\"flight\":\"")
                .append(flight).append('\"');
        sb.append(",\"fixed_term\":\"")
                .append(fixed_term).append('\"');
        sb.append(",\"fixed_begin_term\":\"")
                .append(fixed_begin_term).append('\"');
        sb.append(",\"end_timestamp\":")
                .append(end_timestamp);
        sb.append(",\"discount\":")
                .append(discount);
        sb.append(",\"detail\":\"")
                .append(detail).append('\"');
        sb.append(",\"description\":\"")
                .append(description).append('\"');
        sb.append(",\"departure_time\":\"")
                .append(departure_time).append('\"');
        sb.append(",\"default_detail\":\"")
                .append(default_detail).append('\"');
        sb.append(",\"deal_detail\":\"")
                .append(deal_detail).append('\"');
        sb.append(",\"date_info\":\"")
                .append(date_info).append('\"');
        sb.append(",\"custom_url\":\"")
                .append(custom_url).append('\"');
        sb.append(",\"color\":\"")
                .append(color).append('\"');
        sb.append(",\"code_type\":\"")
                .append(code_type).append('\"');
        sb.append(",\"check_in_url\":\"")
                .append(check_in_url).append('\"');
        sb.append(",\"card_type\":\"")
                .append(card_type).append('\"');
        sb.append(",\"card_id\":\"")
                .append(card_id).append('\"');
        sb.append(",\"can_share\":")
                .append(can_share);
        sb.append(",\"can_give_friend\":")
                .append(can_give_friend);
        sb.append(",\"branch_name\":\"")
                .append(branch_name).append('\"');
        sb.append(",\"bonus_rules\":\"")
                .append(bonus_rules).append('\"');
        sb.append(",\"bonus_cleared\":\"")
                .append(bonus_cleared).append('\"');
        sb.append(",\"bind_openid\":")
                .append(bind_openid);
        sb.append(",\"bind_old_card_url\":\"")
                .append(bind_old_card_url).append('\"');
        sb.append(",\"begin_timestamp\":")
                .append(begin_timestamp);
        sb.append(",\"balance_rules\":\"")
                .append(balance_rules).append('\"');
        sb.append(",\"air_model\":\"")
                .append(air_model).append('\"');
        sb.append(",\"activate_url\":\"")
                .append(activate_url).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Generated(hash = 120773613)
    public GetCardData(String card_id, String local_id, String card_type,
            String default_detail, String deal_detail, String gift, long least_cost,
            long reduce_cost, int discount, String supply_bonus,
            String supply_balance, String bonus_cleared, String bonus_rules,
            String balance_rules, String prerogative, String bind_old_card_url,
            String activate_url, String ticket_class, String guide_url,
            String detail, String from, String to, String flight,
            String departure_time, String landing_time, String check_in_url,
            String air_model, String logo_url, String code_type, String branch_name,
            String title, String color, String notice, String service_phone,
            String description, int use_limit, int get_limit,
            boolean use_custom_code, boolean bind_openid, boolean can_give_friend,
            boolean can_share, String location_id_list, String date_info, int type,
            long begin_timestamp, long end_timestamp, String fixed_term,
            String fixed_begin_term, String sku, int quantity, String url_name_type,
            String custom_url, String source, int status) {
        this.card_id = card_id;
        this.local_id = local_id;
        this.card_type = card_type;
        this.default_detail = default_detail;
        this.deal_detail = deal_detail;
        this.gift = gift;
        this.least_cost = least_cost;
        this.reduce_cost = reduce_cost;
        this.discount = discount;
        this.supply_bonus = supply_bonus;
        this.supply_balance = supply_balance;
        this.bonus_cleared = bonus_cleared;
        this.bonus_rules = bonus_rules;
        this.balance_rules = balance_rules;
        this.prerogative = prerogative;
        this.bind_old_card_url = bind_old_card_url;
        this.activate_url = activate_url;
        this.ticket_class = ticket_class;
        this.guide_url = guide_url;
        this.detail = detail;
        this.from = from;
        this.to = to;
        this.flight = flight;
        this.departure_time = departure_time;
        this.landing_time = landing_time;
        this.check_in_url = check_in_url;
        this.air_model = air_model;
        this.logo_url = logo_url;
        this.code_type = code_type;
        this.branch_name = branch_name;
        this.title = title;
        this.color = color;
        this.notice = notice;
        this.service_phone = service_phone;
        this.description = description;
        this.use_limit = use_limit;
        this.get_limit = get_limit;
        this.use_custom_code = use_custom_code;
        this.bind_openid = bind_openid;
        this.can_give_friend = can_give_friend;
        this.can_share = can_share;
        this.location_id_list = location_id_list;
        this.date_info = date_info;
        this.type = type;
        this.begin_timestamp = begin_timestamp;
        this.end_timestamp = end_timestamp;
        this.fixed_term = fixed_term;
        this.fixed_begin_term = fixed_begin_term;
        this.sku = sku;
        this.quantity = quantity;
        this.url_name_type = url_name_type;
        this.custom_url = custom_url;
        this.source = source;
        this.status = status;
    }

    @Generated(hash = 2077043721)
    public GetCardData() {
    }
    public String getCard_id() {
        return this.card_id;
    }
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }
    public String getLocal_id() {
        return this.local_id;
    }
    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }
    public String getCard_type() {
        return this.card_type;
    }
    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }
    public String getDefault_detail() {
        return this.default_detail;
    }
    public void setDefault_detail(String default_detail) {
        this.default_detail = default_detail;
    }
    public String getDeal_detail() {
        return this.deal_detail;
    }
    public void setDeal_detail(String deal_detail) {
        this.deal_detail = deal_detail;
    }
    public String getGift() {
        return this.gift;
    }
    public void setGift(String gift) {
        this.gift = gift;
    }
    public long getLeast_cost() {
        return this.least_cost;
    }
    public void setLeast_cost(long least_cost) {
        this.least_cost = least_cost;
    }
    public long getReduce_cost() {
        return this.reduce_cost;
    }
    public void setReduce_cost(long reduce_cost) {
        this.reduce_cost = reduce_cost;
    }
    public int getDiscount() {
        return this.discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public String getSupply_bonus() {
        return this.supply_bonus;
    }
    public void setSupply_bonus(String supply_bonus) {
        this.supply_bonus = supply_bonus;
    }
    public String getSupply_balance() {
        return this.supply_balance;
    }
    public void setSupply_balance(String supply_balance) {
        this.supply_balance = supply_balance;
    }
    public String getBonus_cleared() {
        return this.bonus_cleared;
    }
    public void setBonus_cleared(String bonus_cleared) {
        this.bonus_cleared = bonus_cleared;
    }
    public String getBonus_rules() {
        return this.bonus_rules;
    }
    public void setBonus_rules(String bonus_rules) {
        this.bonus_rules = bonus_rules;
    }
    public String getBalance_rules() {
        return this.balance_rules;
    }
    public void setBalance_rules(String balance_rules) {
        this.balance_rules = balance_rules;
    }
    public String getPrerogative() {
        return this.prerogative;
    }
    public void setPrerogative(String prerogative) {
        this.prerogative = prerogative;
    }
    public String getBind_old_card_url() {
        return this.bind_old_card_url;
    }
    public void setBind_old_card_url(String bind_old_card_url) {
        this.bind_old_card_url = bind_old_card_url;
    }
    public String getActivate_url() {
        return this.activate_url;
    }
    public void setActivate_url(String activate_url) {
        this.activate_url = activate_url;
    }
    public String getTicket_class() {
        return this.ticket_class;
    }
    public void setTicket_class(String ticket_class) {
        this.ticket_class = ticket_class;
    }
    public String getGuide_url() {
        return this.guide_url;
    }
    public void setGuide_url(String guide_url) {
        this.guide_url = guide_url;
    }
    public String getDetail() {
        return this.detail;
    }
    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getFrom() {
        return this.from;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public String getTo() {
        return this.to;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public String getFlight() {
        return this.flight;
    }
    public void setFlight(String flight) {
        this.flight = flight;
    }
    public String getDeparture_time() {
        return this.departure_time;
    }
    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }
    public String getLanding_time() {
        return this.landing_time;
    }
    public void setLanding_time(String landing_time) {
        this.landing_time = landing_time;
    }
    public String getCheck_in_url() {
        return this.check_in_url;
    }
    public void setCheck_in_url(String check_in_url) {
        this.check_in_url = check_in_url;
    }
    public String getAir_model() {
        return this.air_model;
    }
    public void setAir_model(String air_model) {
        this.air_model = air_model;
    }
    public String getLogo_url() {
        return this.logo_url;
    }
    public void setLogo_url(String logo_url) {
        this.logo_url = logo_url;
    }
    public String getCode_type() {
        return this.code_type;
    }
    public void setCode_type(String code_type) {
        this.code_type = code_type;
    }
    public String getBranch_name() {
        return this.branch_name;
    }
    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getColor() {
        return this.color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getNotice() {
        return this.notice;
    }
    public void setNotice(String notice) {
        this.notice = notice;
    }
    public String getService_phone() {
        return this.service_phone;
    }
    public void setService_phone(String service_phone) {
        this.service_phone = service_phone;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getUse_limit() {
        return this.use_limit;
    }
    public void setUse_limit(int use_limit) {
        this.use_limit = use_limit;
    }
    public int getGet_limit() {
        return this.get_limit;
    }
    public void setGet_limit(int get_limit) {
        this.get_limit = get_limit;
    }
    public boolean getUse_custom_code() {
        return this.use_custom_code;
    }
    public void setUse_custom_code(boolean use_custom_code) {
        this.use_custom_code = use_custom_code;
    }
    public boolean getBind_openid() {
        return this.bind_openid;
    }
    public void setBind_openid(boolean bind_openid) {
        this.bind_openid = bind_openid;
    }
    public boolean getCan_give_friend() {
        return this.can_give_friend;
    }
    public void setCan_give_friend(boolean can_give_friend) {
        this.can_give_friend = can_give_friend;
    }
    public boolean getCan_share() {
        return this.can_share;
    }
    public void setCan_share(boolean can_share) {
        this.can_share = can_share;
    }
    public String getLocation_id_list() {
        return this.location_id_list;
    }
    public void setLocation_id_list(String location_id_list) {
        this.location_id_list = location_id_list;
    }
    public String getDate_info() {
        return this.date_info;
    }
    public void setDate_info(String date_info) {
        this.date_info = date_info;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public long getBegin_timestamp() {
        return this.begin_timestamp;
    }
    public void setBegin_timestamp(long begin_timestamp) {
        this.begin_timestamp = begin_timestamp;
    }
    public long getEnd_timestamp() {
        return this.end_timestamp;
    }
    public void setEnd_timestamp(long end_timestamp) {
        this.end_timestamp = end_timestamp;
    }
    public String getFixed_term() {
        return this.fixed_term;
    }
    public void setFixed_term(String fixed_term) {
        this.fixed_term = fixed_term;
    }
    public String getFixed_begin_term() {
        return this.fixed_begin_term;
    }
    public void setFixed_begin_term(String fixed_begin_term) {
        this.fixed_begin_term = fixed_begin_term;
    }
    public String getSku() {
        return this.sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getUrl_name_type() {
        return this.url_name_type;
    }
    public void setUrl_name_type(String url_name_type) {
        this.url_name_type = url_name_type;
    }
    public String getCustom_url() {
        return this.custom_url;
    }
    public void setCustom_url(String custom_url) {
        this.custom_url = custom_url;
    }
    public String getSource() {
        return this.source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }


}
