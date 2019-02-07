package com.improve.modules.ui_process.dragview.my;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by javakam on 2018/4/26.
 */
public class DragDemoDefault extends View {
    private int lastX;
    private int lastY;

    public DragDemoDefault(Context context) {
        super(context);
        initView();
    }


    public DragDemoDefault(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DragDemoDefault(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.parseColor("#FF4081"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSize(widthMeasureSpec), measureSize(heightMeasureSpec));
    }

    private int measureSize(int measureSpec) {
        int result;
        int model = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (model == MeasureSpec.EXACTLY) {
            return size;
        } else {
            result = 200;
            if (model == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        Log.d("123", "onTouchEvent");
        //使用视图坐标
//        int x = (int) event.getX();
//        int y = (int) event.getY();
        //使用系统的绝对坐标
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.i("123", "ACTION_DOWN");
                //重新记录坐标值
                lastX = x;
                lastY = y;
                //很多人这样写，其实没必要
//                lastX = (int) event.getX();
//                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.v("123", "ACTION_MOVE...");
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                Log.e("123", offsetX + " --- " + offsetY);
                //1
//                layout(getLeft() + offsetX
//                        , getTop() + offsetY
//                        , getRight() + offsetX
//                        , getBottom() + offsetY);

                //2
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //3
//                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                params.leftMargin = getLeft() + offsetX;
//                params.topMargin = getTop() + offsetY;
//                setLayoutParams(params);

                //4
                //无效 scrollBy&scrollTo拖动的是View的content，比如TextView的content是文本，ImageView的content是drawable对象
//                scrollBy(offsetX, offsetY);
                //再次无效  盖板跑了。。。
//                ((View) getParent()).scrollBy(offsetX, offsetY);
                //OK。。。
//                ((View) getParent()).scrollBy(-offsetX, -offsetY);

                //5  4&5 都是盖板移动的思想
                View parent = (View) getParent();
                parent.scrollTo(parent.getScrollX() - offsetX, parent.getScrollY() - offsetY);

                //重新记录坐标值
                lastX = x;
                lastY = y;
                break;
        }
        return true;
    }
}
