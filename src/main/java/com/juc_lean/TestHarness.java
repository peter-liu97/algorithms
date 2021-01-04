package com.juc_lean;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            new Thread(()->{
                try {
                    startGate.await();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    endGate.countDown();
                }

            }).start();
        }
        startGate.countDown();
        long start = System.nanoTime();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }

    @Test
    public void test(){
        TestHarness testHarness = new TestHarness();
        try {
            long l = testHarness.timeTasks(10000, () -> {
                System.out.println(Thread.currentThread().getName());
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
