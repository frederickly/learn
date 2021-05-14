package tree;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.lintcode.com/problem/leaf-similar-trees/description   1495
 * Description
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 * Example
 *
 * Example 1:
 *
 * Input: {1,#,2,3}, {1,2,#,3}
 * Output:
 * Explaination:
 * the first tree:
 *   1
 *     \
 *      2
 *     /
 *    3
 * the second tree:
 *     1
 *    /
 *   2
 *  /
 * 3
 * The leaf value sequence is: [3], so the same
 *
 * Example 2:
 *
 * Input: {1,#,2,3}, {1,2,#,3}
 * Output:
 * Explaination:
 * the first tree:
 *   1
 *     \
 *      2
 *     /
 *    3
 * the second tree:
 *    1
 *   / \
 *  2   3
 * The first leaf value sequence is: [3], the second tree is: [2, 3], so it is not the same
 */
public class LeafSimilarTrees {

    /**
     * @param root1: the first tree
     * @param root2: the second tree
     * @return: returns whether the leaf sequence is the same
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> sequence1 = new ArrayList<>();
        List<Integer> sequence2 = new ArrayList<>();
        findLeaveSequece(root1, sequence1);
        findLeaveSequece(root2,sequence2);
        return isSame(sequence1, sequence2);
    }

    private void findLeaveSequece(TreeNode node, List<Integer> sequence) {
        if (node == null) return;
        if (node.left == null && node.right == null)
            sequence.add(node.val);
        findLeaveSequece(node.left,sequence);
        findLeaveSequece(node.right,sequence);
    }

    private boolean isSame(List<Integer> sequence1, List<Integer> sequence2) {
        if (sequence1.size() != sequence2.size())
            return false;

        for (int i = 0; i < sequence1.size(); i++)
            if (sequence1.get(i) != sequence2.get(i))
                return false;
        return true;
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
