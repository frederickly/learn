package tree;
/**
 * https://www.lintcode.com/problem/symmetric-tree 1360
Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
        Have you met this question in a real interview?
        Example

        Example1

        Input: {1,2,2,3,4,4,3}
        Output: true
        Explanation:
        1
        / \
        2   2
        / \ / \
        3  4 4  3
        This binary tree {1,2,2,3,4,4,3} is symmetric

        Example2

        Input: {1,2,2,#,3,#,3}
        Output: false
        Explanation:
        1
        / \
        2   2
        \   \
        3    3
        This is not a symmetric tree

*/
public class SymmetricTree {
    /**
     * @param root: root of the given tree
     * @return: whether it is a mirror of itself
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return compare(root.left, root.right);
    }

    public boolean compare(TreeNode left, TreeNode right) {
        if (left == right) return true;
        if (left != right && (left == null || right == null)) return false;

        if (left.val != right.val) return false;

        boolean isSymmetric = compare(left.left, right.right);
        if (isSymmetric)
            isSymmetric = compare(left.right, right.left);
        return isSymmetric;
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
