package com.handpay.coupon.bean;

import java.util.List;

/**
 * Created by haohz on 2018/4/2.
 */

public class SetTestWhiteListBean {
    private List<String> openid;
    private List<String> username;

    public List<String> getOpenid() {
        return openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }
}
