package com.algorithms.imp;

import com.algorithms.api.Tree;

import java.util.*;

public class BinaryTree<Key extends Comparable<Key>> {

     TreeNode root;
     int size = 0;

    public void insert(TreeNode treeNode) {
        if (root == null) {
            root = treeNode;
            size++;
            return;
        }
        TreeNode rootNode = root;
        while (true){
            if (rootNode.key.compareTo(treeNode.key)>0){
                if(rootNode.left==null){
                    rootNode.left = treeNode;
                    size++;
                    break;
                }else {
                    rootNode = rootNode.left;
                }
            }else {
                if(rootNode.right==null){
                    rootNode.right = treeNode;
                    size++;
                    break;
                }else {
                    rootNode = rootNode.right;
                }
            }
        }

    }


    public int size() {
        return this.size;
    }

    /**
     * 树的深度
     * @return
     */

    public int maxDepth() {

        if (root == null){
           return 0;
        }
        return maxDepth(root);

    }

    private int maxDepth(TreeNode node) {
        if(node==null){
            return 0;
        }
        return Math.max(maxDepth(node.left),maxDepth(node.right))+1;
    }


    public static void main(String[] args) {


        System.out.println();
    }


    static class TreeNode<Key extends Comparable<Key>> {

        Key key;
        TreeNode<Key> left;
        TreeNode<Key> right;

        public TreeNode(Key key, TreeNode left, TreeNode right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public TreeNode(Key key) {
            this.key = key;
            this.left=null;
            this.right=null;
        }

        public TreeNode() {
            this.key = null;
            this.left=null;
            this.right=null;
        }
    }
}
