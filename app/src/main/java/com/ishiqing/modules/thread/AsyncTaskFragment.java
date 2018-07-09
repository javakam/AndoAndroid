package com.ishiqing.modules.thread;

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
}
