package tree;

import java.util.Stack;

/**
 * 1094. Second Minimum Node In a Binary Tree
 *
 * https://www.lintcode.com/problem/second-minimum-node-in-a-binary-tree/description
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative value, where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes, then this node's value is not bigger than its two sub-nodes.
 *
 * Given such a binary tree, you need to output the second minimum value in the set made of all the nodes' value in the whole tree.
 *
 * If no such second minimum value exists, output -1 instead.
 * Example
 *
 * Example 1:
 *
 * Input:
 *     2
 *    / \
 *   2   5
 *      / \
 *     5   7
 *
 * Output: 5
 * Explanation: The smallest value is 2, the second smallest value is 5.
 *
 * Example 2:
 *
 * Input:
 *     2
 *    / \
 *   2   2
 *
 * Output: -1
 * Explanation: The smallest value is 2, but there isn't any second smallest value.
 */
public class SecondMinimumNodeInABinaryTree {
    /**
     * @param root: the root
     * @return: the second minimum value in the set made of all the nodes' value in the whole tree
     */
    public int findSecondMinimumValue(TreeNode root) {
        int min=root.val;
        int sec=-1;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.empty()){
            root = stack.pop();
            if(root.val> min && ( sec< 0 || root.val < sec))
                sec= root.val;

            if(root.left!=null){
                stack.push(root.left);
                stack.push(root.right);
            }
        }
        return sec;
    }
     public class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = this.right = null;
      }
  }
}
