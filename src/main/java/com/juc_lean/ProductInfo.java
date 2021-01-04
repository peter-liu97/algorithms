package com.juc_lean;

public class ProductInfo {

    private int anInt ;

    public ProductInfo(int anInt) {
        this.anInt = anInt;
    }

    public static ProductInfo loadProductInfo() {
        return new ProductInfo(1);
    }
}
