package com.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class Solution_Fib {

    Map<Integer, Integer> map = new HashMap<>();

    public int fib(int n) {
        return fib(n, map);
    }

    public int fib(int n, Map<Integer, Integer> map) {

        if (map.containsKey(n)) {
            return map.get(n);
        }

        if (n <= 0) {
            return 0;
        }


        if (n == 1) {
            return 1;
        }

        int first = fib(n - 1);
        int last = fib(n - 2);
        int result  = first + last;
        map.put(n ,result);
        return result;
    }


    @Test
    public void test() {
        Solution_Fib solution_fib = new Solution_Fib();
        int fib = solution_fib.fib(8);
        System.out.println(fib);
    }
}
