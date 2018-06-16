package com.ishiqing.base;


import com.ishiqing.base.qmui.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * Created by cgspine on 2018/1/7.
 */

public abstract class BaseFragment extends QMUIFragment {

    public BaseFragment() {
    }

    @Override
    protected int backViewInitOffset() {
        return QMUIDisplayHelper.dp2px(getContext(), 100);
    }

    @Override
    public void onResume() {
        super.onResume();
//        QDUpgradeManager.getInstance(context).runUpgradeTipTaskIfExist(getActivity());
    }

}
