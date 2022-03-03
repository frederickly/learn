package algorithms.KthLargestElementInArray;

public class Solution {
    /**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        int left = 0, right = nums.length - 1;
        while (true) {
            int pos = partition(nums, left, right);
            if (pos == n - 1) return nums[pos];
            else if (pos > n - 1) right = pos - 1;
            else left = pos + 1;
        }
    }

    int partition(int[] nums, int left, int right) {
        //System.out.println("left="+left +" right="+ right);
        int pivot = nums[right], l = left , r = right-1;
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l++, r--);
            }
            if (nums[l] >= pivot) ++l;
            if (nums[r] <= pivot) --r;
        }
        swap(nums, l, right);
        //System.out.println("l="+l +" right="+ right);
        return l;
    }

    private void swap(int[] nums, int i, int j) {
        //System.out.println("swap l="+i  +" r="+ j);
        int temp;
        temp= nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
}
