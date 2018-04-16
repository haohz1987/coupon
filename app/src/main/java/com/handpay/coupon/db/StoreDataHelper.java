package com.handpay.coupon.db;

import com.handpay.coupon.entity.StoreData;

import org.greenrobot.greendao.AbstractDao;

/**
 *  具体表的实现类
 */
public class StoreDataHelper extends BaseDbHelper<StoreData, Long> {
    public StoreDataHelper(AbstractDao dao) {
        super(dao);
    }
}
