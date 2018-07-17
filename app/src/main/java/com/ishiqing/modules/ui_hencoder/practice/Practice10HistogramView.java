package com.ishiqing.modules.ui_hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.Arrays;
import java.util.List;

/**
 * 综合练习
 * 练习内容：使用各种 Canvas.drawXXX() 方法画直方图
 *
 * @author javakam
 * @date 2018-5-21 10:48:47
 */
public class Practice10HistogramView extends BaseView {
    private String[] s = {"Mon.", "Tue.", "Wed.", "Thur.", "Fri.", "Sat.", "Sun."};
    private List<String> weeks = Arrays.asList(s);

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        //画坐标三个点(100,100) 、 原点(100,600) 、 (500,600)
        canvas.drawLines(new float[]{100, 100, 100, 600, 100, 600, 500, 600}, 0, 8, paint);

        paint.setColor(Color.GREEN);
        HistogramRectF histogramRectF = new HistogramRectF(100, 600, 100, 0, (float) Math.random() * 501 + 100);
        HistogramRectF histogramRectF1 = new HistogramRectF(100, 600, 100, 1, (float) Math.random() * 501 + 100);
        HistogramRectF histogramRectF2 = new HistogramRectF(100, 600, 100, 2, (float) Math.random() * 501 + 100);
        HistogramRectF histogramRectF3 = new HistogramRectF(100, 600, 100, 3, (float) Math.random() * 501 + 100);
        path.addRect(histogramRectF, Path.Direction.CW);
        path.close();
        path.addRect(histogramRectF1, Path.Direction.CW);
        path.close();
        path.addRect(histogramRectF2, Path.Direction.CW);
        path.close();
        path.addRect(histogramRectF3, Path.Direction.CW);
        path.close();
        /*
        如果 Paint.Style 是 FILL/FILL_AND_STROKE 类型的，path会自动封闭子图形，不用加 path.close();
        并设置填充方式为INVERSE_WINDING （ path.setFillType(INVERSE_WINDING); ）
         */
        canvas.drawPath(path, paint);

        //画文字
        paint.setColor(Color.BLACK);
        paint.setTextSize(16);
        for (int i = 0; i < weeks.size(); i++) {
            Rect bounds = new Rect();
            paint.getTextBounds(weeks.get(i), 0, weeks.get(i).length(), bounds);
            //文字和柱状图中间对齐
            //原点坐标 (100,600)
            canvas.drawText(weeks.get(i), 100 + (100 * i) + bounds.width() / 2, 600 + 30, paint);
        }
    }

    private class HistogramRectF extends RectF {
        public HistogramRectF(float ox, float oy, float diffX, int offset, float top) {
            this.left = ox + (diffX * offset);
            this.top = top;
            this.right = ox + (diffX * offset) + diffX;
            this.bottom = oy;
            //Math.floor(ox);//舍掉小数取整
        }

        public HistogramRectF(float left, float top, float right, float bottom) {
            super(left, top, right, bottom);
        }
    }
}
