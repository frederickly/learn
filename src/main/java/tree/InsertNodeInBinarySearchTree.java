package tree;

import static tree.RemoveNodeInBinarySearchTree.*;

public class InsertNodeInBinarySearchTree {
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // write your code here
        if(root ==null){
            return node;
        }

        TreeNode cur = root;
        while(cur!=null) {

            if(cur.val >= node.val) {
                if(cur.left!=null){
                    cur = cur.left;
                    continue;
                }
                cur.left = node;
                break;
            } else{
                if(cur.right!=null){
                    cur = cur.right;
                    continue;
                }
                cur.right = node;
                break;
            }
        }

        return root;
    }
}
