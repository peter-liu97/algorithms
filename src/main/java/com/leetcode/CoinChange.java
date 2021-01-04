package com.leetcode;

import org.junit.Test;

import java.math.BigDecimal;

public class CoinChange {


    public int coinChange(int[] coins, int amount) {
        if (coins.length == 0) {
            return -1;
        }
        int[] coinsNew = sortArray(coins);
        if (amount < coinsNew[0]) {
            return -1;
        }
        int amount1 = amount;
        int result = 0;
        for (int i = coinsNew.length - 1; i > -1 || amount1 <= 0; i--) {
            if (amount1 > coinsNew[i]) {
                int n = amount1 / coinsNew[i];
                amount1 -= coinsNew[i] * n;
                result += n;
                break;
            }
        }

        return result;
    }

    private int[] sortArray(int[] coins) {
        int length = coins.length;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0; j--) {
                if (coins[j] < coins[j - 1]) {
                    int temp = coins[j - 1];
                    coins[j - 1] = coins[j];
                    coins[j] = temp;
                }
            }
        }
        return coins;
    }

    @Test
    public void test() {
        CoinChange coinChange = new CoinChange();
        int[] ints = new int[]{1, 2, 5};
        int count = 11;
        int i = coinChange.coinChange(ints, count);
        System.out.println(i);
    }

    @Test
    public void test2() {

        String contStartDate = "2021-01-06";
        String contEndDate = "2021-04-05";
        int totalNum = 0;
        totalNum = DateUtil.getMonthCountDate(DateUtil.parseStringToDate(contStartDate),
                DateUtil.parseStringToDate(contEndDate));
        if (totalNum > 0) {
            totalNum = totalNum - 1;
            int firstSize = DateUtil.getDaysCountDate1(
                    DateUtil.parseStringToDate(contStartDate),
                    DateUtil.getLastDayOfMonth(DateUtil.parseStringToDate(contStartDate)));
            int firstAllSize = DateUtil.getDaysCountDate1(
                    DateUtil.getMonthFirstDay(DateUtil.parseStringToDate(contStartDate)),
                    DateUtil.getLastDayOfMonth(DateUtil.parseStringToDate(contStartDate)));
            int lastSize = DateUtil.getDaysCountDate1(
                    DateUtil.getMonthFirstDay(DateUtil.parseStringToDate(contEndDate)),
                    DateUtil.parseStringToDate(contEndDate));
            int lastAllSize = DateUtil.getDaysCountDate1(
                    DateUtil.getMonthFirstDay(DateUtil.parseStringToDate(contEndDate)),
                    DateUtil.getLastDayOfMonth(DateUtil.parseStringToDate(contEndDate)));
            BigDecimal beginNum = new BigDecimal(firstSize).divide(new BigDecimal(firstAllSize), 4, BigDecimal.ROUND_HALF_UP);
            BigDecimal endNum = new BigDecimal(lastSize).divide(new BigDecimal(lastAllSize), 4, BigDecimal.ROUND_HALF_UP);

            String money = String.valueOf(new BigDecimal(2250).multiply(new BigDecimal(totalNum).add(beginNum).add(endNum)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        }
    }
}
