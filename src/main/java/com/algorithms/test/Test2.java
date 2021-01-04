package com.algorithms.test;

import com.algorithms.imp.MyMap;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class Test2 {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap();
        map.put(1,":1");
        Map<Integer,String> myMap = new MyMap();
        myMap.put(1,"1");
        myMap.put(2,"2");
        myMap.put(3,"3");
        myMap.put(4,"4");
        myMap.put(5,"5");
        myMap.put(2,"3");
        myMap.put(1,"1");
        System.out.println(myMap.isEmpty());
        int size = myMap.size();
        System.out.println(myMap.get(1));
        String remove = myMap.remove(4);
        Set<Integer> integers = myMap.keySet();
    }

    @Test
    public void leanHashMap(){
        Map<String ,String> map = new HashMap<>(2);
        for (int i = 0; i < 100; i++) {
            map.put(UUID.randomUUID().toString(),UUID.randomUUID().toString());
        }
    }
}
