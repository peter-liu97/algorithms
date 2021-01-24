package com.juc_lean;

import org.junit.Test;

import java.util.concurrent.*;

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

    @Test
    public void test(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        Future<Integer> submit = scheduledExecutorService.submit(new MyCallAble());
        Future<Integer> submit1 = scheduledExecutorService.submit(new MyCallAble());
        Future<Integer> submit2 = scheduledExecutorService.submit(new MyCallAble());
        Future<Integer> submit3 = scheduledExecutorService.submit(new MyCallAble());
        try {
            Integer integer = submit3.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    class MyCallAble implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Thread.sleep(2000);
            return 1;
        }
    }

    @Test
    public void testExecutor(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        if (executorService instanceof ThreadPoolExecutor){
            ((ThreadPoolExecutor)executorService).setCorePoolSize(10);
        }
    }

    @Test
    public void testStringFormat(){
        String str = " aaaaa";
    }
}
