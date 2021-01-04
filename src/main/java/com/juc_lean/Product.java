package com.juc_lean;

public class Product {
    private Integer i;

    public Product(Integer i ) {
        this.i = i;
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }
    public Product(Product product ) {
        this.i = product.getI();
    }

}
