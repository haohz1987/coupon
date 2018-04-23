package com.handpay.coupon.db;

import com.handpay.coupon.dao.GetCardDataDao;
import com.handpay.coupon.dao.StoreDataDao;

/**
 *  获取表 Helper 的工具类
 */
public class DbUtil {
    private static StoreDataHelper storeDataHelper;
    private static GetCardDataHelper getCardDataHelper;
    


    private static StoreDataDao getStoreDataDao() {
        return DbCore.getDaoSession().getStoreDataDao();
    }
    private static GetCardDataDao getCardDataDao(){
        return DbCore.getDaoSession().getGetCardDataDao();
    }

    public static StoreDataHelper getStoreDataHelper() {
        if (storeDataHelper == null) {
            storeDataHelper = new StoreDataHelper(getStoreDataDao());
        }
        return storeDataHelper;
    }
    public static GetCardDataHelper getGetCardDataHelper(){
        if(getCardDataHelper==null){
            getCardDataHelper = new GetCardDataHelper(getCardDataDao());
        }
        return getCardDataHelper;
    }
}
