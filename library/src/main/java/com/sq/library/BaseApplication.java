package com.sq.library;

import android.app.Application;

/**
 * Created by javakam on 2018/7/3.
 */
public class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static BaseApplication getApp() {
        return instance;
    }
}
