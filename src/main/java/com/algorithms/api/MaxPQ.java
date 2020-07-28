package com.algorithms.api;

public interface MaxPQ<Key extends Comparable<Key>> {

    Key max();

    Key insert(Key key);

    Key delMax();

    boolean isEmpty();

    int size();


}
