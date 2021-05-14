import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Each time, when add a num in the middle(not append a new one in the end) of array,
// set the new node to that position, only change prev pointer to previous node.
// it make the new node pointing to the longest subsequence belongs itself
// However for all the node after the new node and replaced node by new node,
// they still point to their previous element belong to their longest subsequence

//[88,4,24,82,86,1,56,74,71,9,8,18,26,53,77,87,60,27,69,17,76,23,67,14,98,13,10,83,20,43,39,29,92,31,0,30,90,70,37,59]
public class longestIncreasingSubsequenceWthValue {
    public List<Integer> longestIncreasingSubsequence(int[] nums) {
        List<Node> piles = new ArrayList<>(nums.length);
        int size=0;
        for (int num : nums) {
            Node node = new Node(num);
            int pile = Collections.binarySearch(piles, node);
            if (pile < 0) pile = ~pile;

            if (pile != 0) {
                node.prev = piles.get(pile - 1);
            }

            // debug here
            // if(num==31) System.out.println("pile="+ pile);

            if (pile == piles.size()) {
                piles.add(node);
            } else {
                piles.set(pile, node);
            }


            // add below code to print all longest sub sequence
            if(piles.size()>= size && pile>= piles.size()-1) {
                if(piles.size()>size)  {
                    size= piles.size();
                    System.out.println("size="+ size);
                }
                System.out.println(extractLIS(piles));
            }
        }

        return extractLIS(piles);
    }

    private List<Integer> extractLIS(List<Node> piles) {
        List<Integer> result = new ArrayList<>(piles.size());
        for (Node curr = piles.isEmpty() ? null : piles.get(piles.size() - 1); curr != null; curr = curr.prev) {
            result.add(curr.val);
        }
        Collections.reverse(result);
        return result;
    }

    private static class Node implements Comparable<Node> {
        int val;
        Node prev;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public int compareTo(Node that) {
            return Integer.compare(this.val, that.val);
        }
    }

    private void print(int[] values) {
        StringBuilder sb = new StringBuilder();
        for (int v : values) sb.append(v + " , ");
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {

        int[] data = new int[]{88, 4, 24, 82, 86, 1, 56, 74, 71, 9, 8, 18, 26, 53, 77, 87, 60,
                27, 69, 17, 76, 23, 67, 14, 98, 13, 10, 83, 20, 43, 39, 29, 92, 31, 0, 30, 90, 70, 37, 59};// expect 10

        longestIncreasingSubsequenceWthValue lis = new longestIncreasingSubsequenceWthValue();
        lis.print(data);
        List<Integer> res = lis.longestIncreasingSubsequence(data);
        System.out.println("res=" + res);
    }
}

