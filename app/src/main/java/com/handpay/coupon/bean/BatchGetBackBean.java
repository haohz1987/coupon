package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class BatchGetBackBean {

    /**
     * errcode : 0
     * errmsg : ok
     * location_list : [{"location_id":493,"name":"steventao home","phone":"020-12345678","address":"广东省广州市番禺区广东省广州市番禺区南浦大道","longitude":113.280212402,"latitude":23.0350666046},{"location_id":468,"name":"TIT 创意园B4","phone":"020-12345678","address":"广东省广州市海珠区","longitude":113.325248718,"latitude":23.1008300781}]
     * count : 2
     */

    private int errcode;
    private String errmsg;
    private int count;
    private List<LocationListBean> location_list;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<LocationListBean> getLocation_list() {
        return location_list;
    }

    public void setLocation_list(List<LocationListBean> location_list) {
        this.location_list = location_list;
    }

    public static class LocationListBean {
        /**
         * location_id : 493
         * name : steventao home
         * phone : 020-12345678
         * address : 广东省广州市番禺区广东省广州市番禺区南浦大道
         * longitude : 113.280212402
         * latitude : 23.0350666046
         */

        private int location_id;
        private String name;
        private String phone;
        private String address;
        private double longitude;
        private double latitude;

        public int getLocation_id() {
            return location_id;
        }

        public void setLocation_id(int location_id) {
            this.location_id = location_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }
}
