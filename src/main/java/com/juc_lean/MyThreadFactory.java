package com.juc_lean;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {
    private final String poolName;
    @Override
    public Thread newThread(@NotNull Runnable r) {
        return null;
    }

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

}
