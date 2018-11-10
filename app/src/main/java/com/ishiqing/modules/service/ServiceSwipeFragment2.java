package com.ishiqing.modules.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.UIRouter;
import com.ishiqing.base.fragment.BaseSwipeFragment;

import butterknife.OnClick;

/**
 * @see MyService2
 * <p>
 * Created by javakam on 2018/6/16.
 */
public class ServiceSwipeFragment2 extends BaseSwipeFragment {

    //不能直接声明，需要在onAttach时执行
    //    private Intent intent=new Intent(getContext(), MyService2.class);
    private Intent intent;
    private MyService2.ServiceBinder serviceBinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        intent = new Intent(getContext(), MyService2.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_service_2;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRouter.FRAG_SERVICE2, true);
    }

    @OnClick({R.id.btnStart, R.id.btnStop, R.id.btnChangeFormat})
    protected void click(View view) {
        int id = view.getId();
        if (id == R.id.btnStart) {
            mActivity.bindService(intent, conn, Context.BIND_AUTO_CREATE);
        } else if (id == R.id.btnStop) {
            if (conn != null) {
                try {
                    mActivity.unbindService(conn);
                } catch (Exception e) {
                    //多次解绑 unbindService 会出现异常
                    // Service not registered: com.ishiqing.modules.service.ServiceSwipeFragment2$1@235144d8
                    System.err.println(e.getMessage());
                }
            }
        } else if (id == R.id.btnChangeFormat) {
            //更改系统时间的输出格式
            //问：如何获得当前已启动的服务？
            // 无法获得
            // 但是可以通过已启动的服务的代理对象
            if (serviceBinder != null) {
                serviceBinder.changeFormatBinder("yyyy/MM/dd");
            }
        }
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //注：只在绑定【成功】时执行--Service中的onBind有返回值时执行
            System.out.println("ServiceSwipeFragment2.onServiceConnected " + name.getClassName());
            serviceBinder = (MyService2.ServiceBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            //当服务所在进程被杀死 或 程序崩溃时执行
            //？？？？？？？？？？？？？为什么不执行？？？？？？？？
            System.out.println("ServiceSwipeFragment2.onServiceDisconnected " + name.getClassName());
        }
    };
}