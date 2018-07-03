package com.ishiqing;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ishiqing.utils.AppUtils;
import com.sq.library.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        // ARouter
        initArouter();
        // LeakCanary
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        AppUtils.init(getApplicationContext());
//        QDUpgradeManager.getInstance(this).check();
    }

    private void initArouter() {
        if (BuildConfig.DEBUG) {    // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
