### AsyncTask缺陷问题

在Android开发中，AsyncTask可以使得用户避免直接使用Thread类和Handler 来处理后台操作，适用于需要异步处理数据并将数据更新到界面上的情况。
AsyncTask适用于后台操作只有几秒的短时操作。但是AsyncTask本身存在很多糟糕的问题，如果使用中不注意，将会影响程序的健壮性。

1、生命周期

       很多开发者会认为一个在Activity中创建的AsyncTask会随着Activity的销毁而销毁。然而事实并非如此。AsyncTask会一直执行, 直到doInBackground()方法执行完毕。然后，如果 cancel(boolean)被调用, 那么onCancelled(Result result) 方法会被执行；否则，执行onPostExecute(Result result) 方法。如果我们的Activity销毁之前，没有取消 AsyncTask，这有可能让我们的AsyncTask崩溃(crash)。因为它想要处理的view已经不存在了。所以，我们总是必须确保在销毁活动之前取消任务。总之，我们使用AsyncTask需要确保AsyncTask正确地取消。

       另外，即使我们正确地调用了cancle() 也未必能真正地取消任务。因为如果在doInBackgroud里有一个不可中断的操作，比如BitmapFactory.decodeStream()，那么这个操作会继续下去。

2、内存泄漏

        如果AsyncTask被声明为Activity的非静态的内部类，那么AsyncTask会保留一个对创建了AsyncTask的Activity的引用。如果Activity已经被销毁，AsyncTask的后台线程还在执行，它将继续在内存里保留这个引用，导致Activity无法被回收，引起内存泄露。

3、结果丢失

       屏幕旋转或Activity在后台被系统杀掉等情况会导致Activity的重新创建，之前运行的AsyncTask会持有一个之前Activity的引用，这个引用已经无效，这时调用onPostExecute()再去更新界面将不再生效。

4、并行还是串行

      在Android 1.6之前的版本，AsyncTask是串行的，在1.6至2.3的版本，改成了并行的。在2.3之后的版本又做了修改，可以支持并行和串行，当想要串行执行时，直接执行execute()方法，如果需要并行执行，则要执行executeOnExecutor(Executor)。