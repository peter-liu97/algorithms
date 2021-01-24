package com.more_thread;

import java.util.concurrent.Semaphore;

public class Foo3 {
    Semaphore first = new Semaphore(1);
    Semaphore second = new Semaphore(0);
    Semaphore third = new Semaphore(0);

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        first.acquire();
        printFirst.run();
        second.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        second.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        third.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        third.acquire();
        printThird.run();
    }
}
