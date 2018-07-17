package com.ishiqing.modules.ui_hencoder.lesson;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义绘制 demo01
 * <p>
 * 1.在 Android 里，每个 View 都有一个自己的坐标系，彼此之间是不影响的。
 * 这个坐标系的原点是 View 左上角的那个点；水平方向是 x 轴，右正左负；竖直方向是 y 轴，
 * 下正上负（注意，是下正上负，不是上正下负，和上学时候学的坐标系方向不一样）。
 * <p>
 * 2.
 * <p>
 * Created by javakam on 2018/5/19.
 */
public class MyDrawView extends View {
    private Paint paint = new Paint();

    public MyDrawView(Context context) {
        this(context, null);
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyDrawView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        initPaint();
    }

    private void initPaint() {
        // Style 修改为画线模式
        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(20f);
        paint.setStrokeWidth(5f);
        paint.setColor(Color.YELLOW);
        //开启抗锯齿来让图形和文字的边缘更加平滑
        //抗锯齿的原理是：修改图形边缘处的像素颜色，从而让图形在肉眼看来具有更加平滑的感觉。一图胜千言，上图：
        //Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //or
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureSpec(widthMeasureSpec), measureSpec(heightMeasureSpec));
    }

    private int measureSpec(int measureSpec) {
        int result = 0;
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

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Tip 设置颜色 - 三种方式
        // 这类颜色填充方法一般用于在绘制之前设置底色，或者在绘制之后为界面设置半透明蒙版
//        canvas.drawColor(Color.parseColor("#88880000"));
//        canvas.drawRGB(100, 200, 100);
        canvas.drawARGB(100, 100, 200, 100);

        //1 绘制一个圆
//        canvas.drawCircle(200, 200, 150, paint);

        //2 绘制矩形 - 也可以直接填写 RectF 或 Rect 对象来绘制矩形
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(100, 150, 300, 300, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawRect(400, 150, 800, 300, paint);

        //3 绘制点
        // Paint.setStrokeCap(cap) 可以设置点的形状，但这个方法并不是专门用来设置点的形状的，而是一个设置线条端点形状的方法。
        // 端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种
//        paint.setStrokeWidth(50);//设置点的大小
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawPoint(50, 50, paint);

//        paint.setStrokeWidth(50);
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawPoint(80, 80, paint);

        //绘制多个点
//        paint.setColor(Color.BLUE);
//        paint.setStrokeWidth(50);
//        paint.setStrokeCap(Paint.Cap.SQUARE);
//        canvas.drawPoints(new float[]{100f, 100f, 200f, 200f, 300f, 300f}, 2, 4, paint);

        //4 绘制椭圆
        /*
        只能绘制横着的或者竖着的椭圆，不能绘制斜的（斜的倒是也可以，但不是直接使用 drawOval()，而是配合几何变换，后面会讲到）。
        left, top, right, bottom 是这个椭圆的左、上、右、下四个边界点的坐标。
         */
//        paint.setStyle(Paint.Style.FILL);
//        canvas.drawOval(150, 150, 400, 300, paint);
//
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawOval(500, 150, 700, 300, paint);

        //5 画线
        //由于直线不是封闭图形，所以 setStyle(style) 对直线没有影响。
//        canvas.drawLine(100, 100, 400, 800, paint);
        /*
        count参数
        可以小于8，比如说取6，效果是只画第一条线没有画第二条线;
        不能大于8，比如9，抛出异常：java.lang.IllegalArgumentException: The lines array must contain 4 elements per line.
         */
//        canvas.drawLines(new float[]{400, 800, 400, 900, 400, 900, 200, 500}, 0, 8, paint);

//        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
//        canvas.drawLines(points, paint);

        //6 画圆角矩形
        //left, top, right, bottom 是四条边的坐标，rx 和 ry 是圆角的横向半径和纵向半径。
//        canvas.drawRoundRect(200, 200, 800, 500, 100, 100, paint);

        //7 绘制弧形或扇形
        /*
        drawArc() 是使用一个椭圆来描述弧形的。left, top, right, bottom 描述的是这个弧形所在的椭圆；
        startAngle 是弧形的起始角度（x 轴的正向，即正右的方向，是 0 度的位置；顺时针为正角度，逆时针为负角度），
        sweepAngle 是弧形划过的角度；
        useCenter 表示是否连接到圆心，如果不连接到圆心，就是弧形，如果连接到圆心，就是扇形。
         */
//        paint.setStyle(Paint.Style.FILL); // 填充模式
//        canvas.drawArc(200, 100, 800, 500, -110, 100, true, paint);// 绘制扇形
//        canvas.drawArc(200, 100, 800, 500, 10, 60, false, paint);// 绘制弧形
//
//        paint.setStyle(Paint.Style.STROKE); // 画线模式
//        canvas.drawArc(200, 100, 800, 500, 180, 60, false, paint); // 绘制不封口的弧形
//        canvas.drawArc(200, 100, 800, 500, 90, 80, true, paint);// 封口弧形

        //以上就是 Canvas 所有的简单图形的绘制.除了简单图形的绘制， Canvas 还可以使用 drawPath(Path path) 来绘制自定义图形。

        //8 绘制自定义图形
        //com.jooy.sqdraw.lesson.MyPathView


        //9
         /*
        除此之外，Canvas 还可以绘制 Bitmap 和文字。
         */

        /*
        画Bitmap
        drawBitmap(Bitmap bitmap, float left, float top, Paint paint)
        绘制 Bitmap 对象，也就是把这个 Bitmap 中的像素内容贴过来。其中 left 和 top 是要把 bitmap 绘制到的位置坐标。它的使用非常简单。
         */
//        Bitmap bitmap = Bitmap.createBitmap(200, 300, Bitmap.Config.ARGB_8888);
//        bitmap.eraseColor(Color.parseColor("#FF0000"));//填充颜色
//        canvas.drawBitmap(bitmap, 100, 100, paint);

        //drawBitmap 还有一个兄弟方法 drawBitmapMesh()，可以绘制具有网格拉伸效果的 Bitmap。

        /*
        绘制文字
        drawText(String text, float x, float y, Paint paint)
        界面里所有的显示内容，都是绘制出来的，包括文字。
        drawText() 这个方法就是用来绘制文字的。
        参数  text 是用来绘制的字符串，x 和 y 是绘制的起点坐标。

        通过 Paint.setTextSize(textSize)，可以设置文字的大小。
         */
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(0f);
        String text = "Hello HenCoder";
        paint.setTextSize(18);
        canvas.drawText(text, 100, 25, paint);
        paint.setTextSize(36);
        canvas.drawText(text, 100, 70, paint);
        paint.setTextSize(60);
        canvas.drawText(text, 100, 145, paint);
        paint.setTextSize(84);
        canvas.drawText(text, 100, 240, paint);

        //设置文字的位置和尺寸，这些只是绘制文字最基本的操作。
        //文字的绘制具有极高的定制性，不过由于它的定制性实在太高了，所以我会在后面专门用一期来讲文字的绘制。这一期就不多讲了。
    }


}
