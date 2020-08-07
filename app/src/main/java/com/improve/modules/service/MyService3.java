package com.improve.modules.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

/**
 * 切记： 混合开发时，必须把 {@link #onUnbind} 的返回值设置成true
 * <p>
 * Created by javakam on 2018/6/17.
 */
public class MyService3 extends Service {

    public class MyService3Binder extends Binder {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("MyService3.onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("MyService3.onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MyService3.onBind");
        return new MyService3Binder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("MyService3.onUnbind");
//        return super.onUnbind(intent);
        return true;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MyService3.onDestroy");
    }
}
