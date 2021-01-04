package com.algorithms.imp;

import java.util.*;

public class MyMap<K, V> implements Map {
    Node<K, V> root = null ;
    Integer size = 0;

    public MyMap() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Node node = root;
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        Node node = root;
        while (node != null) {
            if (node.value.equals(value)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public Object get(Object key) {
        Node node = root;
        while (node != null) {
            if (node.key.equals(key)) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public Object put(Object key, Object value) {
        Object oldValue = null;
        Node node = root;
        Node nextNode = new Node(key, value, null);
        if (node == null) {
            root = nextNode;
            size ++;
            return null;
        }
        while (true) {
            if (node.next == null) {
                node.next = nextNode;
                size++;
                break;
            }

            if (node.key.equals(key)) {
                oldValue = node.value;
                node.value = value;
                break;
            }
            node = node.next;
        }
        return oldValue;
    }

    @Override
    public Object remove(Object key) {
        Node node = root;
        Object value = null;
        if (node == null) {
            return null;
        }
        if (node.key.equals(key)) {
            value = root.value;
            if (node.next != null) {
                root = node.next;
                size --;
                return value;
            } else {
                root = null;
                size --;
                return value;
            }
        }

        while (node.next != null) {

            if (node.next.key.equals(key)) {
                value = node.next.value;
                node.next = node.next.next;
                size --;
                return value;
            }
            node = node.next;
        }
        //tail
        if (node.key.equals(key)) {
            node = null;
            size --;
        }
        return null;
    }

    @Override
    public void putAll(Map m) {
    }

    @Override
    public void clear() {
        root = null; // for GC
    }

    @Override
    public Set keySet() {
        Set set = null;
        Node node = root;
        while (node != null) {
            if (set == null) {
                set = new HashSet();
            }
            set.add(node.key);
            node = node.next;
        }
        return set;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }

    static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }
}