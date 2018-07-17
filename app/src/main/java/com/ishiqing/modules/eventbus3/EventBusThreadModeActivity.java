package com.ishiqing.modules.eventbus3;

import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * EventBus3 线程模型演示
 *
 * @author machangbao
 * 2018-5-13 11:31:18
 */
public class EventBusThreadModeActivity extends AppCompatActivity {
    private TextView tvGetEvent;
    private static final String TAG = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tvGetEvent = findViewById(R.id.tvGetEvent);
    }

    public void postEvent(View view) {
        //1 当发布者在主线程
        postInMainThread();
        //2 当发布者在子线程
//        postInChildThread();
    }

    private void postInMainThread() {
        Log.d(TAG, "发布者线程NAME :" + Thread.currentThread().getName()
                + "  ID : " + Thread.currentThread().getId());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        EventBus.getDefault().post(new MessageEvent("子线程发出的 Event ", "" + SystemClock.currentThreadTimeMillis()));
    }

    private void postInChildThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "发布者线程NAME :" + Thread.currentThread().getName()
                        + "  ID : " + Thread.currentThread().getId());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                EventBus.getDefault().post(new MessageEvent("子线程发出的 Event ", "" + SystemClock.currentThreadTimeMillis()));
            }
        }).start();
    }

    private String getResultString(String threadMode, String msg) {
        StringBuilder sb = new StringBuilder("");
        sb.append(threadMode)
                .append("\n接收到的消息：")
                .append(msg)
                .append("\n线程name:")
                .append(Thread.currentThread().getName())
                .append("\n线程id:")
                .append(Thread.currentThread().getId())
                .append("\n是否是主线程：")
                .append(Looper.getMainLooper() == Looper.myLooper())
                .append("\n");
        return sb.toString();
    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostingModeMessage(MessageEvent event) {
        Log.w(TAG, getResultString("ThreadMode:POSTING", event.getMessage()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainModeMessage(MessageEvent event) {
        Log.w(TAG, getResultString("ThreadMode:MAIN", event.getMessage()));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackgroundModeMessage(MessageEvent event) {
        Log.w(TAG, getResultString("ThreadMode:BACKGROUND", event.getMessage()));
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onAsyncModeMessage(MessageEvent event) {
        Log.w(TAG, getResultString("ThreadMode:ASYNC", event.getMessage()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }


}
