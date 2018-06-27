package com.ishiqing.modules.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * 当前网络更改时发生接收
 * <p>
 * Created by javakam on 2018/6/26 0026.
 */
public class MyNetStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (NetTools.isNetAvaliable(context)) {
            abortBroadcast();
        }
    }
}
