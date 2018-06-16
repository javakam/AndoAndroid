package com.ishiqing;

import android.content.Context;

/**
 * Created by javakam on 2018/6/16.
 */
public class AppUtils {

    private static Context context;

    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Context context) {
        AppUtils.context = context.getApplicationContext();
    }

    /**
     * 获取 ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) {
            return context;
        }
        throw new NullPointerException("u should init first");
    }
}
