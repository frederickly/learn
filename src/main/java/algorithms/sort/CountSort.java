package algorithms.sort;

/**
 * https://www.geeksforgeeks.org/counting-sort/
 * Counting algorithms.sort is a sorting technique based on keys between a specific range.
 * It works by counting the number of objects having distinct key values (kind of hashing).
 * Then doing some arithmetic to calculate the position of each object in the output sequence.
 * Let us understand it with the help of an example.
 * <p>
 * <p>
 * For simplicity, consider the data in the range 0 to 9.
 * Input data: 1, 4, 1, 2, 7, 5, 2
 * 1) Take a count array to store the count of each unique object.
 * Index:     0  1  2  3  4  5  6  7  8  9
 * Count:     0  2  2  0   1  1  0  1  0  0
 * <p>
 * 2) Modify the count array such that each element at each index
 * stores the sum of previous counts.
 * Index:     0  1  2  3  4  5  6  7  8  9
 * Count:     0  2  4  4  5  6  6  7  7  7
 * <p>
 * The modified count array indicates the position of each object in
 * the output sequence.
 * <p>
 * 3) Output each object from the input sequence followed by
 * decreasing its count by 1.
 * Process the input data: 1, 4, 1, 2, 7, 5, 2. Position of 1 is 2.
 * Put data 1 at index 2 in output. Decrease count by 1 to place
 * next data 1 at an index 1 smaller than this index.
 *
 *
 * Points to be noted:
 * 1. Counting algorithms.sort is efficient if the range of input data is not significantly greater than the number of objects to be sorted.
 *    Consider the situation where the input sequence is between range 1 to 10K and the data is 10, 5, 10K, 5K.
 * 2. It is not a comparison based sorting. It running time complexity is O(n) with space proportional to the range of data.
 * 3. It is often used as a sub-routine to another sorting algorithm like radix algorithms.sort.
 * 4. Counting algorithms.sort uses a partial hashing to count the occurrence of the data object in O(1).
 * 5. Counting algorithms.sort can be extended to work for negative inputs also.
 * Exercise:
 * 1. Modify above code to algorithms.sort the input data in the range from M to N.
 * 2. Is counting algorithms.sort stable and online?
 * 3. Thoughts on parallelizing the counting algorithms.sort algorithm.
 *
 * Counting Sort: https://brilliant.org/wiki/counting-sort/
 *
 * Counting Sort Animation: https://www.cs.usfca.edu/~galles/visualization/CountingSort.html
 */
// Java implementation of Counting Sort
class CountingSort {
    void sort(char[] arr) {
        int n = arr.length;

        // The output character array that will have sorted arr
        char[] output = new char[n];

        // Create a count array to store count of individual
        // characters and initialize count array as 0
        int[] count = new int[256];
        for (int i = 0; i < 256; ++i)
            count[i] = 0;

        // store count of each character
        for (int i = 0; i < n; ++i)
            ++count[arr[i]];

        // Change count[i] so that count[i] now contains actual
        // position of this character in output array
        for (int i = 1; i <= 255; ++i)
            count[i] += count[i - 1];

        // Build the output character array
        // To make it stable we are operating in reverse order.
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        // Copy the output array to arr, so that arr now
        // contains sorted characters
        for (int i = 0; i < n; ++i)
            arr[i] = output[i];
    }

    // Driver method
    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();
        char[] arr = {'g', 'e', 'e', 'k', 's', 'f', 'o',
                'r', 'g', 'e', 'e', 'k', 's'};

        countingSort.sort(arr);

        System.out.print("Sorted character array is ");
        for (int i = 0; i < arr.length; ++i)
            System.out.print(arr[i]);
    }
}
/*This code is contributed by Rajat Mishra */
