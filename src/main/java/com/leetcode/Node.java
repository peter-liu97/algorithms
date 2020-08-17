package com.leetcode;

public class Node<Key extends Comparable<Key>> {
    Key key;
    Node next;

    public Node() {
    }

    public Node(Key key) {
        this.key = key;
    }

    public Node(Key key, Node next) {
        this.key = key;
        this.next = next;
    }
}
