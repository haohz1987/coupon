package com.handpay.coupon.bean;

/**
 * Created by haohz on 2018/4/2.
 */

public class BatchGetCardBean {
    /**
     * offset : 0
     * count : 10
     */

    private int offset;
    private int count;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
