package com.thread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.*;

public class TreadPoolDemo {
    int i = 0;


    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
            20,
            2,
            TimeUnit.MINUTES, new ArrayBlockingQueue<>(200),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    public void test() throws InterruptedException {
        new Thread(() -> {
            add();
        }).start();
        new Thread(() -> {
            add();
        }).start();

        Thread.sleep(5000);
        System.out.println(i);
    }

    public void add() {
        for (int j = 0; j < 1000000; j++) {
            i++;
        }
    }

    @Test
    public void test1() {
        for (int j = 0; j < 16; j++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
        }
    }


    @Test
    public void test3() {
        TreadPoolDemo treadPoolDemo = new TreadPoolDemo();
        try {
            treadPoolDemo.test2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void test2() throws InterruptedException {

        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < 100; j++) {
            list.add(j);
        }
        CountDownLatch latch = new CountDownLatch(list.size());
        List<Integer> list2 = new CopyOnWriteArrayList<>();
        List<Integer> list3 = new CopyOnWriteArrayList<>();
        list.forEach(l -> {
            executor.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName());
                    if (l > 50) {
                        list2.add(l);
                    } else {
                        throw new RuntimeException();
                    }
                } catch (Exception e) {
                    list3.add(l);
                } finally {
                    latch.countDown();
                }

            });
        });

//        list.parallelStream().forEach(l -> {
//            if (l != 50) {
//                System.out.println(Thread.currentThread().getName());
//                list2.add(l);
//            } else {
//                throw new RuntimeException();
//            }
//        });
        latch.await();
        System.out.println(list2.size());
    }

}























