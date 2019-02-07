package com.improve.modules.wanandroid;

import android.view.View;

import com.improve.R;
import com.improve.base.fragment.BaseSwipeFragment;

import butterknife.OnClick;

/**
 * 玩Android组件化客户端
 * <p>
 * ARouter
 * https://github.com/alibaba/ARouter/blob/master/README_CN.md
 * <p>
 * Created by javakam on 2018-7-3 .
 */
public class WanSwipeFragment extends BaseSwipeFragment {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_wan_android;
    }

    @Override
    protected void initViews(View v) {
        initTopBar("玩Android", true);
    }

    @OnClick(R.id.btWanAndroid)
    void wan(View v) {
        /*
        app.gradle

        // 引入子模块后，ARouter才会找到该模块！》》》There's no router matched!
        if (isModule.toBoolean()) {
            api project(':sqretrofit')
        }
         */

//        if (v.getId() == R.id.btWanAndroid) {
//            ARouter.getInstance().build("/improve/mainactivity")
//                    .withLong("key1", 666L)
//                    .withString("key3", "888")
////                    .withObject("key4", new Object())
//                    .navigation();
//        }
    }
}
