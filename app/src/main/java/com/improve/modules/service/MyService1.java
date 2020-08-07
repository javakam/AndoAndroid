package com.improve.modules.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by javakam on 2018/6/16.
 */
public class MyService1 extends Service {
    private MyThread myThread;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MyService1.onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myThread = new MyThread();
        System.out.println("MyService1.onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (myThread != null && !myThread.isAlive()) {
            myThread.start();
        }
        System.out.println("MyService1.onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
//        myThread.interrupt();
        if (myThread != null) {
            myThread.setStop();
        }
        super.onDestroy();
        System.out.println("MyService1.onDestroy");
    }

    private class MyThread extends Thread {
        private boolean isStop = false;

        public void setStop() {
            isStop = true;
        }

        @Override
        public void run() {
            super.run();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while (!isStop) {
                System.out.println("实时时间： " + format.format(new Date()));
                SystemClock.sleep(1000);
            }
        }

    }
}
