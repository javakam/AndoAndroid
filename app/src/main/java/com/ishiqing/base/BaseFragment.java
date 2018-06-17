package com.ishiqing.base;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.qmui.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by cgspine on 2018/1/7.
 */

public abstract class BaseFragment extends QMUIFragment {
    @Nullable
    @BindView(R.id.topbar)
    public QMUITopBar mTopBar;

    private Unbinder mUnBinder;
    public BaseFragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseFragmentActivity) context;
    }

    @Override
    protected View onCreateView() {
        View v = LayoutInflater.from(mActivity).inflate(getLayoutResId(), null);
        mUnBinder = ButterKnife.bind(this, v);
        initViews();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
//        QDUpgradeManager.getInstance(context).runUpgradeTipTaskIfExist(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    public BaseFragment() {
    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

    /**
     * 处理View事件
     */
    protected abstract void initViews();

    /**
     * 可以通过重写该方法，重新定义TopBar事件
     */
    public void initTopBar(String title, boolean showImageButtonBack) {
        if (mTopBar != null) {
            mTopBar.setTitle(title);
            if (showImageButtonBack) {
                mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popBackStack();
                    }
                });
            }
        }
    }

}
