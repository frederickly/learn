package algorithms.arraysmanipulation;

/**
 * 373. Partition Array by Odd and Even
 *
 * https://www.lintcode.com/problem/partition-array-by-odd-and-even/description
 *
 * Partition an integers array into odd number first and even number second.
 * Example
 *
 * Example 1:
 *
 * Input: [1,2,3,4]
 * Output: [1,3,2,4]
 *
 * Example 2:
 *
 * Input: [1,4,2,3,5,6]
 * Output: [1,3,5,4,2,6]
 *
 * Challenge
 *
 * Do it in-place.
 * Notice
 *
 * The answer is not unique. All you have to do is give a valid answer.
 *
 * Note
 * 逆向双指针
 * 从左到右。 even pointer
 * 从右到左。 odd pointer
 *
 * 假如要保留原先的顺序
 * 同向双指针
 */
public class PartitionArrayByOddAndEven {
    /*
     * @param nums: an array of integers
     * @return: nothing
     */
    public void partitionArray(int[] nums) {
        int odd =nums.length -1, even =0;
        int temp =0;
        while(odd> even) {
            for(int i=even;i< nums.length;i++) {
                if(nums[i] % 2 ==0) {
                    even=i;
                    break;
                }
            }

            for(int i=odd;i>=0;i--) {
                if(nums[i] % 2 ==1) {
                    odd=i;
                    break;
                }
            }

            if(odd> even) {
                temp = nums[odd];
                nums[odd--]= nums[even];
                nums[even++]= temp;
            }
        }
    }
}
