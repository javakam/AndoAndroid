package com.improve.modules.eventbus3;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.improve.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * EventBus 优先级处理
 * <p>
 * 优先级只是针对于相同的ThreadMode中。默认的优先级为0。
 */
public class EventBusPriorityActivity extends AppCompatActivity {
    private static final String TAG = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_event_priority);
    }

    public void postEvent(View view) {
        //1 当发布者在主线程
//        postInMainThread();
        //2 当发布者在子线程
        postInChildThread();
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
                EventBus.getDefault().post(new MessageEvent("子线程发出的 Event !", "" + SystemClock.currentThreadTimeMillis()));
            }
        }).start();
    }

    @Subscribe(priority = 1)
    public void onPriority1Message(MessageEvent event) {
        Log.d(TAG, "priority = 1:" + event.getMessage());
    }

    @Subscribe(priority = 2)
    public void onPriority2Message(MessageEvent event) {
        Log.d(TAG, "priority = 2:" + event.getMessage());
        EventBus.getDefault().cancelEventDelivery(event);
    }

    @Subscribe(priority = 4)
    public void onPriority4Message(MessageEvent event) {

        Log.d(TAG, "priority = 4:" + event.getMessage());
    }

    @Subscribe(priority = 3)
    public void onPriority3Message(MessageEvent event) {

        Log.d(TAG, "priority = 3:" + event.getMessage());

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
