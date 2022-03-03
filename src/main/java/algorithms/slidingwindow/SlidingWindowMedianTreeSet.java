package algorithms.slidingwindow;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class SlidingWindowMedianTreeSet {

    class Node implements Comparable<Node> {
        int id;
        int val;
        public Node(int id, int val) {
            this.id = id;
            this.val = val;
        }

        public int compareTo(Node other) {
            if(this.val == other.val) {
                return this.id - other.id;
            }
            return this.val - other.val;
        }
    }
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();

        if(nums.length == 0) return ans;

        TreeSet<Node> minheap = new TreeSet<>();
        TreeSet<Node> maxheap = new TreeSet<>();

        int curMedian;

        if(k > 1) {
            minheap.add(new Node(0, nums[0]));

            for(int i = 1;i < k - 1;i++) {
                if(nums[i] < nums[0]) {
                    minheap.add(new Node(i, nums[i]));
                } else {
                    maxheap.add(new Node(i, nums[i]));
                }
            }

            curMedian = nums[0];
        } else {
            curMedian = 0;
        }

        for(int i = k - 1;i < nums.length;i++) {
            if(nums[i] < curMedian) {
                minheap.add(new Node(i, nums[i]));
            } else {
                maxheap.add(new Node(i, nums[i]));
            }

            while(minheap.size() > maxheap.size() + 1) {
                maxheap.add(minheap.last());
                minheap.remove(minheap.last());
            }

            while(minheap.size() < maxheap.size()) {
                minheap.add(maxheap.first());
                maxheap.remove(maxheap.first());
            }

            curMedian = minheap.last().val;
            ans.add(curMedian);

            Node toRemove = new Node(i - k + 1, nums[i - k + 1]);
            if(minheap.contains(toRemove)) {
                minheap.remove(toRemove);
            } else {
                maxheap.remove(toRemove);
            }
        }
        return ans;
    }
}
