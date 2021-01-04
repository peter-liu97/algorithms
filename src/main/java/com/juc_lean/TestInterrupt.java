package com.juc_lean;

public class TestInterrupt {

    public void a() throws InterruptedException {
        Thread.sleep(1000000);
        System.out.println("a() xinglaile");
    }

    public void b(){
        new Thread(()->{
            try {
                a();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public static void main(String[] args) {
        TestInterrupt testInterrupt = new TestInterrupt();
        testInterrupt.b();
    }
}
