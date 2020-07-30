package com.algorithms.imp;

import com.algorithms.api.Tree;
import com.utill.TreeNode;

public class BinaryTree implements Tree {

     TreeNode root;
     int size;

    public void insert(TreeNode treeNode) {

        if (root == null){
            root = treeNode;
            return;
        }
        //TODO



    }

    public int size() {
        return 0;
    }

    public int maxDepth() {
        return 0;
    }
}
