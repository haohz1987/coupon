package com.handpay.coupon.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by haohz on 2018/4/16.
 */
@Entity
public class StoreData {
    @Id
    public String sid;
    public String businessName;   //商户名
    public String branchName;    //分店名
    public String province;       //省
    public String city;        //市
    public String district;         //区
    public String address;         //详细地址
    public String telephone;  //电话
    public String categories;      //门店类型
    public int offsetType;           //坐标类型
    public double longitude;     //门店所在地理位置的经度
    public double latitude;    //门店所在地理位置的纬度（经纬度均为火星坐标，最好选用腾讯地图标记的坐标）
    public String photoListHeadUrl;  //图片列表_商户头像
    public String photoListContentUrl;  //图片列表_展示图片
    public String recommend;   //推荐品
    public String special;   //特色服务
    public String introduction;   //商户简介
    public String openTime; //营业时间
    public int avgPrice; //人均价格，大于0的整数

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"telephone\":\"")
                .append(telephone).append('\"');
        sb.append(",\"special\":\"")
                .append(special).append('\"');
        sb.append(",\"sid\":\"")
                .append(sid).append('\"');
        sb.append(",\"recommend\":\"")
                .append(recommend).append('\"');
        sb.append(",\"province\":\"")
                .append(province).append('\"');
        sb.append(",\"photoListHeadUrl\":\"")
                .append(photoListHeadUrl).append('\"');
        sb.append(",\"photoListContentUrl\":\"")
                .append(photoListContentUrl).append('\"');
        sb.append(",\"openTime\":\"")
                .append(openTime).append('\"');
        sb.append(",\"offsetType\":")
                .append(offsetType);
        sb.append(",\"longitude\":")
                .append(longitude);
        sb.append(",\"latitude\":")
                .append(latitude);
        sb.append(",\"introduction\":\"")
                .append(introduction).append('\"');
        sb.append(",\"district\":\"")
                .append(district).append('\"');
        sb.append(",\"city\":\"")
                .append(city).append('\"');
        sb.append(",\"categories\":\"")
                .append(categories).append('\"');
        sb.append(",\"businessName\":\"")
                .append(businessName).append('\"');
        sb.append(",\"branchName\":\"")
                .append(branchName).append('\"');
        sb.append(",\"avgPrice\":")
                .append(avgPrice);
        sb.append(",\"address\":\"")
                .append(address).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Generated(hash = 1341737226)
    public StoreData(String sid, String businessName, String branchName,
            String province, String city, String district, String address,
            String telephone, String categories, int offsetType, double longitude,
            double latitude, String photoListHeadUrl, String photoListContentUrl,
            String recommend, String special, String introduction, String openTime,
            int avgPrice) {
        this.sid = sid;
        this.businessName = businessName;
        this.branchName = branchName;
        this.province = province;
        this.city = city;
        this.district = district;
        this.address = address;
        this.telephone = telephone;
        this.categories = categories;
        this.offsetType = offsetType;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photoListHeadUrl = photoListHeadUrl;
        this.photoListContentUrl = photoListContentUrl;
        this.recommend = recommend;
        this.special = special;
        this.introduction = introduction;
        this.openTime = openTime;
        this.avgPrice = avgPrice;
    }
    @Generated(hash = 974815071)
    public StoreData() {
    }
    public String getSid() {
        return this.sid;
    }
    public void setSid(String sid) {
        this.sid = sid;
    }
    public String getBusinessName() {
        return this.businessName;
    }
    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }
    public String getBranchName() {
        return this.branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
    public String getProvince() {
        return this.province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return this.district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCategories() {
        return this.categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    public int getOffsetType() {
        return this.offsetType;
    }
    public void setOffsetType(int offsetType) {
        this.offsetType = offsetType;
    }
    public double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public String getPhotoListHeadUrl() {
        return this.photoListHeadUrl;
    }
    public void setPhotoListHeadUrl(String photoListHeadUrl) {
        this.photoListHeadUrl = photoListHeadUrl;
    }
    public String getPhotoListContentUrl() {
        return this.photoListContentUrl;
    }
    public void setPhotoListContentUrl(String photoListContentUrl) {
        this.photoListContentUrl = photoListContentUrl;
    }
    public String getRecommend() {
        return this.recommend;
    }
    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
    public String getSpecial() {
        return this.special;
    }
    public void setSpecial(String special) {
        this.special = special;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getOpenTime() {
        return this.openTime;
    }
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
    public int getAvgPrice() {
        return this.avgPrice;
    }
    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

}
