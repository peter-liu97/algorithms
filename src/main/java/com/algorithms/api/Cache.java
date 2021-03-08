package com.algorithms.api;

import com.algorithms.imp.Node;

public interface Cache {

    void setExTime(long exTime);

    Object get(String key);

    Object set(String key , Object value);

    default long getTime() {
        return System.nanoTime();
    }
}
