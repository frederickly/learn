package algorithms.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 1105. Print Binary Tree
 * https://www.lintcode.com/problem/print-binary-tree/description
 *
 * Print a binary algorithms.tree in an m*n 2D string array following these rules:
 *
 *     The row number m should be equal to the height of the given binary algorithms.tree.
 *     The column number n should always be an odd number.
 *     The root node's value (in string format) should be put in the exactly middle of the first row it can be put. The column and the row where the root node belongs will separate the rest space into two parts (left-bottom part and right-bottom part). You should print the left subtree in the left-bottom part and print the right subtree in the right-bottom part. The left-bottom part and the right-bottom part should have the same size. Even if one subtree is none while the other is not, you don't need to print anything for the none subtree but still need to leave the space as large as that for the other subtree. However, if two subtrees are none, then you don't need to leave space for both of them.
 *     Each unused space should contain an empty string "".
 *     Print the subtrees following the same rules.
 *
 * Example
 *
 * Example 1:
 *
 * Input:{1,2}
 *     1
 *    /
 *   2
 * Output:
 *  [["", "1", ""],
 *   ["2", "", ""]]
 *
 * Example 2:
 *
 * Input:{1,2,3,#,4}
 *     1
 *    / \
 *   2   3
 *    \
 *     4
 * Output:
 *  [["", "", "", "1", "", "", ""],
 *   ["", "2", "", "", "", "3", ""],
 *   ["", "", "4", "", "", "", ""]]
 *
 * Example 3:
 *
 * Input:{1,2,5,3,#,#,#,4}
 *         1
 *        / \
 *       2   5
 *      /
 *     3
 *    /
 *   4
 * Output:
 *  [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 *   ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 *   ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 *   ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 *
 * Notice
 *
 * The height of binary algorithms.tree is in the range of [1, 10].
 */
public class PrintBinaryTree {
    /**
     * @param root: the given algorithms.tree
     * @return: the binary algorithms.tree in an m*n 2D string array
     */
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        if(root==null) return res;

        int height = getHeight(root);
        int columns = getColumns(height);

        int half= columns/2;

        List<String> row = new ArrayList<>();

        Queue<TreeNode> nodes= new LinkedList<>();
        Queue<Integer> indexes = new LinkedList<>();

        nodes.offer(root);
        indexes.offer(half);

        TreeNode node=null;
        int size=0;
        int index=0;

        for(int i=0;i<height;i++){
            row = new ArrayList<>();
            size= indexes.size();
            half/=2;
            for(int j=0;j<columns;j++){
                if(size>0 && indexes.peek()== j){
                    node = nodes.poll();
                    index =indexes.poll();
                    size--;
                    row.add(Integer.toString(node.val));
                    if(node.left!=null){
                        indexes.add(index-half-1);
                        nodes.add(node.left);
                    }
                    if(node.right!=null){
                        indexes.add(index+half+1);
                        nodes.add(node.right);
                    }
                }else
                    row.add("");

            }

            res.add(row);
        }
        return res;
    }

    private int getHeight(TreeNode root){
        if(root==null) return 0;
        if(root.left==null && root.right==null) return 1;
        int left= getHeight(root.left);
        int right = getHeight(root.right);

        return 1+ Math.max(left, right);
    }

    public int getColumns(int height){
        int sum=0;
        for(int i=0;i<height;i++){
            sum+= Math.pow(2, i);
        }
        return sum;
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
