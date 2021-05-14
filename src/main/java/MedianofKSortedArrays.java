/**
 *     LintCode-Logo
 *     Home
 *     Algorithms
 *
 *     AI
 *     CATnew
 *     VIP
 *
 *     Language
 *
 *     avatarjacob2020
 *
 * Back
 * 931. Median of K Sorted Arrays
 *
 * Description
 *
 * There are k sorted arrays nums. Find the median of the given k sorted arrays.
 *
 *     The length of the given arrays may not equal to each other.
 *     The elements of the given arrays are all positive number.
 *     Return 0 if there are no elements in the array.
 *     In order to ensure time complexity, the program will be executed repeatedly.
 *
 * Have you met this question in a real interview?
 * Example
 *
 * Example 1:
 *
 * Input:
 * [[1],[2],[3]]
 * Output:
 * 2.00
 *
 * Example 2:
 *
 * Input:
 * [[1,1,2],[2,4,8],[3,7,10,20]]
 * Output:
 * 3.50
 *
 * Related Problems
 *
 *     DifficultyHard
 *     Total Accepted4173
 *     Total Submitted15065
 *     Accepted Rate27%
 *
 *  Show Tags
 * Leaderboard - Java
 * chris71
 * 3537ms
 * 403548052
 * 3572ms
 * momo445
 * 3612ms
 * chenyunawei
 * 3858ms
 * michelle.juaner
 * 3911ms
 * Note
 * iterate method, use O(kn) complexity, but with some optimezation, still ac. copy the code
 * avatarroger51
 * Created at 24 days ago
 * https://www.lintcode.com/problem/median-of-two-sorted-arrays/
 * avatarmomo445
 * Created at a month ago
 * Solution1: Use heap will have MLE (Memory Limit Exceeded). Solution2: Use similar to median of 2 sorted array: binary search cut off k / m or 1 number of items: https://www.lintcode.com/problem/median-of-two-sorted-arrays Solution3: Try to use binary search the result: We could try to guess the kth smallest value x, which is the median. If x is the kth smallest, must have k-1 nums less than or equal to x, also x is in range min of nums ~ max of nums. Could try to binary search x value. When binary search on x, count how many values in nums is exactly <= x, i.e. in each array find the first number whose value is > x. If count of <= x nums is <= k - 1, means a number larger than x is the kth, should increase x. Otherwise should decrease x. At the end, the kth value x must be in the interval [start, end], also has start + 1 = end, so x must be either start or end.
 * avatarsu2
 * Created at 3 months ago
 * Discuss
 * xarry · Last reply by · TAlu · 2 years ago
 * jie27 · Last reply by · jie27 · 2 years ago
 * charlie_goodwish · Last reply by · charlie_goodwish · 2 years ago
 * 931. Median of K Sorted Arrays
 * https://www.lintcode.com/problem/median-of-k-sorted-arrays
 *
 * There are k sorted arrays nums. Find the median of the given k sorted arrays.
 * Example
 *
 * Example 1:
 *
 * Input:
 * [[1],[2],[3]]
 * Output:
 * 2.00
 *
 * Example 2:
 *
 * Input:
 * [[1,1,2],[2,4,8],[3,7,10,20]]
 * Output:
 * 3.50
 *
 * Notice
 *
 *     The length of the given arrays may not equal to each other.
 *     The elements of the given arrays are all positive number.
 *     Return 0 if there are no elements in the array.
 *     In order to ensure time complexity, the program will be executed repeatedly.
 *
 * [[1],[2],[3]]
 */

public class MedianofKSortedArrays {
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        if (nums == null|| nums.length==0) return 0;

        int count = 0;
        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                count++;
                max = Math.max(max, nums[i][j]);
                min = Math.min(min, nums[i][j]);
            }
        }
        if (count == 0) return 0;

        if (count % 2 == 1) {
            return findKth(nums, count / 2 + 1, min, max);
        }
        return findKth(nums, count / 2, min, max) / 2.0 + findKth(nums, count / 2 + 1, min, max) / 2.0;
    }

    private int findKth(int[][] nums, int k, int min, int max) {
        int mid;
        int left = min;
        int right = max;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (equalOrLessNum(nums, mid) < k) { // Not <=.
                left = mid;
            } else {
                right = mid;
            }
        }

        if (equalOrLessNum(nums, left) == k) {
            return left;
        }
        return right;
    }

    private int equalOrLessNum(int[][] nums, int target) {
        int res = 0;
        for (int[] num : nums) {
            if (num.length == 0) continue;

            int len = 0;
            int left = 0;
            int right = num.length - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (num[mid] > target) {
                    right = mid;
                } else {
                    left = mid;
                }
            }
            if (num[right] <= target) {
                len = right + 1;
            } else if (num[left] <= target) {
                len = left + 1;
            } else {
                len = 0;
            }
            res += len;
        }
        return res;
    }
}
