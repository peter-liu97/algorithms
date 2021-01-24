package com.leetcode;

import org.junit.Test;

public class Solution1422 {

    public int maxScore(String s) {

        if(s == null || s.length() == 0){
            return 0;
        }

        char[] chars = s.toCharArray();
        int result = 0;

        for (int i = 1; i < chars.length; i++) {
            int temp = 0;
            for (int j = 0; j < i; j++) {
                if(chars[j] == '0'){
                    temp++;
                }
            }
            for (int j = i; j < chars.length; j++) {
                if(chars[j] == '1'){
                    temp++;
                }
            }
            result = Math.max(temp,result);
        }
        return result;
    }

    @Test
    public void test() {
        String str = "00111";
        Solution1422 solution1422 = new Solution1422();
        int i = solution1422.maxScore(str);
        System.out.println(i);
    }


}
