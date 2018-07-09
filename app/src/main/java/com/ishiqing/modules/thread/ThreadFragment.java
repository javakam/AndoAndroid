package com.ishiqing.modules.thread;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

/**
 * Thread
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class ThreadFragment extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_thread;
    }

    @Override
    protected void initViews() {
        initTopBar("Thread", true);
    }
}
