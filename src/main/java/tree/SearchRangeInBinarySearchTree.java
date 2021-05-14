package tree;

import java.util.*;

public class SearchRangeInBinarySearchTree {

    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here

        LinkedList<TreeNode> que = new LinkedList<>();
        que.push(root);

        List<Integer> result = new ArrayList<>();
        TreeNode node;

        while(!que.isEmpty()){
            node = que.peek();

            if(node.val<k1 ){
               que.pop();
               if (node.right!=null)
                  que.addFirst(node.right);
            }
            if(node.val>k2 ){
                que.pop();
                if(node.left!=null)
                   que.addFirst(node.left);
            }
            if(node.val>=k1 && node.val<=k2) {
                if(node.left== null&& node.right==null){
                    result.add(node.val);
                    que.pop();
                    continue;
                }

                if(node.right!=null){
                    que.add(1, node.right);
                    node.right= null;
                }
                if(node.left!=null) {
                    que.addFirst(node.left);
                    node.right = null;
                }

            }


        }
        return result;
    }

    /**
     * Definition of TreeNode:
     * */
    public class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

}
