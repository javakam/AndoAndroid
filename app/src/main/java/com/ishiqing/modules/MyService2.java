package com.ishiqing.modules;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by javakam on 2018/6/16.
 */
public class MyService2 extends Service {
    private MyThread myThread;

    /**
     * 当前服务的代理类 ，在 {@link #onBind(Intent)} 中返回
     * <p>
     * 定义一个 Binder 对象，用于跟 Activity 通信
     */
    public class ServiceBinder extends Binder {
        public void changeFormatBinder(String format) {
            changeFormat(format);
        }
    }

    /**
     * 与服务进行通信的方法，更改当前系统时间的输出格式
     */
    public void changeFormat(String format) {
        if (myThread != null) {
            myThread.setFormat(format);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myThread = new MyThread();
        System.out.println("MyService2.onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (myThread != null && !myThread.isAlive()) {
            myThread.start();
        }
        System.out.println("MyService2.onBind");
        return new ServiceBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // bindService不执行此方法
//        if (myThread != null && !myThread.isAlive()) {
//            myThread.start();
//        }
        System.out.println("MyService2.onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        if (myThread != null) {
            //        myThread.interrupt();
            myThread.setStop();
        }
        System.out.println("MyService2.onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MyService2.onDestroy");
    }

    /**
     * 改变时间的子线程
     */
    private class MyThread extends Thread {
        private boolean isStop = false;
        private String format = "yyyy-MM-dd HH:mm:ss";

        public void setStop() {
            isStop = true;
        }

        /**
         * @param format
         */
        public void setFormat(String format) {
            this.format = format;
        }

        @Override
        public void run() {
            super.run();
            SimpleDateFormat simpleDateFormat;
            while (!isStop) {
                simpleDateFormat = new SimpleDateFormat(format);
                System.out.println("实时时间： " + simpleDateFormat.format(new Date()));
                SystemClock.sleep(1500);
            }
        }
    }
}
