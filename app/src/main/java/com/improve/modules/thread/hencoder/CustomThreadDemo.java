package com.improve.modules.thread.hencoder;

/**
 * Created by changbao on 2019/9/12 星期四 .
 */
public class CustomThreadDemo {

    public static void main(String[] args) {
        CustomThread myThread = new CustomThreadDemo().myThread;
        myThread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.looper.setTask(new Runnable() {
            @Override
            public void run() {
                System.out.println("哈哈哈哈哈哈哈");
            }
        });

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread.looper.quit();
    }

    CustomThread myThread = new CustomThread();

    private static class CustomThread extends Thread {
        Looper looper=new Looper();

        @Override
        public void run() {
            looper.loop();
        }
    }

    private static class Looper {
        private Runnable task;
        private boolean quit;

        public void setTask(Runnable task) {
            this.task = task;
        }

        public void quit() {
            this.quit = true;
        }

        public void loop() {
            while (!quit) {
                synchronized (this) {
                    if (task != null) {
                        task.run();
                        task = null;//只执行一次
                    }
                }
            }
        }
    }
}
