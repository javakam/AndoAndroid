package com.ishiqing.modules.ui_process.艺术探索;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by javakam on 2018/7/17.
 */
public class ArtViewBase extends View {
    public ArtViewBase(Context context) {
        super(context);
    }

    public ArtViewBase(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ArtViewBase(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewConfiguration.get(context).getScaledTouchSlop();
    }


}
