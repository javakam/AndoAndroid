package com.ishiqing.modules.ui_process;

import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.BaseActivity;

/**
 * UI绘制流程
 *
 * @author javakam
 * @date 2018/6/27
 */
public class UIDrawingProcessActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
//        com.android.internal.R.layout.screen_simple;
        return R.layout.activity_drawing_process;
    }

    @Override
    protected void initViews() {
        initTopBar("UI绘制流程", true);
        showLoadingEmptyView();
        mEmptyView.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideEmptyView();
            }
        }, 2000);

        View.MeasureSpec measureSpec = new View.MeasureSpec();
        View.MeasureSpec.getMode(1);
        View.MeasureSpec.getSize(1);

    }
}
