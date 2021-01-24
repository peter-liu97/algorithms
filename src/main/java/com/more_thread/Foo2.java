package com.more_thread;

import com.juc_lean.A;

import java.util.concurrent.atomic.AtomicInteger;

public class Foo2 {
    private final AtomicInteger atomicInteger1 = new AtomicInteger(0);
    private final AtomicInteger atomicInteger2 = new AtomicInteger(0);

    public Foo2() {
    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        atomicInteger1.addAndGet(1);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (true) {
            if (atomicInteger1.get() == 1) {
                printSecond.run();
                atomicInteger2.addAndGet(1);
                break;
            }
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (true) {
            if (atomicInteger2.get() == 1) {
                printThird.run();
                break;
            }
        }
        // printThird.run() outputs "third". Do not change or remove this line.
    }
}
