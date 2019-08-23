package com.improve.modules.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.roundwidget.QMUIRoundButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Service混合调用 -- stopService
 * <p>
 * Created by javakam on 2018/6/17.
 */
public class ServiceSwipeFragment3 extends BaseSwipeFragment {

    @BindView(R.id.btnStart)
    QMUIRoundButton button;

    private Intent intent;
    private MyService3.MyService3Binder myService3Binder;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("ServiceSwipeFragment3.onServiceConnected");
            myService3Binder = (MyService3.MyService3Binder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            System.out.println("ServiceSwipeFragment3.onServiceDisconnected");
        }

        @Override
        public void onBindingDied(ComponentName name) {
            System.out.println("ServiceSwipeFragment3.onBindingDied");
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
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_SERVICE3, true);
    }

    @OnClick({R.id.btnStart, R.id.btnBind, R.id.btnUnbind, R.id.btnStop})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                mActivity.startService(intent);
                break;
            case R.id.btnBind:
                mTipDialogUtil.createIconWithTipDialog(mActivity, QMUITipDialog.Builder.ICON_TYPE_LOADING
                        , "正在加载...");
                button.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mTipDialogUtil.dismiss();
                    }
                }, 1000);
                mActivity.bindService(intent, conn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbind:
                try {
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
