package com.ishiqing.modules.service.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

/**
 * IntentService源码分析
 * <p>
 * Created by javakam on 2018/7/14.
 */
public class MyIntentService extends IntentService {
    public MyIntentService() {
        //必须实现父类的构造方法
        this("My IntentService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyIntentService(String name) {
        super(name);
    }

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind");
        return super.onBind(intent);
    }

    @Override
    public void onCreate() {
        System.out.println("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        System.out.println("onStart");
        super.onStart(intent, startId);
    }

    @Override
    public void setIntentRedelivery(boolean enabled) {
        super.setIntentRedelivery(enabled);
        System.out.println("setIntentRedelivery");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        System.out.println("onHandleIntent currentThread() = " + Thread.currentThread().getName());
        String action = intent.getExtras().getString("param");
        if (action.equals("oper1")) {
            System.out.println("Operation1");
        } else if (action.equals("oper2")) {
            System.out.println("Operation2");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }
}
