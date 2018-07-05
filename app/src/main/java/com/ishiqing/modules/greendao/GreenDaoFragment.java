package com.ishiqing.modules.greendao;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ishiqing.R;
import com.ishiqing.base.BaseFragment;

import butterknife.OnClick;

/**
 * Created by javakam on 2018-7-5 19:56:05
 */
public class GreenDaoFragment extends BaseFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_green_dao;
    }

    @Override
    protected void initViews() {
        initTopBar("GreenDao", true);
    }

    @OnClick(R.id.btWanAndroid)
    void wan(View v) {
        if (v.getId() == R.id.btWanAndroid) {
            ARouter.getInstance().build("/sq/mainactivity")
                    .withLong("key1", 666L)
                    .withString("key3", "888")
//                    .withObject("key4", new Object())
                    .navigation();
        }
    }
}
