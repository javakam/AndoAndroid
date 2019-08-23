package com.improve.modules.service;

import android.content.Intent;
import android.view.View;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;

/**
 * @see MyService1
 * <p>
 * Created by javakam on 2018/6/16.
 */
public class ServiceFragment1 extends BaseSwipeFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_service_1;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_SERVICE1, true);

        v.findViewById(R.id.btnStart).setOnClickListener(this);
        v.findViewById(R.id.btnStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent(mActivity, MyService1.class);
        if (id == R.id.btnStart) {
            mActivity.startService(intent);
        } else if (id == R.id.btnStop) {
            mActivity.stopService(intent);
        }
    }
}
