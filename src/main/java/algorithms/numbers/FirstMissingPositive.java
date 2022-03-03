package algorithms.numbers;

import java.util.Arrays;

/**
 * 189. First Missing Positive
 * https://www.lintcode.com/problem/first-missing-positive/description
 *
 * Given an unsorted integer array, find the first missing positive integer.
 * Example
 *
 * Example 1:
 *
 * Input:[1,2,0]
 * Output:3
 *
 * Example 2:
 *
 * Input:[3,4,-1,1]
 * Output:2
 *
 * Challenge
 *
 * Your algorithm should run in O(n) time and uses constant space.
 *
 * Solutions
 * 但是上面的解法不是 O(1) 的空间复杂度，所以需要另想一种解法，既然不能建立新的数组，那么只能覆盖原有数组，
 * 思路是把1放在数组第一个位置 nums[0]，2放在第二个位置 nums[1]，即需要把 nums[i] 放在 nums[nums[i] - 1]上，
 * 遍历整个数组，如果 nums[i] != i + 1, 而 nums[i] 为整数且不大于n，另外 nums[i] 不等于 nums[nums[i] - 1] 的话，将两者位置调换，
 * 如果不满足上述条件直接跳过，
 * 最后再遍历一遍数组，如果对应位置上的数不正确则返回正确的数.
 */
public class FirstMissingPositive {

    /**
     * @param A: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] A) {
        int n = A.length;
        int temp;
        for (int i = 0; i < n; ++i)
            while (A[i] > 0 && A[i] <= n && A[A[i] - 1] != A[i]) {
                temp= A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = temp;
            }
        for (int i = 0; i < n; ++i)
            if (A[i] != i + 1) return i + 1;
        return n + 1;
    }

    /**
     * @param A: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive_SortSolution(int[] A) {
        Arrays.sort(A);
        int count =1;
        for(int i=0;i< A.length;i++){
            if(A[i]< count) continue;
            if(A[i]== count) count++;
            if(A[i]> count ) return count;
        }
        return count++;
    }
}
