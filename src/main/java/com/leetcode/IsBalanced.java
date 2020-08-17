package com.leetcode;

import java.util.Map;

/**
 * leetcood 110
 * <p>
 * 是否是高度平衡的二叉树
 */
public class IsBalanced {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        /**
         * 对比每个节点的左右深度差是否小于等於1
         */
        return Math.abs(depth(root.left)-depth(root.right))<1 && isBalanced(root.left) && isBalanced(root.right);

    }

    /**
     * 计算节点的深度
     * @param root
     * @return
     */
    private int depth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

}
