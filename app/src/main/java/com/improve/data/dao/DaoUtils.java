package com.improve.data.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by javakam on 2018/7/8.
 */
public class DaoUtils {
    private static DaoSession mDaoSession;

    public static void initDao(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "isq.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        mDaoSession = daoMaster.newSession();
    }

    /**
     * 获取 DaoSession
     *
     * @return DaoSession
     */
    public static DaoSession getDao() {
        return mDaoSession;
    }
}
