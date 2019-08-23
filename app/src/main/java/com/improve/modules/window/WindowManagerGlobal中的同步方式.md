# WindowManagerGlobal中的同步方式的理解

问题如图：<br>
![](images/WMG同步方式.png)

其中private final Object mLock = new Object();<br>
问两种方式的区别？

> 纯属个人见解：

WindowManagerGloba是个单例，在单例中使用同步，<br>
有两种情况：
1. 单例中的静态方法，同步时使用字节码文件；
2. 是一种同步思想吧，当一些方法都去同步mLock，说明这些方法都属于同一情况的处理

<br>

上面那种同步的是jvm中类的字节码文件，下面那种同步的是 类的成员常量 Object，觉得和synchronize（this） 是一样的，只不过同步 Object更灵活

关于反射：<https://blog.csdn.net/javazejian/article/details/70768369>

深入理解Java类型信息(Class对象)与反射机制

深入理解Java枚举类型(enum)

深入理解Java注解类型(@Annotation)

深入理解Java并发之synchronized实现原理

深入理解Java内存模型(JMM)及volatile关键字

深入理解Java类加载器(ClassLoader)