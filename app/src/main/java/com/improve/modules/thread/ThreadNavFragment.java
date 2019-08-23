package com.improve.modules.thread;

import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * 线程和消息机制
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class ThreadNavFragment extends BaseSwipeFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_thread_nav;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("线程和消息机制", true);

        v.findViewById(R.id.btHandler).setOnClickListener(this);
        v.findViewById(R.id.btAsyncTask).setOnClickListener(this);
        v.findViewById(R.id.btHandlerThread).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btHandler:
                mActivity.startFragment(new HandlerFragment());
                break;
            case R.id.btAsyncTask:
                mActivity.startFragment(new AsyncTaskFragment());
                break;
            case R.id.btHandlerThread:
                mActivity.startFragment(new ClassLoaderFragment());
                break;
            default:
                break;
        }
    }
}
