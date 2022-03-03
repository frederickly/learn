package algorithms.arraysmanipulation;

/**
 * 585. Maximum Number in Mountain Sequence
 * https://www.lintcode.com/problem/maximum-number-in-mountain-sequence/my-submissions
 *
 * Given a mountain sequence of n integers which increase firstly and then decrease, find the mountain top.
 * Example
 *
 * Example 1:
 *
 * Input: nums = [1, 2, 4, 8, 6, 3]
 * Output: 8
 *
 * Example 2:
 *
 * Input: nums = [10, 9, 8, 7],
 * Output: 10
 *
 * Notice
 *
 * Arrays are strictly incremented, strictly decreasing
 */
public class MaximumNumberInMountainSequence {
    /**
     * @param nums: a mountain sequence which increase firstly and then decrease
     * @return: then mountain top
     */
    public int mountainSequence(int[] nums) {
        int start =0, end = nums.length -1;
        int mid;
        while(start +1 < end){
            mid=(end-start)/2 + start;
            if(nums[mid]< nums[mid+1]){
                start= mid;
            }else{
                end = mid;
            }
        }
        return nums[start]> nums[end]? nums[start]:nums[end];
    }
}
