/**
 * 907. Array Game
 * https://www.lintcode.com/problem/array-game/description
 *
 * Given an array of integers, determine the number of moves to make all elements equals. Each move consists of choosing all but 1 element and incrementing their values by 1.
 * Example
 *
 * Example 1
 *
 * Input: [3, 4, 6, 6, 3]
 * Output: 7
 * Explanation: [3, 4, 6, 6, 3] -> [4, 5, 7, 6, 4] -> [5, 6, 7, 7, 5] -> [6, 7, 8, 7, 6] -> [7, 8, 8, 8, 7] -> [8, 9, 9, 8, 8] -> [9, 9, 10, 9, 9] -> [10, 10, 10, 10, 10]
 *
 * Notice
 *
 * 1≤len(arr)≤10^5
 * 0≤arr[i[≤10^9
 *
 * 把其它所有数+1 等价于 把当前数-1
 * 也就是说每一步只能对元素-1
 * 所以最优解就是把所有元素减到最小的数
 *
 * 首先找到最小的数
 * 然后累加每个元素和最小值的差即可
 * 注意返回值是long
 */
public class ArrayGameMakeAllEquals {
    /**
     * @param arr: the array
     * @return: determine the number of moves to make all elements equals
     */
    public long arrayGame(int[] arr) {
        int min= arr[0];
        for(int v : arr)
            if(v<min) min= v;

        long result=0;
        for(int v: arr){
            result+= v- min;
        }
        return result;
    }
}
