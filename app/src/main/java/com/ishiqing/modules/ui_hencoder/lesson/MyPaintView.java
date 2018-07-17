package com.ishiqing.modules.ui_hencoder.lesson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.ishiqing.R;
import com.ishiqing.modules.ui_hencoder.practice.BaseView;

/**
 * HenCoder Android 开发进阶: 自定义 View 1-2 Paint 详解
 * <p>
 * http://hencoder.com/ui-1-2/
 * <p>
 * Created by javakam on 2018/5/21.
 */
public class MyPaintView extends BaseView {
    private Shader shader;
    private float mCenterX, mCenterY;

    public MyPaintView(Context context) {
        this(context, null);
    }

    public MyPaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyPaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
         /*
         一、颜色
         Paint 设置颜色的方法有两种：一种是直接用 Paint.setColor/ARGB() 来设置颜色，另一种是使用 Shader 来指定着色方案
         */
        //1 基本颜色
        //setColor/ARGB() & setShader()
//        basicColors(canvas);
        /*
        除了使用 setColor/ARGB() 和 setShader() 来设置 基本颜色， Paint 还可以来设置 ColorFilter，来对颜色进行第二层处理。
         */
        //2 setColorFilter(ColorFilter colorFilter) -- 对每个像素都进行过滤后再绘制出来
//        colorFilter(canvas);

        /*
        Paint 最后一层处理颜色的方法是 setXfermode(Xfermode xfermode) ，它处理的是「当颜色遇上 View」的问题。
         */
        //3 setXfermode(Xfermode xfermode)
        /*
        通俗地说，其实就是要你以绘制的内容作为源图像，以 View 中已有的内容作为目标图像，
        选取一个  PorterDuff.Mode 作为绘制内容的颜色处理方案。
         */
//        transferMode(canvas);
        /*
        Xfermode 注意事项
            Xfermode 使用很简单，不过有两点需要注意：

            1. 使用离屏缓冲（Off-screen Buffer）
         使用离屏缓冲有两种方式：
                Canvas.saveLayer()
                      saveLayer() -- 可以做短时的离屏缓冲。
                      使用方法很简单，在绘制代码的前后各加一行代码，在绘制之前保存，绘制之后恢复：
                      int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

                      canvas.drawBitmap(rectBitmap, 0, 0, paint); // 画方
                      paint.setXfermode(xfermode); // 设置 Xfermode
                      canvas.drawBitmap(circleBitmap, 0, 0, paint); // 画圆
                      paint.setXfermode(null); // 用完及时清除 Xfermode

                      canvas.restoreToCount(saved);
               View.setLayerType() --
                  View.setLayerType() 是直接把整个 View 都绘制在离屏缓冲中。
                  setLayerType(LAYER_TYPE_HARDWARE) 是使用 GPU 来缓冲， setLayerType(LAYER_TYPE_SOFTWARE) 是直接直接用一个 Bitmap 来缓冲。

            2. 控制好透明区域
            使用 Xfermode 来绘制的内容，除了注意使用离屏缓冲，还应该注意控制它的透明区域不要太小，
            要让它足够覆盖到要和它结合绘制的内容，否则得到的结果很可能不是你想要的。

            由于透明区域过小而覆盖不到的地方，将不会受到 Xfermode 的影响。

            到此为止，前面讲的就是 Paint 的第一类 API——关于颜色的三层设置：
                直接设置颜色的 API 用来给图形和文字设置颜色；
                setColorFilter() 用来基于颜色进行过滤处理；
                setXfermode() 用来处理源图像和  View 已有内容的关系。
         */

/*
-----------------------------------------------------------------------------------------------------------------------------------------
 */
        /*
        二、效果
        效果类的 API ，指的就是抗锯齿、填充/轮廓、线条宽度等等这些。
         */
        //setAntiAlias (boolean aa) 设置抗锯齿 ；
        //setStyle(Paint.Style style)
        //线条形状:共有 4 个方法：
        //      setStrokeWidth(float width),
        //      setStrokeCap(Paint.Cap cap)设置线头的形状。线头形状有三种：BUTT 平头、ROUND 圆头、SQUARE 方头。默认为 BUTT。,
        //      setStrokeJoin(Paint.Join join)设置拐角的形状。有三个值可以选择：MITER 尖角、 BEVEL 平角和 ROUND 圆角。默认为 MITER。,
        //      setStrokeMiter(float miter) miter limit 的默认值是 4，对应的是一个大约 29° 的锐角

        //色彩优化: setDither(boolean dither) 和 setFilterBitmap(boolean filter) 。
        //         它们的作用都是让画面颜色变得更加「顺眼」，但原理和使用场景是不同的。
        //setDither(boolean dither)设置图像的抖动。
        //setFilterBitmap(boolean filter) 设置是否使用双线性过滤来绘制 Bitmap 。若为true，放大绘制 Bitmap 的时候就会使用双线性过滤了。

        /*
        Paint 的两个色彩优化的方法：
            setDither(dither) ，设置抖动来优化色彩深度降低时的绘制效果；
            setFilterBitmap(filterBitmap) ，设置双线性过滤来优化 Bitmap 放大绘制的效果。
         */

        //路径效果：setPathEffect(PathEffect effect)
        //使用 PathEffect 来给图形的轮廓设置效果。对 Canvas 所有的图形绘制有效，也就是 drawLine() drawCircle() drawPath() 这些方法。
        /*
         Android 中的 6 种 PathEffect。
         PathEffect 分为两类，
                单一效果的   CornerPathEffect   DiscretePathEffect   DashPathEffect   PathDashPathEffect ，
                和组合效果的 SumPathEffect      ComposePathEffect。
         */


    }

    /**
     * 1 基本颜色
     *
     * @param canvas
     */
    private void basicColors(Canvas canvas) {
        //1.1 setColor(int color)
//        paint.setColor(Color.parseColor("#009688"));
//        canvas.drawRect(30, 30, 230, 180, paint);
//
//        paint.setColor(Color.parseColor("#FF9800"));
//        canvas.drawLine(300, 30, 450, 180, paint);
//
//        paint.setColor(Color.parseColor("#E91E63"));
//        canvas.drawText("HenCoder", 500, 130, paint);

        //1.2 setARGB(int a, int r, int g, int b)
//        paint.setARGB(100, 255, 0, 0);
//        canvas.drawRect(0, 0, 200, 200, paint);
//        paint.setARGB(100, 0, 0, 0);
//        canvas.drawLine(0, 0, 200, 200, paint);

        //1.3 setShader(Shader shader)
        /*
        Shader 这个英文单词很多人没有见过，它的中文叫做「着色器」，也是用于设置绘制颜色的。
        「着色器」不是 Android 独有的，它是图形领域里一个通用的概念，它和直接设置颜色的区别是，着色器设置的是一个颜色方案，或者说是一套着色规则。
        当设置了 Shader 之后，Paint 在绘制图形和文字时就不使用  setColor/ARGB() 设置的颜色了，而是使用 Shader 的方案中的颜色。

        在 Android 的绘制里使用 Shader ，并不直接用 Shader 这个类，而是用它的几个子类。
        具体来讲有  LinearGradient RadialGradient SweepGradient BitmapShader ComposeShader 这么几个。
         */

        //1.3.1 LinearGradient 线性渐变 -- 设置两个点和两种颜色，以这两个点作为端点，使用两种颜色的渐变来绘制颜色。
        /*
        参数：
            x0 y0 x1 y1：渐变的两个端点的位置
            color0 color1 是端点的颜色
            tile：端点范围之外的着色规则，类型是 TileMode -- 平铺模式。TileMode 一共有 3 个值可选： CLAMP, MIRROR 和  REPEAT。
                CLAMP （夹子模式？？？算了这个词我不会翻）会在端点之外延续端点处的颜色；
                MIRROR 是镜像模式；
                REPEAT 是重复模式。
         */
//        shader = new LinearGradient(100, 100, 500, 500, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);

        //无效设置
//        paint.setColor(Color.RED);

//        canvas.drawCircle(300, 300, 200, paint);
//        canvas.drawRect(50, 50, 800, 1600, paint);//很秀 -  配合改动 Shader.TileMode.CLAMP、MIRROR、REPEAT 看效果
        /*
        设置了 Shader 之后，绘制出了渐变颜色的圆。（其他形状以及文字都可以这样设置颜色，我只是没给出图。）
        注意：在设置了 Shader 的情况下， Paint.setColor/ARGB() 所设置的颜色就不再起作用。
         */

        //1.3.2  RadialGradient 辐射渐变 -- 辐射渐变很好理解，就是从中心向周围辐射状的渐变。
        /*
        参数：
            centerX centerY：辐射中心的坐标
            radius：辐射半径
            centerColor：辐射中心的颜色
            edgeColor：辐射边缘的颜色
            tileMode：辐射范围之外的着色模式。
         */
//        shader = new RadialGradient(300, 300, 200, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"), Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//        canvas.drawCircle(300, 300, 200, paint);

        //1.3.3 SweepGradient 扫描渐变
//        shader = new SweepGradient(300, 300, Color.parseColor("#E91E63"),
//                Color.parseColor("#2196F3"));
//        paint.setShader(shader);

//        canvas.drawCircle(300, 300, 200, paint);
        /*
        参数：
            cx cy ：扫描的中心
            color0：扫描的起始颜色
            color1：扫描的终止颜色
         */

        //1.3.4  BitmapShader -- 用Bitmap来着色（终于不是渐变了）。其实也就是用 Bitmap 的像素来作为图形或文字的填充。
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
//        shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//        paint.setShader(shader);
//
//        canvas.drawCircle(300, 300, 300, paint);
        /*
        嗯，看着跟 Canvas.drawBitmap() 好像啊？事实上也是一样的效果。
        如果你想绘制圆形的 Bitmap，就别用 drawBitmap() 了，改用 drawCircle() + BitmapShader 就可以了（其他形状同理）。

        参数：
            bitmap：用来做模板的 Bitmap 对象
            tileX：横向的 TileMode
            tileY：纵向的 TileMode。
         */

        //1.3.5 ComposeShader 混合着色器 -- 所谓混合，就是把两个 Shader 一起使用。
        // 第一个 Shader：头像的 Bitmap
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.batman_logo);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER);
        paint.setShader(shader);

        canvas.drawCircle(300, 300, 150, paint);
        /*
        注意：上面这段代码中我使用了两个 BitmapShader 来作为 ComposeShader() 的参数，
        而  ComposeShader() 在硬件加速下是不支持两个相同类型的 Shader 的，所以这里也需要关闭硬件加速才能看到效果。

        参数：
            shaderA, shaderB：两个相继使用的 Shader
            mode: 两个 Shader 的叠加模式，即 shaderA 和 shaderB 应该怎样共同绘制。它的类型是 PorterDuff.Mode 。
         */

        /*
        PorterDuff.Mode - 波特-达夫

        PorterDuff.Mode 是用来指定两个图像共同绘制时的颜色策略的。它是一个 enum，不同的 Mode 可以指定不同的策略。
        「颜色策略」的意思，就是说把源图像绘制到目标图像处时应该怎样确定二者结合后的颜色，而对于 ComposeShader(shaderA, shaderB, mode) 这个具体的方法，
        就是指应该怎样把 shaderB 绘制在 shaderA 上来得到一个结合后的 Shader。

        没有听说过 PorterDuff.Mode 的人，看到这里很可能依然会一头雾水：「什么怎么结合？就……两个图像一叠加，结合呗？还能怎么结合？」
        你还别说，还真的是有很多种策略来结合。

        最符合直觉的结合策略，就是我在上面这个例子中使用的 Mode: SRC_OVER。
        它的算法非常直观：就像上面图中的那样，把源图像直接铺在目标图像上。不过，除了这种，其实还有一些其他的结合方式。
        例如如果我把上面例子中的参数 mode 改为 PorterDuff.Mode.DST_OUT，就会变成挖空效果。
         */

        /*
        具体来说， PorterDuff.Mode 一共有 17 个，可以分为两类：
               1. Alpha 合成 (Alpha Compositing)
               2. 混合 (Blending)

        第一类，Alpha 合成，其实就是 「PorterDuff」 这个词所指代的算法。
        「PorterDuff」 并不是一个具有实际意义的词组，而是两个人的名字（准确讲是姓）。
        这两个人当年共同发表了一篇论文，描述了 12 种将两个图像共同绘制的操作（即算法）。
        而这篇论文所论述的操作，都是关于 Alpha 通道（也就是我们通俗理解的「透明度」）的计算的，后来人们就把这类计算称为Alpha 合成 ( Alpha Compositing ) 。

        看下效果吧。效果直接盗 Google 的官方文档了。
        https://developer.android.google.cn/reference/android/graphics/PorterDuff.Mode

        第二类，混合，也就是 Photoshop 等制图软件里都有的那些混合模式（multiply  darken lighten 之类的）。
        这一类操作的是颜色本身而不是 Alpha 通道，并不属于 Alpha 合成，所以和 Porter 与 Duff 这两个人也没什么关系，
        不过为了使用的方便，它们同样也被 Google 加进了 PorterDuff.Mode 里。


        结论
            从效果图可以看出，Alpha 合成类的效果都比较直观，
            基本上可以使用简单的口头表达来描述它们的算法（起码对于不透明的源图像和目标图像来说是可以的），
            例如 SRC_OVER 表示「二者都绘制，但要源图像放在目标图像的上面」，DST_IN 表示「只绘制目标图像，并且只绘制它和源图像重合的区域」。

            而混合类的效果就相对抽象一些，只从效果图不太能看得出它们的着色算法，更看不出来它们有什么用。
            不过没关系，你如果拿着这些名词去问你司的设计师，他们八成都能给你说出来个 123。

            所以对于这些 Mode，正确的做法是：对于 Alpha 合成类的操作，掌握他们，
            并在实际开发中灵活运用；而对于混合类的，你只要把它们的名字记住就好了，
            这样当某一天设计师告诉你「我要做这种混合效果」的时候，你可以马上知道自己能不能做，怎么做。

            另外：PorterDuff.Mode 建议你动手用一下试试，对加深理解有帮助。

        巧记Alpha合成模式：
            SRC_ ： 原图像在上面；
            DST_ ： 目标图像在上面

        Alpha合成 12 ：
            CLEAR
            SRC
            DST
            SRC_OVER
            DST_OVER
            SRC_IN
            DST_IN
            SRC_OUT
            DST_OUT
            SRC_ATOP
            DST_ATOP
            XOR
        混合模式 5 ：
            DARKEN
            LIGHTEN
            MULTIPLY
            SCREEN
            ADD --
            OVERLAY
         */
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = (w - getPaddingLeft() - getPaddingRight()) / 2;
        mCenterY = (h - getPaddingTop() - getPaddingBottom()) / 2;
    }
}
