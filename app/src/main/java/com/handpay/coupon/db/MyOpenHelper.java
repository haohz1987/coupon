package com.handpay.coupon.db;


import android.content.Context;

import com.handpay.coupon.dao.DaoMaster;
import com.handpay.coupon.utils.LogT;

import org.greenrobot.greendao.database.Database;

/**
 * 辅助类
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }
    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        LogT.w("db version update from " + oldVersion + " to " + newVersion);
        switch (oldVersion) {
//            case 1:
//                不能先删除表，否则数据都木了
//                AppDataDao.dropTable(db, true);
//                AppDataDao.createTable(db, true);
//                // 加入新字段 score
//                db.execSQL("ALTER TABLE 'APPDATA' ADD 'SCORE' TEXT;");
//                break;
        }
    }
}
