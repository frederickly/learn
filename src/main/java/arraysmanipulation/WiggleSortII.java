package arraysmanipulation;

import java.util.Arrays;

public class WiggleSortII {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;

        Arrays.sort(nums);
        int n = nums.length;

        int[] temp = new int[n];
        int left = (n - 1) / 2;
        int right = n - 1;

        for (int i = 0; i < n; i++) {
            if ((i & 1) == 0) {
                temp[i] = nums[left--];
            } else {
                temp[i] = nums[right--];
            }
        }

        System.arraycopy(temp, 0, nums, 0, n);
    }
}
