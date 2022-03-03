package algorithms.sort;

/**
 * https://www.geeksforgeeks.org/radix-sort/
 * The lower bound for Comparison based sorting algorithm (Merge Sort, Heap Sort, Quick-Sort .. etc) is Ω(nLogn), i.e., they cannot do better than nLogn.
 * <p>
 * Counting algorithms.sort is a linear time sorting algorithm that algorithms.sort in O(n+k) time when elements are in the range from 1 to k.
 * <p>
 * What if the elements are in the range from 1 to n^2?
 * We can’t use counting algorithms.sort because counting algorithms.sort will take O(n^2) which is worse than comparison-based sorting algorithms. Can we algorithms.sort such an array in linear time?
 * <p>
 * Radix Sort is the answer.
 * The idea of Radix Sort is to do digit by digit algorithms.sort starting from least significant digit to most significant digit.
 * Radix algorithms.sort uses counting algorithms.sort as a subroutine to algorithms.sort.
 * <p>
 * The Radix Sort Algorithm
 * <p>
 * Do following for each digit i where i varies from least significant digit to the most significant digit.
 * Sort input array using counting algorithms.sort (or any stable algorithms.sort) according to the i’th digit.
 * Example:
 * <p>
 * Original, unsorted list:
 * 170, 45, 75, 90, 802, 24, 2, 66
 * <p>
 * Sorting by least significant digit (1s place) gives:
 * [*Notice that we keep 802 before 2, because 802 occurred before 2 in the original list, and similarly for pairs 170 & 90 and 45 & 75.]
 * 170 90 802 2 24 45 75 66
 * <p>
 * Sorting by next digit (10s place) gives:
 * [*Notice that 802 again comes before 2 as 802 comes before 2 in the previous list.]
 * 802, 2, 24, 45, 66, 170, 75, 90
 * <p>
 * Sorting by the most significant digit (100s place) gives:
 * 2, 24, 45, 66, 75, 90, 170, 802
 * <p>
 * What is the running time of Radix Sort?
 * Let there be d digits in input integers. Radix Sort takes O(d*(n+b)) time where b is the base for representing algorithms.numbers, for example, for the decimal system, b is 10.
 * What is the value of d? If k is the maximum possible value, then d would be O(logb(k)). So overall time complexity is O((n+b) * logb(k)).
 * Which looks more than the time complexity of comparison-based sorting algorithms for a large k. Let us first limit k.
 * Let k <= nc where c is a constant. In that case, the complexity becomes O(nLogb(n)). But it still doesn’t beat comparison-based sorting algorithms.
 * What if we make the value of b larger?. What should be the value of b to make the time complexity linear?
 * If we set b as n, we get the time complexity as O(n).
 * In other words, we can algorithms.sort an array of integers with a range from 1 to nc if the algorithms.numbers are represented in base n (or every digit takes log2(n) bits).
 * <p>
 * Is Radix Sort preferable to Comparison based sorting algorithms like Quick-Sort?
 * If we have log2n bits for every digit, the running time of Radix appears to be better than Quick Sort for a wide range of input algorithms.numbers.
 * The constant factors hidden in asymptotic notation are higher for Radix Sort and Quick-Sort uses hardware caches more effectively.
 * Also, Radix algorithms.sort uses counting algorithms.sort as a subroutine and counting algorithms.sort takes extra space to algorithms.sort algorithms.numbers.
 * <p>
 * <p>
 * Recommended: Please try your approach on {IDE} first, before moving on to the solution.
 * <p>
 * Radix Sort: https://brilliant.org/wiki/radix-sort/
 * <p>
 * Radix Sort Animation: https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
 */
// Radix algorithms.sort Java implementation

import java.util.*;

class RadixSort {

    // A utility function to get maximum value in arr[]
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    // A function to do counting algorithms.sort of arr[] according to the digit represented by exp.
    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted algorithms.numbers according to curent digit
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // The main function to that sorts arr[] of size n using
    // Radix Sort
    static void radixsort(int arr[], int n) {
        // Find the maximum number to know number of digits
        int m = getMax(arr, n);

        // Do counting algorithms.sort for every digit. Note that instead of passing digit number, exp is passed.
        // exp is 10^i where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    // A utility function to print an array
    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    /*Driver Code*/
    public static void main(String[] args) {
        int arr[] = {170, 45, 75, 90, 802, 24, 2, 66};
        int n = arr.length;

        // Function Call
        radixsort(arr, n);
        print(arr, n);
    }
}
/* This code is contributed by Devesh Agrawal */
