### View基础
---
#### View坐标
- left、top、right、bottom 是相当父容器的坐标！是一种相对坐标。
- View的宽高和坐标关系：
```
width=right-left;
height=bottom-top;
```
- 获取方式 getLeft、getTop...

> Android3.0开始增加的几个参数：x、y、translateX、translateY
x、y是View左上角坐标，tX、tY是View左上角相对父容器的偏移量，tX、tY默认值为0
换算关系：
```
x = left + translateX;
y = top  + translateY;
```
注：View在平移过程中，top和left表示的是原始左上角的位置信息，其值并不会发生改变，此时发生改变的是x、y、translateX、translateY这四个参数。

- MotionEvent 触屏事件
ActionDown ActionMove ActionUp

- TouchSlop 系统可识别出的最小滑动距离。常量。
获取方式
```
ViewConfiguration.get(context).getScaledTouchSlop();
```

- VelocityTracker 速度追踪器

- GestureDetector 手势检测 <br><br>
![](.doc_images\2dbc6ab4.png)

- Scroller 弹性滑动对象，用于实现View的弹性滑动。
> 优化用户体验，做有过渡效果的滑动。

> Scroller本身无法让View滑动，需要配合 View.computeScroll 方法才能共同完成这个功能。

### View滑动
---
> 实现滑动的三种方式：1.View的scrollTo/scrollBy;2.动画对View施加平移效果;3.改变View的LayoutParams使得View重新布局从而实现滑动。






