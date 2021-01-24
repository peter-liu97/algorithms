package com.juc_lean;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 把执行好的结果放在 BlockingDeque 中
 */
public class ExecutorCompletionServiceLean {
    @Test
    public void test() {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        LinkedBlockingDeque<Future<Integer>> linkedBlockingDeque = new LinkedBlockingDeque<>();
        ExecutorCompletionService<Integer> executorCompletionService = new ExecutorCompletionService(scheduledExecutorService, linkedBlockingDeque);
        executorCompletionService.submit(() -> {
           Thread.sleep(10000);
           return 1;
        });

//            Future<Integer> first = linkedBlockingDeque.getFirst();
//
//            Integer integer1 = first.get();
//            System.out.println(integer1);
//            executorCompletionService.poll();
//        Future<Integer> take = null;
//        try {
//            take = executorCompletionService.take();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Thread.currentThread().interrupt();
//            take.cancel(true);
//        }
//        Integer integer = null;
//        try {
//            integer = take.get();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        System.out.println(integer);
//            System.out.println(linkedBlockingDeque);
        Integer integer= null;
        try {
            Future<Integer> take  =  new FutureTask<>(()->{
                Thread.sleep(111111);
                return 1;
            });
            long endTime = System.nanoTime() + 10;
//             integer = take.get(endTime-System.nanoTime(),TimeUnit.NANOSECONDS);
            integer = take.get();

        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            integer =2;
            e.printStackTrace();
        }
//        catch (TimeoutException e) {
//            integer =3;
//            e.printStackTrace();
//        }
        System.out.println(integer);

    }
}
