package com.leetcode;

public class SwapPairs {
    /**
     *
     * @param head
     * @return
     */
    public Node swapPairs(Node head) {
        if(head == null || head.next == null) return  head;

        Node firstNode = head;
        Node secondNode = head.next;

        firstNode.next = swapPairs(secondNode.next);
        secondNode.next = firstNode;

        return secondNode;
    }

}
