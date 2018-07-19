package com.ishiqing.modules.service.intentservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;

import butterknife.OnClick;

/**
 * Service混合调用 -- stopService
 * <p>
 * Created by javakam on 2018/6/17.
 */
public class IntentServiceFragment extends BaseFragment {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_intentservice;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRoute.FRAG_INTENT_SERVICE, true);
    }

    @OnClick({R.id.btnStart})
    void start(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                //可以启动多次，每启动一次，就会新建一个work thread，但IntentService的实例始终只有一个
                //Operation 1
                /*
                java.lang.IllegalArgumentException:
                    Service Intent must be explicit: Intent { act=com.isq.myintentservice (has extras) }
                 */
                Intent startServiceIntent = new Intent("suibianqimingdouxing");
                //【注】让Service可以做隐式跳转的核心配置，必须加上应用包名!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //【注】这个应用ID是指对方的，不是自己的!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                startServiceIntent.setPackage("com.ishiqing");// 或者 BuildConfig.APPLICATIONID
                Bundle bundle = new Bundle();
                bundle.putString("param", "oper1");
                startServiceIntent.putExtras(bundle);
                mActivity.startService(startServiceIntent);

                //Operation 2
                Intent startServiceIntent2 = new Intent(mActivity, MyIntentService.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("param", "oper2");
                startServiceIntent2.putExtras(bundle2);
                mActivity.startService(startServiceIntent2);
                break;
            case R.id.btnStop:
                break;
        }
    }
}
