package com.javase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class ListStudy {
    @Test
    public void test(){
        List list = new ArrayList();
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(2);
        /**
         *  1, capacit有界队列必须指定容量
         *  2，
         */


        System.out.println(Runtime.getRuntime().availableProcessors());



    }

    public static void main(String[] args) {
        String s = "122";
        long l = 111;
        Thread thread = new Thread(()->{
            System.out.println("22222");
        });
        thread.start();

        new Thread(()->{
            System.out.println("111111");
        }).start();

        String str = String.valueOf(l);
    }
}
