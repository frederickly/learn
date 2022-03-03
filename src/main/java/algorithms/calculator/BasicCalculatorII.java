package algorithms.calculator;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class BasicCalculatorII {
    /**
     * @param s: the given expression
     * @return: the result of expression
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        Set<Character> operands = new HashSet<>();
        operands.add('/');
        operands.add('-');
        operands.add('*');
        operands.add('+');

        int res = 0;
        char[] chars = s.toCharArray();
        int length = chars.length;
        int i = 0;
        char operand = '+';
        int num = 0;

        for (; i < length; i++) {

            if (chars[i] == ' ') continue;

            if (operands.contains(chars[i])) {
                operand = chars[i];
                continue;
            }

            num = 0;
            for (; i < length; i++) {
                if (Character.isDigit(chars[i])) {
                    num = num * 10 + chars[i] - '0';
                } else {
                    i--;
                    break;
                }
            }

            switch (operand) {
                case '*':
                    stack.push(stack.pop() * num);
                    break;
                case '/':
                    stack.push(stack.pop() / num);
                    break;
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
            }
        }

        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}
