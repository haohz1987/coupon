package com.handpay.coupon.db;

import com.handpay.coupon.dao.StoreDataDao;

/**
 *  获取表 Helper 的工具类
 */
public class DbUtil {
    private static StoreDataHelper storeDataHelper;
    


    private static StoreDataDao getDriverDao() {
        return DbCore.getDaoSession().getStoreDataDao();
    }

    public static StoreDataHelper getStoreDataHelper() {
        if (storeDataHelper == null) {
            storeDataHelper = new StoreDataHelper(getDriverDao());
        }
        return storeDataHelper;
    }
}
