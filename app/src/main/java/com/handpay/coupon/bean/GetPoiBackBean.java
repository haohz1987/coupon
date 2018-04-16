package com.handpay.coupon.bean;

// FIXME generate failure  field _$Business108

import java.io.Serializable;
import java.util.List;

/**
 * Created by haohz on 2018/4/9.
 */

public class GetPoiBackBean implements Serializable{

    /**
     * errcode : 0
     * errmsg : ok
     * business : {"base_info":{"sid":"001","business_name":"麦当劳","branch_name":"艺苑路店","province":"广东省","city":"广州市","address":"海珠区艺苑路11 号","telephone":"020-12345678","categories":["美食,快餐小吃"],"offset_type":1,"longitude":115.32375,"latitude":25.097486,"photo_list":[{"photo_url":"https:// XXX.com"},{"photo_url":"https://XXX.com"}],"recommend":"麦辣鸡腿堡套餐，麦乐鸡，全家桶","special":"免费wifi，外卖服务","introduction":"麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品","open_time":"8:00-20:00","avg_price":35,"available_state":3,"update_status":0}}
     */

    private int errcode;
    private String errmsg;
    private BusinessBean business;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"errmsg\":\"")
                .append(errmsg).append('\"');
        sb.append(",\"errcode\":")
                .append(errcode);
        sb.append(",\"business\":")
                .append(business);
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

    public BusinessBean getBusiness() {
        return business;
    }

    public void setBusiness(BusinessBean business) {
        this.business = business;
    }

    public static class BusinessBean implements Serializable{
        /**
         * base_info : {"sid":"001","business_name":"麦当劳","branch_name":"艺苑路店","province":"广东省","city":"广州市","address":"海珠区艺苑路11 号","telephone":"020-12345678","categories":["美食,快餐小吃"],"offset_type":1,"longitude":115.32375,"latitude":25.097486,"photo_list":[{"photo_url":"https:// XXX.com"},{"photo_url":"https://XXX.com"}],"recommend":"麦辣鸡腿堡套餐，麦乐鸡，全家桶","special":"免费wifi，外卖服务","introduction":"麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品","open_time":"8:00-20:00","avg_price":35,"available_state":3,"update_status":0}
         */

        private BaseInfoBean base_info;

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"base_info\":")
                    .append(base_info);
            sb.append('}');
            return sb.toString();
        }

        public BaseInfoBean getBase_info() {
            return base_info;
        }

        public void setBase_info(BaseInfoBean base_info) {
            this.base_info = base_info;
        }

        public static class BaseInfoBean implements Serializable{
            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"update_status\":")
                        .append(update_status);
                sb.append(",\"telephone\":\"")
                        .append(telephone).append('\"');
                sb.append(",\"special\":\"")
                        .append(special).append('\"');
                sb.append(",\"sid\":\"")
                        .append(sid).append('\"');
                sb.append(",\"recommend\":\"")
                        .append(recommend).append('\"');
                sb.append(",\"province\":\"")
                        .append(province).append('\"');
                sb.append(",\"photo_list\":")
                        .append(photo_list);
                sb.append(",\"open_time\":\"")
                        .append(open_time).append('\"');
                sb.append(",\"offset_type\":")
                        .append(offset_type);
                sb.append(",\"longitude\":")
                        .append(longitude);
                sb.append(",\"latitude\":")
                        .append(latitude);
                sb.append(",\"introduction\":\"")
                        .append(introduction).append('\"');
                sb.append(",\"city\":\"")
                        .append(city).append('\"');
                sb.append(",\"categories\":")
                        .append(categories);
                sb.append(",\"business_name\":\"")
                        .append(business_name).append('\"');
                sb.append(",\"branch_name\":\"")
                        .append(branch_name).append('\"');
                sb.append(",\"avg_price\":")
                        .append(avg_price);
                sb.append(",\"available_state\":")
                        .append(available_state);
                sb.append(",\"address\":\"")
                        .append(address).append('\"');
                sb.append('}');
                return sb.toString();
            }

            /**
             * sid : 001
             * business_name : 麦当劳
             * branch_name : 艺苑路店
             * province : 广东省
             * city : 广州市
             * address : 海珠区艺苑路11 号
             * telephone : 020-12345678
             * categories : ["美食,快餐小吃"]
             * offset_type : 1
             * longitude : 115.32375
             * latitude : 25.097486
             * photo_list : [{"photo_url":"https:// XXX.com"},{"photo_url":"https://XXX.com"}]
             * recommend : 麦辣鸡腿堡套餐，麦乐鸡，全家桶
             * special : 免费wifi，外卖服务
             * introduction : 麦当劳是全球大型跨国连锁餐厅，1940 年创立于美国，在世界上大约拥有3 万间分店。主要售卖汉堡包，以及薯条、炸鸡、汽水、冰品、沙拉、水果等快餐食品
             * open_time : 8:00-20:00
             * avg_price : 35
             * available_state : 3
             * update_status : 0
             */

            private String sid;
            private String business_name;
            private String branch_name;
            private String province;
            private String city;
            private String address;
            private String telephone;
            private int offset_type;
            private double longitude;
            private double latitude;
            private String recommend;
            private String special;
            private String introduction;
            private String open_time;
            private int avg_price;
            private int available_state;
            private int update_status;
            private List<String> categories;
            private List<PhotoListBean> photo_list;

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getBusiness_name() {
                return business_name;
            }

            public void setBusiness_name(String business_name) {
                this.business_name = business_name;
            }

            public String getBranch_name() {
                return branch_name;
            }

            public void setBranch_name(String branch_name) {
                this.branch_name = branch_name;
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

            public String getAddress() {
                return "位  置:"+address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getTelephone() {
                return telephone;//"订餐电话:"+
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public int getOffset_type() {
                return offset_type;
            }

            public void setOffset_type(int offset_type) {
                this.offset_type = offset_type;
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

            public String getRecommend() {
                return recommend;
            }

            public void setRecommend(String recommend) {
                this.recommend = recommend;
            }

            public String getSpecial() {
                return special;
            }

            public void setSpecial(String special) {
                this.special = special;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getOpen_time() {
                return open_time;
            }

            public void setOpen_time(String open_time) {
                this.open_time = open_time;
            }

            public int getAvg_price() {
                return avg_price;
            }

            public void setAvg_price(int avg_price) {
                this.avg_price = avg_price;
            }

            public int getAvailable_state() {
                return available_state;
            }

            public void setAvailable_state(int available_state) {
                this.available_state = available_state;
            }

            public int getUpdate_status() {
                return update_status;
            }

            public void setUpdate_status(int update_status) {
                this.update_status = update_status;
            }

            public List<String> getCategories() {
                return categories;
            }
            public String getCategoriesString(){
                return "门店类型:"+categories.toString();
            }

            public void setCategories(List<String> categories) {
                this.categories = categories;
            }

            public List<PhotoListBean> getPhoto_list() {
                return photo_list;
            }

            public void setPhoto_list(List<PhotoListBean> photo_list) {
                this.photo_list = photo_list;
            }

            public static class PhotoListBean implements Serializable{
                /**
                 * photo_url : https:// XXX.com
                 */

                private String photo_url;

                @Override
                public String toString() {
                    final StringBuilder sb = new StringBuilder("{");
                    sb.append("\"photo_url\":\"")
                            .append(photo_url).append('\"');
                    sb.append('}');
                    return sb.toString();
                }

                public String getPhoto_url() {
                    return photo_url;
                }

                public void setPhoto_url(String photo_url) {
                    this.photo_url = photo_url;
                }
            }
        }
    }
}
