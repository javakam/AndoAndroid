package com.improve.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.improve.R;
import com.improve.base.BaseQMUIFragmentActivity;
import com.improve.widget.SQTipDialogUtil;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Fragment基类  -- 不带侧滑的Fragment
 * <p>
 * Created by javakam on 2016/6/25.
 */
public abstract class BaseFragment extends Fragment {
    /**
     * Fragment backstack
     */
    public static final String FM_BACKSTACK = "FM_BACKSTACK";

    protected FragmentDelegate mDelegate;
    protected SQTipDialogUtil mTipDialogUtil;
    public BaseQMUIFragmentActivity mActivity;

    @Nullable
    @BindView(R.id.topbar)
    public QMUITopBar mTopBar;
    @Nullable
    @BindView(R.id.emptyView)
    public QMUIEmptyView mEmptyView;
    private Unbinder mUnBinder;

    public BaseFragment() {
        if (mDelegate == null) {
            mDelegate = new FragmentDelegate(this);
        }
    }

    public FragmentDelegate getDelegate() {
        return mDelegate;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = mDelegate.onAttach(context);
        mTipDialogUtil = SQTipDialogUtil.getInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //此处可以给Fragment设置主题
        //mContext = new ContextThemeWrapper(getActivity(), android.R.style.Theme_Holo_Light);
    }

    @NonNull
    @LayoutRes
    protected abstract int getLayoutResId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = LayoutInflater.from(mActivity).inflate(getLayoutResId(), null);
        mUnBinder = ButterKnife.bind(this, v);
        initViews(v);
        return v;
    }

    protected abstract void initViews(View v);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        if (mDelegate != null) {
            mDelegate = null;
        }
    }

    public void shortToast(@Nullable Context context, String msg) {
        mDelegate.shortToast(mActivity, msg);
    }

    public void longToast(@Nullable Context context, String msg) {
        mDelegate.longToast(mActivity, msg);
    }

    /**
     * loading
     */
    public void showLoadingEmptyView() {
        mDelegate.showLoadingEmptyView();
    }

    public void hideEmptyView() {
        mDelegate.hideEmptyView();
    }

    /**
     * 显示联网异常的 QMUIEmptyView
     *
     * @param onClickListener 按钮的onClick监听，不需要则传null
     */
    public void showNetErrorEmptyView(@android.support.annotation.Nullable View.OnClickListener onClickListener) {
        mDelegate.showNetErrorEmptyView(onClickListener);
    }

    protected void startService(Intent service) {
        mDelegate.startService(service);
    }

    protected void stopService(Intent service) {
        mDelegate.stopService(service);
    }

    protected void bindService(Intent service, ServiceConnection conn, int flags) {
        mDelegate.bindService(service, conn, flags);
    }

    protected void unbindService(ServiceConnection conn) {
        mDelegate.unbindService(conn);
    }

    /**
     * 处理回退事件<p>
     * 对于导航条上RadioGroup+Fragment实现的切换，无视此方法 ；
     * 其他Fragment需要自己处理返回键问题
     * （注）并且需要在onResume()里面调用才会生效
     */
    public void handleBackEvent() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back event
                    getFragmentManager().popBackStack(FM_BACKSTACK,
                            FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    return true;
                }
                return false;
            }
        });
    }
}
