package com.ishiqing.base;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.ishiqing.R;
import com.ishiqing.base.qmui.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.sq.library.utils.UIUtils;
import com.sq.library.widget.SQTipDialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by javakam on 2018/6/20.
 */
public abstract class BaseFragment extends QMUIFragment {
    @Nullable
    @BindView(R.id.topbar)
    public QMUITopBar mTopBar;

    private Unbinder mUnBinder;
    public BaseQMUIFragmentActivity mActivity;
    protected SQTipDialogUtil tipDialogUtil;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (BaseQMUIFragmentActivity) context;
        tipDialogUtil = SQTipDialogUtil.getInstance();
    }

    @Override
    protected View onCreateView() {
        View v = LayoutInflater.from(mActivity).inflate(getLayoutResId(), null);
        mUnBinder = ButterKnife.bind(this, v);
        initViews(v);
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

    @NonNull
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
    protected abstract void initViews(View v);

    /**
     * 可以通过重写该方法，重新定义TopBar事件
     */
    public void initTopBar(String title, boolean showImageBack) {
        if (mTopBar != null) {
            mTopBar.setTitle(title);
            if (showImageBack) {
                mTopBar.addLeftBackImageButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UIUtils.hideIputKeyboard(mActivity);
                        popBackStack();
                    }
                });
            }
        }
    }
}
