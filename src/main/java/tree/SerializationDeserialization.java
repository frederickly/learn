package tree;

import java.util.LinkedList;
import java.util.Queue;
/*
Description
中文
English

Design an algorithm and write code to serialize and deserialize a binary tree. Writing the tree to a file is called 'serialization' and reading back from the file to reconstruct the exact same binary tree is 'deserialization'.

There is no limit of how you deserialize or serialize a binary tree, LintCode will take your output of serialize as the input of deserialize, it won't check the result of serialize.
Have you met this question in a real interview?
Example

Example 1:

Input：{3,9,20,#,#,15,7}
Output：{3,9,20,#,#,15,7}
Explanation：
Binary tree {3,9,20,#,#,15,7},  denote the following structure:
	  3
	 / \
	9  20
	  /  \
	 15   7
it will be serialized {3,9,20,#,#,15,7}

Example 2:

Input：{1,2,3}
Output：{1,2,3}
Explanation：
Binary tree {1,2,3},  denote the following structure:
   1
  / \
 2   3
it will be serialized {1,2,3}

Our data serialization use BFS traversal. This is just for when you got Wrong Answer and want to debug the input.

You can use other method to do serializaiton and deserialization.

 */
public class SerializationDeserialization {

    /**
     * This method will be invoked first, you should design your own algorithm
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if(root == null){
            return "{}";
        }
        Queue<TreeNode> que= new LinkedList<>();
        que.add(root);
        TreeNode node;
        String result="";
        while(que.size()>0){
            node = que.poll();
            if(node!=null) {
                result+= ","+node.val;
                que.add(node.left);
                que.add(node.right);
            }else{
                result+=",#";
            }
            //System.out.println("que 1 size="+ que.size());
        }

        return "{"+result.substring(1)+"}";

    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if(data==null || data.equals("") || data.equals("{}")){
            return null;
        }
        String[] values= data.split(",");
        values[0] = values[0].replace("{", "");
        values[values.length-1] = values[values.length-1].replace("}", "");

        TreeNode root = !"#".equals(values[0])? new TreeNode(Integer.valueOf(values[0])) : null;
        if(root!=null) {
            Queue<TreeNode> que= new LinkedList();
            que.add(root);

            TreeNode cur;

            int j= 1;
            while(que.size()>0){
                //System.out.println("que 2 size="+ que.size());
                cur = que.poll();

                cur.left = j<= values.length-1 && !"#".equals(values[j])? new TreeNode(Integer.valueOf(values[j])) : null;
                cur.right =  j<= values.length-2 && !"#".equals(values[j+1])?new TreeNode(Integer.valueOf(values[j+1])): null;

                j+=2;

                if(cur.left!=null){
                    que.add(cur.left);
                }
                if(cur.right!=null){
                    que.add(cur.right);
                }


            }
        }

        return root;

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


