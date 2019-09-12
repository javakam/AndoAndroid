package com.improve.modules.thread.hencoder;

/**
 * Created by changbao on 2019/9/12 星期四 .
 */
public class ThreadWaitDemo {

    public static void main(String[] args) {
        new ThreadWaitDemo().runTest();
    }

    private String sharedString;

    private synchronized void initString() {
        sharedString = "分享内容";
        //notify();
        System.out.println("initString notifyAll ... ");
        notifyAll();
    }

    private synchronized void printString() {
        //标准使用 wait 方式
        while (sharedString == null) {
            try {
                System.out.println("printString wait ... ");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //被唤醒后有限执行此处
        }
        System.out.println("printString awake : " + sharedString);
    }

    public void runTest() {
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printString();
            }
        };
        thread1.start();
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initString();
            }
        };
        thread2.start();
    }

}
