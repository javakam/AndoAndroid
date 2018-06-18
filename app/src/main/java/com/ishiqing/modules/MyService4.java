package com.ishiqing.modules;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 切记： 混合开发时，必须把 {@link #onUnbind} 的返回值设置成true
 * <p>
 * 带有线程的{@linkplain MyService4}，记住一点，要做到服务在线程在！
 * <p>
 * Created by javakam on 2018/6/17.
 */
public class MyService4 extends Service {
    private MyThread myThread;
    private Intent intent;

    /**
     * 忽然发现 用onTransact也可以Activity与Service通讯，不过没有bind/unbind灵活。这里只是简单的玩了一下
     */
    public class MyService4Binder extends Binder {

        @Override
        protected boolean onTransact(int code, @NonNull Parcel data, @Nullable Parcel reply, int flags) throws RemoteException {
            System.out.println("MyService4Binder.onTransact");
            //新的时间输出格式
            String newDateFormat = data.readString();
            reply.writeString("From Service 通信成功");
            System.out.println("来自Activity组件的讯息 code = [" + code + "], " +
                    "data = [" + newDateFormat + "], " +
                    "flags = [" + flags + "]");
            //通讯成功，开始计时
            if (myThread == null) {
                myThread = new MyThread();
                myThread.setFormat(newDateFormat);
                myThread.start();
            }
            return super.onTransact(code, data, reply, flags);
        }
    }

    /**
     * 用于演示的线程
     * <p>
     * 可以通过 setStop 停止输出
     */
    private class MyThread extends Thread {
        private boolean isStop = false;
        private String format = "yyyy-MM-dd HH:mm:ss";

        /**
         * @param stop true Thread Stop , but Alive.
         */
        public void setStop(boolean stop) {
            isStop = stop;
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

    @Override
    public void onCreate() {
        super.onCreate();
        if (myThread == null) {
            myThread = new MyThread();
            myThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    System.out.println("t = [" + t.getName() + "], e = [" + e.getMessage() + "]");
                    //如果线程异常，关闭Service
                    myThread.setStop(true);
                    myThread = null;
                    onDestroy();
                }
            });
        }
        System.out.println("MyService4.onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("MyService4.onStartCommand");
        this.intent = intent;
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("MyService4.onBind");
        if (myThread == null) {
//            System.out.println("线程 isAlive：" + myThread.isAlive());
//            System.out.println("线程 isDaemon：" + myThread.isDaemon());
//            System.out.println("线程 isInterrupted：" + myThread.isInterrupted());
            myThread = new MyThread();
        }
        myThread.start();
        return new MyService4Binder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        System.out.println("MyService4.onUnbind");
        if (myThread != null) {
            myThread.setStop(true);
            myThread = null;//简单处理，应该做成阻塞
        }
        return true;//必须返回true，不然没法多次创建
    }

    @Override
    public void onDestroy() {
        if (myThread != null) {
            myThread.setStop(true);
            //服务不在线程不在！
            myThread = null;
        }
        System.out.println("MyService4.onDestroy");
        super.onDestroy();
    }
}
