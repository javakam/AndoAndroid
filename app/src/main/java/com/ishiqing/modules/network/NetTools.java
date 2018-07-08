package com.ishiqing.modules.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

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
            Toast.makeText(context, "无可用网络!", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    Toast.makeText(context, "当前使用的是无线网络!", Toast.LENGTH_SHORT).show();
                    return true;
                } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    Toast.makeText(context, "当前使用的是移动网络!", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        return false;
    }
}
