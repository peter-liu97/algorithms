package com.juc_lean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 避免死锁
 */
public class AvoidDeathLock {
    private final ReentrantLock lock = new ReentrantLock(true);

    public boolean transferMoney(Account fromAcct,
                                 Account toAcct,
                                 DollarAmount amount,
                                 long timeout,
                                 TimeUnit unit) throws InterruptedException {


//        long nanosToLock =  unit.toNanos(timeout) - estimat


        return false;
    }

    public boolean trySendOnSharedLine(String massage,long timeout,TimeUnit unit) throws InterruptedException {
        if(!lock.tryLock(timeout,unit)){
            return false;
        }
        try {
            return sendOnSharedLine(massage);
        }finally {
            lock.unlock();
        }
    }

    private boolean sendOnSharedLine(String massage) throws InterruptedException {

        lock.lockInterruptibly();
        try {
            return cancellableSendOnSharedline(massage);
        }finally {
            lock.unlock();
        }
    }

    private boolean cancellableSendOnSharedline(String massage) {
        return false;
    }

}
