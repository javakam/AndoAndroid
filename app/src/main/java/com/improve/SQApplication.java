package com.improve;

import com.alibaba.android.arouter.launcher.ARouter;
import com.improve.BaseApplication;
import com.improve.data.dao.DaoUtils;
import com.improve.utils.AppUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // 配置方法数超过 64K 的应用  --  代码混淆涉及到很多的点，inspect code、analyze apk、classesN.dex
        // 方法数64K限制 ： 65536=64*1024
//        MultiDex.install(this);

        //设置线程的优先级，不与主线程抢资源
        //Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
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
        DaoUtils.initDao(getApplicationContext());
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {    // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}