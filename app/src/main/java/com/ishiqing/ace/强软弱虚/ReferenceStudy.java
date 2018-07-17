package com.ishiqing.ace.强软弱虚;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Java 强软弱虚引用 <br>
 * 源码位于 java.lang.ref 下:<br>
 * Finalizer.class<br>
 * FinalReference.class<br>
 * PhantomReference.class<br>
 * Reference.class<br>
 * ReferenceQueue.class<br>
 * SoftReference.class<br>
 * WeakReference.class
 * <p>
 * {@see https://blog.csdn.net/mazhimazh/article/details/19752475}}
 * <p>
 * Android开发中，用的最多的还是WeakReference。<br>
 * 比如，MVP中P层弱引用V层，Handler、AsyncTask中弱引用Context对象等等。<br>
 * 【重】如果这个对象是偶尔的使用，并且希望在使用时随时就能获取到，但又不想影响此对象的垃圾收集，那么你应该用 Weak Reference 来记住此对象。<br>
 * 【重】当你想引用一个对象，但是这个对象有自己的生命周期，你不想介入这个对象的生命周期，这时候你就是用弱引用。
 * <p>
 *
 * @author javakam
 * @blog https://www.jooy.top
 * @datetime 2018年7月16日 上午9:27:02
 */
public class ReferenceStudy {
    public static void main(String[] args) {
//		strongReference();
//		softReference();
//		weakReference();
//		phantomReference();
//		summary();
    }

    /**
     * 强引用（StrongReference）
     * <p>
     * 强引用是使用最普遍的引用。如果一个对象具有强引用，那垃圾回收器绝不会回收它。
     */
    private static void strongReference() {
        //1方法内部的强引用。
        Object obj = new Object();//强引用
//		System.out.println(obj); //Console: java.lang.Object@70dea4e
        //当方法执行完后会退出方法栈，对引用内容的引用不存在，这个Object会被回收，所以说这个操作是没必要的
//		obj=null; // 帮助垃圾收集器回收此对象
//		System.out.println(obj); //Console: null

        //2但是如果这个o是全局的变量时，就需要在不用这个对象时赋值为null，因为强引用不会被垃圾回收。详见博客内容
        //例如：ArrayList clear 源码
        ArrayList<String> list = new ArrayList<>();
        list.clear();
    }

    /**
     * 软引用（SoftReference）
     * <p>
     * 如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它；如果内存空间不足了，就会回收这些对象的内存。<br>
     * 只要垃圾回收器没有回收它，该对象就可以被程序使用。<br>
     * 软引用可用来实现内存敏感的高速缓存。<br>
     */
    private static void softReference() {
        String str = new String("machangbao"); // 强引用
        SoftReference<String> softRefer = new SoftReference<String>(str); // 软引用
        //当内存不足时，等价于：
//		if (JVM.内存不足()) {
//			str = null;	 	// 转换为软引用
//			System.gc(); 	// 垃圾回收器进行回收
//		}

        // 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，
        // Java虚拟机就会把这个软引用加入到与之关联的引用队列中。
        ReferenceQueue<String> rq = new ReferenceQueue<>();// TODO 2018年7月16日10:11:31 有待提高。。。
    }

    /**
     * 弱引用（WeakReference）
     * <p>
     * 弱引用与软引用的区别在于：只具有弱引用的对象拥有更短暂的生命周期。<br>
     * 在垃圾回收器线程扫描它所管辖的内存区域的过程中，一旦发现了只具有弱引用的对象，不管当前内存空间足够与否，都会回收它的内存。<br>
     * 不过，由于垃圾回收器是一个优先级很低的线程，因此不一定会很快发现那些只具有弱引用的对象。
     * <p>
     * 【重】 如果这个对象是偶尔的使用，并且希望在使用时随时就能获取到，但又不想影响此对象的垃圾收集，那么你应该用 Weak Reference 来记住此对象。
     */
    private static void weakReference() {
        String str = new String("machangbao"); // 强引用
        WeakReference<String> weakRefer = new WeakReference<String>(str);
        // 当垃圾回收器进行扫描回收时等价于：
//		str = null;
//		System.gc();

        //下面的代码会让str再次变为一个强引用：
        //注意，如果这个弱引用对象以及被垃圾收集器clear掉了，返回的是null
        //这个是WeakReference.get()的注释<br>
        /*
         * The object to which this reference refers, or <code>null</code> if this reference
         * object has been cleared
         */
        String name = weakRefer.get();

        /*
         * 弱引用可以和一个引用队列（ReferenceQueue）联合使用
         * 如果弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个弱引用加入到与之关联的引用队列中。
         * 【重】当你想引用一个对象，但是这个对象有自己的生命周期，你不想介入这个对象的生命周期，这时候你就是用弱引用。
         */
        // 这个引用不会在对象的垃圾回收判断中产生任何附加的影响。
        // {@see com.isq.test.ReferenceTest}
    }

    /**
     * 虚引用（PhantomReference）
     * <p>
     * “虚引用”顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。<br>
     * 如果一个对象仅持有虚引用，那么它就和没有任何引用一样， 在任何时候都可能被垃圾回收器回收。<br>
     * 虚引用主要用来跟踪对象被垃圾回收器回收的活动。<br>
     * 【重】虚引用与软引用和弱引用的一个区别在于：虚引用必须和引用队列（ReferenceQueue）联合使用。<br>
     * 当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
     */
    private static void phantomReference() {
    }

    /**
     * 总结：Java强软弱虚四大引用.md<br>
     * Java四种引用的级别由高到低依次为：强引用 > 软引用 > 弱引用 > 虚引用
     */
    private static void summary() {
    }
}
