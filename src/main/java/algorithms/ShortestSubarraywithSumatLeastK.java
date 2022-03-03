package algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * 1507. Shortest Subarray with Sum at Least K
 * https://www.lintcode.com/problem/shortest-subarray-with-sum-at-least-k
 *
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 *
 * If there is no non-empty subarray with sum at least K, return -1.
 * Example
 *
 * Example 1:
 *
 * Input: A = [1], K = 1
 * Output: 1
 *
 * Example 2:
 *
 * Input: A = [1,2], K = 4
 * Output: -1
 *
 * Notice
 *
 *     1≤A.length≤500001 \leq A.length \leq 500001≤A.length≤50000
 *     −105≤A[i]≤105-10 ^ 5 \leq A[i] \leq 10 ^ 5−10​5​​≤A[i]≤10​5​​
 *     1≤K≤1091 \leq K \leq 10 ^ 91≤K≤10​9​​
 */
public class ShortestSubarraywithSumatLeastK {
    /**
     * @param A: the array
     * @param K: sum
     * @return: the length
     */
    public int shortestSubarray(int[] A, int K) {
        int N = A.length, res = N + 1;
        int[] B = new int[N + 1];
        for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i <= N ; i++) {
            while (d.size() > 0 && B[i] - B[d.getFirst()] >= K){
                res = Math.min(res, i - d.pollFirst());
            }
            while (d.size() > 0 && B[i] <= B[d.getLast()]) {
                d.pollLast();
            }
            d.addLast(i);
        }
        return res <= N ? res : -1;
    }
}

