import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 76. Longest Increasing Subsequence
 * https://www.lintcode.com/problem/longest-increasing-subsequence
 *
 * Given a sequence of integers, find the longest increasing subsequence (LIS).
 *
 * You code should return the length of the LIS.
 * Example
 *
 * Example 1:
 * 	Input:  [5,4,1,2,3]
 * 	Output:  3
 *
 * 	Explanation:
 * 	LIS is [1,2,3]
 *
 *
 * Example 2:
 * 	Input: [4,2,4,5,3,7]
 * 	Output:  4
 *
 * 	Explanation:
 * 	LIS is [2,4,5,7]
 *
 * Challenge
 *
 * Time complexity O(n^2) or O(nlogn)
 * Clarification
 *
 * What's the definition of longest increasing subsequence?
 *
 *     The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.
 *
 *     https://en.wikipedia.org/wiki/Longest_increasing_subsequence
 */
public class LongestIncreasingSubsequence {
    public int longestIncreasingSubsequence(int[] nums) {
        List<Integer> piles = new ArrayList<>(nums.length);
        for (int num : nums) {
            int pile = Collections.binarySearch(piles, num);
            if (pile < 0) pile = ~pile;
            if (pile == piles.size()) {
                piles.add(num);
            } else {
                piles.set(pile, num);
            }
        }
        return piles.size();
    }

    public int longestIncreasingSubsequenceDP(int[] nums) {
        if(nums.length==0) return 0;
        int[] dp= new int[nums.length];
        dp[0]=1;
        int max=1;
        for(int i=1;i<nums.length;i++){
            dp[i]=1;
            for(int j=0; j<i;j++) {
                if(nums[i]> nums[j]) {
                    if(dp[j]+1> dp[i]) {
                        dp[i]=dp[j]+1;
                    }
                }
            }

            if(dp[i]>max) max= dp[i];
        }
        return max;
    }
}
