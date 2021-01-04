package com.juc_lean;

public interface Computable<A,V> {
    V compute(A arg) throws InterruptedException;
}

