package StringManipulation;

import java.util.Stack;

/**
 *
 *
 Back1425. Backspace String Compare

 https://www.lintcode.com/problem/backspace-string-compare/description

 Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

 1 <= S.length <= 200
 1 <= T.length <= 200
 S and T only contain lowercase letters and '#' characters.

 Have you met this question in a real interview?
 Example

 Example 1:

 Input: S = "ab#c", T = "ad#c"
 Output: true
 Explanation: Both S and T become "ac".

 Example 2:

 Input: S = "ab##", T = "c#d#"
 Output: true
 Explanation: Both S and T become "".

 Example 3:

 Input: S = "a##c", T = "#a#c"
 Output: true
 Explanation: Both S and T become "c".

 Example 4:

 Input: S = "a#c", T = "b"
 Output: false
 Explanation: S becomes "c" while T becomes "b".

 Challenge

 Can you solve it in O(N) time and O(1) space?

 DifficultyEasy
 Total Accepted312
 Total Submitted617
 Accepted Rate50%

 Recommend Courses
 九章算法基础班（Java）
 九章算法基础班（Java）
 算法零基础必备课程，从 Java 语言到基础算法与数据结构，从 0 到 1 打下坚实算法根基。
 Company
 Leaderboard - Java
 jojozhuang
 Silver II
 153ms
 emileliao
 153ms
 Avalanche
 Silver IV
 156ms
 madrigal
 Bronze II
 158ms
 Good_Good_Study_
 Bronze I
 159ms
 Note
 m is size of S, n is size of T 方法一 stack O(n+m) time O(n+m) space 方法二 two pointers - preferred O(n+m) time O(1) space  从右向左 iterate，遇到 "#": count++, 遇到数字， 如果count >0 就 count --, 否则保留这个数字，并把两个string 这个数字做对比
 avatarjun57
 Created at a month ago
 Scan the String from tail to head and maintain the "#" count. Increment 'count' if the character is '#', otherwise decrement it. If the count is 0, append that character to the result. Perform above for both the strings.
 avatarBunni
 Created at 5 months ago
 * 最直接的方法是使用栈，但需要O(n)空间 * 如果不用额外空间，可以利用双指针，从后往前遍历 * while i >= 0 and (S[i] == '#' or cnt1 > 0)，可以一次处理连续的##，当循环结束时，一定是最后真正要保留的字符 * 每次i -= 1, j -=1 ,所以最小为-1
 avatarlaurapple
 Created at 6 months ago
 Discuss
 No topic
 1425. Backspace String Compare
 中文
 English

 Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 Example

 Example 1:

 Input: S = "ab#c", T = "ad#c"
 Output: true
 Explanation: Both S and T become "ac".

 Example 2:

 Input: S = "ab##", T = "c#d#"
 Output: true
 Explanation: Both S and T become "".

 Example 3:

 Input: S = "a##c", T = "#a#c"
 Output: true
 Explanation: Both S and T become "c".

 Example 4:

 Input: S = "a#c", T = "b"
 Output: false
 Explanation: S becomes "c" while T becomes "b".

 Challenge

 Can you solve it in O(N) time and O(1) space?
 Notice

 1 <= S.length <= 200
 1 <= T.length <= 200
 S and T only contain lowercase letters and '#' characters.

 "ab#c"
 "ad#c"


 m is size of S, n is size of T
 方法一 stack
 O(n+m) time O(n+m) space

 方法二 two pointers - preferred
 O(n+m) time O(1) space
 从右向左 iterate，遇到 "#": count++, 遇到数字， 如果count >0 就 count --, 否则保留这个数字，并把两个string 这个数字做对比

 */
public class BackspaceStringCompare {
    public boolean backspaceCompare(String S, String T) {
        Stack<Character> sStack = getStack(S);
        Stack<Character> tStack = getStack(T);
        if (sStack.size() != tStack.size()) {
            return false;
        }
        while (!sStack.isEmpty()) {
            if (sStack.pop() != tStack.pop()) {
                return false;
            }
        }
        return true;
    }

    private Stack<Character> getStack(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                if (stack.isEmpty())
                    continue;
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack;
    }
}
