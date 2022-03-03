package algorithms;

/**
 * 65. algorithms.Median of two Sorted Arrays
 * https://www.lintcode.com/problem/median-of-two-sorted-arrays
 *
 * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.
 * Example
 *
 * Example 1
 *
 * Input:
 * A = [1,2,3,4,5,6]
 * B = [2,3,4,5]
 * Output: 3.5
 *
 * Example 2
 *
 * Input:
 * A = [1,2,3]
 * B = [4,5]
 * Output: 3
 *
 * Challenge
 *
 * The overall run time complexity should be O(log (m+n)).
 * Clarification
 *
 *     The definition of the median:
 *
 *     The median here is equivalent to the median in the mathematical definition.
 *
 *     The median is the middle of the sorted array.
 *
 *     If there are n algorithms.numbers in the array and n is an odd number, the median is A[(n-1)/2].
 *
 *     If there are n algorithms.numbers in the array and n is even, the median is (A[n / 2] + A[n / 2 + 1]) / 2.
 *
 *     For example, the median of the array A=[1,2,3] is 2, and the median of the array A=[1,19] is 10.
 */
public class MedianofTwoSortedArrays {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int len = A.length+ B.length;
        if(len%2==0) {
            return (findMedian(A, 0, B, 0, len/2) +  findMedian(A, 0, B, 0, len/2+1))/2.0;
        }
        return findMedian(A, 0, B, 0, len/2+1);
    }

    private int findMedian(int[] A, int startA, int[] B, int startB, int k) {
        if(startA>= A.length) return B[startB+k-1];
        if(startB>= B.length) return A[startA+k-1];
        if(k==1) return Math.min(A[startA], B[startB]);

        int half= k/2;

        int kthA= startA + half -1< A.length?  A[ startA+ half-1]:Integer.MAX_VALUE;
        int kthB= startB + half -1< B.length?  B[ startB+ half-1]:Integer.MAX_VALUE;

        if(kthA< kthB) {
            return findMedian(A, startA+ half, B, startB, k- half);
        }else{
            return findMedian(A, startA, B, startB+ half, k- half);
        }

    }
}
