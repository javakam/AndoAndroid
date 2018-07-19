package com.ishiqing.modules.service;

import android.content.Intent;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import butterknife.OnClick;

/**
 * @see MyService1
 * <p>
 * Created by javakam on 2018/6/16.
 */
public class ServiceFragment1 extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_service_1;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRoute.FRAG_SERVICE1, true);
    }

    @OnClick({R.id.btnStart, R.id.btnStop})
    protected void click(View view) {
        int id = view.getId();
        Intent intent = new Intent(mActivity, MyService1.class);
        if (id == R.id.btnStart) {
            mActivity.startService(intent);
        } else if (id == R.id.btnStop) {
            mActivity.stopService(intent);
        }
    }
}
