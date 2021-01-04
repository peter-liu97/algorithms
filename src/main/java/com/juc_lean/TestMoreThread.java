package com.juc_lean;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class TestMoreThread {
    private int i = 0;

    public synchronized void m1() {
        try {
            i++;
            System.out.println("m1" + Thread.currentThread().getName() + i);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public synchronized void m2() {
        try {
            Thread.sleep(10);
            i++;
            System.out.println("m2" + Thread.currentThread().getName() + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public synchronized void m3() {
        try {
            Thread.sleep(10);
            i++;
            System.out.println("m3" + Thread.currentThread().getName() + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test() {
        final CountDownLatch startGate = new CountDownLatch(2);
        new Thread(() -> {
            m1();
            startGate.countDown();
        }, "thread1").start();


        new Thread(() -> {
            m2();
            startGate.countDown();
        }, "thread2").start();

        try {
            startGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
