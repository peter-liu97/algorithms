package com.leetcode;


import org.junit.Test;

import java.util.*;

/**
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 */
public class GroupAnagrams {

    private final int DEFAULT_LENGTH = 10;

    transient Node<String>[] table;


    public List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> table = new ArrayList<>();
        if (strs.length == 0) {
            return table;
        }
        for (String str : strs) {
            if (table.isEmpty()) {
                List<String> linked = new ArrayList<>();
                linked.add(str);
                table.add(linked);
                continue;
            }
            boolean f = true;
            for (List<String> list : table) {
                if (isEqual(list.get(0), str)) {
                    list.add(str);
                    f = false;
                }
            }
            if (f) {
                List<String> linked = new ArrayList<>();
                linked.add(str);
                table.add(linked);
            }
        }


        return table;
    }

    private boolean isEqual(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        if ("".equals(s1) || "".equals(s2)) {
            return false;
        }
        return sortStr(s1).equals(sortStr(s2));
    }

    private void put(String str) {

    }

    private String sortStr(String str) {
        if ("".equals(str)) {
            return "";
        }
        if (str.length() == 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int length = chars.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (chars[j] < chars[j - 1]) {
                    char temp = chars[j - 1];
                    chars[j - 1] = chars[j];
                    chars[j] = temp;
                }
            }
        }
        return String.copyValueOf(chars);
    }

    @Test
    public void test() {
        GroupAnagrams groupAnagrams = new GroupAnagrams();
        String[] strs = {"nozzle", "punjabi"};
        System.out.println(strs.length);
        List<List<String>> lists = groupAnagrams.groupAnagrams(strs);
        System.out.println(lists);
    }
    @Test
    public void leanHashMap(){
        Map<Integer,String> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(i*3, UUID.randomUUID().toString());
        }
    }
}
