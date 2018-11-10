package com.ishiqing.modules.widgets.v02广告轮播效果;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ishiqing.R;
import com.ishiqing.UIRoute;
import com.ishiqing.base.fragment.BaseSwipeFragment;
import com.sq.library.utils.ResourceUtil;
import com.sq.library.utils.UiUtil;

import java.util.ArrayList;

/**
 * 01优酷菜单 {@link UiUtil hideView() }
 * Created by machangbao on 2018/10/1.
 */
public class BannerFragment extends BaseSwipeFragment {
    private static final String TAG = BannerFragment.class.getSimpleName();
    protected ViewPager viewpager;
    protected TextView tv_title;
    protected LinearLayout ll_point_group;
    //ListView的使用
    //1.在布局文件中定义ListView
    //2.在代码中实例化ListView
    //3.准备数据
    //4.设置适配器-item布局-绑定数据

    private BannerInfo bannerInfo;
    /**
     * 上一次高亮显示的位置
     */
    private int prePosition = 0;
    /**
     * 是否已经滚动
     */
    private boolean isDragging = false;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int item = viewpager.getCurrentItem() + 1;
            viewpager.setCurrentItem(item);

            //延迟发消息
            handler.sendEmptyMessageDelayed(0, 4000);
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_widget_v02_banner;
    }

    @Override
    protected void initViews(View v) {
        initTopBar(UIRoute.FRAG_BANNER, true);
        initBannerDatas();//初始化广告信息

        viewpager = (ViewPager) v.findViewById(R.id.viewpager);
        tv_title = (TextView) v.findViewById(R.id.tv_title);
        ll_point_group = (LinearLayout) v.findViewById(R.id.ll_point_group);
        //ViewPager的使用
        //1.在布局文件中定义ViewPager
        //2.在代码中实例化ViewPager
        //3.准备数据

        for (int i = 0; i < bannerInfo.imageIds.size(); i++) {
            ImageView imageView = new ImageView(mActivity);
            imageView.setBackgroundResource(bannerInfo.imageIds.get(i));

            //添加到集合中
            bannerInfo.imageViews.add(imageView);

            //添加点-----------------------
            ImageView point = new ImageView(mActivity);
            point.setBackgroundResource(R.drawable.point_selector);
//            point.setImageResource(R.drawable.point_selector);
            //在代码中设置的都是像数-问题，在所有的手机上都是8个像数
            //把8px当成是dp-->px
            int width = UiUtil.dip2px(mActivity, 8);
            Toast.makeText(mActivity, "width==" + width, Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i == 0) {
                point.setEnabled(true); //显示红色
            } else {
                point.setEnabled(false);//显示灰色
                params.leftMargin = width;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);
        }

        //4.设置适配器(PagerAdapter)-item布局-绑定数据
        viewpager.setAdapter(new BannerPagerAdapter(mActivity, handler, bannerInfo));
        //设置监听ViewPager页面的改变
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置中间位置
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % bannerInfo.imageViews.size();//要保证imageViews的整数倍

        viewpager.setCurrentItem(item);
        tv_title.setText(bannerInfo.imageDescriptions.get(prePosition));

        //发消息
        handler.sendEmptyMessageDelayed(0, 3000);

    }

    /**
     * 初始化广告信息
     */
    private void initBannerDatas() {
        bannerInfo = new BannerInfo();
        bannerInfo.imageViews = new ArrayList<>();
        bannerInfo.imageIds = new ArrayList<>();
        bannerInfo.imageDescriptions = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            bannerInfo.imageIds.add(ResourceUtil.getDrawableId(mActivity, String.valueOf("cat_" + (i + 1))));
            bannerInfo.imageDescriptions.add("猫猫No." + i);
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动了的时候回调这个方法
         *
         * @param position             当前页面的位置
         * @param positionOffset       滑动页面的百分比
         * @param positionOffsetPixels 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        /**
         * 当某个页面被选中了的时候回调
         *
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {
            int realPosition = position % bannerInfo.imageViews.size();
            Log.e(TAG, "onPageSelected==" + realPosition);
            //设置对应页面的文本信息
            tv_title.setText(bannerInfo.imageDescriptions.get(realPosition));

            //把上一个高亮的设置默认-灰色
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮-红色
            ll_point_group.getChildAt(realPosition).setEnabled(true);

            prePosition = realPosition;


        }

        /**
         * 当页面滚动状态变化的时候回调这个方法
         * 静止->滑动
         * 滑动-->静止
         * 静止-->拖拽
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isDragging = true;
                handler.removeCallbacksAndMessages(null);
                Log.e(TAG, "SCROLL_STATE_DRAGGING-------------------");
            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                Log.e(TAG, "SCROLL_STATE_SETTLING-----------------");
            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                isDragging = false;
                Log.e(TAG, "SCROLL_STATE_IDLE------------");
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0, 4000);
            }
        }
    }
}
