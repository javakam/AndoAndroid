package com.ishiqing;

import android.app.Application;
import android.content.Context;

import com.ishiqing.utils.AppUtils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by javakam on 2018/6/16.
 */
public class SQApplication extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        AppUtils.init(getApplicationContext());
//        QDUpgradeManager.getInstance(this).check();
    }
}
