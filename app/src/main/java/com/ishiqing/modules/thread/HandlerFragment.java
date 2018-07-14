package com.ishiqing.modules.thread;

import android.os.Handler;
import android.os.Message;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

/**
 * Handler
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class HandlerFragment extends BaseFragment {

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_handler;
    }

    @Override
    protected void initViews() {
        initTopBar("Handler", true);
    }
}
