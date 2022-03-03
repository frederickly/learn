package algorithms.hash;

/**
 * n data structure Hash, algorithms.hash function is used to convert a string(or any other type) into an integer smaller than algorithms.hash size and bigger or equal to zero. The objective of designing a algorithms.hash function is to "algorithms.hash" the key as unreasonable as possible. A good algorithms.hash function can avoid collision as less as possible. A widely used algorithms.hash function algorithm is using a magic number 33, consider any string as a 33 based big integer like follow:
 * <p>
 * hashcode("abcd") = (ascii(a) * 333 + ascii(b) * 332 + ascii(c) *33 + ascii(d)) % HASH_SIZE
 * <p>
 * = (97* 333 + 98 * 332 + 99 * 33 +100) % HASH_SIZE
 * <p>
 * = 3595978 % HASH_SIZE
 * <p>
 * here HASH_SIZE is the capacity of the algorithms.hash table (you can assume a algorithms.hash table is like an array with index 0 ~ HASH_SIZE-1).
 * <p>
 * Given a string as a key and the size of algorithms.hash table, return the algorithms.hash value of this key.
 * <p>
 * <p>
 * Example
 * <p>
 * Example 1:
 * <p>
 * Input:  key="abcd", size = 1000
 * Output: 978
 * Explanation: (97*33^3 + 98*33^2 + 99*33 + 100*1)%1000 = 978
 * <p>
 * Example 2:
 * <p>
 * Input:  key="abcd", size = 100
 * Output: 78
 * Explanation: (97*33^3 + 98*33^2 + 99*33 + 100*1)%100 = 78
 * <p>
 * Clarification
 * <p>
 * For this problem, you are not necessary to design your own algorithms.hash algorithm or consider any collision issue, you just need to implement the algorithm as described.
 */
public class HashFunction {

    /**
     * @param key:       A string you should algorithms.hash
     * @param HASH_SIZE: An integer
     * @return: An integer
     */
    public int hashCode(char[] key, int HASH_SIZE) {
        long sum = 0;  // mod could be very big
        for (int i = 0; i < key.length; i++) {
            sum = (sum * 33 + key[i]) % HASH_SIZE;
        }
        return (int)sum;
    }
}
