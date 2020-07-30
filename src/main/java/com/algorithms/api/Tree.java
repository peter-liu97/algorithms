package com.algorithms.api;

import com.utill.TreeNode;

public interface Tree<Key extends Comparable<Key>> {

    /**
     * 插入节点
     * @param treeNode
     */
    void insert(TreeNode treeNode);

    /**
     * 节点个数
     * @return
     */
    int size();

    /**
     * 树的深度
     * @return
     */
    int maxDepth();


}
