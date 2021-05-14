package bloomfilter;

import java.util.Arrays;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 556. Standard Bloom Filter
 * https://www.lintcode.com/problem/standard-bloom-filter/description
 * Implement a standard bloom filter. Support the following method:
 *
 *     StandardBloomFilter(k) The constructor and you need to create k hash functions.
 *     add(string) Add a string into bloom filter.
 *     contains(string) Check a string whether exists in bloom filter.
 *
 * Example
 *
 * Example1
 *
 * Input:
 *     StandardBloomFilter(3)
 *     add("lint")
 *     add("code")
 *     contains("lint")
 *     contains("world")
 * Output: [true,false]
 *
 * Example2
 *
 * Input:
 *     StandardBloomFilter(10)
 *     add("hello")
 *     contains("hell")
 *     contains("helloa")
 *     contains("hello")
 *     contains("hell")
 *     contains("helloa")
 *     contains("hello")
 * Output: [false,false,true,false,false,true]
 */
public class StandardBloomFilter {

    private static final int DEFAULT_SIZE = 2 << 24;//布隆过滤器的比特长度

    private static int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};

    private static BitSet bits = new BitSet(DEFAULT_SIZE);

    private static SimpleHash[] func = new SimpleHash[seeds.length];
    /*
     * @param k: An integer
     */
    public StandardBloomFilter(int k) {
        func = new SimpleHash[k];
        seeds= new int[k];
        List<Integer> primes = sieveOfEratosthenes(1000000, k);
        for(int i=0;i<k;i++) {
            seeds[i]= primes.get(i);
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
        }
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        if (word != null) {
            for (SimpleHash f :func)
                bits.set(f.hash(word), true);
        }
    }

    /*
     * @param word: A string
     * @return: True if contains word
     */
    public boolean contains(String word) {

        if (word == null) return false;

        boolean ret = true;

        for (SimpleHash f : func)//这里其实没必要全部跑完，只要一次ret==false那么就不包含这个字符串

            ret = ret && bits.get(f.hash(word));

        return ret;

    }

    public static List<Integer> sieveOfEratosthenes(int n, int k) {
        boolean prime[] = new boolean[n + 1];
        Arrays.fill(prime, true);

        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * 2; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        List<Integer> primeNumbers = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primeNumbers.add(i);
                if(primeNumbers.size()==k) break;
            }
        }
        return primeNumbers;
    }

    class SimpleHash {
        private int cap;
        private int seed;

        public SimpleHash(int cap, int seed) {
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value) {//字符串哈希，选取好的哈希函数很重要

            int result = 0;

            int len = value.length();

            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
                result%= cap;
            }

            return (cap - 1) & result;

        }

    }
}
