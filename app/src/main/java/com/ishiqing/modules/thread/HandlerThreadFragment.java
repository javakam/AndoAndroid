package com.ishiqing.modules.thread;

import android.support.annotation.NonNull;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

/**
 * Created by javakam on 2018/8/15.
 */
public class HandlerThreadFragment extends BaseFragment {
    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_asynctask;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("HandlerThread", true);
    }
}
