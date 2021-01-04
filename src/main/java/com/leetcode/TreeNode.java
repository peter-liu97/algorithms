package com.leetcode;

public class TreeNode<Key extends Comparable<Key>>{

     Key key ;
     TreeNode<Key> left ;
     TreeNode<Key> right ;
     TreeNode<Key> next ;

    public TreeNode(Key key, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Key key, TreeNode<Key> left, TreeNode<Key> right, TreeNode<Key> next) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.next = next;
    }

    public TreeNode(Key key) {
        this.key = key;
    }

    public TreeNode() {
    }
}
