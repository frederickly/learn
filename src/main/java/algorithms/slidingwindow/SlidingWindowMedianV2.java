package algorithms.slidingwindow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SlidingWindowMedianV2 {
    private PriorityQueue<Integer> maxHeap, minHeap;
    private int numOfElements = 0;

    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> ans = new ArrayList<>();
        if(nums==null || nums.length==0) return ans;

        Comparator<Integer> revCmp = Comparator.reverseOrder();
        int cnt = nums.length;
        maxHeap = new PriorityQueue<>(cnt, revCmp);
        minHeap = new PriorityQueue<>(cnt);

        for(int i=0;i<k;i++){
            addNumber(nums[i]);
        }
        ans.add(getMedian());

        for (int i = k; i < cnt; ++i) {
            remove(nums[i-k]);
            addNumber(nums[i]);
            ans.add(getMedian());
        }
        return ans;
    }

    void remove(int value){
        if(!maxHeap.remove(value)) {
            minHeap.remove(value);
            numOfElements =1;
        }else{
            numOfElements =0;
        }
    }

    void addNumber(int value) {
        maxHeap.add(value);
        if (numOfElements%2 == 0) {
            if (minHeap.isEmpty()) {
                numOfElements++;
                return;
            }else if (maxHeap.peek() > minHeap.peek()) {
                Integer maxHeapRoot = maxHeap.poll();
                Integer minHeapRoot = minHeap.poll();
                maxHeap.add(minHeapRoot);
                minHeap.add(maxHeapRoot);
            }
        } else {
            minHeap.add(maxHeap.poll());
        }
        numOfElements++;
    }

    int getMedian() {
        return maxHeap.peek();
    }
}
