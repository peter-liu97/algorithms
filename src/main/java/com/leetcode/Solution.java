package com.leetcode;

import org.junit.Test;

import java.util.*;

public class Solution {
    /**
     * 235. 二叉搜索树的最近公共祖先
     */

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = getPath(root , p);
        List<TreeNode> qPath = getPath(root , q);
        TreeNode ancestor = null;
        for (int i = 0; i < (pPath.size()<qPath.size()? pPath.size() : qPath.size()); i++) {
            if(pPath.get(i) == qPath.get(i)){
                ancestor = pPath.get(i);
            } else {
                break;
            }
        }
        return ancestor;
    }

    private List<TreeNode> getPath(TreeNode root, TreeNode node) {
        List<TreeNode> path = new ArrayList<>();
        TreeNode treeNode =root;
        while (treeNode!=null && treeNode != node){
            path.add(treeNode);
            if(treeNode.key.compareTo(node.key)<=0){
                treeNode = treeNode.right;
            }
            if(treeNode.key.compareTo(node.key)>0){
                treeNode = treeNode.left;
            }
        }
        return path;
    }


    /**
     * 117. 填充每个节点的下一个右侧节点指针 II
     *
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 初始状态下，所有 next 指针都被设置为 NULL。
     *
     * 来源：力扣（LeetCode）
     */
    public Node connect(Node root) {

        return null;
    }

    @Test
    public void testCompareTo(){
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode("a");
        TreeNode b = new TreeNode("b");
        TreeNode c = new TreeNode("c");
        TreeNode d = new TreeNode("d");
        TreeNode e = new TreeNode("e");
        TreeNode f = new TreeNode("f");
        TreeNode g = new TreeNode("g");
        TreeNode h = new TreeNode("h");
        TreeNode i = new TreeNode("i");
        TreeNode j = new TreeNode("j");
        TreeNode k = new TreeNode("k");
        TreeNode l = new TreeNode("l");
        root.left = b;
        root.right = c;
        b.left=d;
        b.right=e;
        c.left=f;
        c.right=g;
        d.left=h;
        d.right=i;
        e.left=j;
        e.right=k;
        f.left=l;
        inorder(root);
        list.forEach(n->{
            System.out.println(n.key);
        });
    }

    List <TreeNode> list = new ArrayList();
    public void inorder(TreeNode root){
        TreeNode node = root;
        if (node == null){
            return;
        }
        inorder(node.left);
        list.add(node);
        inorder(node.right);
    }

}
