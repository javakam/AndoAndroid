### Service 服务
com.improve.modules.service
#### startService
> com.improve.modules.service.ServiceFragment1
```
com.improve I/System.out: MyService.onCreate
com.improve I/System.out: MyService.onStartCommand
com.improve I/System.out: 实时时间： 2018-06-17 12:17:59
com.improve I/System.out: 实时时间： 2018-06-17 12:18:00
com.improve I/System.out: 实时时间： 2018-06-17 12:18:01
com.improve I/System.out: 实时时间： 2018-06-17 12:18:02
com.improve I/System.out: 实时时间： 2018-06-17 12:18:03
com.improve I/System.out: 实时时间： 2018-06-17 12:18:04
com.improve I/System.out: 实时时间： 2018-06-17 12:18:05
com.improve I/System.out: 实时时间： 2018-06-17 12:18:06
com.improve I/System.out: 实时时间： 2018-06-17 12:18:07
com.improve I/System.out: 实时时间： 2018-06-17 12:18:08
com.improve I/System.out: 实时时间： 2018-06-17 12:18:09
com.improve I/System.out: 实时时间： 2018-06-17 12:18:10
com.improve I/System.out: MyService.onDestroy
```
#### bindService
> com.improve.modules.service.ServiceFragment2
```
com.improve I/System.out: MyService2.onCreate
com.improve I/System.out: MyService2.onBind
com.improve I/System.out: 实时时间： 2018-06-17 15:36:27
com.improve I/System.out: ServiceFragment2.onServiceConnected com.improve.modules.MyService2
com.improve I/System.out: 实时时间： 2018-06-17 15:36:29
com.improve I/System.out: 实时时间： 2018-06-17 15:36:30
com.improve I/System.out: 实时时间： 2018-06-17 15:36:32
com.improve I/System.out: 实时时间： 2018/06/17
com.improve I/System.out: 实时时间： 2018/06/17
com.improve I/System.out: 实时时间： 2018/06/17
com.improve I/System.out: 实时时间： 2018/06/17
com.improve I/System.out: MyService2.onUnbind
com.improve I/System.out: MyService2.onDestroy
```
#### 混合调用(Service生命周期)
> com.improve.modules.service.ServiceFragment3

  1. 一次绑定只能解绑一次，多次解绑（unbindService）会有问题 [stopService没事儿]：
  java.lang.IllegalArgumentException: Service not registered: com.improve.modules.service.ServiceFragment2$1@db7c143
  解决办法：为unbindService加上一个try catch代码块
  2. 既需要服务长期在后台运行（即使Activity销毁了也在运行），
  又需要与服务进行通讯，可以采用混合调用
  3. 混合调用步骤：
  startService -> bindService -> unbindService -> stopService
  4. 混合调用时，如果不解绑服务，是没有办法停止服务的，为了防止直接退出activity时忘记解绑而导致服务停止不了，
  通常的做法是在每一个调用服务的Activity的onDestroy方法中去解绑服务。

##### 混合模式生命周期以及出现的一些问题（友情提示：千万不要意淫，坑很多，强烈建议躬行）

> 执行顺序 ：start → bind → unbind → stop

    com.improve I/System.out: MyService3.onCreate
    com.improve I/System.out: MyService3.onStartCommand
    com.improve I/System.out: MyService3.onBind
    com.improve I/System.out: ServiceFragment3.onServiceConnected
    com.improve I/System.out: MyService3.onUnbind
    com.improve I/System.out: MyService3.onDestroy
    
> 执行顺序：

start → bind → stop（没有监听到onDestroy和其他Service周期方法）→ unbind（同时触发 onUnbind和onDestroy）<br>

    com.improve I/System.out: MyService3.onCreate
    com.improve I/System.out: MyService3.onStartCommand
    com.improve I/System.out: MyService3.onBind
    com.improve I/System.out: ServiceFragment3.onServiceConnected
    先执行stop没任何输出，再执行unbind同时执行下面两个方法
    com.improve I/System.out: MyService3.onUnbind
    com.improve I/System.out: MyService3.onDestroy

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
com.improve I/System.out: MyService3.onCreate<br>
com.improve I/System.out: MyService3.onStartCommand<br>
**bind**<br>
com.improve I/System.out: MyService3.onBind<br>
com.improve I/System.out: ServiceFragment3.onServiceConnected<br>
**unbind**<br>
com.improve I/System.out: MyService3.onUnbind<br>

**第一次解绑之后，出现新的情形**<br>
**循环执行bind + unbind组合，
每次bind时候都会触发ServiceConnection的onServiceConnected回调**<br>

    com.improve I/System.out: ServiceFragment3.onServiceConnected
    com.improve I/System.out: ServiceFragment3.onServiceConnected
    com.improve I/System.out: ServiceFragment3.onServiceConnected
    com.improve I/System.out: ServiceFragment3.onServiceConnected

**【两种停止方式】**<br>
- 方式一<br>
**先unbind → 再stop**<br>
**unbind**<br>
没有执行任何周期方法，其实是解绑成功了的<br>
**stop**<br>
com.improve I/System.out: MyService3.onDestroy<br>
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
    {@link com.improve.modules.MyService4 } & {@link com.improve.modules.ServiceFragment4}

 > 踩了一天坑，MMP，混合开发时，混合开发时，
 把 {@link #onUnbind} 的返回值设置成true时，bind/unbind才能正常使用（可以多次绑定/解绑了）！

    @Override
     public boolean onUnbind(Intent intent) {
         System.out.println("MyService3.onUnbind");
        // return super.onUnbind(intent);
         return true;
     }
运行结果才会是你要我要大家要内种：

    06-18 20:28:03.720 8454-8454/com.improve I/System.out: MyService3.onCreate
    06-18 20:28:03.721 8454-8454/com.improve I/System.out: MyService3.onStartCommand
    06-18 20:28:05.063 8454-8454/com.improve I/System.out: MyService3.onBind
    06-18 20:28:05.077 8454-8454/com.improve I/System.out: ServiceFragment3.onServiceConnected
    06-18 20:28:06.808 8454-8454/com.improve I/System.out: MyService3.onUnbind
    06-18 20:28:08.003 8454-8454/com.improve I/System.out: ServiceFragment3.onServiceConnected
    06-18 20:28:09.459 8454-8454/com.improve I/System.out: MyService3.onUnbind
    06-18 20:28:10.897 8454-8454/com.improve I/System.out: ServiceFragment3.onServiceConnected
    06-18 20:28:11.359 8454-8454/com.improve I/System.out: MyService3.onUnbind
    06-18 20:28:12.583 8454-8454/com.improve I/System.out: MyService3.onDestroy



