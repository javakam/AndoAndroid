package com.ishiqing.modules.ui_process.heros;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by javakam on 2018/5/7 0007.
 */
public class DrawerMenu extends ViewGroup {
    private int mButtonBottomWidth;
    private int mButtonBottomHeight;
    private int mButtonX;
    private int mButtonY;
    private Button mButtonBottom;

    public DrawerMenu(Context context) {
        this(context, null);
    }

    public DrawerMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawerMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, 0);
        initView(context);
    }

    private void initView(Context context) {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            layoutBottom();
            int childs = getChildCount();
            for (int i = 1; i < childs; i++) {
                View child = getChildAt(i);
                int buttonWidth = child.getMeasuredWidth();
                int buttonHeight = child.getMeasuredHeight();
                child.layout(0, mButtonY - mButtonBottomHeight * i * 2
                        , buttonWidth, getMeasuredHeight());
                child.setVisibility(GONE);
            }
        }
    }

    private void layoutBottom() {

        mButtonBottom = (Button) getChildAt(0);
        mButtonBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mButtonBottomWidth = mButtonBottom.getMeasuredWidth();
        mButtonBottomHeight = mButtonBottom.getMeasuredHeight();
        mButtonX = 0;
        int mButtonY = getMeasuredHeight() - mButtonBottomHeight;
        System.out.println("mButtonX: " + mButtonX + " mButtonY: " + mButtonY
                + " mButtonBottomWidth: " + mButtonBottomWidth + " getMeasuredHeight:" + getMeasuredHeight());
        mButtonBottom.layout(mButtonX, mButtonY, mButtonBottomWidth, getMeasuredHeight());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childs = getChildCount();
        for (int i = 0; i < childs; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        setMeasuredDimension(measureSpec(widthMeasureSpec), measureSpec(heightMeasureSpec));
    }

    private int measureSpec(int measureSpec) {
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

    public View getButtonBottom() {
        return mButtonBottom;
    }
}
