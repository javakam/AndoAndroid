package com.improve.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.multidex.MultiDexApplication;


/**
 * Created by javakam on 2018/7/3.
 */
public abstract class BaseApplication extends MultiDexApplication {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApplication getApp() {
        return instance;
    }


    /**
     * 随着系统文字大小的设置，造成文字显示大小异常的问题。也就是说，
     * 如果要像微信一样，所有字体都不允许随系统调节而发生大小变化，要怎么办呢？【一些机型无效】
     * <p>
     * 利用 Android 的 Configuration 类中的 fontScale 属性，其默认值为1，会随系统调节字体大小而发生变化，
     * 如果我们强制让其等于默认值，就可以实现字体不随用户调节而改变。
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1) {//非默认值
            getResources();
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                createConfigurationContext(newConfig);
//            } else {
//                res.updateConfiguration(newConfig, res.getDisplayMetrics());
//            }
        }
        return res;
    }
}
