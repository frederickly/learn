package algorithms.calculator;

import java.util.Stack;

/**
 * Implement a basic algorithms.calculator to evaluate a simple expression string.
 *
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 *
 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.
 *
 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647]
 * Example
 *
 * Example 1:
 *
 * Input："1 + 1"
 * Output：2
 * Explanation：1 + 1 = 2
 *
 * Example 2:
 *
 * Input：" 6-4 / 2 "
 * Output：4
 * Explanation：4/2=2，6-2=4
 *
 * Notice
 *
 * Do not use the eval built-in library function.
 */
public class BasicCalculatorIII {
    /**
     * @param s: the given expression
     * @return: the result of expression
     */
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        Stack<String> curStack = new Stack<>();

        long res;

        int length = s.length();
        int i=0;
        int start;
        String operand = "+";
        String value, value1;
        char c;
        long num;

        do{

            for(;i<length;i++) {
                c = s.charAt(i);
                if(c==')') {
                    i++;
                    break;
                }
                if(c==' ') continue;

                if(c=='+'){
                    stack.push("+");
                    continue;
                }
                if(c=='-'){
                    stack.push("-");
                    continue;
                }
                if(c=='*'){
                    stack.push("*");
                    continue;
                }
                if(c=='/'){
                    stack.push("/");
                    continue;
                }
                if(c=='('){
                    stack.push("(");
                    continue;
                }
                start =i;

                for(;i<length;i++){
                    c =s.charAt(i);
                    if(c>='0' && c<='9'){
                        continue;
                    }else{
                        break;
                    }
                }

                if(i==length){
                    value= s.substring(start);
                }else{
                    value= s.substring(start,i);
                    i--;
                }

                if(!stack.isEmpty() &&("*".equals(stack.peek())|| "/".equals(stack.peek()))){
                    operand = stack.pop();
                    value1 = stack.pop();
                    num = Long.parseLong(value1);
                    res = "*".equals(operand)? num * Long.parseLong(value):  num / Long.parseLong(value);
                    stack.push(Long.toString(res));
                }else{
                    stack.push(value);
                }
            }

            //System.out.println("Before stack="+ stack);
            do{
                value = stack.pop();
                if(!"(".equals(value)){
                    curStack.push(value);
                }else{
                    break;
                }

            }while(!stack.isEmpty() );
            // calculate the value for expression between (  )

            //System.out.println("curStack="+ curStack);
            res=0;
            operand="+";
            while(!curStack.isEmpty()){
                value = curStack.pop();

                if("+".equals(value) || "-".equals(value)){
                    operand = value;
                    continue;
                }
                res ="+".equals(operand)? res+ Long.parseLong(value):  res - Long.parseLong(value);
            }

            if(i>=length && stack.isEmpty()){
                break;
            }

            if(!stack.isEmpty() &&("*".equals(stack.peek())|| "/".equals(stack.peek()))){
                operand = stack.pop();
                value1 = stack.pop();
                num = Long.parseLong(value1);
                res = "*".equals(operand)? num * res:  num /res;
                stack.push(Long.toString(res));
            }else{
                stack.push(Long.toString(res));
            }

            //System.out.println("After stack="+ stack);

        } while(!stack.isEmpty());

        return (int)res;
    }

}
