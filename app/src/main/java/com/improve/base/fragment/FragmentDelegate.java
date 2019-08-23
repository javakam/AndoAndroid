package com.improve.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.improve.R;
import com.improve.base.BaseQMUIFragmentActivity;
import com.improve.utils.ToastUtils;
import com.improve.utils.UiUtil;
import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.QMUITopBar;

/**
 * Created by javakam on 2018/8/15.
 */
public class FragmentDelegate implements IFragmentDelegate {
    public boolean isSwipe = true;
    private BaseFragment mFragment;
    private BaseSwipeFragment mSwipeFragment;
    private BaseQMUIFragmentActivity mActivity;

    public FragmentDelegate(Fragment fragment) {
        if (fragment instanceof BaseSwipeFragment) {
            mSwipeFragment = (BaseSwipeFragment) fragment;
            isSwipe = true;
        } else {
            mFragment = (BaseFragment) fragment;
            isSwipe = false;
        }
    }

    @Override
    public BaseQMUIFragmentActivity onAttach(Context context) {
        return mActivity = (BaseQMUIFragmentActivity) context;
    }

    @Override
    public void initTopBar(QMUITopBar topBar, String title, boolean showImageBack) {
        if (topBar == null) {
            return;
        }
        topBar.setTitle(title);
        if (showImageBack) {
            topBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UiUtil.hideIputKeyboard(mActivity);
                    if (isSwipe) {
                        mSwipeFragment.popBackStack();
                    } else {
                    }
                }
            });
        }
    }

    @Override
    public void shortToast(@Nullable Context context, String msg) {
        ToastUtils.showShort(context, msg);
    }

    @Override
    public void longToast(@Nullable Context context, String msg) {
        ToastUtils.showLong(context, msg);
    }

    public QMUIEmptyView getEmptyView() {
        if (isSwipe) {
            return mSwipeFragment.mEmptyView;
        } else {
            return mFragment.mEmptyView;
        }
    }

    @Override
    public void showLoadingEmptyView() {
        if (getEmptyView() != null) {
            getEmptyView().show(true);
        }
    }

    @Override
    public void hideEmptyView() {
        if (getEmptyView() != null) {
            getEmptyView().hide();
        }
    }

    @Override
    public void showNetErrorEmptyView(@Nullable View.OnClickListener onClickListener) {
        if (getEmptyView() != null) {
            getEmptyView().show(false, //是否要显示loading
                    //标题的文字，不需要则传null
                    mActivity.getResources().getString(R.string.emptyView_mode_desc_fail_title),
                    // 详情文字，不需要则传null
                    mActivity.getResources().getString(R.string.emptyView_mode_desc_fail_desc),
                    //按钮的文字，不需要按钮则传null
                    mActivity.getResources().getString(R.string.emptyView_mode_desc_retry),
                    onClickListener);
        }
    }

    public boolean checkFragmentAttached() {
        if (isSwipe) {
            return mSwipeFragment != null && mSwipeFragment.isAttachedToActivity() && mSwipeFragment.isVisible();
        } else {
            return mFragment != null && !mFragment.isDetached() && mFragment.isVisible();
        }
    }

    @Override
    public void startService(Intent service) {
        if (service != null && mActivity != null && checkFragmentAttached()) {
            mActivity.startService(service);
        }
    }

    @Override
    public void stopService(Intent service) {
        if (service != null && mActivity != null && checkFragmentAttached()) {
            mActivity.stopService(service);
        }
    }

    @Override
    public void bindService(Intent service, ServiceConnection conn, int flags) {
        if (service != null && mActivity != null && checkFragmentAttached()) {
            mActivity.bindService(service, conn, flags);
        }
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        if (mActivity != null && mFragment != null && checkFragmentAttached()) {
            mActivity.unbindService(conn);
        }
    }
}
