package algorithms.tree;

/**
 * 1359. Convert Sorted Array to Binary Search Tree
 * https://www.lintcode.com/problem/convert-sorted-array-to-binary-search-tree/description
 *
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *
 * For this problem, a height-balanced binary algorithms.tree is defined as a binary algorithms.tree in which the depth of the two subtrees of every node never differ by more than 1.
 * Example
 *
 * Example 1:
 *
 * Input: [-10,-3,0,5,9]
 * Output: [0,-3,9,-10,#,5]
 * Explanation:
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * Example 2:
 *
 * Input: [1,3]
 * Output: [3,1]
 * Explanation:
 * One possible answer is: [3,1], which represents the following height balanced BST:
 *   3
 *  /
 * 1
 */
public class ConvertSortedArrayToBinarySearchTree {
    /**
     * @param nums: the sorted array
     * @return: the root of the algorithms.tree
     */
    public TreeNode convertSortedArraytoBinarySearchTree(int[] nums) {
        if(nums==null) return null;
        return convertSortedArraytoBinarySearchTree(nums, 0, nums.length-1);
    }

    private TreeNode convertSortedArraytoBinarySearchTree(int[] nums, int start, int end) {
        if(start> end) return null;
        int mid = (start+ end)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = convertSortedArraytoBinarySearchTree(nums, start, mid-1);
        node.right = convertSortedArraytoBinarySearchTree(nums, mid +1, end);
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
