package com.juc_lean;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {
    public static void main(String[] args) {
        Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(100);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("xxxxx");
            }
        };
        Future<?> xxxxx = executorService.submit(runnable);
        try {
            Object o = xxxxx.get();
            System.out.println(o.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {

            e.printStackTrace();
        }
    }
}
