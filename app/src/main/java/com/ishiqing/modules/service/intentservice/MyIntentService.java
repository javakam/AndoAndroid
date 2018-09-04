package com.ishiqing.modules.service.intentservice;

import android.app.IntentService;
import android.content.Intent;

import com.sq.library.utils.L;

/**
 * IntentService源码分析
 * <p>
 * Created by javakam on 2018/7/14.
 */
public class MyIntentService extends IntentService {
    private static final String THREAD_NAME = "My IntentService";

    public MyIntentService() {
        super(THREAD_NAME);
    }

    @Override
    public void onCreate() {
        L.v("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.v("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        //Intent是从Activity发过来的，携带识别参数，根据参数不同执行不同的任务
        L.e("onHandleIntent currentThread() = " + Thread.currentThread().getName());
        String action = intent.getExtras().getString("param");
        if (action.equals("oper1")) {
            L.v("Operation1");
        } else if (action.equals("oper2")) {
            L.v("Operation2");
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        L.v("onDestroy");
        super.onDestroy();
    }
}
