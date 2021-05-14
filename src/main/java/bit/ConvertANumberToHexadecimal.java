package bit;

/**
 * 1253. Convert a Number to Hexadecimal
 * https://www.lintcode.com/problem/convert-a-number-to-hexadecimal/description
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 *
 * 1.All letters in hexadecimal (a-f) must be in lowercase.
 * 2.The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 * 3.The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * 4.You must not use any method provided by the library which converts/formats the number to hex directly.
 * Have you met this question in a real interview?
 * Example
 *
 * Example 1:
 *
 * Input：26
 * Output："1a"
 *
 * Example 2:
 *
 * Input：-1
 * Output："ffffffff"
 */
public class ConvertANumberToHexadecimal {
    /**
     * @param num: an integer
     * @return: convert the integer to hexadecimal
     */
    public String toHex(int num) {
        String res = "", str = "0123456789abcdef";
        int cnt = 0;
        while (num != 0 && cnt++ < 8) {
            res = str.charAt(num & 0xf) + res;
            num >>= 4;
        }
        return res=="" ? "0" : res;
    }
}
