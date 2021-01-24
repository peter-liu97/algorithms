package com.leetcode;

import org.junit.Test;

import java.util.HashSet;

public class Solution26 {
    public int removeDuplicates2(int[] nums) {
        int len = nums.length;
        if(len == 0 ||len == 1 ){
            return len;
        }
        int a = 0;
        int[] temp = new int[len];
        for (int i = 0; i < nums.length; i++) {
            if(!contain(temp,nums[i])){
                temp [a++] = nums[i];
            }
        }
        for (int i = a; i < len ; i++) {
            temp[i] = nums[i];
        }
        nums = temp;

        return a;
    }
    public boolean contain(int[] nums , int num){
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == num){
                return true;
            }
        }
        return false;
    }


    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }





    private int[] addLen(int[] nums,int num){
        int len = nums.length;

        for (int i = 0; i < len; i++) {
            if(num == nums[i]){
                return nums;
            }
        }
        int[] newNums =  new int[len+1];
        for (int i = 0; i < len + 1; i++) {
            newNums[i] = nums[i];
        }
        newNums[len] = num;
        return newNums;
    }

    @Test
    public void test(){
        int [] nums = {1,1,1,1,2,2,3,4};
        Solution26 solution26 = new Solution26();
        solution26.removeDuplicates(nums);
    }
}
