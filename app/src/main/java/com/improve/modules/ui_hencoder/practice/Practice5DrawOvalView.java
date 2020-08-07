package com.improve.modules.ui_hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

/**
 * 练习内容：使用 canvas.drawOval() 方法画椭圆
 *
 * @author javakam
 * @date 2018-5-21 10:56:11
 */
public class Practice5DrawOvalView extends BaseView {

    public Practice5DrawOvalView(Context context) {
        super(context);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice5DrawOvalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawOval(new RectF(10, 10, 20, 30), paint);
    }
}
