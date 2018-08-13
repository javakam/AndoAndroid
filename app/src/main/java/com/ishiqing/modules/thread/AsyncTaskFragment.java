package com.ishiqing.modules.thread;

import android.os.AsyncTask;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

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
    }

    class MyAsyncTask extends AsyncTask<Integer,String,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... integers) {
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
