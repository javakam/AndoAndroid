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

![https://images2018.cnblogs.com/blog/803593/201806/803593-20180627112023576-1538255318.png](https://images2018.cnblogs.com/blog/803593/201806/803593-20180627112023576-1538255318.png)

#### 2 绘制的流程
