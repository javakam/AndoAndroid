package com.improve.modules.thread;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import androidx.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.utils.CommonUtils;

/**
 * Handler
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class HandlerFragment extends BaseSwipeFragment {
    private static final int MSG_ERROR = 0x001;

    private TextView mThreadInfo;
    private TextView mResult;

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mThreadInfo.setText(String.format("Thread id : %d Name : %s",
                    mHandler.getLooper().getThread().getId(), mHandler.getLooper().getThread().getName()));
            if (msg.what == MSG_ERROR) {
                mResult.setText("错误信息：");
                mResult.setTextColor(Color.parseColor("#FF0000"));//红色  ff0099cc
            }
            mResult.setText(String.format("%s\n%s", mResult.getText().toString(), msg.obj.toString()));
            return false;//默认false
        }
    });

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_handler;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("Handler", true);
        mThreadInfo = v.findViewById(R.id.tvThreadInfo);
        mResult = v.findViewById(R.id.tvResult);

        v.findViewById(R.id.btChildThread).setOnClickListener(this);
        v.findViewById(R.id.btChildThreadErrLog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //神奇。。。
//        mResult.setText("");
        switch (v.getId()) {
            case R.id.btChildThread:
                childThread();
                break;
            case R.id.btChildThreadErrLog:
                childThreadLog();
                break;
            default:
                break;
        }
    }

    //子线程更新UI
    private void childThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //原理：Handler创建时会采用当前线程的Looper来构建内部的消息循环系统，如果当前线程没有Looper就会报错。
                    /*
                    抛出异常：
                    public Handler(Callback callback, boolean async) {
                      ...
                      mLooper = Looper.myLooper();
                      if (mLooper == null) {
                        throw new RuntimeException("Can't create handler inside thread that has not called Looper.prepare()");
                      }
                      ...
                     }
                     */
                    Looper.prepare();
                    //不能更新UI
//                    Handler inHandler = new Handler() {
//                        @Override
//                        public void handleMessage(Message msg) {
//                            super.handleMessage(msg);
//                            mResult.setTextColor(Color.GREEN);
//                            mResult.setText(msg.obj.toString());
//                        }
//                    };
                    //use this
                    Handler inHandler = new Handler();
                    Message message = new Message();
                    message.obj = "inHandler...";
                    inHandler.sendMessage(message);
                    Looper.loop();
                } catch (Exception e) {
                    showErrorLogOnCCTV(e);
                }
            }
        }, "childThread#1").start();
    }

    //子线程更新UI-错误日志
    private void childThreadLog() {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {
                try {
                    /*
                    ViewRootImpl中的checkThread()源码：
                    void checkThread() {
                      if (mThread != Thread.currentThread()) {
                        throw new CalledFromWrongThreadException(
                                "Only the original thread that created a view hierarchy can touch its views.");
                      }
                    }
                    */
                    mResult.setText("子线程跟新UI操作。。。");
                    /*
                    抛出异常：
                     android.view.ViewRootImpl$CalledFromWrongThreadException:
                     Only the original thread that created a view hierarchy can touch its views.
                     */
                } catch (Exception e) {
                    showErrorLogOnCCTV(e);
                }
            }
        }, "childThreadLog#2").start();
    }

    /**
     * 把错误信息展示出来
     *
     * @param e
     */
    private void showErrorLogOnCCTV(Exception e) {
        Message message = new Message();

        //此法日志不全
//      StackTraceElement[] traceElements = e.getStackTrace();
//      StringBuilder sb = new StringBuilder();
//      sb.append(e.getMessage() + "\n");//1异常信息
//      for (StackTraceElement se : traceElements) {
//          sb.append(se + "\n");//1异常信息-抛出方法栈
//      }
        message.what = MSG_ERROR;
        message.obj = CommonUtils.getExceptionInfo(e);//获取错误信息
        mHandler.sendMessage(message);
    }


    private void demo() {
        HandlerThread handlerThread = new HandlerThread("mythread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        MyHandler myHandler = new MyHandler(looper);
        new Handler().getLooper();
    }

    private class MyHandler extends Handler {
        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }
}
