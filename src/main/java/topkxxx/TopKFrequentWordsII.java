package topkxxx;

import java.util.*;

/**
 * 550. Top K Frequent Words II
 * https://www.lintcode.com/problem/top-k-frequent-words-ii/description
 *
 * Find top k frequent words in realtime data stream.
 *
 * Implement three methods for Topk Class:
 *
 *     TopK(k). The constructor.
 *     add(word). Add a new word.
 *     topk(). Get the current top k frequent words.
 *
 * Example
 *
 * Example 1:
 *
 * Input：
 * TopK(2)
 * add("lint")
 * add("code")
 * add("code")
 * topk()
 * Output：["code", "lint"]
 * Explanation：
 * "code" appears twice and "lint" appears once, they are the two most frequent words.
 *
 * Example 2:
 *
 * Input：
 * TopK(1)
 * add("aa")
 * add("ab")
 * topk()
 * Output：["aa"]
 * Explanation：
 * "aa" and "ab" appear once , but aa's dictionary order is less than ab's.
 *
 * Notice
 *
 * If two words have the same frequency, rank them by dictionary order.
 */
public class TopKFrequentWordsII {
    private Map<String, Integer> words = null;
    private NavigableSet<String> topk = null;
    private int k;

    private Comparator<String> myComparator = new Comparator<String>() {
        public int compare(String left, String right) {
            if (left.equals(right)) return 0;

            int left_count = words.get(left);
            int right_count = words.get(right);
            if (left_count != right_count)
                return right_count - left_count;
            return left.compareTo(right);
        }
    };

    public TopKFrequentWordsII(int k) {
        this.k = k;
        words = new HashMap<>();
        topk = new TreeSet<>(myComparator);
    }

    public void add(String word) {
        if (words.containsKey(word)) {
            if (topk.contains(word))
                topk.remove(word);
            words.put(word, words.get(word) + 1);
        } else {
            words.put(word, 1);
        }

        topk.add(word);
        if (topk.size() > k)
            topk.pollLast();
    }

    public List<String> topk() {
        List<String> results = new ArrayList<>();
        Iterator it = topk.iterator();
        while(it.hasNext())
            results.add((String)it.next());
        return results;
    }
}
