package com.leetcode;

/**
 * Solution1000
 *
 * tatle：合并石头的最低成本
 *
 * 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
 * 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeStones {
    int result = 0;
    boolean isFirst =true;
    public int mergeStones(int[] stones, int K) {
        int N = stones.length;
        if(N==K){
            for (int i = 0; i < N; i++) {
                result+=stones[i];
            }
            return result;
        }
        if(N<K){
            if(!isFirst){
                return -1;
            }else {
                return 0;
            }

        }
        isFirst=false;
        int min=0;
        int index=0;
        for (int i = 0; i <N-K+1 ; i++) {
            int k = 0;
            for (int j = i; j < i+K; j++) {
                k+=stones[j];
            }

            if(i==0){
                min = k;
                continue;
            }
            if(min>k){
                min = k;
                index =i;
            }
        }
        int[] newStones  = new int[N-K+1];

        for (int i = 0; i <index ; i++) {
            newStones[i] = stones[i];
        }
        newStones[index]=min;
        result+=min;
        for (int i = index+K; i <N ; i++) {
            newStones[i-K+1] = stones[i];
        }
       return mergeStones(newStones,K);
    }

    public static void main(String[] args) {
        MergeStones merge = new MergeStones();
        int[] stones ={6,4,4,6};
        int K =2;
        int i = merge.mergeStones(stones, K);
        System.out.println(i);
    }
}
