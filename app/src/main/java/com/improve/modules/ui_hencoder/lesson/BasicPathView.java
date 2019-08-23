package com.improve.modules.ui_hencoder.lesson;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * drawPath(Path path, Paint paint) 画自定义图形
 * <p>
 * drawPath(path) 这个方法是通过描述路径的方式来绘制图形的，它的 path 参数就是用来描述图形路径的对象。path 的类型是 Path。
 * <p>
 * Created by javakam on 2018/5/20.
 */
public class BasicPathView extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    /**
     * 初始化 Path 对象
     */
    private Path path = new Path();

    public BasicPathView(Context context) {
        this(context, null);
    }

    public BasicPathView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BasicPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr); initViews(context);
    }

    private void initViews(Context context) {
        initPaint();
    }

    private void initPaint() {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(8f);
        paint.setColor(Color.CYAN);
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
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        /*
        Path 可以描述直线、二次曲线、三次曲线、圆、椭圆、弧形、矩形、圆角矩形。把这些图形结合起来，就可以描述出很多复杂的图形。
         */
        //画心
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
//        path.close();
//        canvas.drawPath(path, paint);//绘制出 path 描述的图形（心形），大功告成

        /*
        Path 有两类方法，一类是直接描述路径的，另一类是辅助的设置或计算。
         */
        //第一类：直接描述路径。 这一类方法还可以细分为两组：添加子图形和画线（直线或曲线）
        pathFirstKind(canvas);
        //第二类：辅助的设置或计算
//        pathSecondKind(canvas);
    }

    /**
     * Path 方法第二类：辅助的设置或计算
     * <p>
     * 这类方法的使用场景比较少，我在这里就不多讲了，只讲其中一个方法：  setFillType(FillType fillType)。
     *
     * @param canvas
     */
    private void pathSecondKind(Canvas canvas) {
        /*
        设置填充方式
        Path.setFillType(Path.FillType ft)

        前面在说 dir 参数的时候提到， Path.setFillType(fillType) 是用来设置图形自相交时的填充算法的。
        FillType取值有四种：
        1 EVEN_ODD              即 even-odd rule （奇偶原则）
        2 WINDING （默认值）      即 non-zero winding rule （非零环绕数原则）
        3 INVERSE_EVEN_ODD
        4 INVERSE_WINDING
        两个带有 INVERSE_ 前缀的，只是前两个的反色版本

        WINDING 是「全填充」，而 EVEN_ODD 是「交叉填充」

        EVEN_ODD 和 WINDING 的原理 - 见作者博客 http://hencoder.com/ui-1-1/
         */

        /*
        图形的方向：
        对于添加子图形类方法（如 Path.addCircle() Path.addRect()）的方向，由方法的 dir 参数来控制，这个在前面已经讲过了；
        而对于画线类的方法（如 Path.lineTo() Path.arcTo()）就更简单了，线的方向就是图形的方向。

        总结；
        图形简单时，使用 drawCircle() drawRect() 等方法来直接绘制；图形复杂时，使用 drawPath() 来绘制自定义图形。
         */
    }

    /**
     * Path 方法第一类：直接描述路径。
     * <p>
     * 这一类方法还可以细分为两组：添加子图形和画线（直线或曲线）
     *
     * @param canvas
     */
    private void pathFirstKind(Canvas canvas) {
        // 第一组： addXxx() —— 添加子图形
        // addCircle(float x, float y, float radius, Direction dir) 添加圆
        // x, y, radius 这三个参数是圆的基本信息，最后一个参数 dir 是画圆的路径的方向。
        /*
        注 - 路径方向有两种：顺时针 (CW clockwise) 和逆时针 (CCW counter-clockwise) 。
        对于普通情况，这个参数填 CW 还是填 CCW 没有影响。
        它只是在需要填充图形 (Paint.Style 为 FILL 或 FILL_AND_STROKE) ，并且图形出现自相交时，用于判断填充范围的。
         */
//        path.addCircle(300, 300, 200, Path.Direction.CW);
//        canvas.drawPath(path, paint);

        //addOval、addRect、addRoundRect、addPath(Path path) 添加另一个 Path
        //可见，如果只画一个圆，没必要用 Path，直接用 drawCircle() 就行了。drawPath() 一般是在绘制组合图形时才会用到的。


        // 第二组：xxxTo() —— 画线（直线或曲线）
        // 这一组和第一组 addXxx() 方法的区别在于，第一组是添加的完整封闭图形（除了 addPath() ），而这一组添加的只是一条线。
        /*
        1.lineTo(float x, float y) / rLineTo(float x, float y) 画直线

        从当前位置向目标位置画一条直线， x 和 y 是目标位置的坐标。
        这两个方法的区别是，lineTo(x, y) 的参数是绝对坐标，而 rLineTo(x, y) 的参数是相对当前位置的相对坐标 （前缀 r 指的就是 relatively 「相对地」)。

        注 - 当前位置：所谓当前位置，即最后一次调用画 Path 的方法的终点位置。初始值为原点 (0, 0)。
         */
//        paint.setStyle(Paint.Style.STROKE);
//        path.lineTo(100, 100); // 由初始原点 (0, 0) 向 (100, 100) 画一条直线
//        path.rLineTo(100, 0);  // 由最后一次Path终点位置 (100, 100) 向正右方 100 像素的位置画一条直线
//        canvas.drawPath(path,paint);

        /*
        2.
        //画二次贝塞尔曲线
        quadTo(float x1, float y1, float x2, float y2)
        rQuadTo(float dx1, float dy1, float dx2, float dy2)

        这条二次贝塞尔曲线的起点就是当前位置，而参数中的 x1, y1 和 x2, y2 则分别是控制点和终点的坐标。
        和 rLineTo(x, y) 同理，rQuadTo(dx1, dy1, dx2, dy2) 的参数也是相对坐标

        贝塞尔曲线：贝塞尔曲线是几何上的一种曲线。它通过起点、控制点和终点来描述一条曲线，主要用于计算机图形学。

        //画三次贝塞尔曲线
        cubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
        rCubicTo(float x1, float y1, float x2, float y2, float x3, float y3)
         */

        /*
        3.moveTo(float x, float y) / rMoveTo(float x, float y) 移动到目标位置
        不论是直线还是贝塞尔曲线，都是以当前位置作为起点，而不能指定起点。
        但你可以通过 moveTo(x, y) 或  rMoveTo() 来改变当前位置，从而间接地设置这些方法的起点。
         */
//        paint.setStyle(Paint.Style.STROKE);
//        path.lineTo(100, 100); // 画斜线
//        path.moveTo(200, 100); // 我移~~
//        path.lineTo(200, 0); // 画竖线
//        canvas.drawPath(path, paint);

        //moveTo(x, y) 虽然不添加图形，但它会设置图形的起点，所以它是非常重要的一个辅助方法。
        //另外，第二组还有两个特殊的方法： arcTo() 和 addArc()。它们也是用来画线的，但并不使用当前位置作为弧线的起点。

        /*
        4.画弧形
        arcTo(RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
        arcTo(float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean forceMoveTo)
        arcTo(RectF oval, float startAngle, float sweepAngle)
        这个方法和 Canvas.drawArc() 比起来，少了一个参数 useCenter，而多了一个参数 forceMoveTo 。

        少了 useCenter ，是因为 arcTo() 只用来画弧形而不画扇形，所以不再需要 useCenter 参数；
        而多出来的这个 forceMoveTo 参数的意思是，绘制是要「抬一下笔移动过去」，还是「直接拖着笔过去」，区别在于是否留下移动的痕迹。
         */
//        path.lineTo(100, 100);
//        path.arcTo(100, 100, 500, 500, -90, 90, true);// 强制移动到弧形起点（无痕迹）
//
//        path.moveTo(200, 200);
//        path.arcTo(100, 800, 500, 1200, -90, 90, false); // 直接连线连到弧形起点（有痕迹）
//        canvas.drawPath(path, paint);

        /*
        addArc(float left, float top, float right, float bottom, float startAngle, float sweepAngle)
        addArc(RectF oval, float startAngle, float sweepAngle)
        又是一个弧形的方法。一个叫 arcTo ，一个叫 addArc()，都是弧形，区别在哪里？其实很简单： addArc() 只是一个直接使用了 forceMoveTo = true 的简化版 arcTo() 。
         */
//        path.lineTo(300, 300);
//        RectF rectF = new RectF(300, 300, 800, 600);
//        path.addArc(rectF, -180, 225);
//        canvas.drawPath(path, paint);

        /*
        5.close() 封闭当前子图形
        它的作用是把当前的子图形封闭，即由当前位置向当前子图形的起点绘制一条直线。
         */
//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(150, 150);
        // 子图形未封闭

//        path.moveTo(100, 100);
//        path.lineTo(200, 100);
//        path.lineTo(150, 150);
//        path.close();
        // 使用 close() 封闭子图形。等价于 path.lineTo(100, 100)

//        canvas.drawPath(path, paint);
        /*
        close() 和 lineTo(起点坐标) 是完全等价的。

        「子图形」：官方文档里叫做 contour 。
        但由于在这个场景下我找不到这个词合适的中文翻译（直译的话叫做「轮廓」），所以我换了个便于中国人理解的词：「子图形」。
        前面说到，第一组方法是「添加子图形」，所谓「子图形」，指的就是一次不间断的连线。一个 Path 可以包含多个子图形。
        当使用第一组方法，即 addCircle() addRect() 等方法的时候，每一次方法调用都是新增了一个独立的子图形；
        而如果使用第二组方法，即 lineTo() arcTo() 等方法的时候，则是每一次断线（即每一次「抬笔」），都标志着一个子图形的结束，以及一个新的子图形的开始。

        另外，不是所有的子图形都需要使用 close() 来封闭。当需要填充图形时（即 Paint.Style 为 FILL 或 FILL_AND_STROKE），Path 会自动封闭子图形。
         */
        paint.setStyle(Paint.Style.FILL);
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(150, 150);
        // 这里只绘制了两条边，但由于 Style 是 FILL ，所以绘制时会自动封口
        canvas.drawPath(path, paint);
    }
}
