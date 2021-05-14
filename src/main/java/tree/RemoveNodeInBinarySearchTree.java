package tree;

/**
 dummy node point to root
 parent for target node

 value< root.Val   go to left sub tree
 else go to right sub tree

 if the target node is leaf , delete it.
 if target has both left and right subtree, find the rightest(biggest) node in left sub tree to place the position where target node is
 if the target has no left sub tree, parent point to the right sub tree

 别人的 note, 比我说的清楚
 分层判断：
 目标值和根节点值比较：
 目标值大，递归右节点；
 目标值小，递归左节点；
 目标值等于根节点，此节点即为要删除的节点，继续判断：
 当前节点如果没有左右子节点，直接删除节点(return null);
 当前节点如果只有左/右节点，直接将左/右节点接在当前根节点上(return root.right/root.left);
 当前节点如果有左节点和右节点：找到当前根节点的左子树的最大子节点（无右子节点），用此节点替换当 前根节点，最后删除此节点（递归方法）
 */
public class RemoveNodeInBinarySearchTree {
    /*
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        if(root == null) return null;
        TreeNode dummy = new TreeNode(Integer.MAX_VALUE);
        dummy.left = root;
        TreeNode parent= dummy,temp =null, preTemp =null;

        while(root!=null){
            if(root.val == value) {
                if(root.left ==null && root.right == null) { // it is a leaf
                    if(parent.left == root) { // delete left
                        parent.left = null;
                        break;
                    }
                    if(parent.right == root) {// delete right
                        parent.right = null;
                        break;
                    }
                }

                if(root.left!=null && root.right!=null ){ // look for the rightest(biggest) leaf in sub left tree
                    temp = root.left;

                    while(temp!=null) {

                        if(temp.right ==null) {
                            temp.left = root.left!= temp? root.left: null;
                            parent.left = temp;
                            temp.right = root.right;
                            if(preTemp!=null){
                                preTemp.right =null;
                            }
                            break;
                        }
                        preTemp = temp;
                        temp= temp.right;
                    }
                    break;
                }

                if(root.left != null ){
                    if(parent.right == root){
                        parent.right = root.left;
                    }else{
                        parent.left = root.left;
                    }
                    root.left =null;
                    break;
                }

                if(root.left == null && root.right!=null){
                    if(parent.right == root){
                        parent.right = root.right;
                    }else{
                        parent.left = root.right;
                    }
                    root.right=null;
                    break;
                }
            }
            if(value <root.val) {
                parent = root;
                root = root.left;
            }else{
                parent = root;
                root = root.right;
            }
        }
        return dummy.left;
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
