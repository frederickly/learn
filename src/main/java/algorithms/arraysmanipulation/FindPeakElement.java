package algorithms.arraysmanipulation;

/**
 * 75. Find Peak Element
 * https://www.lintcode.com/problem/find-peak-element/description
 *
 * There is an integer array which has the following features:
 *
 *     The algorithms.numbers in adjacent positions are different.
 *     A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
 *
 * We define a position P is a peak if:
 *
 * A[P] > A[P-1] && A[P] > A[P+1]
 *
 * Find a peak element in this array. Return the index of the peak.
 * Example
 *
 * Example 1:
 * 	Input:  [1, 2, 1, 3, 4, 5, 7, 6]
 * 	Output:  1 or 6
 *
 * 	Explanation:
 * 	return the index of peek.
 *
 *
 * Example 2:
 * 	Input: [1,2,3,4,1]
 * 	Output:  3
 *
 * Challenge
 *
 * Time complexity O(logN)
 * Notice
 *
 *     It's guaranteed the array has at least one peak.
 *     The array may contain multiple peeks, find any of them.
 *     The array has at least 3 algorithms.numbers in it.
 *
 * Solution:
 * 由于题目中提示了要用对数级的时间复杂度，那么我们就要考虑使用类似于二分查找法来缩短时间，
 * 由于只是需要找到任意一个峰值，那么我们在确定二分查找折半后中间那个元素后，和紧跟的那个元素比较下大小，
 * 如果大于，则说明峰值在前面，如果小于则在后面。这样就可以找到一个峰值了
 */
public class FindPeakElement {

    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        int start=1, end =A.length -2;
        int mid=0;
        while(start +1 < end){
            mid =(end-start) /2 + start;
            if(A[mid]< A[mid +1]){
                start= mid;
            }else{
                end= mid;
            }
        }
        return A[start]> A[end]? start: end;
    }

    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < nums[mid + 1]) left = mid + 1;
            else right = mid;
        }
        return right;
    }
}
