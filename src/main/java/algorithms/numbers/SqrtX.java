package algorithms.numbers;

/**
 *
 * 141. Sqrt(x)  https://www.lintcode.com/problem/sqrtx/description
 * 中文
 * English
 *
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x.
 * Example
 *
 * Example 1:
 * 	Input:  0
 * 	Output: 0
 *
 *
 * Example 2:
 * 	Input:  3
 * 	Output: 1
 *
 * 	Explanation:
 * 	return the largest integer y that y*y <= x.
 *
 * Example 3:
 * 	Input:  4
 * 	Output: 2
 *
 *
 * Challenge
 *
 * O(log(x))
 */
public class SqrtX {

    /**
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
        if(x== 0){
            return 0;
        }

        int start =0;
        int end = x;
        int middle=0;
        while(start+1 <end){

            middle = (start + end) /2;

            if(middle <= x/ middle){
                start= middle;
            }else{
                end = middle;
            }
        }

        if(end <= x /end){
            return end;
        }else{
            return start;
        }

    }
}
