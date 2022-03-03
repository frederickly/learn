package algorithms.tree;


import java.util.Stack;

/**
 * Description
 * 中文
 * English
 *
 * Design an iterator over a binary search algorithms.tree with the following rules:
 *
 *     Elements are visited in ascending order (i.e. an in-order traversal)
 *     next() and hasNext() queries run in O(1) time in average.
 *
 * Have you met this question in a real interview?
 * Example
 *
 * Example 1
 *
 * Input:  {10,1,11,#,6,#,12}
 * Output:  [1, 6, 10, 11, 12]
 * Explanation:
 * The BST is look like this:
 *   10
 *   /\
 *  1 11
 *   \  \
 *    6  12
 * You can return the inorder traversal of a BST [1, 6, 10, 11, 12]
 *
 * Example 2
 *
 * Input: {2,1,3}
 * Output: [1,2,3]
 * Explanation:
 * The BST is look like this:
 *   2
 *  / \
 * 1   3
 * You can return the inorder traversal of a BST algorithms.tree [1,2,3]
 *
 * Challenge
 *
 * Extra memory usage O(h), h is the height of the algorithms.tree.
 *
 * Super Star: Extra memory usage O(1)
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Example of iterate a algorithms.tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * }
 */

public class BSTIterator {

    private Stack<TreeNode> stack;
    /*
     * @param root: The root of binary algorithms.tree.
     */public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        while(root!=null) {
            stack.push(root);
            root = root.left;
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        return !stack.empty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        TreeNode node = stack.pop();
        TreeNode temp = node.right;
        while(temp!=null) {
            stack.push(temp);
            temp = temp.left;
        }
        return node;
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
