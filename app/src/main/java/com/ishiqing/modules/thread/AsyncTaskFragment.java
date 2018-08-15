package com.ishiqing.modules.thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;
import com.sq.library.utils.L;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * AsyncTask  & 多线程 ThreadPoolExecutor + Executors
 * <p>
 * {@see AsyncTask缺陷问题.md}
 * <p>
 * 郭霖 AsyncTask  https://blog.csdn.net/guolin_blog/article/details/11711405
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class AsyncTaskFragment extends BaseFragment {
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @BindView(R.id.tvCurrentThread)
    TextView tvCurrentThread;
    @BindView(R.id.tvResult)
    TextView tvResult;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_asynctask;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("AsyncTask", true);
        /*
        About Executors
         */
        Executors.newFixedThreadPool(2);
        //适合执行大量的耗时较少的任务 -- 容易开启过多线程，不推荐
        Executors.newCachedThreadPool();
        //定时执行任务
        Executors.newScheduledThreadPool(2);
        Executors.newSingleThreadExecutor();
    }

    //自定义线程池
    public static final Executor MY_THREAD_POOL_EXECUTOR;
    //Java虚拟机可用的处理器数量 -- 即 CPU核❤数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    //线程池的核心线程数。默认情况下，核心线程会一直存活，即使它们处于闲置状态。
    //【注】设置了超时机制除外，当 allowCoreThreadTimeOut 属性为 true 时,那么闲置的核心线程在等待新任务到来时会有超时策略，
    //当等待时间超过 keepAliveTime 所指定的时长后，核心线程就会被终止！
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));

    //线程池所能容纳的最大线程数。当活动线程数达到这个数值后，后续的新任务将会被阻塞。
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;

    //非核心线程闲置时的超时时长，超过这个时长，非核心线程就会被回收。
    //当 allowCoreThreadTimeOut 属性为 true 时，keepAliveTime 同样作用于核心线程。
    private static final int KEEP_ALIVE_SECONDS = 60;

    //线程池中的任务队列。
    //通过 线程池 的 execute 方法提交的 Runnable 对象会存储在里面。
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);

    //线程工厂，为线程池提供创建线程的功能。
    //ThreadFactory是个接口，只有一个方法：Thread newThread(Runnable r);
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "我的 AsyncTask #" + mCount.getAndIncrement());
        }
    };

    static {
        //征服手持机 Android6.0: CPU核心数 : 8
        L.d("CPU_COUNT : " + CPU_COUNT);
        //征服手持机 Android6.0: 自定义线程池的配置：CORE_POOL_SIZE: 4  MAXIMUM_POOL_SIZE: 17  KEEP_ALIVE_SECONDS: 60
        L.i("自定义线程池的配置：" + "CORE_POOL_SIZE: " + CORE_POOL_SIZE + "  MAXIMUM_POOL_SIZE: " + MAXIMUM_POOL_SIZE
                + "  KEEP_ALIVE_SECONDS: " + KEEP_ALIVE_SECONDS);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS,
                TimeUnit.SECONDS,//指定 keepAliveTime 的时间单位
                sPoolWorkQueue, sThreadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        MY_THREAD_POOL_EXECUTOR = threadPoolExecutor;
        //或者简单点的线程池
//        Executor exec = new ThreadPoolExecutor(15, 200, 10,
//                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    }

    @OnClick({R.id.btAsyncTaskSerial, R.id.btAsyncTaskParallel, R.id.btAsyncTaskParallelByUs, R.id.btAsyncTaskParallelByUs2})
    void startAsyncTask(View view) {
        tvCurrentThread.setText("");
        tvResult.setText("");
        switch (view.getId()) {
            case R.id.btAsyncTaskSerial://串行执行
                new MyAsyncTask(mActivity, "Task#c1 ").execute("123");
                new MyAsyncTask(mActivity, "Task#c2 ").execute("123");
                new MyAsyncTask(mActivity, "Task#c3 ").execute("123");
                break;
            case R.id.btAsyncTaskParallel://并行执行 -- 这里使用 AsyncTask 自带的线程池
                new MyAsyncTask(mActivity, "Task#b1 ").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#b2 ").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#b3 ").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "123");
                break;
            case R.id.btAsyncTaskParallelByUs://并行执行 -- 自定义线程池
                new MyAsyncTask(mActivity, "Task#bb1 ").executeOnExecutor(MY_THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#bb2 ").executeOnExecutor(MY_THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#bb3 ").executeOnExecutor(MY_THREAD_POOL_EXECUTOR, "123");
                break;
            case R.id.btAsyncTaskParallelByUs2://并行执行 -- 自定义线程池 另外一种方式
                MyAsyncTask.setDefaultExecutor(MY_THREAD_POOL_EXECUTOR);//替换掉默认的  AsyncTask.SERIAL_EXECUTOR
                new MyAsyncTask(mActivity, "Task#a ").execute("abc");
                new MyAsyncTask(mActivity, "Task#b ").execute("abc");
                new MyAsyncTask(mActivity, "Task#c ").execute("abc");
                break;
        }
    }

    class MyAsyncTask extends AsyncTask<String, Integer, String> {
        WeakReference<Activity> mWeakActivity;
        private String mName;

        public MyAsyncTask(Activity activity, String mName) {
            mWeakActivity = new WeakReference<Activity>(activity);
            this.mName = mName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String curThread = Thread.currentThread().getId() + "   " + Thread.currentThread().getName() + "\n";
            L.d("doInBackground : " + curThread);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mName;
        }

        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            String curThread = Thread.currentThread().getId() + "   " + Thread.currentThread().getName() + "\n";
            tvCurrentThread.setText("onPostExecute : " + curThread);
            L.d("onPostExecute : " + curThread);

            // 重新获取Actiity的强引用，并且判断是否存活
            Activity activity = mWeakActivity.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                // activity死亡了，不再做任何的事情
                return;
            }
            // The activity is still valid, do main-thread stuff here
            String s = result + " finished at " + SDF.format(new Date()) + "\n";
            L.e(s);
            tvResult.setText(tvResult.getText() + s);
        }
    }
}
