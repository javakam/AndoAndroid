package com.ishiqing.modules.network;

import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.fragment.BaseSwipeFragment;

/**
 * Android 增量更新 -- 2H
 * <p>
 * https://www.cniao5.com/course/courseware/10054
 * <p>
 * Created by javakam on 2018/6/28.
 */
public class 增量更新 extends BaseSwipeFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.button_layout;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("增量更新", true);
    }
}
