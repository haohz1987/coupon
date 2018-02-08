package com.handpay.coupon.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.http.ParamNames;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ManageBeans extends BaseObservable implements Parcelable {

    /**
     * respCode : 0000
     * respMsg : 请求成功
     * logo : http://api.map.baidu.com/images/weather/day/duoyun.png
     * mechantName : 商户01
     * mechantCode : 0123456789
     * couponType : 2
     * cardAccount : 2
     * cardTotal : 5
     * validRepertoryTerm : 30
     * result : [{"merchantId":"123456789","storeName":"茶餐厅","storeIcon":"http://p0.meituan.net/deal/f95d88734ab566b7c9b1bd31926a782e51858.jpg","storeQueue":15,"couponAmount":"9折","explain":"到店请出示优惠券"},{"merchantId":"123456789","storeName":"火锅自助餐厅","storeIcon":"http://p0.meituan.net/deal/__49021345__6631633.jpg","storeQueue":10,"couponAmount":"5折","explain":"到店请出示优惠券"},{"merchantId":"123456789","storeName":"川菜","storeIcon":"http://p0.meituan.net/mogu/657659cd0f6ada9ba68115b298b9766162717.jpg","storeQueue":0,"couponAmount":"满50减10","explain":"到店请出示优惠券"},{"merchantId":"123456789","storeName":"美食广场","storeIcon":"https://img.meituan.net/msmerchant/754f953e2a7b25d4db55faab07013fb11390753.jpg","storeQueue":20,"couponAmount":"满100减20","explain":"到店请出示优惠券"},{"merchantId":"123456789","storeName":"哥老官美蛙","storeIcon":"https://img.meituan.net/msmerchant/754f953e2a7b25d4db55faab07013fb11390753.jpg","storeQueue":2,"couponAmount":"8折","explain":"到店请出示优惠券"},{"merchantId":"123456789","storeName":"查厘士","storeIcon":"https://img.meituan.net/msmerchant/754f953e2a7b25d4db55faab07013fb11390753.jpg","storeQueue":0,"couponAmount":"满200减10","explain":"到店请出示优惠券"}]
     */
    @ParamNames("respCode")
    private String respCode;
    @ParamNames("respMsg")
    private String respMsg;
    @ParamNames("logo")
    private String logo;
    @ParamNames("mechantName")
    private String mechantName;
    @ParamNames("mechantCode")
    private String mechantCode;
    @ParamNames("couponType")
    private int couponType;
    @ParamNames("cardAccount")
    private int cardAccount;
    @ParamNames("cardTotal")
    private int cardTotal;
    @ParamNames("validRepertoryTerm")
    private String validRepertoryTerm;
    @ParamNames("result")
    private List<ResultBean> result;
    @Bindable
    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }
    @Bindable
    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
    @Bindable
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
    @Bindable
    public String getMechantName() {
        return mechantName;
    }

    public void setMechantName(String mechantName) {
        this.mechantName = mechantName;
    }
    @Bindable
    public String getMechantCode() {
        return mechantCode;
    }

    public void setMechantCode(String mechantCode) {
        this.mechantCode = mechantCode;
    }
    @Bindable
    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
    }
    @Bindable
    public int getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(int cardAccount) {
        this.cardAccount = cardAccount;
    }
    @Bindable
    public int getCardTotal() {
        return cardTotal;
    }

    public void setCardTotal(int cardTotal) {
        this.cardTotal = cardTotal;
    }
    @Bindable
    public String getValidRepertoryTerm() {
        return validRepertoryTerm;
    }

    public void setValidRepertoryTerm(String validRepertoryTerm) {
        this.validRepertoryTerm = validRepertoryTerm;
    }
    @Bindable
    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean extends BaseObservable implements Serializable {
        /**
         * merchantId : 123456789
         * storeName : 茶餐厅
         * storeIcon : http://p0.meituan.net/deal/f95d88734ab566b7c9b1bd31926a782e51858.jpg
         * storeQueue : 15
         * couponAmount : 9折
         * explain : 到店请出示优惠券
         */
        @ParamNames("merchantId")
        private String merchantId;
        @ParamNames("storeName")
        private String storeName;
        @ParamNames("storeIcon")
        private String storeIcon;
        @ParamNames("storeQueue")
        private int storeQueue;
        @ParamNames("couponAmount")
        private String couponAmount;
        @ParamNames("explain")
        private String explain;
        @Bindable
        public String getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(String merchantId) {
            this.merchantId = merchantId;
        }
        @Bindable
        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }
        @Bindable
        public String getStoreIcon() {
            return storeIcon;
        }

        public void setStoreIcon(String storeIcon) {
            this.storeIcon = storeIcon;
        }
        @Bindable
        public int getStoreQueue() {
            return storeQueue;
        }

        public void setStoreQueue(int storeQueue) {
            this.storeQueue = storeQueue;
        }
        @Bindable
        public String getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(String couponAmount) {
            this.couponAmount = couponAmount;
        }
        @Bindable
        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

//        @Override
//        public int describeContents() {
//            return 0;
//        }
//
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(this.merchantId);
//            dest.writeString(this.storeName);
//            dest.writeString(this.storeIcon);
//            dest.writeInt(this.storeQueue);
//            dest.writeString(this.couponAmount);
//            dest.writeString(this.explain);
//        }

        public ResultBean() {
        }

        protected ResultBean(Parcel in) {
            this.merchantId = in.readString();
            this.storeName = in.readString();
            this.storeIcon = in.readString();
            this.storeQueue = in.readInt();
            this.couponAmount = in.readString();
            this.explain = in.readString();
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel source) {
                return new ResultBean(source);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.respCode);
        dest.writeString(this.respMsg);
        dest.writeString(this.logo);
        dest.writeString(this.mechantName);
        dest.writeString(this.mechantCode);
        dest.writeInt(this.couponType);
        dest.writeInt(this.cardAccount);
        dest.writeInt(this.cardTotal);
        dest.writeString(this.validRepertoryTerm);
        dest.writeList(this.result);
    }

    public ManageBeans() {
    }

    protected ManageBeans(Parcel in) {
        this.respCode = in.readString();
        this.respMsg = in.readString();
        this.logo = in.readString();
        this.mechantName = in.readString();
        this.mechantCode = in.readString();
        this.couponType = in.readInt();
        this.cardAccount = in.readInt();
        this.cardTotal = in.readInt();
        this.validRepertoryTerm = in.readString();
        this.result = new ArrayList<ResultBean>();
        in.readList(this.result, ResultBean.class.getClassLoader());
    }

    public static final Creator<ManageBeans> CREATOR = new Creator<ManageBeans>() {
        @Override
        public ManageBeans createFromParcel(Parcel source) {
            return new ManageBeans(source);
        }

        @Override
        public ManageBeans[] newArray(int size) {
            return new ManageBeans[size];
        }
    };
}
