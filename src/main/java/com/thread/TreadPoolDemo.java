package com.thread;

import org.junit.Test;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TreadPoolDemo {
    int i =0;
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
    }
    @Test
    public void test() throws InterruptedException {
       new Thread(()->{
           add();
       }).start();
       new Thread(()->{
           add();
       }).start();

       Thread.sleep(5000);
        System.out.println(i);
    }
    public  void add(){
        for (int j = 0; j <1000000 ; j++) {
            i++;
        }
    }

    @Test
    public void test1(){
        for (int j = 0; j < 16; j++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
        }
    }

}
