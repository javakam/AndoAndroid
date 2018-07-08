### Android 网络编程

> 网络编程常用的API

- 网络连接方式：3/4G 、 WIFI等 <br>
- 网络连接状态：ConnectivityManager 、 NetworkInfo  <br>
- 一些常用设置： <br>
1.系统设置网络的Action ：Settings.ACTION_WIRELESS_SETTINGS ；
```
// 跳转到系统内置设置网络的页面
Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
```
2.系统监听网络连接状态的广播接收者：android.net.conn.CONNECTIVITY_CHANGE <br>

#### 下载APK
- 流程分析 <br>
![](.README_images\下载APK流程.png) <br>
- 操作
