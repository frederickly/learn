package arraysmanipulation;

import java.util.HashMap;
import java.util.Map;

/**
 * 1854. Partition Array III
 * 中文
 * English
 * https://www.lintcode.com/problem/partition-array-iii/leaderboard
 * Given an array of numbers, you are required to check if it is possible to partition the array into some subsequences of length k each, such that:
 *
 *     Each element in the array occurs in exactly one subsequence
 *     All the numbers in a subsequence are distinct
 *     Elements in the array having the same value must be in different subsequences
 *
 * Is possible to partition the array satisfying the above conditions? If it is possible, return true, else false.
 *
 * The length of array <= 10^5
 * Example
 *
 * Example 1:
 *
 * input: array=[1,2,3,4], k = 2
 *
 * output:true
 *
 * Example 2:
 *
 * input: array=[1,2,2,3], k = 3
 *
 * output:false
 */
public class PartitionArrayIII {
    /**
     * @param array: the input array
     * @param k: the sequence length
     * @return: if it is possible, return true, otherwise false
     */
    public boolean partitionArratIII(int[] array, int k) {
        if(array.length%k !=0) return false;
        int numOfSub = array.length / k;
        Map<Integer, Integer> valCountMap = new HashMap<>();
        for(int i=0;i< array.length;i++) {
            if(valCountMap.containsKey(array[i])){
                valCountMap.put(array[i], valCountMap.get(array[i])+1);
            }else{
                valCountMap.put(array[i], 1);
            }
            if(valCountMap.get(array[i])> numOfSub) return false;
        }

        return valCountMap.keySet().size() >=k;
    }
}
