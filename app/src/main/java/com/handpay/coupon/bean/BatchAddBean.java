package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class BatchAddBean {

    private List<LocationListBean> location_list;

    public List<LocationListBean> getLocation_list() {
        return location_list;
    }

    public void setLocation_list(List<LocationListBean> location_list) {
        this.location_list = location_list;
    }

    public static class LocationListBean {
        /**
         * business_name : TIT 创意园1 号店
         * province : 广东省
         * city : 广州市
         * district : 海珠区
         * address : 中国广东省广州市海珠区艺苑路11 号
         * telephone : 020-89772059
         * category : 房产小区
         * longitude : 115.32375
         * latitude : 25.097486
         */

        private String business_name;
        private String province;
        private String city;
        private String district;
        private String address;
        private String telephone;
        private String category;
        private String longitude;
        private String latitude;

        public String getBusiness_name() {
            return business_name;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
