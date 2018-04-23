package com.handpay.coupon.db;

import com.handpay.coupon.entity.GetCardData;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by haohz on 2018/4/18.
 */
public class GetCardDataHelper extends BaseDbHelper<GetCardData, Long> {
    public GetCardDataHelper(AbstractDao dao) {
        super(dao);
    }
}
