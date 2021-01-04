package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeUtill {

    List<TreeNode> list = new ArrayList();
    /**
     * 前序遍历
     */
    public void inorder(TreeNode root){
       TreeNode node = root;
       if (node == null){
           return;
       }
       list.add(node);
       inorder(node.left);
       inorder(node.right);
    }
    /**
     * 中序遍历
     */
    public void inorder2(TreeNode root){
        TreeNode node = root;
        if (node == null){
            return;
        }
        inorder2(node.left);
        list.add(node);
        inorder2(node.right);
    }
    /**
     * 后续遍历
     */
    public void inorder3(TreeNode root){
        TreeNode node = root;
        if (node == null){
            return;
        }
        inorder3(node.left);
        inorder3(node.right);
        list.add(node);
    }
}
