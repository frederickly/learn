package bit;

/**
 * https://www.lintcode.com/problem/179/
 * Update Bits
 * Description
 *
 * Given two 32-bit numbers, N and M, and two bit positions, i and j.
 * Write a method to set all bits between i and j in N equal to M (e g , M becomes a substring of N start from i to j)
 *
 * In the function, the numbers N and M will given in decimal, you should also return a decimal number.
 * You can assume that the bits j through i have enough space to fit all of M. That is, if M=10011，
 * you can assume that there are at least 5 bits between j and i.
 * You would not, for example, have j=3 and i=2, because M could not fully fit between bit 3 and bit 2.
 * Example
 *
 * Example 1:
 *
 * Input: N=(10000000000)2 M=(10101)2 i=2 j=6
 *
 * Output: N=(10001010100)2
 *
 * Example 2:
 *
 * Input: N=(10000000000)2 M=(11111)2 i=2 j=6
 *
 * Output: N=(10001111100)2
 *
 * Challenge
 * Minimum number of operations?
 *
 * 记住i是在低位， j是在高位
 * 第一步是将n中从i到j之间的位清零， idea是做一个mask, 大于j的位为1和小于i的位为1, 然后将这个mask和n相与=
 * 第二步是将m填到n中i到j的区间里。idea是将m左移i位，然后或上加完mask的n。
 */
public class UpdateBitsSolution {
    public class Solution {
        /**
         * @param n: An integer
         * @param m: An integer
         * @param i: A bit position
         * @param j: A bit position
         * @return: An integer
         */
        public int updateBits(int n, int m, int i, int j) {
            long hMask=~((1L<<(j+1)) -1);
            long lMask= (1L<<i) -1;
            long mask = hMask | lMask;
            n&= mask;
            return n | m<<i;
        }
    }
}

