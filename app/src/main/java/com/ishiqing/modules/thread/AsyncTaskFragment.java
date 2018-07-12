package com.ishiqing.modules.thread;

import android.os.AsyncTask;

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
    protected void initViews() {
        initTopBar("AsyncTask", true);
    }

    class MyAsyncTask extends AsyncTask<Integer,String,String>{

        @Override
        protected String doInBackground(Integer... integers) {
            return null;
        }
    }
}
