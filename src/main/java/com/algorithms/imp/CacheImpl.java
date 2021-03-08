package com.algorithms.imp;

import com.algorithms.api.Cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

abstract class AbstractCache<T extends Node> implements Cache {

    private final Map<String, T> cacheMap = new ConcurrentHashMap<>();

    private long exTime = 1000 * 60 * 30;

    public void setExTime(long exTime) {
        this.exTime = exTime;
    }

    @Override
    public Object get(String key) {
        T node = cacheMap.get(key);
        if (null == node) {
            Object obj = getByDB(key);
            set(key, obj);
            return obj;
        }
        if (node.getTime() + exTime < getTime()) {
            Object obj = getByDB(key);
            set(key, obj);
            return obj;
        }
        return node.getData();
    }

    @Override
    public Object set(String key, Object value) {
        return cacheMap.put(key, (T) new Node(value));
    }

    abstract Object getByDB(String key);
}
