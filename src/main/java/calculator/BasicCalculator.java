package calculator;

import java.util.Stack;

public class BasicCalculator {

    /**
     * @param s: the given expression
     * @return: the result of expression
     */
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        Stack<String> curStack = new Stack<>();
        char[] chars = s.toCharArray();
        int res;

        int i=0;
        int start;
        String operand;
        String value;
        char c;

        do{
            //System.out.println("i="+ i );
            for(;i<chars.length;i++) {
                c = chars[i];
                //if (i>53) //System.out.println("i="+ i + " c="+c);

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
                if(c=='('){
                    stack.push("(");
                    continue;
                }
                start =i;

                for(;i<chars.length;i++){
                    c = chars[i];
                    if(c>='0' && c<='9'){
                        continue;
                    }else{
                        break;
                    }
                }
                //System.out.println("start=" + start +" i="+i);
                if(i==chars.length){
                    stack.push(s.substring(start));
                }else{
                    stack.push(s.substring(start,i));
                    i--;
                }

            }
            //System.out.println("s="+ s);
            //System.out.println("remain="+ s.substring(i));
            //System.out.println("stack="+ stack);
            do{
                value = stack.pop();
                if(!"(".equals(value)){
                    curStack.push(value);
                }else{
                    break;
                }

            }while(!stack.isEmpty() );
            //System.out.println("curStack="+ curStack);
            // calculate the value for expression between (  )
            res=0;
            operand="+";
            while(!curStack.isEmpty()){
                value = curStack.pop();
                //System.out.println("res="+ res+" operand="+ operand+" value="+ value);
                if("+".equals(value) || "-".equals(value)){
                    operand = value;
                    continue;
                }
                res ="+".equals(operand)? res+ Integer.parseInt(value):  res- Integer.parseInt(value);
            }

            if(i>=chars.length && stack.isEmpty()){
                break;
            }
            stack.push(Integer.toString(res));


        } while(!stack.isEmpty());

        return res;
    }
}
