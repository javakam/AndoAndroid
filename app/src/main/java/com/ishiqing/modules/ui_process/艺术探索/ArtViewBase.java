package com.ishiqing.modules.ui_process.艺术探索;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by javakam on 2018/7/17.
 */
public class ArtViewBase extends View {
    public ArtViewBase(Context context) {
        this(context, null, 0);
    }

    public ArtViewBase(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ArtViewBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawX = (int) event.getRawX();
        int rawY = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);

    }
}
