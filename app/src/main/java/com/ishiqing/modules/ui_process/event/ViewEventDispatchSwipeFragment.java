package com.ishiqing.modules.ui_process.event;

import android.support.annotation.NonNull;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.fragment.BaseSwipeFragment;

/**
 * Created by javakam on 2018/7/17 0017.
 */
public class ViewEventDispatchSwipeFragment extends BaseSwipeFragment {
    @NonNull
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_view_event_dispatch;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("View事件传递", true);
    }
}
