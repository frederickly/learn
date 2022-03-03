package algorithms.topkxxx;

import java.util.*;

/**
 * 1883. Top K Frequently Mentioned Keywords
 * https://www.lintcode.com/problem/top-k-frequently-mentioned-keywords/description
 *
 * Given a list of reviews, a list of keywords and an integer k . Find the top k keywords that appear most frequently in different reviews and in order of most to least frequently mentioned.The comparison of strings is case-insensitive. If keywords are mentioned an equal number of times in reviews, algorithms.sort alphabetically.
 * Example
 *
 * Example 1:
 * Input:
 * k = 2
 * keywords = ["anacell", "cetracular", "betacellular"]
 * reviews = [
 *   "Anacell provides the best services in the city",
 *   "betacellular has awesome services",
 *   "Best services provided by anacell, everyone should use anacell",
 * ]
 * Output:
 * ["anacell", "betacellular"]
 * Explanation:
 * "anacell" is occuring in 2 different reviews and "betacellular" is only occuring in 1 review.
 *
 * Example 2:
 * Input:
 * k = 2
 * keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
 * reviews = [
 *   "I love anacell Best services; Best services provided by anacell",
 *   "betacellular has great services",
 *   "deltacellular provides much better services than betacellular",
 *   "cetracular is worse than anacell",
 *   "Betacellular is better than deltacellular.",
 * ]
 * Output:
 * ["betacellular", "anacell"]
 * Explanation:
 * "betacellular" is occuring in 3 different reviews. "anacell" and "deltacellular" are occuring in 2 reviews, but "anacell" is lexicographically smaller.
 *
 * Notice
 *
 *     K is not guaranteed to be less than the length of the keywords
 *     the length of keywords within range: [1, 100]
 *     the length of reviews within range: [1, 1000]
 *     keywords [i] consists of lowercase letters
 *     reviews [i] consists of uppercase and lowercase letters and punctuation: [ "[", "\", "!", "?", ",", ";" , ".", "]", " "]
 *
 */
public class TopKFrequentlyMentionedKeywords {
    /**
     * @param K: a integer
     * @param keywords: a list of string
     * @param reviews: a list of string
     * @return: return the top k keywords list
     */
    public List<String> TopkKeywords(int K, String[] keywords, String[] reviews) {

        PriorityQueue<Element> queue= new PriorityQueue<>(comparator);
        Map<String, Integer> map =new HashMap<>();

        Set<String> keywordsMap = new HashSet<>();
        for(String key: keywords)
            keywordsMap.add(key);

        String key="";
        Set<String> keyWordsInreview = new HashSet<>();
        for(int i=0;i< reviews.length;i++){
            String[] words = reviews[i].split("\\W+");
            keyWordsInreview = new HashSet<>();
            for(int j=0;j<words.length;j++){
                key = words[j].toLowerCase();
                if(keywordsMap.contains(key) && !keyWordsInreview.contains(key)) {
                    map.put(key, map.getOrDefault(key, 0)+1);
                    keyWordsInreview.add(key);
                }
            }
        }

        for(Map.Entry<String, Integer> entry: map.entrySet())
            queue.offer(new Element(entry.getKey(), entry.getValue()));

        List<String> res = new ArrayList<>();
        int size = Math.min(queue.size(), K);
        //System.out.println("queue="+ queue);
        for(int i=0;i<size;i++)
            res.add(queue.poll().word);
        return res;
    }

    private Comparator<Element> comparator = (left, right) -> {
        if (left.count != right.count)
            return right.count - left.count;
        return left.word.compareTo(right.word);
    };

    class Element{
        public String word;
        public int count;
        public Element(String word, int count){
            this.word=word;
            this.count=count;
        }
        public String toString(){
            return word+"="+ count;
        }
    }
}
