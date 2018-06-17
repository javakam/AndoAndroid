#### startService
> com.ishiqing.modules.ServiceFragment1
```
06-17 12:17:59.573 17984-17984/com.ishiqing I/System.out: MyService.onCreate
06-17 12:17:59.575 17984-17984/com.ishiqing I/System.out: MyService.onStartCommand
06-17 12:17:59.578 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:17:59
06-17 12:18:00.579 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:00
06-17 12:18:01.580 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:01
06-17 12:18:02.581 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:02
06-17 12:18:03.582 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:03
06-17 12:18:04.583 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:04
06-17 12:18:05.585 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:05
06-17 12:18:06.586 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:06
06-17 12:18:07.587 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:07
06-17 12:18:08.588 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:08
06-17 12:18:09.589 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:09
06-17 12:18:10.590 17984-18133/com.ishiqing I/System.out: 实时时间： 2018-06-17 12:18:10
06-17 12:18:11.048 17984-17984/com.ishiqing I/System.out: MyService.onDestroy
```
#### bindService
> com.ishiqing.modules.ServiceFragment2
```
06-17 15:36:27.595 26543-26543/com.ishiqing I/System.out: MyService2.onCreate
06-17 15:36:27.596 26543-26543/com.ishiqing I/System.out: MyService2.onBind
06-17 15:36:27.599 26543-26589/com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:27
06-17 15:36:27.608 26543-26543/com.ishiqing I/System.out: ServiceFragment2.onServiceConnected com.ishiqing.modules.MyService2
06-17 15:36:29.100 26543-26589/com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:29
06-17 15:36:30.603 26543-26589/com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:30
06-17 15:36:32.106 26543-26589/com.ishiqing I/System.out: 实时时间： 2018-06-17 15:36:32
06-17 15:36:33.607 26543-26589/com.ishiqing I/System.out: 实时时间： 2018/06/17
06-17 15:36:35.115 26543-26589/com.ishiqing I/System.out: 实时时间： 2018/06/17
06-17 15:36:36.617 26543-26589/com.ishiqing I/System.out: 实时时间： 2018/06/17
06-17 15:36:38.118 26543-26589/com.ishiqing I/System.out: 实时时间： 2018/06/17
06-17 15:36:38.619 26543-26543/com.ishiqing I/System.out: MyService2.onUnbind
06-17 15:36:38.619 26543-26543/com.ishiqing I/System.out: MyService2.onDestroy
```
#### 混合调用(Service生命周期)
> com.ishiqing.modules.ServiceFragment3

  1. 一次绑定只能解绑一次，多次解绑（unbindService）会有问题 [stopService没事儿]：
  java.lang.IllegalArgumentException: Service not registered: com.ishiqing.modules.ServiceFragment2$1@db7c143
  解决办法：为unbindService加上一个try catch代码块
  2. 既需要服务长期在后台运行（即使Activity销毁了也在运行），
  又需要与服务进行通讯，可以采用混合调用
  3. 混合调用步骤：
  startService -> bindService -> unbindService -> stopService
  4. 混合调用时，如果不解绑服务，是没有办法停止服务的，为了防止直接退出activity时忘记解绑而导致服务停止不了，
  通常的做法是在每一个调用服务的Activity的onDestroy方法中去解绑服务。