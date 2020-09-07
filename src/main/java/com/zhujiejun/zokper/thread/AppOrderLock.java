package com.zhujiejun.zokper.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class AppOrderLock {

    private int flag = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private final Condition conditionC = lock.newCondition();

    public void printA() {
        try {
            lock.lock();
            if (flag != 1) {
                try {
                    conditionA.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("current thread is %s\n", Thread.currentThread().getName());
            flag = 2;
            conditionB.signal();
        } finally {
            lock.unlock();
        }

    }

    public void printB() {
        try {
            lock.lock();
            if (flag != 2) {
                try {
                    conditionB.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("current thread is %s\n", Thread.currentThread().getName());
            flag = 3;
            conditionC.signal();
        } finally {
            lock.unlock();
        }

    }

    public void printC() {
        try {
            lock.lock();
            if (flag != 3) {
                try {
                    conditionC.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("current thread is %s\n\n", Thread.currentThread().getName());
            flag = 1;
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        AppOrderLock order = new AppOrderLock();

        /*Thread threadA = new Thread(order::printA, "thread-A");
        Thread threadB = new Thread(order::printB, "thread-B");
        Thread threadC = new Thread(order::printC, "thread-C");*/

        Thread threadA = new Thread(() -> IntStream.range(0, 5).forEach(i -> order.printA()), "thread-A");
        Thread threadB = new Thread(() -> IntStream.range(0, 5).forEach(i -> order.printB()), "thread-B");
        Thread threadC = new Thread(() -> IntStream.range(0, 5).forEach(i -> order.printC()), "thread-C");

        threadB.start();
        threadA.start();
        threadC.start();
    }
}