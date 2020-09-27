package com.algorithms.test;

import java.math.BigDecimal;

public class Test2 {
    public static void main(String[] args) {
        try {
            String str =null;
            BigDecimal bigDecimal = new BigDecimal(str);
            System.out.println(bigDecimal);
        }catch (NullPointerException e){
            System.out.println(e.hashCode());
        }

    }
}
