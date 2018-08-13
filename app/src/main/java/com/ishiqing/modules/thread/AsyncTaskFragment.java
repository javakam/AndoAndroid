package com.ishiqing.modules.thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

import java.lang.ref.WeakReference;

/**
 * AsyncTask
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class AsyncTaskFragment extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_asynctask;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("AsyncTask", true);

        new MyAsyncTask(mActivity, "MyAsyncTask#1  ").execute("123");
        new MyAsyncTask(mActivity, "MyAsyncTask#2  ").execute("123");
        new MyAsyncTask(mActivity, "MyAsyncTask#3  ").execute("123");
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
            try {
                Thread.sleep(3000);
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
            // 重新获取Actiity的强引用，并且判断是否存活
            Activity activity = mWeakActivity.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                // activity死亡了，不再做任何的事情
                return;
            }
            // The activity is still valid, do main-thread stuff here

        }
    }
}
