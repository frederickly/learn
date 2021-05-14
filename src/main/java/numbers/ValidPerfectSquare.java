package numbers;

/**
 * 777. Valid Perfect Square
 * https://www.lintcode.com/problem/valid-perfect-square/description
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Example
 *
 * Example1:
 *
 * Input: num = 16
 * Output: True
 * Explanation:
 * sqrt(16) = 4
 *
 * Example2:
 *
 * Input: num = 15
 * Output: False
 * Explanation:
 * sqrt(15) = 3.87
 *
 * Notice
 *
 * Do not use any built-in library function such as sqrt.
 */
public class ValidPerfectSquare {

    /**
     * @param num: a positive integer
     * @return: if num is a perfect square else False
     */
    public boolean isPerfectSquare(int num) {
        int start =0;
        int end = num;
        int middle=0;
        while(start+1 <end){
            middle = (start + end) /2;
            if(middle <= num/ middle){
                start= middle;
            }else{
                end = middle;
            }
        }

        if(end * end == num){
            return true;
        }else if (start * start == num){
            return true;
        }
        return false;
    }
}
