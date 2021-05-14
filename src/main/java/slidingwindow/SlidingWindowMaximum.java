package slidingwindow;

import java.util.*;

public class SlidingWindowMaximum {
    public class Node {
        public int value;
        public int index;
        public Node(int v, int i){
            this.value = v;
            this.index = i;
        }
    }

    public class MyMaxComparator implements Comparator<Node>{
        public int compare(Node arg0, Node arg1) {
            if(arg0.value > arg1.value) return -1;
            if(arg0.value < arg1.value) return 1;
            return arg0.index - arg1.index;
        }
    }

    /*
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 1) return null;
        List<Integer> result = new ArrayList<>();

        TreeSet<Node> maxHeap = new TreeSet<>(new MyMaxComparator());

        for(int i = 0; i < k; i++)
            maxHeap.add(new Node(nums[i], i));

        int max = maxHeap.first().value;
        result.add(max);
        int start;
        Node node;

        for(int i = k; i < nums.length; i++){
            start = i - k;
            node = new Node(nums[start], start);

            maxHeap.remove(node);
            maxHeap.add(new Node(nums[i], i));

            max = maxHeap.first().value;
            result.add(max);
        }
        return result;
    }
}
