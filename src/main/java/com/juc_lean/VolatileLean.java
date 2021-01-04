package com.juc_lean;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class VolatileLean {
    volatile Product product = new Product(1);
    volatile int a = 0;
    public void change1(){
        this.product.setI(2);
    }
    public void change2(){
        this.product = new Product(3);
    }

    @Test
    public void test1(){
        new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            change2();
        },"thread1").start();

        new Thread(()->{
            while (true){
                System.out.println(product.getI());
            }
        }).start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        final CountDownLatch startGate = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    a = a+1;
                }
                startGate.countDown();
            }).start();
        }

        try {
            startGate.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
