package com.algorithms.imp;

import com.algorithms.api.MaxPQ;

public class TopM<Key extends Comparable<Key>> implements MaxPQ {

    class Node {
        Comparable key;
        Node next;

        public Node() {
        }

        public Node(Comparable key) {
            this.key = key;
        }
    }

    private int max = Integer.MAX_VALUE;
    private int size = 0;
    Node root;

    public TopM() {
    }

    public TopM(int max) {
        this.max = max;
    }

    public TopM(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    public TopM(Comparable[] a, int max) {
        this.max = max;
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }

    }

    public Comparable max() {
        return root.key;
    }

    public Comparable insert(Comparable comparable) {
        Node newNode = new Node(comparable);
        if (isEmpty()) {
            root = newNode;
            size++;
            return null;
        }
        if (less(root.key, comparable)) {
            newNode.next = root;
            root = newNode;
            size++;
        } else if (root.next == null) {
            root.next = newNode;
            size++;
        } else {
            Node node = root;
            while (node != null) {
                if (null == node.next) {
                    node.next = newNode;
                    size++;
                    break;
                }
                if (less(node.next.key, comparable)) {
                    newNode.next = node.next;
                    node.next = newNode;
                    size++;
                    break;
                }
                node = node.next;
            }
        }
        if (size <= max) {
            return null;
        } else {
            return delMin();
        }


    }


    public Comparable delMax() {
        Node node = root;
        root = root.next;
        return node.key;
    }


    public Comparable delMin() {
        Node node = root;
        if(size==0) return null;
        if (size == 1) {
            root = null;  //for GC
            size--;
            return node.key;
        }
        if (size==2){
            node=root.next;
            root.next=null; //for GC
            return node.key;
        }

        while (node.next.next != null) {
            node = node.next;
        }
        Node minNode = node.next;
        node.next = null; //for GC
        size--;
        return minNode.key;
    }


    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    private boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
