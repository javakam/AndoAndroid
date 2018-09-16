package com.ishiqing.modules.ui_hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

/**
 * 综合练习
 * 练习内容：使用各种 Canvas.drawXXX() 方法画饼图
 *
 * @author javakam
 * @date 2018-5-21 10:48:47
 */
public class Practice11PieChartView extends BaseView {
    private String[] s = {"选择题", "填空题", "判断题", "简单题", "应用题"};
    private String[] p = {"30%", "10%", "10%", "20%", "30%"};
    private String[] p2 = {"0.3", "0.1", "0.1", "0.2", "0.3"};
    /**
     * 不同题型不同颜色 - 简单处理 ， 实际开发中应将头三条属性相亲成对象
     */
    private int pieColors[] = new int[]{Color.RED, Color.YELLOW, Color.BLUE, Color.GREEN, Color.CYAN};
    private float mTotalWidth, mTotalHeight;
    /**
     * rectFTouch点击到某个扇形时，我们需要这个扇形突出一点，就是把矩形的长宽都扩大一点
     */
    private RectF rectF, rectFTouch;
    /**
     * 绘制区域的半径
     */
    private float mRadius;
    /**
     * 题型
     */
    private List<String> subjectNames;
    /**
     * 百分比
     */
    private List<String> percent;
    /**
     * 百分比转换成角度
     */
    private List<String> percentAngle;
    /**
     * 留白用的笔
     */
    private Paint paintWhite = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 从360°拿出来60°做不同题型的间隔,用这个值60除以题型个数得出的平均值，就是每一个模块多出来的留白大小，让圆盘更好看
     */
    private float distanceAngle = 15;
    /**
     * 起始旋转角度
     */
    private float startAngle = -180;
    /**
     * 画线
     */
    private Paint paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);

    public Practice11PieChartView(Context context) {
        super(context);
        init(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        rectF = new RectF();
        rectFTouch = new RectF();

        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        paintWhite.setColor(Color.LTGRAY);
        paintLine.setColor(Color.BLACK);
        paintLine.setTextSize(16);

        subjectNames = Arrays.asList(s);
        percent = Arrays.asList(p);
        percentAngle = Arrays.asList(p2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先把画布移到屏幕的中间，这样坐标的原点就在屏幕中间了，便于绘制
        canvas.translate(mTotalWidth / 2, mTotalHeight / 2);

        //aveAngle
        int size = subjectNames.size();
        float aveAngle = distanceAngle / size;
        float diffAngle;
        for (int i = 0; i < size; i++) {
            /*
            1 画饼
             */
            //计算百分比，eg: 0.3 * （360-30）=99 → 选择题的饼大小
            diffAngle = Float.valueOf(percentAngle.get(i)) * (360 - distanceAngle);
            //给饼换成不同颜色
            paint.setColor(pieColors[i]);
            //画饼
            canvas.drawArc(rectF, startAngle, diffAngle, true, paint);
            //画饼的留白
            canvas.drawArc(rectF, startAngle + diffAngle, aveAngle, true, paintWhite);
            /*
            2 画线 - 每个饼从中点拉出一条线
             */
            //先画斜线 - 数学参考《弧度角度的转换》：https://blog.csdn.net/hongxiali/article/details/5355954
            float lineAngle = diffAngle / 2;
            float pxs = (float) (mRadius * Math.cos(Math.toRadians(startAngle + lineAngle)));
            float pys = (float) (mRadius * Math.sin(Math.toRadians(startAngle + lineAngle)));
            float pxt = (float) ((mRadius + 30) * Math.cos(Math.toRadians(startAngle + lineAngle)));
            float pyt = (float) ((mRadius + 30) * Math.sin(Math.toRadians(startAngle + lineAngle)));
            canvas.drawLine(pxs, pys, pxt, pyt, paintLine);
            Log.w("123", " 坐标： " + pxs + " $    " + pys + " $    " + pxt + " $    " + pyt);
            //再画直线 & 文字
            //pxt > 0 第一、四象限 ； pxt < 0 第二、三象限
            String textStr = subjectNames.get(i);
            float textBounds = getTextBounds(textStr);
            if (pxt > 0) {
                //向右方向画线
                canvas.drawLine(pxt, pyt, pxt + 30, pyt, paintLine);
                canvas.drawText(textStr, pxt + 30, pyt, paintLine);
            } else {
                canvas.drawLine(pxt, pyt, pxt - 30, pyt, paintLine);
                canvas.drawText(textStr, pxt - 30 - textBounds, pyt, paintLine);
            }
            //每画完一个饼，重新设置起始角度
            startAngle = startAngle + diffAngle + aveAngle;
        }
        //向X、Y轴的正方向偏移
//        canvas.translate(30, 30);
    }

    private int getTextBounds(String s) {
        Rect rect = new Rect();
        paintLine.getTextBounds(s, 0, s.length(), rect);
        return rect.width();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTotalWidth = w - getPaddingLeft() - getPaddingRight();
        mTotalHeight = h - getPaddingTop() - getPaddingBottom();

        //确定弧形所在的椭圆 - 圆是特殊的椭圆
//        rectF = new RectF(100, 100, 400, 400);
        mRadius = (float) (Math.min(mTotalWidth, mTotalHeight) / 2 * 0.6);

        rectF.left = -mRadius;
        rectF.top = -mRadius;
        rectF.right = mRadius;
        rectF.bottom = mRadius;

        rectFTouch.left = -mRadius - 16;
        rectFTouch.top = -mRadius - 16;
        rectFTouch.right = mRadius + 16;
        rectFTouch.bottom = mRadius + 16;
    }

    private class PieArea {
        public PieArea(float startAngle, float sweepAngle) {

        }
    }
}
