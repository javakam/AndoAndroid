package com.improve.modules.ui_process.event;

import androidx.annotation.NonNull;
import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * Created by javakam on 2018/7/17 0017.
 */
public class ViewEventDispatchFragment extends BaseSwipeFragment {
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
