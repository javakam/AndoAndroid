package com.improve.modules.ui_process.dragview.my;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by javakam on 2018/4/27.
 */
public class ViewDragHelperDemo extends FrameLayout {

    private ViewDragHelper viewDragHelper;
    private View mMenuView, mMainView;
    private int mMenuWidth, mMenuHeight, mMainWidth, mMainHeight;

    public ViewDragHelperDemo(Context context) {
        super(context);
        initView();
    }

    public ViewDragHelperDemo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ViewDragHelperDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView() {
        viewDragHelper = ViewDragHelper.create(this, callBack);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragHelper处理，此操作必不可少
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback callBack = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            //如果是mMainView开始检测
            return child == mMainView;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return super.clampViewPositionVertical(child, top, dy);
        }

        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            return left;
        }

//        @Override
//        public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
//            super.onViewPositionChanged(changedView, left, top, dx, dy);
//        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓慢移动到指定位置
            if (mMainView.getLeft() < 500) {//关闭菜单
                viewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
            } else {//打开菜单
                viewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
            }
            ViewCompat.postInvalidateOnAnimation(ViewDragHelperDemo.this);
        }
    };

    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMenuWidth = mMenuView.getMeasuredWidth();
        mMainWidth = mMainView.getMeasuredWidth();
        Log.e("123", mMenuWidth + " --- " + mMainWidth + " --- " + getMeasuredWidth());


    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuView = getChildAt(0);
        mMainView = getChildAt(1);
    }
}
