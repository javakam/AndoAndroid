package com.improve.modules.network;

import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * Android IncrementalUpdateFragment -- 2H
 * <p>
 * https://www.cniao5.com/course/courseware/10054
 * <p>
 * Created by javakam on 2018/6/28.
 */
public class IncrementalUpdateFragment extends BaseSwipeFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_main_controller;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("IncrementalUpdateFragment", true);
    }
}
