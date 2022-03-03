package algorithms.numbers;

import java.util.Arrays;

/**
 * 196. Missing Number
 * https://www.lintcode.com/problem/missing-number/note/200173
 *
 * Given an array contains N algorithms.numbers of 0 .. N, find which number doesn't exist in the array.
 * Example
 *
 * Example 1:
 *
 * Input:[0,1,3]
 * Output:2
 *
 * Example 2:
 *
 * Input:[1,2,3]
 * Output:0
 *
 * Challenge
 *
 * Do it in-place with O(1) extra memory and O(n) time.
 *
 * Solution 1:
 * 我们来看一种更省空间的解法，这种解法思路相当巧妙，遍历每个数字，然后将其应该出现的位置上的数字变为其相反数，
 * 这样如果我们再变为其相反数之前已经成负数了，说明该数字是重复数，将其将入结果res中，
 * 然后再遍历原数组，如果某个位置上的数字为正数，说明该位置对应的数字没有出现过，加入res中即可
 *
 *
 * Solution 2;
 * 下面这种方法也很赞，首先我们把乱序的数字放到其正确的位置上，用while循环来不停的放，直到该数字在正确的位置上，
 * 那么一旦数组有序了，我们只要从头遍历就能直接找到重复的数字，然后缺失的数字同样也就知道了
 *
 * Solution 3:
 *  algorithms.sort array. find the missing number which nums[1]!= i
 *
 * Solution 4
 * 本题的意思是，从1到n的整数，其中某个数丢失了，替代它的是0。要我们找出这个丢失的数。
 * 我们可以用公式计算出从1到n的和，减去实际数组的总和，差值就是那个丢失的数。
 * This is the best solution so far
 */
public class MissingNumber {

    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int findMissing(int[] nums) {
        int n=  nums.length;

        for (int i : nums) {
            if(Math.abs(i) >= nums.length) {
                n = nums.length * -1;
                continue;
            }
            if (nums[Math.abs(i) ] >= 0)
                nums[Math.abs(i) ] *= -1;
        }

        int res=-1;
        for(int i=0;i< nums.length;i++)
            if(nums[i]>0)
                res= i;

        if(res ==-1) res=n;
        return res;
    }

     /* @param nums: An array of integers
     * @return: An integer
     */
    public int findMissing_solution3(int[] nums) {
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
            if(nums[i]!=i)
                return i;
        return nums.length;
    }

    public int findMissing_solution4(int[] nums) {
        long sum= (1+ nums.length) * nums.length/2;
        for(int i: nums)
            sum -= i;
        return (int)sum;
    }
}
