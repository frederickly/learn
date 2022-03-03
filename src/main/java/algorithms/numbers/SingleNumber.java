package algorithms.numbers;

/**
 * 82. Single Number
 * https://www.lintcode.com/problem/single-number/description
 *
 * Given 2 * n + 1 algorithms.numbers, every algorithms.numbers occurs twice except one, find it.
 * Example
 *
 * Example 1:
 *
 * Input：[1,1,2,2,3,4,4]
 * Output：3
 * Explanation:
 * Only 3 appears once
 *
 * Example 2:
 *
 * Input：[0,0,1]
 * Output：1
 * Explanation:
 * Only 1 appears once
 *
 * Challenge
 *
 * One-pass, constant extra space.
 * Notice
 *
 *     n≤100
 */
public class SingleNumber {

    /**
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] A) {
        // write your code here
        int result =0;
        for(int i=0;i<A.length;i++) {
            result = result ^ A[i];

        }
        return result;
    }
}
