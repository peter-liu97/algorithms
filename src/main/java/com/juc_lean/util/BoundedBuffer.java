package com.juc_lean.util;

public class BoundedBuffer<V> extends BaseBoundedBuffer<V> {
    protected BoundedBuffer(int capacity) {
        super(capacity);
    }

    public synchronized void put(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        boolean wasFull = isFull();
        doPut(v);
        if (wasFull) {
            notifyAll();
        }
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        boolean wasEmpty = isEmpty();
        V v = doTake();
        if (wasEmpty) {
            notifyAll();
        }
        return v;
    }
}
