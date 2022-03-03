package algorithms.StringManipulation;

import java.util.HashSet;
import java.util.Set;

/**
 * 1510. Buddy Strings
 * https://www.lintcode.com/problem/buddy-strings/description
 *
 * Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.
 * Example
 *
 * Example 1:
 *
 * Input: A = "ab", B = "ba"
 * Output: true
 *
 * Example 2:
 *
 * Input: A = "ab", B = "ab"
 * Output: false
 *
 * Example 3:
 *
 * Input: A = "aa", B = "aa"
 * Output: true
 *
 * Example 4:
 *
 * Input: A = "aaaaaaabc", B = "aaaaaaacb"
 * Output: true
 *
 * Example 5:
 *
 * Input: A = "", B = "aa"
 * Output: false
 *
 * Notice
 *
 * 1.0 <= A.length <= 20000
 * 2.0 <= A.length <= 20000
 * 3.A and B consist only of lowercase letters.
 *
 *
 * 这道题给了两个字符串A和B，说是我们必须调换A中的两个字符的位置一次，问是否能得到字符串B。
 * 这道题给的例子又多又全，基本上把所有的 corner cases 都覆盖了，
 * 比如我们对比例子2和例子3，可以发现虽然两个例子中A和B字符串都相等，但是仔细观察的话，可以发现 "ab" 中没有相同的字符，
 * 而 "aa" 中有相同的字符，那么实际上 "aa" 是可以调换两个字符的位置的，这样还跟字符串B相等，是符合题意的，因为题目要求必须要调换一次位置，若没有相同的字符，是无法调换位置后和B相等的。
 * 那么我们应该可以总结出一些规律了，
 * 首先字符串A和B长度必须要相等，不相等的话直接返回 false。
 * 假如起始时A和B就完全相等，那么只有当A中有重复字符出现的时候，才能返回 true。
 * 快速检测重复字符的方法就是利用 HashSet 的自动去重复功能，将A中所有字符存入 HashSet 中，若有重复字符，那么最终 HashSet 的大小一定会小于原字符串A的长度。
 * 对于A和B长度相等，但是字符串本身不相等的一般情况，我们可以记录出所有对应字符不相同的位置，放到一个数组 diff 中，
 * 最终判断 diff 数组的长度是否为2，且判断交换位置后是否跟B中对应的位置上的字符相同即可
 */
public class BuddyStrings {

    /**
     * @param A: string A
     * @param B: string B
     * @return: bool
     */
    public boolean buddyStrings(String A, String B) {
        if(A==null || B==null) return false;
        if(A.length()!=B.length()) return false;

        int len = A.length();
        int index1 =-1;
        int index2= -1;
        int count=0;

        Set<Character> chars =new HashSet<>();

        for(int i=0;i<len;i++){
            chars.add(A.charAt(i));
            if(A.charAt(i) != B.charAt(i)){
                count++;
                if(count==1){
                    index1= i;
                }else if(count==2){
                    index2 = i;
                }else {
                    return false;
                }
            }
        }

        if(index1>=0 && index2>=0 &&A.charAt(index1)== B.charAt(index2) &&A.charAt(index2)== B.charAt(index1))
            return true;

        if(A.equals(B) && len -chars.size()>=1) return true;
        return false;
    }
}
