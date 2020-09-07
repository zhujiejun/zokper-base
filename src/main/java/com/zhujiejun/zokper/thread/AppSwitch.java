package com.zhujiejun.zokper.thread;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class AppSwitch {
    //private static int count = 0;
    private static volatile int count = 0;
    //private static volatile AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {
        IntStream.range(0, 5).forEach(d -> {
            Thread thread = new Thread(() -> {
                /*System.out.printf("cuttent thread is %s\n", Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("current count is %d\n", count++);*/

                /*synchronized (App.class) {
                    System.out.printf("cuttent thread is %s\n", Thread.currentThread().getName());
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("current count is %d\n", count++);
                }*/

                System.out.printf("cuttent thread is %s\n", Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count++;
                //System.out.printf("current count is %d\n", count++);
                //System.out.printf("current count is %d\n", count.getAndIncrement());
            });
            thread.setName(String.format("thread-%d", d));
            thread.start();
        });
        System.out.printf("the last count is %d\n", count);
    }
}
