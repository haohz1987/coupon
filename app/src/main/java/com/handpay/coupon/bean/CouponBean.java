package com.handpay.coupon.bean;

import android.databinding.BaseObservable;

import java.io.Serializable;
import java.util.List;

public class CouponBean extends BaseObservable implements Serializable{

    /**
     * respCode : 0000
     * respMsg : 请求成功
     * mechantName : 商户01
     * mechantCode : 0123456789
     * cardTotal : 5
     * validRepertoryTerm : 30
     * result : [{"coupNo":"201712181033","logo":"http://api.map.baidu.com/images/weather/day/duoyun.png","coupColor":0,"coupTitle":"coupTitle","coupAmount":2.03,"discount":"","consumeLite":10000.63,"validPeriod":"30","receiveTime":"201712181033","startDay":"201712181033","endDay":"coupTitle","repertoryAmount":100,"usedLimit":1,"explain":"到店请出示优惠券","usedPerson":2,"usedCount":2,"cancelCount":1,"qrCode":"https://qr.api.cli.im/qr?data=%25E5%25BE%25AE%25E4%25BF%25A1&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=9c0c47f10f90fa1e73c7f86dcc151ac9"},{"coupNo":"201712181133","logo":"http://api.map.baidu.com/images/weather/day/duoyun.png","coupColor":0,"coupTitle":"coupTitle","coupAmount":2.03,"discount":"","consumeLite":10000.63,"validPeriod":"","receiveTime":"","startDay":"201712181033","endDay":"201712181033","repertoryAmount":100,"usedLimit":1,"explain":"到店请出示优惠券","usedPerson":2,"usedCount":2,"cancelCount":1,"qrCode":"https://qr.api.cli.im/qr?data=%25E5%25BE%25AE%25E4%25BF%25A1&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=9c0c47f10f90fa1e73c7f86dcc151ac9"},{"coupNo":"201712181033","logo":"http://api.map.baidu.com/images/weather/day/duoyun.png","coupColor":0,"coupTitle":"coupTitle","coupAmount":2.03,"discount":"","consumeLite":10000.63,"validPeriod":"30","receiveTime":"201712181033","startDay":"","endDay":"","repertoryAmount":100,"usedLimit":1,"explain":"到店请出示优惠券","usedPerson":2,"usedCount":2,"cancelCount":1,"qrCode":"https://qr.api.cli.im/qr?data=%25E5%25BE%25AE%25E4%25BF%25A1&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=9c0c47f10f90fa1e73c7f86dcc151ac9"},{"coupNo":"201712181133","logo":"http://api.map.baidu.com/images/weather/day/duoyun.png","coupColor":0,"coupTitle":"coupTitle","coupAmount":2.03,"discount":"","consumeLite":10000.63,"validPeriod":"","receiveTime":"","startDay":"201712181033","endDay":"201712181033","repertoryAmount":100,"usedLimit":1,"explain":"到店请出示优惠券","usedPerson":2,"usedCount":2,"cancelCount":1,"qrCode":"https://qr.api.cli.im/qr?data=%25E5%25BE%25AE%25E4%25BF%25A1&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=9c0c47f10f90fa1e73c7f86dcc151ac9"},{"coupNo":"201712181033","logo":"http://api.map.baidu.com/images/weather/day/duoyun.png","coupColor":0,"coupTitle":"coupTitle","coupAmount":2.03,"discount":"","consumeLite":10000.63,"validPeriod":"30","receiveTime":"201712181033","startDay":"","endDay":"","repertoryAmount":100,"usedLimit":1,"explain":"到店请出示优惠券","usedPerson":2,"usedCount":2,"cancelCount":1,"qrCode":"https://qr.api.cli.im/qr?data=%25E5%25BE%25AE%25E4%25BF%25A1&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=9c0c47f10f90fa1e73c7f86dcc151ac9"},{"coupNo":"201712181133","logo":"http://api.map.baidu.com/images/weather/day/duoyun.png","coupColor":0,"coupTitle":"coupTitle","coupAmount":2.03,"discount":"","consumeLite":10000.63,"validPeriod":"","receiveTime":"","startDay":"201712181033","endDay":"201712181033","repertoryAmount":100,"usedLimit":1,"explain":"到店请出示优惠券","usedPerson":2,"usedCount":2,"cancelCount":1,"qrCode":"https://qr.api.cli.im/qr?data=%25E5%25BE%25AE%25E4%25BF%25A1&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=9c0c47f10f90fa1e73c7f86dcc151ac9"}]
     */

    private String respCode;
    private String respMsg;
    private String mechantName;
    private String mechantCode;
    private int cardTotal;
    private String validRepertoryTerm;
    private List<ResultBean> result;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"validRepertoryTerm\":\"")
                .append(validRepertoryTerm).append('\"');
        sb.append(",\"result\":")
                .append(result);
        sb.append(",\"respMsg\":\"")
                .append(respMsg).append('\"');
        sb.append(",\"respCode\":\"")
                .append(respCode).append('\"');
        sb.append(",\"mechantName\":\"")
                .append(mechantName).append('\"');
        sb.append(",\"mechantCode\":\"")
                .append(mechantCode).append('\"');
        sb.append(",\"cardTotal\":")
                .append(cardTotal);
        sb.append('}');
        return sb.toString();
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getMechantName() {
        return mechantName;
    }

    public void setMechantName(String mechantName) {
        this.mechantName = mechantName;
    }

    public String getMechantCode() {
        return mechantCode;
    }

    public void setMechantCode(String mechantCode) {
        this.mechantCode = mechantCode;
    }

    public int getCardTotal() {
        return cardTotal;
    }

    public void setCardTotal(int cardTotal) {
        this.cardTotal = cardTotal;
    }

    public String getValidRepertoryTerm() {
        return validRepertoryTerm;
    }

    public void setValidRepertoryTerm(String validRepertoryTerm) {
        this.validRepertoryTerm = validRepertoryTerm;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean extends BaseObservable implements Serializable{
        /**
         * coupNo : 201712181033
         * logo : http://api.map.baidu.com/images/weather/day/duoyun.png
         * coupColor : 0
         * coupTitle : coupTitle
         * coupAmount : 2.03
         * discount :
         * consumeLite : 10000.63
         * validPeriod : 30
         * receiveTime : 201712181033
         * startDay : 201712181033
         * endDay : coupTitle
         * repertoryAmount : 100
         * usedLimit : 1
         * explain : 到店请出示优惠券
         * usedPerson : 2
         * usedCount : 2
         * cancelCount : 1
         * qrCode : https://qr.api.cli.im/qr?data=%25E5%25BE%25AE%25E4%25BF%25A1&level=H&transparent=false&bgcolor=%23ffffff&forecolor=%23000000&blockpixel=12&marginblock=1&logourl=&size=280&kid=cliim&key=9c0c47f10f90fa1e73c7f86dcc151ac9
         */

        private String coupNo;
        private String logo;
        private int coupColor;
        private String coupTitle;
        private double coupAmount;
        private String discount;
        private double consumeLite;
        private String validPeriod;
        private String receiveTime;
        private String startDay;
        private String endDay;
        private int repertoryAmount;
        private int usedLimit;
        private String explain;
        private int usedPerson;
        private int usedCount;
        private int cancelCount;
        private String qrCode;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"validPeriod\":\"")
                    .append(validPeriod).append('\"');
            sb.append(",\"usedPerson\":")
                    .append(usedPerson);
            sb.append(",\"usedLimit\":")
                    .append(usedLimit);
            sb.append(",\"usedCount\":")
                    .append(usedCount);
            sb.append(",\"startDay\":\"")
                    .append(startDay).append('\"');
            sb.append(",\"repertoryAmount\":")
                    .append(repertoryAmount);
            sb.append(",\"receiveTime\":\"")
                    .append(receiveTime).append('\"');
            sb.append(",\"qrCode\":\"")
                    .append(qrCode).append('\"');
            sb.append(",\"logo\":\"")
                    .append(logo).append('\"');
            sb.append(",\"explain\":\"")
                    .append(explain).append('\"');
            sb.append(",\"endDay\":\"")
                    .append(endDay).append('\"');
            sb.append(",\"discount\":\"")
                    .append(discount).append('\"');
            sb.append(",\"coupTitle\":\"")
                    .append(coupTitle).append('\"');
            sb.append(",\"coupNo\":\"")
                    .append(coupNo).append('\"');
            sb.append(",\"coupColor\":")
                    .append(coupColor);
            sb.append(",\"coupAmount\":")
                    .append(coupAmount);
            sb.append(",\"consumeLite\":")
                    .append(consumeLite);
            sb.append(",\"cancelCount\":")
                    .append(cancelCount);
            sb.append('}');
            return sb.toString();
        }

        public String getCoupNo() {
            return coupNo;
        }

        public void setCoupNo(String coupNo) {
            this.coupNo = coupNo;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public int getCoupColor() {
            return coupColor;
        }

        public void setCoupColor(int coupColor) {
            this.coupColor = coupColor;
        }

        public String getCoupTitle() {
            return coupTitle;
        }

        public void setCoupTitle(String coupTitle) {
            this.coupTitle = coupTitle;
        }

        public double getCoupAmount() {
            return coupAmount;
        }

        public void setCoupAmount(double coupAmount) {
            this.coupAmount = coupAmount;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public double getConsumeLite() {
            return consumeLite;
        }

        public void setConsumeLite(double consumeLite) {
            this.consumeLite = consumeLite;
        }

        public String getValidPeriod() {
            return validPeriod;
        }

        public void setValidPeriod(String validPeriod) {
            this.validPeriod = validPeriod;
        }

        public String getReceiveTime() {
            return receiveTime;
        }

        public void setReceiveTime(String receiveTime) {
            this.receiveTime = receiveTime;
        }

        public String getStartDay() {
            return startDay;
        }

        public void setStartDay(String startDay) {
            this.startDay = startDay;
        }

        public String getEndDay() {
            return endDay;
        }

        public void setEndDay(String endDay) {
            this.endDay = endDay;
        }

        public int getRepertoryAmount() {
            return repertoryAmount;
        }

        public void setRepertoryAmount(int repertoryAmount) {
            this.repertoryAmount = repertoryAmount;
        }

        public int getUsedLimit() {
            return usedLimit;
        }

        public void setUsedLimit(int usedLimit) {
            this.usedLimit = usedLimit;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public int getUsedPerson() {
            return usedPerson;
        }

        public void setUsedPerson(int usedPerson) {
            this.usedPerson = usedPerson;
        }

        public int getUsedCount() {
            return usedCount;
        }

        public void setUsedCount(int usedCount) {
            this.usedCount = usedCount;
        }

        public int getCancelCount() {
            return cancelCount;
        }

        public void setCancelCount(int cancelCount) {
            this.cancelCount = cancelCount;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
    }
}
