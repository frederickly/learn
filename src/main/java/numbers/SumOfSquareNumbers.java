package numbers;

/**
 * 697. Sum of Square Numbers
 * https://www.lintcode.com/problem/sum-of-square-numbers/note/144554
 * Given a integer c, your task is to decide whether there're two integers a and b such that a^2 + b^2 = c.
 * Example
 *
 * Example 1:
 *
 * Input : n = 5
 * Output : true
 * Explanation :
 * 1 * 1 + 2 * 2 = 5
 *
 * Example 2:
 *
 * Input : n = -5
 * Output : false
 */
public class SumOfSquareNumbers {
    /**
     * @param num: the given number
     * @return: whether whether there're two integers
     */
    public boolean checkSumOfSquareNumbers(int num) {
        if(num< 0) return false;
        double b = 0;
        for(int i=0;i<=Math.sqrt(num);i++){
            b = num - i*i;
            if(Math.sqrt(b)==(int)Math.sqrt(b))
                return true;
        }
        return false;
    }
}
