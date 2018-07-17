### Java内存模型
---
- 概念 <br>
共享内存模型指的就是Java内存模型(简称JMM)，JMM决定一个线程对共享变量的写入何时对另一个线程可见。 <br>
从抽象的角度来看，JMM定义了线程和主内存之间的抽象关系：线程之间的共享变量存储在主内存（main memory）中，每个线程都有一个私有的本地内存（local memory），
本地内存中存储了该线程以读/写共享变量的副本。 <br>本地内存是JMM的一个抽象概念，并不真实存在。它涵盖了缓存，写缓冲区，寄存器以及其他的硬件和编译器优化。

- 面试 <br>
面试的时候回答方式，首先要了解好内存模型可以简单说一下 java运行时数据区，然后在此基础之上去回答JMM模型可能更好

- [https://www.jianshu.com/p/7e0833df599b](https://www.jianshu.com/p/7e0833df599b) <br><br>
![](.document_images\java内存模型.png)

- [https://blog.csdn.net/suifeng3051/article/details/52611310](https://blog.csdn.net/suifeng3051/article/details/52611310)
