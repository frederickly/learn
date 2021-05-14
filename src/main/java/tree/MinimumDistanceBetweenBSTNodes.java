package tree;

import java.util.Stack;

/**
 * 1746. Minimum Distance Between BST Nodes
 * https://www.lintcode.com/problem/minimum-distance-between-bst-nodes/description
 *
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any two different nodes in the tree.
 * Example
 *
 * Example 1:
 *
 * Input: root = {2,1}
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree {2,1} is represented by the following diagram:
 *
 *       2
 *      /
 *     1
 *
 * while the minimum difference in this tree is 1, it occurs between node 1 and node 2, also between node 3 and node 2.
 *
 * Example 2:
 *
 * Input: root = {4,2,6,1,3}
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * while the minimum difference in this tree is 1.
 *
 * Notice
 *
 *     The size of the BST will be between 2 and 100.
 *     The BST is always valid, each node's value is an integer, and each node's value is different.
 *
 * {4,2,6,1,3}
 */
public class MinimumDistanceBetweenBSTNodes {
    /**
     * @param root:  a Binary Search Tree (BST) with the root node
     * @return: the minimum difference
     *
     * 解法一、inorder traversal:
     * 设置一个TreeNode的全局变量去记录prev node
     * 处理当前结点时，若prev不是null则计算minDiff，否则将当前root附给prev再向右遍历
     */
    public int minDiffInBST(TreeNode root) {
        Stack<TreeNode> stack= new Stack<>();
        while(root!=null){
            stack.push(root);
            root=root.left;
        }
        int diff = Integer.MAX_VALUE;
        int pre =-1;
        TreeNode cur;
        while(!stack.empty()){
            cur= stack.pop();
            if(pre !=-1) {
                diff= Math.min(diff,  cur.val - pre);
            }
            pre = cur.val;

            if(cur.right!=null){
                cur = cur.right;
                while(cur!=null){
                    stack.push(cur);
                    cur = cur.left;
                }
            }
        }
        return diff;
    }
    /**
     * @param root:  a Binary Search Tree (BST) with the root node
     * @return: the minimum difference
     * Divide and Conquer
     *
     */
    public int minDiffInBST2(TreeNode root) {
        int gapLeft = Integer.MAX_VALUE;
        TreeNode nearest =null;
        if(root.left!=null){
            nearest = root.left;
            while(nearest.right!=null)
                nearest = nearest.right;
            gapLeft = Math.min(root.val- nearest.val, minDiffInBST2(root.left));
        }

        int gapright = Integer.MAX_VALUE;
        if(root.right!=null){
            nearest = root.right;
            while(nearest.left!=null)
                nearest = nearest.left;
            gapright = Math.min(nearest.val-root.val, minDiffInBST2(root.right));
        }

        return Math.min(gapLeft, gapright);
    }

    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }
}
