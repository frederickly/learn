package slidingwindow;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class SlidingWindowMedianTreeSetFast {

    public class setNode{
        public int value;
        public int index;
        public setNode(int v, int i){
            this.value = v;
            this.index = i;
        }
    }

    public class MyMinComparator implements Comparator<setNode> {

        public int compare(setNode arg0, setNode arg1) {
            if(arg0.value > arg1.value) return 1;
            if(arg0.value < arg1.value) return -1;
            return arg0.index - arg1.index;
        }
    }

    public class MyMaxComparator implements Comparator<setNode>{
        public int compare(setNode arg0, setNode arg1){
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
        TreeSet<setNode> minHeap = new TreeSet<>(new MyMinComparator());
        TreeSet<setNode> maxHeap = new TreeSet<>(new MyMaxComparator());
        int mid = (k + 1)/2; // even number 6 is 3, odd number 7 is 4
        for(int i = 0; i < k; i++){
            if(i < mid){
                maxHeap.add(new setNode(nums[i], i));
            }else{
                if(nums[i] >= maxHeap.first().value){
                    minHeap.add(new setNode(nums[i], i));
                }else{
                    minHeap.add(maxHeap.pollFirst());
                    maxHeap.add(new setNode(nums[i], i));
                }
            }
        }
        int median = maxHeap.first().value;
        result.add(median);
        int start;
        setNode node;

        for(int i = k; i < nums.length; i++){
            start = i - k;
            node = new setNode(nums[start], start);
            if(maxHeap.contains(node)){
                maxHeap.remove(node);
            }else{
                minHeap.remove(node);
            }

            if(nums[i] >= median){
                minHeap.add(new setNode(nums[i], i));
            }else{
                maxHeap.add(new setNode(nums[i], i));
            }

            if(maxHeap.size() < mid){
                maxHeap.add(minHeap.pollFirst());
            }else if(maxHeap.size() > mid){
                minHeap.add(maxHeap.pollFirst());
            }
            median = maxHeap.first().value;
            result.add(median);
        }

        return result;
    }
}
