package com.improve.modules.thread.hencoder;

/**
 * Created by changbao on 2019/9/12 星期四 .
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) {
        new ThreadInterruptDemo().runTest();
    }

    public void runTest() {
        final int num = 1_000_000;
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < num; i++) {
                    if (isInterrupted()) {
                        return;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
//                        e.printStackTrace();
                        return;
                    }
                    System.out.println("num : " + i);
                }
            }
        };
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
