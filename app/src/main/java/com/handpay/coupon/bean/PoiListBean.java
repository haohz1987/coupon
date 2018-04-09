package com.handpay.coupon.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by haohz on 2018/4/9.
 */

public class PoiListBean implements Parcelable{

    /**
     * errcode : 0
     * errmsg : ok
     * business_list : [{"base_info":{"sid":"100","poi_id":"271864249","business_name":"麦当劳","branch_name":"艺苑路店","address":"艺苑路11 号","available_state":3}},{"base_info":{"sid":"101","business_name":"麦当劳","branch_name":"赤岗路店","address":"赤岗路102 号","available_state":4}}]
     * total_count : 2
     */

    private int errcode;
    private String errmsg;
    private String total_count;
    private List<BusinessListBean> business_list;

    protected PoiListBean(Parcel in) {
        errcode = in.readInt();
        errmsg = in.readString();
        total_count = in.readString();
    }

    public static final Creator<PoiListBean> CREATOR = new Creator<PoiListBean>() {
        @Override
        public PoiListBean createFromParcel(Parcel in) {
            return new PoiListBean(in);
        }

        @Override
        public PoiListBean[] newArray(int size) {
            return new PoiListBean[size];
        }
    };

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"total_count\":\"")
                .append(total_count).append('\"');
        sb.append(",\"errmsg\":\"")
                .append(errmsg).append('\"');
        sb.append(",\"errcode\":")
                .append(errcode);
        sb.append(",\"business_list\":")
                .append(business_list);
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

    public String getTotal_count() {
        return total_count;
    }

    public void setTotal_count(String total_count) {
        this.total_count = total_count;
    }

    public List<BusinessListBean> getBusiness_list() {
        return business_list;
    }

    public void setBusiness_list(List<BusinessListBean> business_list) {
        this.business_list = business_list;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(errcode);
        dest.writeString(errmsg);
        dest.writeString(total_count);
    }

    public static class BusinessListBean implements Parcelable{
        /**
         * base_info : {"sid":"100","poi_id":"271864249","business_name":"麦当劳","branch_name":"艺苑路店","address":"艺苑路11 号","available_state":3}
         */

        private BaseInfoBean base_info;

        protected BusinessListBean(Parcel in) {
        }

        public static final Creator<BusinessListBean> CREATOR = new Creator<BusinessListBean>() {
            @Override
            public BusinessListBean createFromParcel(Parcel in) {
                return new BusinessListBean(in);
            }

            @Override
            public BusinessListBean[] newArray(int size) {
                return new BusinessListBean[size];
            }
        };

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
        @Override
        public int describeContents() {
            return 0;
        }
        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        public static class BaseInfoBean implements Parcelable{
            /**
             * sid : 100
             * poi_id : 271864249
             * business_name : 麦当劳
             * branch_name : 艺苑路店
             * address : 艺苑路11 号
             * available_state : 3
             */

            private String sid;
            private String poi_id;
            private String business_name;
            private String branch_name;
            private String address;
            private int available_state;

            protected BaseInfoBean(Parcel in) {
                sid = in.readString();
                poi_id = in.readString();
                business_name = in.readString();
                branch_name = in.readString();
                address = in.readString();
                available_state = in.readInt();
            }

            public static final Creator<BaseInfoBean> CREATOR = new Creator<BaseInfoBean>() {
                @Override
                public BaseInfoBean createFromParcel(Parcel in) {
                    return new BaseInfoBean(in);
                }

                @Override
                public BaseInfoBean[] newArray(int size) {
                    return new BaseInfoBean[size];
                }
            };

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("{");
                sb.append("\"sid\":\"")
                        .append(sid).append('\"');
                sb.append(",\"poi_id\":\"")
                        .append(poi_id).append('\"');
                sb.append(",\"business_name\":\"")
                        .append(business_name).append('\"');
                sb.append(",\"branch_name\":\"")
                        .append(branch_name).append('\"');
                sb.append(",\"available_state\":")
                        .append(available_state);
                sb.append(",\"address\":\"")
                        .append(address).append('\"');
                sb.append('}');
                return sb.toString();
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getPoi_id() {
                return poi_id;
            }

            public void setPoi_id(String poi_id) {
                this.poi_id = poi_id;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAvailable_state() {
                return available_state;
            }

            public void setAvailable_state(int available_state) {
                this.available_state = available_state;
            }
            @Override
            public int describeContents() {
                return 0;
            }
            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(sid);
                dest.writeString(poi_id);
                dest.writeString(business_name);
                dest.writeString(branch_name);
                dest.writeString(address);
                dest.writeInt(available_state);
            }
        }
    }
}
