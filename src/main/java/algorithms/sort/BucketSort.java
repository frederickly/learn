package algorithms.sort;

/**
 * Bucket algorithms.sort is mainly useful when input is uniformly distributed over a range.
 * For example, consider the following problem.
 * Sort a large set of floating point algorithms.numbers which are in range from 0.0 to 1.0 and are uniformly distributed across the range.
 * How do we algorithms.sort the algorithms.numbers efficiently?
 * <p>
 * A simple way is to apply a comparison based sorting algorithm.
 * The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) is Ω(n Log n),
 * i.e., they cannot do better than nLogn.
 * <p>
 * Can we algorithms.sort the array in linear time? Counting algorithms.sort can not be applied here as we use keys as index in counting algorithms.sort.
 * Here keys are floating point algorithms.numbers.
 * <p>
 * The idea is to use bucket algorithms.sort. Following is bucket algorithm.
 * <p>
 * bucketSort(arr[], n)
 * 1) Create n empty buckets (Or lists).
 * 2) Do following for every array element arr[i].
 * .......a) Insert arr[i] into bucket[n*array[i]]
 * 3) Sort individual buckets using insertion algorithms.sort.
 * 4) Concatenate all sorted buckets.
 * BucketSort
 * <p>
 * Time Complexity: If we assume that insertion in a bucket takes O(1) time then steps 1 and 2 of the above algorithm clearly take O(n) time.
 * The O(1) is easily possible if we use a linked list to represent a bucket (In the following code, C++ vector is used for simplicity).
 * Step 4 also takes O(n) time as there will be n items in all buckets.
 * The main step to analyze is step 3. This step also takes O(n) time on average
 * if all algorithms.numbers are uniformly distributed (please refer CLRS book for more details)
 * Following is the implementation of the above algorithm.
 */
// Java program to algorithms.sort an array
// using bucket algorithms.sort

import java.util.Collections;
import java.util.Vector;

class BucketSort {

    // Function to algorithms.sort arr[] of size n   using bucket algorithms.sort
    static void bucketSort(float[] arr, int n) {
        if (n <= 0)
            return;

        // 1) Create n empty buckets
        @SuppressWarnings("unchecked")
        Vector<Float>[] buckets = new Vector[n];

        for (int i = 0; i < n; i++)
            buckets[i] = new Vector<>();

        // 2) Put array elements in different buckets
        for (int i = 0; i < n; i++) {
            float idx = arr[i] * n;
            buckets[(int) idx].add(arr[i]);
        }

        // 3) Sort individual buckets
        for (int i = 0; i < n; i++)
            Collections.sort(buckets[i]);

        // 4) Concatenate all buckets into arr[]
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++)
                arr[index++] = buckets[i].get(j);
        }
    }

    // Driver code
    public static void main(String args[]) {
        float[] arr = {(float) 0.897, (float) 0.565,
                (float) 0.656, (float) 0.1234,
                (float) 0.665, (float) 0.3434};

        bucketSort(arr, arr.length);

        System.out.println("Sorted array is ");
        for (float el : arr)
            System.out.print(el + " ");
    }
}

// This code is contributed by Himangshu Shekhar Jha
