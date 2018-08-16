package com.ishiqing.modules.thread;

import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.base.fragment.BaseSwipeFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 线程和消息机制
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class ThreadNavSwipeFragment extends BaseSwipeFragment {
    @BindView(R.id.tvResult)
    TextView tvResult;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_thread_nav;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("线程和消息机制", true);
    }

    @OnClick({R.id.btHandler, R.id.btAsyncTask, R.id.btHandlerThread})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btHandler:
                mActivity.startFragment(new HandlerSwipeFragment());
                break;
            case R.id.btAsyncTask:
                mActivity.startFragment(new AsyncTaskSwipeFragment());
                break;
            case R.id.btHandlerThread:
                mActivity.startFragment(new HandlerThreadSwipeFragment());
                break;
        }
    }
}
