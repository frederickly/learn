package algorithms.tree;

import java.util.*;

/**
 * 651. Binary Tree Vertical Order Traversal
 * 中文
 * English
 *
 * Given a binary algorithms.tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 * Example
 *
 * Example1
 *
 * Inpurt:  {3,9,20,#,#,15,7}
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 *    3
 *   /\
 *  /  \
 *  9  20
 *     /\
 *    /  \
 *   15   7
 *
 * Example2
 *
 * Input: {3,9,8,4,0,1,7}
 * Output: [[4],[9],[3,0,1],[8],[7]]
 * Explanation:
 *      3
 *     /\
 *    /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 */
public class BinaryTreeVerticalOrderTraversal {

    /**
     * @param root: the root of algorithms.tree
     * @return: the vertical order traversal
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }

        Map<Integer, List<Integer>> hash = new HashMap<>();
        Queue<Integer> qCol = new LinkedList<>();
        Queue<TreeNode> qNode = new LinkedList<>();

        qCol.offer(0);
        qNode.offer(root);

        while (!qCol.isEmpty()) { // bfs
            int c = qCol.poll();
            TreeNode node = qNode.poll();

            hash.putIfAbsent(c, new ArrayList<>());
            hash.get(c).add(node.val);

            if (node.left != null) {
                qCol.offer(c - 1);
                qNode.offer(node.left);
            }
            if (node.right != null) {
                qCol.offer(c + 1);
                qNode.offer(node.right);
            }
        }

        for (int i = Collections.min(hash.keySet()); i <= Collections.max(hash.keySet()); i++) {
            ans.add(hash.get(i));
        }
        return ans;
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
