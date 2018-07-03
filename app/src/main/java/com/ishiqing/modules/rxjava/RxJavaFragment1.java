package com.ishiqing.modules.rxjava;

import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

/**
 * Created by javakam on 2018/7/2.
 */
public class RxJavaFragment1 extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.main_layout;
    }

    @Override
    protected void initViews() {
        initTopBar("RxJava2 复习", true);

    }
}
