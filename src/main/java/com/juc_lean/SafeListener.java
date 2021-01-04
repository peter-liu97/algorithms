package com.juc_lean;

import javafx.event.Event;

import java.util.EventListener;

public class SafeListener {

    private final EventListener listener;

    public SafeListener(EventListener listener) {
        this.listener = listener;
    }

    private SafeListener() {
        listener = new EventListener() {

            public void onEvent(Event event) {
                doSomething(event);
            }
        };
    }
    public static SafeListener newInstance(){
        return null;
    }


    private void doSomething(Event event) {


    }
}
