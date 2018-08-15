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
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * AsyncTask
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
    }

    //自定义线程池
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 60;
    public static final Executor MY_THREAD_POOL_EXECUTOR;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    private static final BlockingQueue<Runnable> sPoolWorkQueue =
            new LinkedBlockingQueue<Runnable>(128);

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
                sPoolWorkQueue, sThreadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        MY_THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }

    @OnClick({R.id.btAsyncTaskSerial, R.id.btAsyncTaskParallel, R.id.btAsyncTaskParallelByUs, R.id.btAsyncTaskParallelByUs2})
    void startAsyncTask(View view) {
        tvCurrentThread.setText("");
        tvResult.setText("");
        switch (view.getId()) {
            case R.id.btAsyncTaskSerial://串行执行
                new MyAsyncTask(mActivity, "Task#1 ").execute("123");
                new MyAsyncTask(mActivity, "Task#2 ").execute("123");
                new MyAsyncTask(mActivity, "Task#3 ").execute("123");
                break;
            case R.id.btAsyncTaskParallel://并行执行 -- 这里使用 AsyncTask 自带的线程池
                new MyAsyncTask(mActivity, "Task#1 ").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#2 ").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#3 ").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "123");
                break;
            case R.id.btAsyncTaskParallelByUs://并行执行 -- 自定义线程池
                new MyAsyncTask(mActivity, "Task#1 ").executeOnExecutor(MY_THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#2 ").executeOnExecutor(MY_THREAD_POOL_EXECUTOR, "123");
                new MyAsyncTask(mActivity, "Task#3 ").executeOnExecutor(MY_THREAD_POOL_EXECUTOR, "123");
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
