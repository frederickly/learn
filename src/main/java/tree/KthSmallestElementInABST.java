package tree;

import java.util.Stack;

/**
 *https://www.lintcode.com/problem/kth-smallest-element-in-a-bst/description
 902. Kth Smallest Element in a BST
 中文
 English

 Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 Example

 Example 1:

 Input：{1,#,2},2
 Output：2
 Explanation：
 1
 \
 2
 The second smallest element is 2.

 Example 2:

 Input：{2,1,3},1
 Output：1
 Explanation：
 2
 / \
 1   3
 The first smallest element is 1.

 Challenge

 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 Notice

 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 {1,#,2}
 2

 */
public class KthSmallestElementInABST {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        int count =0;
        int res=-1;
        stack.push(root);
        while(root.left!=null){
            root= root.left;
            stack.push(root);
        }
        while(!stack.empty()){
            root = stack.pop();
            count++;
            if(count==k){
                res= root.val;
                break;
            }

            if(root.right!=null) {
                stack.push(root.right);
                root=root.right;
                while(root.left!=null){
                    root= root.left;
                    stack.push(root);
                }
            }
        }
        return res;
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
