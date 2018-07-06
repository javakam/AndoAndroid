package com.ishiqing;

import android.database.sqlite.SQLiteDatabase;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ishiqing.utils.AppUtils;
import com.sq.domain.dao.DaoMaster;
import com.sq.domain.dao.DaoSession;
import com.sq.library.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQApplication extends BaseApplication {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        // ARouter
        initArouter();
        // LeakCanary
        if (initLeakCanary()) {
            return;
        }
        // GreenDao
        initGreenDao();

        AppUtils.init(getApplicationContext());
//        QDUpgradeManager.getInstance(this).check();
    }

    private boolean initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return true;
        }
        LeakCanary.install(this);
        return false;
    }

    /**
     * 初始化 GreenDao
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "isq.db");
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    /**
     * 获取 DaoSession
     *
     * @return
     */
    public static DaoSession getDaoSession() {
        return daoSession;
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {    // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
