package algorithms.topkxxx;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 471. Top K Frequent Words
 * https://www.lintcode.com/problem/top-k-frequent-words/note
 *
 * Given a list of words and an integer k, return the top k frequent words in the list.
 * Example
 *
 * Example 1:
 *
 * Input:
 *   [
 *     "yes", "lint", "code",
 *     "yes", "code", "baby",
 *     "you", "baby", "chrome",
 *     "safari", "lint", "code",
 *     "body", "lint", "code"
 *   ]
 *   k = 3
 * Output: ["code", "lint", "baby"]
 *
 * Example 2:
 *
 * Input:
 *   [
 *     "yes", "lint", "code",
 *     "yes", "code", "baby",
 *     "you", "baby", "chrome",
 *     "safari", "lint", "code",
 *     "body", "lint", "code"
 *   ]
 *   k = 4
 * Output: ["code", "lint", "baby", "yes"]
 *
 * Challenge
 *
 * Do it in O(nlogk) time and O(n) extra space.
 * Notice
 *
 * You should order the words by the frequency of them in the return list,
 * the most frequent one comes first.
 * If two words has the same frequency,
 * the one with lower alphabetical order come first.
 */
public class TopKFrequentWords {
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    public String[] topKFrequentWords(String[] words, int k) {
        PriorityQueue<Element> queue= new PriorityQueue<>(comparator);
        Map<String, Integer> map =new HashMap<>();
        for(String word : words)
            map.put(word, map.getOrDefault(word, 0)+1);

        for(Map.Entry<String, Integer> entry: map.entrySet())
            queue.offer(new Element(entry.getKey(), entry.getValue()));

        String[] res = new String[k];
        int size = Math.min(queue.size(), k);
        for(int i=0;i<size;i++)
            res[i]= queue.poll().word;
        return res;
    }

    private Comparator<Element> comparator = new Comparator<Element>() {
        public int compare(Element left, Element right) {
            if (left.count != right.count)
                return right.count - left.count;
            return left.word.compareTo(right.word);
        }
    };

    class Element{
        public String word;
        public int count;
        public Element(String word, int count){
            this.word=word;
            this.count=count;
        }
    }
}
