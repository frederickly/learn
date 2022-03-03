package algorithms.arraysmanipulation;

import java.util.ArrayList;
import java.util.List;

/**
 * 793. Balanced Sales Array
 * https://www.lintcode.com/problem/balanced-sales-array/description
 *
 * Given has an array of sales algorithms.numbers, what is the index of the smallest index element for which the sums of all elements to the left and to the right are equal. The array may not be reordered.
 * For example, given the array sales = [1, 2, 3, 4, 6],we see that 1+2+3=6，Using zero based indexing,sales[3] = 4 is the value sought.The index is 3.
 * Example
 *
 * Example:
 * Input : [1, 2, 3, 4, 6]
 * Output : 3
 *
 * Notice
 *
 *     3 <= n <= 10^5
 *     1 <= sales[i] <= 2*10^4,where 0 <= i<n
 *     It is guaranteed that a solution always exists
 *
 * Solution:
 * 两个指针，分别从开头和结尾开始遍历，遍历过程中求和并判断和是否相等。
 * 也可以用前缀和数组，遍历两边数组，不过空间和时间都更长。
 */
public class BalancedSalesArray {
    /**
     * @param sales: a integer array
     * @return: return a Integer
     */
    public int BalancedSalesArray(int[] sales) {
        List<Integer> presum= new ArrayList<>(sales.length);
        List<Integer> postsum= new ArrayList<>(sales.length);

        int sum = 0;
        for(int i = 0; i < sales.length; ++i) {
            sum += sales[i];
            presum.add(sum);
        }

        sum = 0;
        for(int i = sales.length - 1; i >= 0; --i) {
            sum += sales[i];
            postsum.add(0, sum);
        }

        for(int i = 1; i < sales.length - 1; ++i)
            if(presum.get(i - 1) - postsum.get(i + 1)==0)
                return i;
        return 0;
    }
}
