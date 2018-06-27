### Service 服务
com.ishiqing.modules.service
#### startService
> com.ishiqing.modules.service.ServiceFragment1
```
com.ishiqing I/System.out: MyService.onCreate
com.ishiqing I/System.out: MyService.onStartCommand
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:17:59
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:00
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:01
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:02
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:03
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:04
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:05
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:06
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:07
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:08
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:09
com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:10
com.ishiqing I/System.out: MyService.onDestroy
```
#### bindService
> com.ishiqing.modules.service.ServiceFragment2
```
com.ishiqing I/System.out: MyService2.onCreate
com.ishiqing I/System.out: MyService2.onBind
com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:27
com.ishiqing I/System.out: ServiceFragment2.onServiceConnected com.ishiqing.modules.MyService2
com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:29
com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:30
com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:32
com.ishiqing I/System.out: 实时时间： 2018/06/17
com.ishiqing I/System.out: 实时时间： 2018/06/17
com.ishiqing I/System.out: 实时时间： 2018/06/17
com.ishiqing I/System.out: 实时时间： 2018/06/17
com.ishiqing I/System.out: MyService2.onUnbind
com.ishiqing I/System.out: MyService2.onDestroy
```
#### 混合调用(Service生命周期)
> com.ishiqing.modules.service.ServiceFragment3

  1. 一次绑定只能解绑一次，多次解绑（unbindService）会有问题 [stopService没事儿]：
  java.lang.IllegalArgumentException: Service not registered: com.ishiqing.modules.service.ServiceFragment2$1@db7c143
  解决办法：为unbindService加上一个try catch代码块
  2. 既需要服务长期在后台运行（即使Activity销毁了也在运行），
  又需要与服务进行通讯，可以采用混合调用
  3. 混合调用步骤：
  startService -> bindService -> unbindService -> stopService
  4. 混合调用时，如果不解绑服务，是没有办法停止服务的，为了防止直接退出activity时忘记解绑而导致服务停止不了，
  通常的做法是在每一个调用服务的Activity的onDestroy方法中去解绑服务。

##### 混合模式生命周期以及出现的一些问题（友情提示：千万不要意淫，坑很多，强烈建议躬行）

> 执行顺序 ：start → bind → unbind → stop

    com.ishiqing I/System.out: MyService3.onCreate
    com.ishiqing I/System.out: MyService3.onStartCommand
    com.ishiqing I/System.out: MyService3.onBind
    com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    com.ishiqing I/System.out: MyService3.onUnbind
    com.ishiqing I/System.out: MyService3.onDestroy
    
> 执行顺序：

start → bind → stop（没有监听到onDestroy和其他Service周期方法）→ unbind（同时触发 onUnbind和onDestroy）<br>

    com.ishiqing I/System.out: MyService3.onCreate
    com.ishiqing I/System.out: MyService3.onStartCommand
    com.ishiqing I/System.out: MyService3.onBind
    com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    先执行stop没任何输出，再执行unbind同时执行下面两个方法
    com.ishiqing I/System.out: MyService3.onUnbind
    com.ishiqing I/System.out: MyService3.onDestroy

> 执行顺序：

    start → bind → unbind → bind
    → n * unbind（不会执行onUnbind和其他Service周期方法）——（虽然,原因看后面stop）
    → bind①
    → stop（神奇的地方来了！！！此时并不能停止服务！因为①之后没有unbind所以不执行该方法，也不能bindService。
    从而验证了一点：在执行n * unbind时，虽然没有监听到onUnbind和其他Service周期方法，但是还是成功解绑了。）
    【注：下面这俩方法执行谁都可以销毁服务。。。】
    → unbind/stop （onDestroy） → unbind/stop （onDestroy）

> 步骤详解：

**start**<br>
com.ishiqing I/System.out: MyService3.onCreate<br>
com.ishiqing I/System.out: MyService3.onStartCommand<br>
**bind**<br>
com.ishiqing I/System.out: MyService3.onBind<br>
com.ishiqing I/System.out: ServiceFragment3.onServiceConnected<br>
**unbind**<br>
com.ishiqing I/System.out: MyService3.onUnbind<br>

**第一次解绑之后，出现新的情形**<br>
**循环执行bind + unbind组合，
每次bind时候都会触发ServiceConnection的onServiceConnected回调**<br>

    com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    com.ishiqing I/System.out: ServiceFragment3.onServiceConnected

**【两种停止方式】**<br>
- 方式一<br>
**先unbind → 再stop**<br>
**unbind**<br>
没有执行任何周期方法，其实是解绑成功了的<br>
**stop**<br>
com.ishiqing I/System.out: MyService3.onDestroy<br>
- 方式二<br>
**先stop → 再unbind**<br>
同上！

### 总结：
> 混合模式开发Service

    1. onCreate只执行一次，根据这个特性可以在它里面做初始化操作，如开启线程池；
    2. 多次调用startService会执行多次onStartCommand方法；
    3. onBind只会执行一次，每次销毁后可以重新绑定，都会回调onServiceConnected，适合动态操作；
    4. onUnbind只会执行一次，是在第一次onBind成功后执行 unbindService 时触发；
    5. onDestroy 必须要用 unbindService + stopService（调用不分先后）方式才能销毁！
    6. 实际开发中，我们要在适当时候startService开启它（Service），
    并在需要的时候通过bindService（即回调onServiceConnected方法进行通讯）绑定服务，
    在不需要的时候通过unbindService（神TM不回调onServiceDisconnected方法。。。卵子）
    去解除服务，【要注意的是onUnbind只会执行一次（Tip 4），想再次通过unbindService去解绑
    服务是可以的，只不过不会再触发onUnbind了，相当于劫的影分身】，
    最后在适当的时候或是APP退出异或崩溃异常（百度 CrashHandler）时去关闭服务。
    7. 举个例子：
    {@link com.ishiqing.modules.MyService4 } & {@link com.ishiqing.modules.ServiceFragment4}

 > 踩了一天坑，MMP，混合开发时，混合开发时，
 把 {@link #onUnbind} 的返回值设置成true时，bind/unbind才能正常使用（可以多次绑定/解绑了）！

    @Override
     public boolean onUnbind(Intent intent) {
         System.out.println("MyService3.onUnbind");
        // return super.onUnbind(intent);
         return true;
     }
运行结果才会是你要我要大家要内种：

    06-18 20:28:03.720 8454-8454/com.ishiqing I/System.out: MyService3.onCreate
    06-18 20:28:03.721 8454-8454/com.ishiqing I/System.out: MyService3.onStartCommand
    06-18 20:28:05.063 8454-8454/com.ishiqing I/System.out: MyService3.onBind
    06-18 20:28:05.077 8454-8454/com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    06-18 20:28:06.808 8454-8454/com.ishiqing I/System.out: MyService3.onUnbind
    06-18 20:28:08.003 8454-8454/com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    06-18 20:28:09.459 8454-8454/com.ishiqing I/System.out: MyService3.onUnbind
    06-18 20:28:10.897 8454-8454/com.ishiqing I/System.out: ServiceFragment3.onServiceConnected
    06-18 20:28:11.359 8454-8454/com.ishiqing I/System.out: MyService3.onUnbind
    06-18 20:28:12.583 8454-8454/com.ishiqing I/System.out: MyService3.onDestroy



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
