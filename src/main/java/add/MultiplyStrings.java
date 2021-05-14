package add;

/**
 *Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2
 * Example
 *
 * Example1
 *
 * Input:
 * "123"
 * "45"
 * Output:
 * "5535"
 * Explanation:
 * 123 x 45 = 5535
 *
 * Example2
 *
 * Input:
 * "0"
 * "0"
 * Output:
 * "0"
 *
 * Notice
 *
 *     The length of both num1 and num2 is < 110.
 *     Both num1 and num2 contains only digits 0-9.
 *     Both num1 and num2 does not contain any leading zero.
 *     You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {

    /**
     * @param num1: a non-negative integers
     * @param num2: a non-negative integers
     * @return: return product of num1 and num2
     */
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        int[] result = new int[m + n];
        int index = m + n - 1;
        for(int i = m - 1; i >= 0; i --){
            int p = index;
            for(int j = n - 1; j >= 0; j --){
                result[p --] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
            index --;
        }
        int carry = 0;
        String str = "";
        for(int i = m + n - 1; i >= 0; i --){
            int sum = carry + result[i];
            str = (sum % 10) + str;
            carry = sum / 10;
        }
        if(carry > 0){
            str = carry + str;
        }
        while(str.length() != 0 && str.charAt(0) == '0'){
            str = str.substring(1);
        }
        return str.length() == 0 ? "0" : str;
    }
}
