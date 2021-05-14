package tree;

import java.util.Stack;

/**
 * 649. Binary Tree Upside Down
 * https://www.lintcode.com/problem/binary-tree-upside-down/description
 *
 * Given a binary tree where all the right nodes are either leaf nodes with a sibling (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * Example
 *
 * Example1
 *
 * Input: {1,2,3,4,5}
 * Output: {4,5,2,#,#,3,1}
 * Explanation:
 * The input is
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * and the output is
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 *
 * Example2
 *
 * Input: {1,2,3,4}
 * Output: {4,#,2,3,1}
 * Explanation:
 * The input is
 *     1
 *    / \
 *   2   3
 *  /
 * 4
 * and the output is
 *    4
 *     \
 *      2
 *     / \
 *    3   1
 */
public class BinaryTreeUpsideDown {
    /**
     * @param root: the root of binary tree
     * @return: new root
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root==null) return null;
        if(root.left==null && root.right==null) return root;

        Stack<TreeNode> stack = new Stack<>();
        while(root!=null){
            stack.push(root);
            root= root.left;
        }
        root = stack.pop();
        TreeNode prev= root;
        TreeNode node = null;
        while(!stack.empty()){
            node = stack.pop();

            prev.right= node;
            prev.left= node.right;

            prev= node;
            node.left=null;
            node.right=null;
        }

        return root;
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
