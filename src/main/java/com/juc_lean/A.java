package com.juc_lean;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class A {
    private AtomicReference<Product> ap = new AtomicReference<>();
    private Product product = new Product(1);

    private int i = 1;

    @Test
    public void test() {


        i++;
        new Thread(() -> {
            while (true) {
                ap.set(new Product(i));
            }

        }).start();
        new Thread(() -> {
            while (true) {
                Product product = ap.get();
                System.out.println(product.getI());
            }

        }).start();

    }

    @Test
    public void copy(){
        Product p = product;
        p.setI(2);
        System.out.println(product.getI());
        Product p2 = new Product(product.getI());
        Product p3 = new Product(product);
        System.out.println(product.hashCode());
        System.out.println(p.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p3.hashCode());
    }
}
