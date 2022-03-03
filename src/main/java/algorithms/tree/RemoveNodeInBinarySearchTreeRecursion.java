package algorithms.tree;

import algorithms.tree.RemoveNodeInBinarySearchTree.TreeNode;

public class RemoveNodeInBinarySearchTreeRecursion {
    /*
     * @param root: The root of the binary search algorithms.tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search algorithms.tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        dummy.left = root;
        dummy.left = remove(root, dummy, value);
        return dummy.left;
    }

    private TreeNode remove(TreeNode root, TreeNode parent, int value){
        if(root == null) return null;

        if(root.val == value) {
            if(root.left==null && root.right==null ){
                return null;
            }

            if(root.left == null){
                return root.right;
            }

            if(root.right == null){
                return root.left;
            }

            TreeNode rightest = root.left;
            TreeNode prev = null;

            while(rightest!=null){

                if(rightest.right== null){
                    if(prev!= null)
                        prev.right = null;
                    if(root.left!= rightest)
                        rightest.left=root.left;
                    if(root.right!= rightest){
                        rightest.right= root.right;
                    }
                    return rightest;
                }
                prev= rightest;
                rightest = rightest.right;
            }
        }


        if(value< root.val) {
            root.left = remove(root.left, root, value);
        }else {
            root.right = remove(root.right, root, value);
        }

        return root;
    }


}
