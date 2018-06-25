package com.ishiqing.modules.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Service混合调用 -- stopService
 * <p>
 * Created by javakam on 2018/6/17.
 */
public class ServiceFragment3 extends BaseFragment {
    @BindView(R.id.btnStart)
    QMUIRoundButton button;

    private Intent intent;
    private MyService3.MyService3Binder myService3Binder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("ServiceFragment3.onServiceConnected");
            myService3Binder = (MyService3.MyService3Binder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("ServiceFragment3.onServiceDisconnected");
        }

        @Override
        public void onBindingDied(ComponentName name) {
            System.out.println("ServiceFragment3.onBindingDied");
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        intent = new Intent(mActivity, MyService3.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_service_3;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_SERVICE3, true);
    }

    @OnClick({R.id.btnStart, R.id.btnBind, R.id.btnUnbind, R.id.btnStop})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mActivity.startService(intent);
                break;
            case R.id.btnBind:
                tipDialogUtil.createIconWithTipDialog(mActivity, QMUITipDialog.Builder.ICON_TYPE_LOADING
                        , "正在加载...");
                mActivity.bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbind:
                try {
                    tipDialogUtil.dismiss();
                    mActivity.unbindService(conn);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
            case R.id.btnStop:
                mActivity.stopService(intent);
                break;
        }
    }
}
