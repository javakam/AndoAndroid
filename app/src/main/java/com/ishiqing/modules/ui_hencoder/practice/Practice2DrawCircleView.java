package com.ishiqing.modules.ui_hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by javakam on 2018/5/20.
 */
public class Practice2DrawCircleView extends BaseView {
    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(100, 100, 50, paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 100, 50, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);
        canvas.drawCircle(100, 500, 50, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        path.moveTo(300, 500);
        path.setFillType(Path.FillType.EVEN_ODD);
        path.addCircle(300, 500, 50, Path.Direction.CW);
        path.addCircle(300, 500, 80, Path.Direction.CW);
        path.close();
        canvas.drawPath(path, paint);

    }
}
