package com.juc_lean;

import org.junit.Test;

import java.io.File;
import java.util.UUID;
import java.util.zip.DataFormatException;


public class Test11 {
    private  static File file ;

    public static void main(String[] args) {
        file  = new File("D:\\juc-lean");
        File[] files = new File[1];
        files[0] = file;
        FileCrawler.startIndexing(files);
    }


    @Test
    public void test(){


        System.out.println("start");
        new Thread(()->{
            while (true){
                System.out.println("111");
                ya();
            }
        }).start();




    }

    public static void ya(){
        try {
            throw new RuntimeException();
        }catch (Exception e){
            Thread.currentThread().interrupt();
        }

    }

    /**
     * 关于位运算符无非也就 与(&)、或(|)、异或(^)、取反(~)、左移(<<)、右移(>>)、无符号右移(>>>)
     */
    @Test
    public void test2(){
        /**
         *  & 都是true(1) 才为true(1)
         */
        System.out.println(3&1);
        /**
         * | 不同为 true(1)|false(0) = true(1) , true(1)|true(1) = true (1) , false(0)|false(0) = false(0)
         * 101 | 010  = 111
         */
        System.out.println(3&2);
    }

    @Test
    public void test1(){
        System.out.println(Runtime.getRuntime().availableProcessors());
    }


    @Test
    public void testPreloader(){
        Preloader preloader = new Preloader();
        preloader.start();
        ProductInfo productInfo = null;
        try {
             productInfo = preloader.get();
        } catch (DataFormatException e) {
            e.printStackTrace();
        }
        System.out.println(productInfo);
    }

    @Test
    public void testSql(){
        for (int i = 0; i < 70; i++) {
            System.out.println(UUID.randomUUID().toString().replaceAll("-",""));
        }
    }

    @Test
    public void test3(){
        int i = 0;
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
        System.out.println(i++);
    }
}
