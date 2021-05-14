package arraysmanipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1236. Find All Numbers Disappeared in an Array
 * https://www.lintcode.com/problem/find-all-numbers-disappeared-in-an-array/description
 *
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * Example
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 * Output:
 * [5,6]
 */
public class FindAllNumbersDisappearedInAnArray {
    /**
     * @param nums: a list of integers
     * @return: return a list of integers
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int temp =0;
        int index =0;
        List<Integer> res=new ArrayList<>();
        for(int i=0;i<nums.length;i++){
            index = i;
            while(nums[index]!= index+1) {
                temp = nums[nums[index]-1];
                if(temp==nums[index]){
                    break;
                }
                nums[nums[index]-1] = nums[index];
                nums[index]= temp;
            }
        }

        for(int i=0;i<nums.length;i++)
            if(nums[i]!=i+1)
                res.add(i+1);
        return res;
    }
    /**
     * @param nums: a list of integers
     * @return: return a list of integers
     */
    public List<Integer> findDisappearedNumbers2(int[] nums) {
        Arrays.sort(nums);
        List<Integer> res=new ArrayList<>();
        int count =0;
        int len = nums.length-1;
        if(nums[0]> 1){
            count = 1;
            while(count< nums[0])
                res.add(count++);
        }

        for(int i=0;i<len;i++){
            if(nums[i+1] - nums[i]> 1){
                count= nums[i] +1;
                while(count< nums[i+1])
                    res.add(count++);
            }
        }

        if(nums[len]< nums.length) {
            count = nums[len] +1;
            while(count<=nums.length)
                res.add(count++);
        }
        return res;
    }
}
