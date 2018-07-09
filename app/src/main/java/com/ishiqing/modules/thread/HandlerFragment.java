package com.ishiqing.modules.thread;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

/**
 * Handler
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class HandlerFragment extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_handler;
    }

    @Override
    protected void initViews() {
        initTopBar("Handler", true);
    }
}
