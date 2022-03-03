package algorithms.topkxxx;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 544. Top k Largest Numbers
 *
 * https://www.lintcode.com/problem/top-k-largest-numbers/description
 *
 * Given an integer array, find the top k largest algorithms.numbers in it.
 * Example
 *
 * Example1
 *
 * Input: [3, 10, 1000, -99, 4, 100] and k = 3
 * Output: [1000, 100, 10]
 *
 * Example2
 *
 * Input: [8, 7, 6, 5, 4, 3, 2, 1] and k = 5
 * Output: [8, 7, 6, 5, 4]
 *
 * Solution 1:
 * algorithms.sort the array. get first(desc)/last(asc) k
 * Solution 2:
 * Priority Queue
 * Solution 3:
 *  Quick select with recursion
 */
public class TopkLargestNumbers {
    /*
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest algorithms.numbers in array
     */

    public int[] topkSolution2(int[] nums, int k) {
        Queue<Integer> queue= new PriorityQueue(k);

        for(int i=0;i<nums.length;i++){
            if(i<k) {
                queue.offer(nums[i]);
            }else{
                if(nums[i]> queue.peek()){
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }

        int[] res= new int[k];
        for(int i=k-1;i>=0;i--)
            res[i]= queue.poll();

        return res;
    }

    public int[] topk(int[] nums, int k) {
        int[] result = new int[k];
        if (nums == null || nums.length < k) {
            return result;
        }

        quickSort(nums, 0, nums.length - 1, k);
        // first k elements are the k largest elements
        quickSort(nums, 0, k - 1, -1);
        for (int i = 0; i < k; i++) {
            result[i] = nums[i];
        }

        return result;
    }

    private void quickSort(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return;
        }

        int left = start;
        int right = end;
        int pivot = nums[start + (end - start) / 2];
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }

            while (left <= right && nums[right] < pivot) {
                right--;
            }

            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            }
        }

        if (k != -1) {
            if (start + k - 1 < right + 1) {
                quickSort(nums, start, right, k);
            } else if (start + k - 1 > right + 1) {
                quickSort(nums, left, end, k - (left - start));
            }
        } else {
            quickSort(nums, start, right, k);
            quickSort(nums, left, end, k);
        }
    }
}
