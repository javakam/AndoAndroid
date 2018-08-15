package com.ishiqing.home;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.ishiqing.R;
import com.ishiqing.base.fragment.BaseSwipeFragment;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.HashMap;

import butterknife.BindView;

/**
 * @author javakam
 * @date 2018-6-16
 */
public class MainSwipeFragment extends BaseSwipeFragment {
    private final static String TAG = MainSwipeFragment.class.getSimpleName();
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    QMUITabSegment mTabSegment;
    private HashMap<Pager, MainController> mPages;
    private PagerAdapter mPagerAdapter = new PagerAdapter() {

        private int mChildCount = 0;

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public Object instantiateItem(final ViewGroup container, int position) {
            MainController page = mPages.get(Pager.getPagerFromPositon(position));
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(page, params);
            return page;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            if (mChildCount == 0) {
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            mChildCount = getCount();
            super.notifyDataSetChanged();
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initViews(View v) {
        initTabs();
        initPagers();
    }

    private void initTabs() {

        int normalColor = QMUIResHelper.getAttrColor(mActivity, R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(mActivity, R.attr.qmui_config_color_blue);
        mTabSegment.setDefaultNormalColor(normalColor);
        mTabSegment.setDefaultSelectedColor(selectColor);
//        mTabSegment.setDefaultTabIconPosition(QMUITabSegment.ICON_POSITION_BOTTOM);

//        // 如果你的 icon 显示大小和实际大小不吻合:
//        // 1. 设置icon 的 bounds
//        // 2. Tab 使用拥有5个参数的构造器
//        // 3. 最后一个参数（setIntrinsicSize）设置为false
//        int iconShowSize = QMUIDisplayHelper.dp2px(getContext(), 20);
//        Drawable normalDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component);
//        normalDrawable.setBounds(0, 0, iconShowSize, iconShowSize);
//        Drawable selectDrawable = ContextCompat.getDrawable(getContext(), R.mipmap.icon_tabbar_component_selected);
//
//        selectDrawable.setBounds(0, 0, iconShowSize, iconShowSize);
//
//        QMUITabSegment.Tab component = new QMUITabSegment.Tab(
//                normalDrawable,
//                normalDrawable,
//                "Components", false, false
//        );

        QMUITabSegment.Tab home = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.ic_tabbar_home),
                ContextCompat.getDrawable(getContext(), R.mipmap.ic_tabbar_home_selected),
                getString(R.string.nav_main), true
        );

        QMUITabSegment.Tab pop = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.ic_tabbar_pop),
                ContextCompat.getDrawable(getContext(), R.mipmap.ic_tabbar_pop_selected),
                getString(R.string.nav_pop), true
        );
        QMUITabSegment.Tab component = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.mipmap.ic_tabbar_component),
                ContextCompat.getDrawable(getContext(), R.mipmap.ic_tabbar_component_selected),
                getString(R.string.nav_component), true
        );

        mTabSegment.addTab(home).addTab(pop).addTab(component);
    }

    private void initPagers() {

        MainController.MainControlListener listener = new MainController.MainControlListener() {
            @Override
            public void startFragment(BaseSwipeFragment fragment) {
                MainSwipeFragment.this.startFragment(fragment);
            }

            @Override
            public void startActivity(Activity activity) {
                MainSwipeFragment.this.startActivity(new Intent(getActivity(), activity.getClass()));
            }
        };

        mPages = new HashMap<>();

        MainController homeControl = new MainHomeController(mActivity);
        homeControl.setMainControlListener(listener);
        mPages.put(Pager.HOME, homeControl);

        MainController popControl = new MainPopularController(mActivity);
        popControl.setMainControlListener(listener);
        mPages.put(Pager.POPULAR, popControl);

        MainController componentsControl = new MainComponentsController(mActivity);
        componentsControl.setMainControlListener(listener);
        mPages.put(Pager.COMPONENT, componentsControl);

        mViewPager.setAdapter(mPagerAdapter);
        mTabSegment.setupWithViewPager(mViewPager, false);
        mViewPager.setOffscreenPageLimit(4);
    }


    enum Pager {
        HOME, POPULAR, COMPONENT;

        public static Pager getPagerFromPositon(int position) {
            switch (position) {
                case 0:
                    return HOME;
                case 1:
                    return POPULAR;
                case 2:
                    return COMPONENT;
                default:
                    return HOME;
            }
        }
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }
}