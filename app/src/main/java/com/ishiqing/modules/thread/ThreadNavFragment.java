package com.ishiqing.modules.thread;

import android.view.View;
import android.widget.TextView;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Handler
 * <p>
 * Created by javakam on 2018/7/9 .
 */
public class ThreadNavFragment extends BaseFragment {
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

    @OnClick({R.id.btAsyncTask, R.id.btHandler, R.id.btThread})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btAsyncTask:
                mActivity.startFragment(new AsyncTaskFragment());
                break;
            case R.id.btHandler:
                mActivity.startFragment(new HandlerFragment());
                break;
            case R.id.btThread:
                mActivity.startFragment(new ThreadFragment());
                break;
        }
    }
}
