package algorithms.numbers;

/**
 * 1112. Set Mismatch
 * https://www.lintcode.com/problem/set-mismatch/note/199801
 *
 * The set S originally contains algorithms.numbers from 1 to n. But unfortunately, due to the data error, one of the algorithms.numbers in the set got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 *
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice and then find the number that is missing. Return them in the form of an array.
 * Example
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Explanation:
 * 2 is the number occurs twice, 3 is the missing number.
 *
 * Example 2:
 *
 * Input: nums = [1,3,3,4]
 * Output: [3,2]
 * Explanation:
 * 3 is the number occurs twice, 2 is the missing number.
 *
 * Notice
 *
 * 1.The given array size will in the range [2, 10000].
 * 2.The given array's algorithms.numbers won't have any order.
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
 */
public class SetMismatch {

    /**
     * @param nums: an array
     * @return: the number occurs twice and the number that is missing
     */
    public int[] findErrorNums(int[] nums) {
        int[] res = new int[2];
        for (int i : nums) {
            if (nums[Math.abs(i) - 1] < 0) res[0] = Math.abs(i);
            else nums[Math.abs(i) - 1] *= -1;
        }

        for(int i=0;i< nums.length;i++){
            if(nums[i]>0)
                res[1]= i+1;
        }
        return res;
    }

    /**
     * @param nums: an array
     * @return: the number occurs twice and the number that is missing
     */
    public int[] findErrorNums_Solution2(int[] nums) {
        int temp;
        int[] res = new int[2];
        for(int i=0;i< nums.length;i++){
            while(nums[i]!= i+1) {
                temp = nums[nums[i]-1];
                if(temp == nums[i]){
                    res[0]= temp;
                    res[1]= i +1;
                    break;
                }else{
                    nums[nums[i]-1] = nums[i];
                    nums[i]= temp;
                }
            }
        }
        return res;
    }
}
