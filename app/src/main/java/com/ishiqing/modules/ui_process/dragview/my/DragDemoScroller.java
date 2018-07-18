package com.ishiqing.modules.ui_process.dragview.my;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by javakam on 2018/4/26.
 */
public class DragDemoScroller extends View {
    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public DragDemoScroller(Context context) {
        super(context);
        initView(context);
    }

    public DragDemoScroller(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public DragDemoScroller(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setBackgroundColor(Color.parseColor("#FFE4C4"));
        //初始化Scroller
        mScroller = new Scroller(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }

    private int measureSize(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = 200;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        // 判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(
                    mScroller.getCurrX(),
                    mScroller.getCurrY());
            //通过重绘来不断调用computeScroll
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
//        int x = (int) event.getX();
//        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //手指离开时，执行滑动过程
                View parent = (View) getParent();
                mScroller.startScroll(
                        parent.getScrollX(),
                        parent.getScrollY(),
                        -parent.getScrollX(),
                        -parent.getScrollY()
                );
                invalidate();
                break;
        }
        return true;
    }
}
