package com.juc_lean;

import org.junit.Test;

import java.util.Map;

public class TestDeadlock {
    private String lock1 = "LOCK_1";
    private String lock2 = "LOCK_1";
    private Product product1 = new Product(1);
    private Product product2 = product1;



    @Test
    public void testEquas(){
        TestDeadlock testDeadlock = new TestDeadlock();
//        final Product product1 = new Product(1);
//        final Product product2 = new Product(2);


        /**
         *为什么 i1 = i2
         */
        int i1 = System.identityHashCode(product1);
        int i2 = System.identityHashCode(product1);
        System.out.println(product1.hashCode());
        System.out.println(product2.hashCode());
        System.out.println(product1.equals(product2));
    }

    @Test
    public void testDeadlock (){
        TestDeadlock testDeadlock = new TestDeadlock();
        new Thread(()->{
            try {
                testDeadlock.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try {
                testDeadlock.method2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method1() throws InterruptedException {
        synchronized (product1){
            Thread.sleep(1000);
            synchronized (product2){
                System.out.println("lock1 + lock2");
            }
        }
    }
    public void method2() throws InterruptedException {
        synchronized (product2){
            Thread.sleep(1000);
            synchronized (product1){
                System.out.println("lock2 + lock1");
            }
        }
    }
}
