### Android线程和线程池
---
- 转自 [https://blog.csdn.net/wzhworld/article/details/78337554](https://blog.csdn.net/wzhworld/article/details/78337554)
- 线程与服务的区别 <br>
如果你的 Thread 需要不停地隔一段时间就要连接服务器做某种同步的话，该 Thread 需要在 Activity 没有start的时候也在运行。<br>
这个时候当你 start 一个 Activity 就没有办法在该 Activity 里面控制之前创建的 Thread。<br>
因此你便需要创建并启动一个 Service ，在 Service 里面创建、运行并控制该 Thread，这样便解决了该问题（因为任何 Activity 都可以控制同一 Service，
而系统也只会创建一个对应 Service 的实例）。
- AsyncTask <br>
     采用Thread与Handler，但不适合特别耗时的任务。<br>
     四个主要方法：onPreExecute()、doInBackground()、onProgressUpdate()、onPostExecute()、onCancelled()被调用时onPostExecute不会调用 <br>
     从execute()开始会先进行onPreExecute，AsyncTask是通过两个线程池、一个Handler进行操作。
     串行线程池通过ArrayDeque进行线程任务排队，另一个线程池进行任务的执行doInBackground。
     Handler主要是进行onProgressUpdate、onPostExecute（会有进行取消的判断，如果取消了，就不会进行onPostExecute的操作）<br>
     Handler是一个静态的对象，能够将执行环境切换到主线程中，静态成员在进行加在类的时候会初始化，因此需要将AsyncTask在主线程中加载。<br>
     串行：Android4.1之后是默认串行，如果想要并行的话，需要一个、executeOnExecutor方法进行并行的处理<br>
- HandlerThread <br>
     采用Handler与Thread的结合，主要是对于Handler机制的理解。<br>
- IntentService <br>
     基于HandlerThread进行操作，通过onStart里面的Handler发送消息Intent，即启动Intent服务时的Intent，
     由于Handler是按顺序进行操作的，所以进行耗时操作的时候也是按照顺序的，onStopself（startId）来判断是否已经运行完，否则等待所有服务进行完才结束。<br>
- 线程池 <br>
     线程池可以重用线程，避免因为线程的重用、销毁所带来的性能开销 <br>
     能控制线程池的最大并发数，避免资源争夺导致的阻塞 <br>
     ThreadPoolExecutor：核心线程数、最大线程数、超时时间、<br>
- 线程池分类 <br>
      FiexedThreadPool：只有核心线程 <br>
      CachedThreadPool：只有非核心线程 <br>
      ScheduledThreadPool：核心线程固定，非核心没有限制，非核心闲置时会被立即回收 <br>
      SingleThreadPool：只有一个核心线程，确保顺序执行（这个在面试深信服的时候，面试过问过A、B、C如何保证顺序执行） <br>
