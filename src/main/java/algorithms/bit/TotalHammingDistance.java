package algorithms.bit;

/**
 * https://www.lintcode.com/problem/total-hamming-distance/description   1217
 * Description
 * 中文
 * English
 *
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Now your job is to find the total Hamming distance between all pairs of the given algorithms.numbers.
 *
 *     Elements of the given array are in the range of 0 to 10^9
 *     Length of the array will not exceed 10^4.
 *
 * Have you met this question in a real interview?
 * Example
 *
 * Example 1:
 *
 * Input: [4, 14, 2]
 * Output: 6
 * Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 *
 * Example 2:
 *
 * Input: [2, 1, 0]
 * Output: 4
 * Explanation: In binary representation, the 2 is 10, 1 is 01, and 0 is 00 (just
 * showing the four bits relevant in this case). So the answer will be:
 * HammingDistance(2, 1) + HammingDistance(1, 0) + HammingDistance(2, 0) = 2 + 1 + 1 = 4.
 *
 *
 *
 * 直观思路：两两比较找到位数不一样的个数 -> 超时
 * 换个角度：所有的数的每一个位上一起比较，找出i位上1，0的个数，那么不同的个数即是每次挑的两个数分别一个是1一个是0的时候，这样的组合有多少个的问题 -> Cn1 * Cn0
 */
public class TotalHammingDistance {

    /**
     * @param nums: the gievn integers
     * @return: the total Hamming distance between all pairs of the given algorithms.numbers
     */
    public int totalHammingDistance(int[] nums) {
        int total = 0, n = nums.length;
        for (int j = 0; j < 32; j++) {
            int bitCount = 0;
            for (int i = 0; i < n; i++)
                bitCount += (nums[i] >> j) & 1;
            total += bitCount*(n - bitCount);
        }
        return total;
    }
}
