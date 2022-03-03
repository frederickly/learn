package algorithms.bloomfilter;

import java.util.ArrayList;
import java.util.List;

/**
 * 555. Counting Bloom Filter
 * https://www.lintcode.com/problem/counting-bloom-filter/description
 * Implement a counting bloom filter. Support the following method:
 *
 *     add(string). Add a string into bloom filter.
 *     contains(string). Check a string whether exists in bloom filter.
 *     remove(string). Remove a string from bloom filter.
 *
 * Example
 *
 * Example1
 *
 * Input:
 * CountingBloomFilter(3)
 * add("lint")
 * add("code")
 * contains("lint")
 * remove("lint")
 * contains("lint")
 *
 * Output:
 * [true,false]
 *
 * Example2
 *
 * Input:
 * CountingBloomFilter(3)
 * add("lint")
 * add("lint")
 * contains("lint")
 * remove("lint")
 * contains("lint")
 *
 * Output:
 * [true,true]
 */
public class CountingBloomFilter {
    public int[] bits;
    public int k;
    public List<HashFunction> hashFunc;

    public CountingBloomFilter(int k) {
        this.k = k;
        hashFunc = new ArrayList<>();
        for (int i = 0; i < k; ++i) {
            // each algorithms.hash function has different cap and seed
            hashFunc.add(new HashFunction(100000 + i, 2 * i + 3));
        }
        bits = new int[100000 + k];
    }

    public void add(String word) {
        for (int i = 0; i < k; ++i) {
            int position = hashFunc.get(i).hash(word);
            bits[position] += 1;
        }
    }

    public void remove(String word) {

        for (int i = 0; i < k; ++i) {
            int position = hashFunc.get(i).hash(word);
            bits[position] -= 1;
        }
    }

    public boolean contains(String word) {
        for (int i = 0; i < k; ++i) {
            int position = hashFunc.get(i).hash(word);
            if (bits[position] <= 0)
                return false;
        }
        return true;
    }
}

class HashFunction {
    public int cap, seed;

    public HashFunction(int cap, int seed) {
        this.cap = cap;
        this.seed = seed;
    }

    public int hash(String value) {
        int ret = 0;
        int n = value.length();
        for (int i = 0; i < n; ++i) {
            ret += seed * ret + value.charAt(i);
            ret %= cap;
        }
        return ret;
    }
}
