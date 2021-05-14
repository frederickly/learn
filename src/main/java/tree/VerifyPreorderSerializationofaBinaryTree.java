package tree;

import java.util.Stack;

/**
 * 1066. Verify Preorder Serialization of a Binary Tree
 * https://www.lintcode.com/problem/verify-preorder-serialization-of-a-binary-tree/description
 *
 * One way to serialize a binary tree is to use pre-order traversal. When we encounter a non-null node, we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *
 *      _9_
 *     /   \
 *    3     2
 *   / \   / \
 *  4   1  #  6
 * / \ / \   / \
 * # # # #   # #
 *
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#", where # represents a null node.
 *
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of a binary tree. Find an algorithm without reconstructing the tree.
 *
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid, for example it could never contain two consecutive commas such as "1,,3".
 * Example
 *
 * Example 1:
 * 	Input:  tree = "#"
 * 	Output:  true
 *
 * 	Explanation:
 * 	Empty tree is legal.
 *
 * Example 2:
 * 	Input: tree = "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * 	Output:  true
 *
 * Example 3:
 * 	Input: tree = "1,#"
 * 	Output:  false
 *
 * 	Explanation:
 * 	It's not a complete tree.
 *
 * Example 4:
 * 	Input: tree = "9,#,#,1"
 * 	Output:  false
 *
 * 	Explanation:
 * 	It's not a tree.
 */
public class VerifyPreorderSerializationofaBinaryTree {
    /**
     * @param preorder: a string
     * @return: return a bool
     */
    public boolean isValidSerialization(String preorder) {
        if("#".equals(preorder)) return true;
        String[] values= preorder.split(",");
        Stack<TreeNode> stack= new Stack<>();
        boolean res= true;
        TreeNode temp=null;
        for(int i=0;i< values.length;i++) {
            if("#".equals(values[i])) {
                if(stack.empty()){
                    res= false;
                    break;
                }
                if(stack.peek().left.equals("")){
                    stack.peek().left="#";
                }else if(!stack.peek().left.equals("") && stack.peek().right.equals("")){ // leave
                    stack.peek().right="#";
                    temp = stack.pop();
                    if(temp.left.equals("")|| temp.right.equals("")){
                        res= false;
                        break;
                    }
                }else{
                    res= false;
                    break;
                }

            }else{
                temp = new TreeNode(values[i]) ;
                if(!stack.empty()) {
                    if(stack.peek().left.equals(""))
                        stack.peek().left= values[i];
                    else
                        stack.peek().right= values[i];
                }
                stack.push(temp);
            }

            while(!stack.empty() && !stack.peek().left.equals("") && !stack.peek().right.equals("")){
                temp =stack.pop();
            }
        }

        // System.out.println("stack="+ stack);
        while(!stack.empty()){
            temp = stack.pop();
            if(temp.left.equals("") || temp.right.equals("")){
                res=false;
                break;
            }
        }

        return res;
    }

    class TreeNode {
        public String val;
        public String left="", right="";

        public TreeNode(String val){
            this.val= val;
            left=""; right="";
        }

        public String toString(){
            return "[val="+ val+"  left="+ left+" right="+right+"]";
        }
    }
}
