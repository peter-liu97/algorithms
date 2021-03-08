package com.algorithms.imp;

public class Node {

    private long time;

    private Object data;

    private long expire = -1;

    public Node( ) {

    }

    public Node(Object data ) {
        this.time = System.nanoTime();
        this.data = data;
    }

    public Node(long time, Object data) {
        this.time = time;
        this.data = data;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
