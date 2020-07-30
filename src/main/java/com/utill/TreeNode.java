package com.utill;

public class TreeNode<Key extends Comparable<Key>>{

    Key key;
    TreeNode left;
    TreeNode right;

    public TreeNode(Key key, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public TreeNode(Key key) {
        this.key = key;
    }

    public TreeNode() {
    }
}
