### UI绘制流程
分两部分：一是布局的加载；二是绘制的流程；
#### 1 布局的加载
- Activity是如何加载我们的布局的？ setContentView（xml）；
- 如果想玩好自定义View和自定义控件，UI绘制流程必须要会的！
- WindowManager 中定义了 Window 的类型 {@link WindowManager.LayoutParams#type}；
> setContentView -> getWindow().setContentView() -> 最终调用的是 PhoneWindow 的 setContentView()；
* PhoneWindow 在 setContentView() 首先装载了一个装饰器：installDecor():

setContentView部分代码：
```
 @Override
    public void setContentView(int layoutResID) {
        // 从这里可以看出，installDecor就是用来初始化mContentParent的！
        // This is the view in which the window contents are placed. It is either
        // mDecor itself, or a child of mDecor where the contents go.
        // ViewGroup mContentParent;
        if (mContentParent == null) {
            //1 初始化 DecorView，向 mDecor(DecorView)中添加系统布局，获取其中的帧布局 FrameLayout(id:content)，并做了一些 DecorView的初始设置
            installDecor();//（MS:我们先说第一个核心方法xxx）
        } else if (!hasFeature(FEATURE_CONTENT_TRANSITIONS)) {
            mContentParent.removeAllViews();
        }
        ...
        //2 将我们自己的 布局添加到这个FrameLayout里面！！！
        mLayoutInflater.inflate(layoutResID, mContentParent);
        ...
}
```
installDecor 部分代码 (也有两个核心方法)：
```
private void installDecor() {
        mForceDecorInstall = false;
        if (mDecor == null) {
            mDecor = generateDecor(-1);//new 一个 DecorView
            mDecor.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
            mDecor.setIsRootNamespace(true);
            if (!mInvalidatePanelMenuPosted && mInvalidatePanelMenuFeatures != 0) {
                mDecor.postOnAnimation(mInvalidatePanelMenuRunnable);
            }
        } else {
            mDecor.setWindow(this);
        }
        if (mContentParent == null) {
            mContentParent = generateLayout(mDecor);//返回一个帧布局FrameLayout
        }
        ...
}
```
* installDecor() -> generateDecor(int featureId) 中 :
```
return new DecorView(context, featureId, this, getAttributes());
```
说白了 generateDecor() 就是new了一个 DecorView出来。
* installDecor() -> generateLayout(DecorView decor)
向 mDecor(DecorView)中添加系统布局，获取其中的帧布局 FrameLayout(id:content)，并做了一些 DecorView的初始设置;
generateLayout(DecorView decor)部分代码：
```
// 加载系统内置的布局文件（系统有多个布局，这里只截取这一个用于说明）
// Tip：我们可以再代码中 通过  com.android.internal.R.layout.screen_simple 查看系统内置的xml
...
layoutResource = R.layout.screen_simple;
...
mDecor.startChanging();//开始改变...
mDecor.onResourcesLoaded(mLayoutInflater, layoutResource);

// ID_ANDROID_CONTENT 值为 com.android.internal.R.id.content
ViewGroup contentParent = (ViewGroup)findViewById(ID_ANDROID_CONTENT);//是一个FrameLayout
...
// 一顿蹂躏。。。
mDecor.setWindowBackground(background);
mDecor.setWindowFrame(frame);
mDecor.setElevation(mElevation);
mDecor.setClipToOutline(mClipToOutline);
...
mDecor.finishChanging();//结束改变...内部调用了 invalidate();
return contentParent;
```
总结(淡蓝色部分是我们添加布局的区域)：

<!--![https://images2018.cnblogs.com/blog/803593/201806/803593-20180627112023576-1538255318.png](https://images2018.cnblogs.com/blog/803593/201806/803593-20180627112023576-1538255318.png)
-->

#### 2 绘制的流程

了解绘制流程之前，介绍一下必须知道的相关类之间的关系：
```
public class View implements Drawable.Callback, KeyEvent.Callback,AccessibilityEventSource {}
// 谁实现了它就可以当爸爸
/*Defines the responsibilities for a class that will be a parent of a View.
This is the API that a view sees when it wants to interact with its parent.*/
public interface ViewParent {}

// 提供了管理者应具有的一些方法
// WindowManager、ViewGroup 是 ViewManager 的两个直接从属，一个通过继承的方式，另一个则是通过接口实现。详情往下看。
public interface ViewManager
{
    public void addView(View view, ViewGroup.LayoutParams params);
    public void updateViewLayout(View view, ViewGroup.LayoutParams params);
    public void removeView(View view);
}

// 接口下面的接口，它并不是干活的，它的实现类：WindowManagerImpl
public interface WindowManager extends ViewManager {}

// WindowManagerImpl 是个很小的类，一百多行代码，而且只有四个属性。
// 它是通过组合的方式 找了一些小弟去 干活的（够懒我喜欢）。MS：体现了多态中组合的思想。
public final class WindowManagerImpl implements WindowManager {
    private final WindowManagerGlobal mGlobal = WindowManagerGlobal.getInstance();
    private final Context mContext;
    private final Window mParentWindow;
    private IBinder mDefaultToken;
    ...
}
// WindowManagerGlobal 这个帮手很给力，不止实现了 ViewManager 中定义的方法，
而且还定义了一个很常见的类：WindowLeaked(与WindowManagerGlobal 同在一个.java文件中)：
public final class WindowManagerGlobal {}
final class WindowLeaked extends AndroidRuntimeException {
    public WindowLeaked(String msg) {
        super(msg);
    }
}

// ViewGroup 实现了 ViewParent 说明它是个爹，可以管理儿子；
// 实现了 ViewManger 说明它既是爹又是你的上司，可以聘用你，也可以开除你。
public abstract class ViewGroup extends View implements ViewParent, ViewManager {}

// 猴哥辛苦了
// 核心方法 performTraversals()
public final class ViewRootImpl implements ViewParent,View.AttachInfo.Callbacks, ThreadedRenderer.DrawCallbacks {}

```
> 通过对【1布局的加载】的分析，我们知道在Activity中 setContentView(R.layout.xxx) 最终调用的是 PhoneWindow
的 setContentView(int layoutResID) 里
```
mLayoutInflater.inflate(layoutResID, mContentParent)
```
方法将我们自己的布局渲染上的。
Ok！<br>
其实在 PhoneWindow 中共有三个 setContentView 构造方法：
```
1 setContentView(int layoutResID)
2 @Override
  public void setContentView(View view) {
      // 调用的是第三个构造器
      setContentView(view, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
  }
3 setContentView(View view, ViewGroup.LayoutParams params){
      ...
      mContentParent.addView(view, params);//通过ViewGroup的addView方法添加我们的布局
      ...
  }
```
由此可见，inflate 和 addView 是等效的！因为LayoutInflater.inflate 内部最终调用的就是ViewGroup.addView方法进行渲染的。<br>
> ViewGroup.addView 中的代码:
```
/**
 * Adds a child view with the specified layout parameters.
 *
 * <p><strong>Note:</strong> do not invoke this method from
 * {@link #draw(android.graphics.Canvas)}, {@link #onDraw(android.graphics.Canvas)},
 * {@link #dispatchDraw(android.graphics.Canvas)} or any related method.</p>
 *
 * @param child the child view to add
 * @param index the position at which to add the child or -1 to add last
 * @param params the layout parameters to set on the child
 */
public void addView(View child, int index, LayoutParams params) {
    if (DBG) {
        System.out.println(this + " addView");
    }
    if (child == null) {
        throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
    }
    // addViewInner() will call child.requestLayout() when setting the new LayoutParams
    // therefore, we call requestLayout() on ourselves before, so that the child's request
    // will be blocked at our level
    requestLayout();
    invalidate(true);
    addViewInner(child, index, params, false);
}
```
先从 invalidate(true) 找起，该方法调用的是View -> invalidate(boolean invalidateCache) ：
```
public void invalidate(boolean invalidateCache) {
    invalidateInternal(0, 0, mRight - mLeft, mBottom - mTop, invalidateCache, true);
}
```
invalidateInternal() 指向 ViewParent.invalidateChild(this, damage)，
而ViewParent是个接口，而它的实现类是ViewGroup，也就是说 invalidateChild 调用的是 ViewGroup 中的 invalidateChild 方法 :<br>
ViewGroup -> invalidateChild 核心代码:
```
do(){
    ...
    if (drawAnimation) {
        if (view != null) {
            view.mPrivateFlags |= PFLAG_DRAW_ANIMATION;
        } else if (parent instanceof ViewRootImpl) {
            ((ViewRootImpl) parent).mIsAnimating = true;// 用的是ViewGroup 的实现类 ViewRootImpl
        }
    }
    ...
    // 通过do-while循环找到最终的父容器 DecorView
    // 注意： 这里的parent用的是 ViewRootImpl 中的方法
    parent = parent.invalidateChildInParent(location, dirty);
    ...
} while (parent != null);
```
ViewRootImpl > invalidateChildInParent -> invalidateRectOnScreen -> scheduleTraversals() 中 :
```
 mChoreographer.postCallback(Choreographer.CALLBACK_TRAVERSAL, mTraversalRunnable, null);
```
mTraversalRunnable :
```
final class TraversalRunnable implements Runnable {
    @Override
    public void run() {
        doTraversal();
    }
}
final TraversalRunnable mTraversalRunnable = new TraversalRunnable();
```
doTraversal() ->   performTraversals() : <br>
注意观察 【mView】 这个东西！(mView is a View !)
```
 private void performTraversals() { // 该方法很大，大概 800 行代码
        // 注意 mView 这个东西
        // cache mView since it is used so much below...
        final View host = mView;
        // 遍历的开关
        mIsInTraversal = true;
        ...
        performConfigurationChange(mPendingMergedConfiguration, !mFirst,
                                    INVALID_DISPLAY /* same display */);
        //三个核心方法
        ①performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
        ②performLayout(lp, mWidth, mHeight);
        //
        if(XXX){
          ③performDraw();
        }else{
          scheduleTraversals(); // 重新开始遍历
        }
        ...
        // 遍历关闭
        mIsInTraversal = false;
}
```
这三个核心方法的源码分别为：
```
//测量
private void performMeasure(int childWidthMeasureSpec, int childHeightMeasureSpec) {
    if (mView == null) {
        return;
    }
    Trace.traceBegin(Trace.TRACE_TAG_VIEW, "measure");
    try {
        // mView 执行测量操作
        mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    } finally {
        Trace.traceEnd(Trace.TRACE_TAG_VIEW);
    }
}

//布局
private void performLayout(WindowManager.LayoutParams lp, int desiredWindowWidth,
        int desiredWindowHeight) {
    final View host = mView;
    if (host == null) {return;}
    // mView 布局
    host.layout(0, 0, host.getMeasuredWidth(), host.getMeasuredHeight());
}

// 绘制
private void performDraw() {
    // 调用顺序 performDraw() -> draw(boolean fullRedrawNeeded) -> drawSoftware(...)
}
```
我们看下 drawSoftware(...) 代码：（只看 Canvas 和 mView 就够了）
```
// 如果绘制成功返回true
private boolean drawSoftware(Surface surface, AttachInfo attachInfo, int xoff, int yoff,boolean scalingRequired, Rect dirty) {
    final Canvas canvas;// Draw with software renderer.
    // 1 Canvas 初始化的地方！我们在自定义View时用的Canvas就是在这取出来的
    // final Surface mSurface = new Surface(); //额，new出来的。。。
    canvas = mSurface.lockCanvas(dirty);
    // 一顿小拳拳。。。
    canvas.setDensity(mDensity);// TODO: Do this in native
    canvas.drawColor(0, PorterDuff.Mode.CLEAR);
    canvas.translate(-xoff, -yoff);
    mTranslator.translateCanvas(canvas);
    canvas.setScreenDensity(scalingRequired ? mNoncompatDensity : 0);
    // 2 调用 View.draw(Cancas) 绘制布局
    mView.draw(canvas);
    ...
}
```
>可见，performTraversals() 中：<br>
performMeasure    mView.measure  <br>
performLayout     mView.layout  <br>
performDraw       mView.draw(canvas)   <br>

总结：
    <p>我们在Activity调用的 setContentView(id) 最终是 PhoneWindow 中的
    ```
    mLayoutInflater.inflate(layoutResID, mContentParent);
    ```
    方法，而 LayoutInflater 本质上是调用的 ViewGroup.addView 方法。
    ViewGroup.addView 中核心步骤是 ViewGroup.invalidate(true) ，该方法又去父类
    里面调它的 View -> invalidate(boolean) 方法 ，View 内部做了这样一个判断：(MS:可简单带一句)
    ```
    // Propagate the damage rectangle to the parent view.
    final AttachInfo ai = mAttachInfo;
    final ViewParent p = mParent;
    if (p != null && ai != null && l < r && t < b) {
        final Rect damage = ai.mTmpInvalRect;
        damage.set(l, t, r, b);
        p.invalidateChild(this, damage);// 该方法调用的是ViewParent的实现类 ViewGroup 去处理的
    }
    ```
    说明：如果当前View所在的parent（ViewGroup）不是空，那么把事件传给parent去处理。
    又将引用回传到 ViewGroup -> invalidateChild 方法去做处理，相当于绕了一圈儿又回来了。。。
    ViewGroup 的 invalidateChild(View child, final Rect dirty)  方法中做了一个do-while (parent != null)循环，
    去从你当前 Activity 所属的 ViewGroup 向上找，一直到 DecorView 为止。找到之后，


    </p>