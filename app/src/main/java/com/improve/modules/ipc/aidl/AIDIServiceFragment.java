package com.improve.modules.ipc.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import com.android.internal.telephony.ITelephony;
import com.improve.R;
import com.improve.UIRouter;
import com.improve.base.fragment.BaseSwipeFragment;
import com.improve.utils.L;

import java.lang.reflect.Method;

import improve.support.ipc.aidl.IMyServiceProxy;

/**
 * AIDL Client - https://blog.csdn.net/u011240877/article/details/72765136#1创建-aidl
 * <p>
 * AIDL（Android interface defination language）通信技术
 * <p>
 * Created by javakam on 2018/6/25.
 */
public class AIDIServiceFragment extends BaseSwipeFragment {

    TextView mTvResult;

    private ServiceConnection conn;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_aidl;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_AIDL, true);
        mTvResult = v.findViewById(R.id.tvAIDLResult);

        rootView.findViewById(R.id.btnAIDLBind).setOnClickListener(this);
        rootView.findViewById(R.id.btnAIDLUnBind).setOnClickListener(this);
        rootView.findViewById(R.id.btnBindSysAIDL).setOnClickListener(this);
        rootView.findViewById(R.id.btnUnBindSysAIDL).setOnClickListener(this);
    }

    /**
     * 演示 -=- 调用自定义的AIDL远程服务 & 系统内置的服务
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAIDLBind://调用自定义的AIDL
                if (conn == null) {
                    conn = new ServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName name, IBinder service) {
                            L.i("client onServiceConnected");
                            IMyServiceProxy proxy = IMyServiceProxy.Stub.asInterface(service);
                            try {
                                proxy.playMusic("春天花会开");
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName name) {
                            L.i("client onServiceDisconnected");
                        }
                    };
                    Intent service = new Intent("it.is.an.aidl.service.server");
                    // 【注】这个 应用ID 是指对方的，不是自己的！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
                    service.setPackage("com.improve.support");//Application ID
                    bindService(service, conn, Context.BIND_AUTO_CREATE);
                }
                break;
            case R.id.btnAIDLUnBind:
                try {
                    if (conn != null) {
                        unbindService(conn);
                        conn = null;
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
            case R.id.btnBindSysAIDL://调用系统的AIDL -- 反射
                L.d("调用系统的AIDL...");
                try {
                    //反射获取 ServiceManager，并调用 getService 方法获取 IBinder对象
                    Class clz = Class.forName("android.os.ServiceManager");
                    Method method = clz.getMethod("getService", String.class);
                    IBinder iBinder = (IBinder) method.invoke(null, Context.TELEPHONY_SERVICE);

                    ITelephony telephony = ITelephony.Stub.asInterface(iBinder);
                    //调用系统服务挂断电话
                    telephony.endCall();
                    //【注】记得加上权限！
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnUnBindSysAIDL:
                break;
            default:
                break;
        }
    }
}
