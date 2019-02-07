package com.improve.modules.ui_hencoder.practice;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by javakam on 2018/5/20.
 */
public abstract class BaseView extends View {
    public Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public Path path = new Path();

    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSpec(widthMeasureSpec), measureSpec(heightMeasureSpec));
    }

    private int measureSpec(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            //为wrap_content设置默认值
            result = 1000;
            if (specMode == MeasureSpec.UNSPECIFIED) {
                result = Math.min(result, measureSpec);
            }
        }
        return result;
    }
}
