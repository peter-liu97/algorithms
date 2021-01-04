package com.juc_lean;

public class ExpensiveFunction implements Computable<String, Integer> {

    @Override
    public Integer compute(String arg) throws InterruptedException {
        return arg.hashCode();
    }
}
