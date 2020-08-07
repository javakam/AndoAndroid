package com.improve.modules.ui_hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by javakam on 2018/5/20.
 */
public class Practice1DrawColorView extends BaseView {
    public Practice1DrawColorView(Context context) {
        this(context, null);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(100, 100, 500, 900, new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.drawColor(Color.GRAY);
        paint.setTextSize(50f);
        canvas.drawText("Draw Color", 200, 200, paint);
    }
}
