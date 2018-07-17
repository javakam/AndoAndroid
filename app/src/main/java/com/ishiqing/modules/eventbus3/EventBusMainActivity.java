package com.ishiqing.modules.eventbus3;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.base.BaseActivity;

public class EventBusMainActivity extends BaseActivity {
    private TextView myText;
    private Handler mHadndler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            myText.setText("收到消息啦..." + msg.what);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_eb3;
    }

    @Override
    protected void initViews() {
        myText = findViewById(R.id.myTextView);
        myText.setOnClickListener(v -> method4());
    }

    public void goEventBusThreadModeActivity(View view) {
        startActivity(new Intent(EventBusMainActivity.this, EventBusThreadModeActivity.class));
    }

    public void goEventBusPriorityActivity(View view) {
        startActivity(new Intent(EventBusMainActivity.this, EventBusPriorityActivity.class));
    }


    /**
     * 方法一：Handler
     */
    private void method1() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //耗时操作
                    connectNet();
                    //向Handler发送消息
                    mHadndler.sendEmptyMessage(111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 方法二：通过View.post(Runnable)
     */
    private void method2() {
        myText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //耗时操作
                            connectNet();
                            myText.post(new Runnable() {
                                @Override
                                public void run() {
                                    myText.setText("联网结束，更新UI数据");
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }

    /**
     * 方法三：Activity.runOnUiThread（Runnable )
     */
    private void method3() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //耗时操作
                    connectNet();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myText.setText("runOnUiThread...");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 方法四：AsyncTask
     */
    private void method4() {
        //UI线程中执行
        new LoadTask().execute("www.91dota.com");
    }

    private class LoadTask extends AsyncTask<Object, Object, String> {
        @Override
        protected void onPostExecute(String result) {
            myText.setText(result); //得到来自网络的信息刷新页面
        }

        @Override
        protected String doInBackground(Object[] objects) {
            //后台耗时操作
            try {
                connectNet();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "联网成功。。。";
        }
    }

    private void connectNet() throws InterruptedException {
        Thread.sleep(2000);
    }
}
