package com.improve.base.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.improve.R;
import com.improve.base.BaseQMUIFragmentActivity;
import com.improve.base.qmui.QMUIFragment;
import com.improve.view.TipDialogUtils;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by javakam on 2018/6/20.
 */
public abstract class BaseSwipeFragment extends QMUIFragment implements View.OnClickListener {
    protected FragmentDelegate mDelegate;
    protected TipDialogUtils mTipDialogUtil;
    public BaseQMUIFragmentActivity mActivity;

    public View rootView;
    public QMUITopBar mTopBar;
    public QMUIEmptyView mEmptyView;

    public BaseSwipeFragment() {
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
        mTipDialogUtil = TipDialogUtils.getInstance();
    }

    @NonNull
    @LayoutRes
    protected abstract int getLayoutResId();

    @Override
    protected View onCreateView() {
        rootView = LayoutInflater.from(mActivity).inflate(getLayoutResId(), null);
        mTopBar = rootView.findViewById(R.id.topbar);
        mEmptyView = rootView.findViewById(R.id.emptyView);
        initViews(rootView);
        return rootView;
    }

    protected abstract void initViews(View v);

    @Override
    public void onResume() {
        super.onResume();
//        QDUpgradeManager.getInstance(context).runUpgradeTipTaskIfExist(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDelegate != null) {
            mDelegate = null;
        }
    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

    @Override
    public void onClick(View v) {
    }

    /**
     * 可以通过重写该方法，重新定义TopBar事件
     */
    public void initTopBar(String title, boolean showImageBack) {
        mDelegate.initTopBar(mTopBar, title, showImageBack);
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
    public void showNetErrorEmptyView(@Nullable View.OnClickListener onClickListener) {
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
}
