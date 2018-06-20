package com.ishiqing.modules.activity;

import android.support.v4.view.ViewPager;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.BaseFragment;
import com.ishiqing.base.adapter.BasePagerAdapter;
import com.ishiqing.base.adapter.ContentPage;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import butterknife.BindView;

/**
 * Created by javakam on 2018/6/20.
 */
public class ActiviyEntranceFragment extends BaseFragment {
    @BindView(R.id.tabSegment)
    QMUITabSegment mTabSegment;
    @BindView(R.id.contentViewPager)
    ViewPager mContentViewPager;

    private BasePagerAdapter mPagerAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_activity_entrance;
    }

    @Override
    protected void initViews() {
        initTopBar(UIRoute.FRAG_ACTIVITY1, true);
        initTabAndPager();
    }

    private void initTabAndPager() {
        mPagerAdapter = new BasePagerAdapter(mActivity);
        mContentViewPager.setAdapter(mPagerAdapter);
        mContentViewPager.setCurrentItem(ContentPage.ITEM0.getPosition(), false);
        for (int i = 0; i < 2; i++) {
            mTabSegment.addTab(new QMUITabSegment.Tab("Item " + (i + 1)));
        }
        int space = QMUIDisplayHelper.dp2px(getContext(), 16);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setItemSpaceInScrollMode(space);
        mTabSegment.setupWithViewPager(mContentViewPager, false);
        mTabSegment.setPadding(space, 0, space, 0);
    }
}
