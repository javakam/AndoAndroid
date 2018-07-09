package com.ishiqing.modules.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sq.library.utils.ToastUtils;

/**
 * ConnectivityManager API
 * NetworkInfo
 * <p>
 * Created by javakam on 2018/6/26 0026.
 */
public class NetTools {

    public static boolean isNetAvaliable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null) {
            ToastUtils.showLong("无可用网络!");
            return false;
        } else {
            if (info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    ToastUtils.showLong("当前使用的是无线网络!");
                    return true;
                } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    ToastUtils.showLong("当前使用的是移动网络!");
                    return true;
                }
            }
        }
        return false;
    }
}
