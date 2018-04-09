package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class GetColorsBackBean {

    /**
     * errcode : 0
     * errmsg : ok
     * colors : [{"name":"Color010","value":"#61ad40"},{"name":"Color020","value":"#169d5c"},{"name":"Color030","value":"#239cda"}]
     */

    private int errcode;
    private String errmsg;
    private List<ColorsBean> colors;

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

    public List<ColorsBean> getColors() {
        return colors;
    }

    public void setColors(List<ColorsBean> colors) {
        this.colors = colors;
    }

    public static class ColorsBean {
        /**
         * name : Color010
         * value : #61ad40
         */

        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
