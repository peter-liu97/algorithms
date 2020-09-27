package com.algorithms.api;


/**
 * 优先队列
 * @param <Key>
 */
public interface MaxPQ<Key extends Comparable<Key>> {

    Key max();

    Key insert(Key key);

    Key delMax();

    boolean isEmpty();

    int size();


}
