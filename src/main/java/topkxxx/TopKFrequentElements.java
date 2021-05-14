package topkxxx;

import java.util.*;

/**
 * 1281. Top K Frequent Elements
 * https://www.lintcode.com/problem/top-k-frequent-elements/description
 *
 * Given a non-empty array of integers, return the k most frequent elements.
 * Example
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 *
 * Example 2:
 *
 * Input: nums = [1], k = 1
 * Output: [1]
 *
 * Notice
 *
 *     You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *     Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    /**
     * @param nums: the given array
     * @param k:    the given k
     * @return: the k most frequent elements
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counters = new HashMap<>();
        for (int v : nums)
            counters.put(v, counters.getOrDefault(v, 0) + 1);

        Queue<Element> queue = new PriorityQueue<Element>((v1, v2) -> v2.count - v1.count);

        for (Map.Entry<Integer, Integer> entry : counters.entrySet())
            queue.offer(new Element(entry.getKey(), entry.getValue()));

        List<Integer> res = new ArrayList<>();

        while (k > 0) {
            k--;
            res.add(queue.poll().value);
        }
        return res;
    }

    class Element {
        public int value;
        public int count;

        public Element(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
