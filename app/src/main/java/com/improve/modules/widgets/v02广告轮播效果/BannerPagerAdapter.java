package com.improve.modules.widgets.v02广告轮播效果;

import android.content.Context;
import android.os.Handler;
import androidx.viewpager.widget.PagerAdapter;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

class BannerPagerAdapter extends PagerAdapter {
    private static final String TAG = "123";
    private Context mContext;
    private Handler mHandler;
    private BannerInfo mBannerInfo;

    public BannerPagerAdapter(Context context, Handler handler, BannerInfo info) {
        this.mContext = context;
        this.mHandler = handler;
        this.mBannerInfo = info;
    }

    /**
     * 得到图片的总数
     *
     * @return
     */
    @Override
    public int getCount() {
//            return imageViews.size();
        return Integer.MAX_VALUE;
    }

    /**
     * 相当于getView方法
     *
     * @param container ViewPager自身
     * @param position  当前实例化页面的位置
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        int realPosition = position % mBannerInfo.imageViews.size();

        final ImageView imageView = mBannerInfo.imageViews.get(realPosition);
        container.addView(imageView);//添加到ViewPager中
        Log.e("123", "instantiateItem==" + realPosition + ",---imageView==" + imageView);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN://手指按下
                        Log.e(TAG, "onTouch==手指按下");
                        mHandler.removeCallbacksAndMessages(null);
                        break;

                    case MotionEvent.ACTION_MOVE://手指在这个控件上移动
                        break;
                    case MotionEvent.ACTION_CANCEL://手指在这个控件上移动
                        Log.e(TAG, "onTouch==事件取消");
//                            handler.removeCallbacksAndMessages(null);
//                            handler.sendEmptyMessageDelayed(0,4000);
                        break;
                    case MotionEvent.ACTION_UP://手指离开
                        Log.e(TAG, "onTouch==手指离开");
                        mHandler.removeCallbacksAndMessages(null);
                        mHandler.sendEmptyMessageDelayed(0, 4000);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });

        imageView.setTag(position);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "点击事件");
                int position = (int) v.getTag() % mBannerInfo.imageViews.size();
                String text = mBannerInfo.imageDescriptions.get(position);
                Toast.makeText(mContext, "text==" + text, Toast.LENGTH_SHORT).show();

            }
        });

        return imageView;
    }

    /**
     * 比较view和object是否同一个实例
     *
     * @param view   页面
     * @param object 这个方法instantiateItem返回的结果
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
//            if(view == object){
//                return true;
//            }else{
//                return  false;
//            }
        return view == object;
    }


    /**
     * 释放资源
     *
     * @param container viewpager
     * @param position  要释放的位置
     * @param object    要释放的页面
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//            Log.e(TAG, "destroyItem==" + position + ",---object==" + object);
        container.removeView((View) object);
    }
}