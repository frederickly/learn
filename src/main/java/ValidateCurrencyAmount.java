import java.util.HashSet;
import java.util.Set;

public class ValidateCurrencyAmount {

    Set<Character> symbols= new HashSet<>();

    public static final void main(String[] args) {
        ValidateCurrencyAmount v=new ValidateCurrencyAmount();
        System.out.println(v.validCurrencyAmount("(-D3.50)"));
        System.out.println(v.validCurrencyAmount("(Y2400)"));
        System.out.println(v.validCurrencyAmount("D450"));
        System.out.println(v.validCurrencyAmount("D450.16"));
        System.out.println(v.validCurrencyAmount("D0"));
        System.out.println(v.validCurrencyAmount("D0.14"));
        System.out.println(v.validCurrencyAmount("D0.143"));
        System.out.println(v.validCurrencyAmount("Y0.14"));
        System.out.println(v.validCurrencyAmount("Y0.1"));
        System.out.println(v.validCurrencyAmount("Y14"));
        System.out.println(v.validCurrencyAmount("Y014"));
        System.out.println(v.validCurrencyAmount("D014.14"));
        System.out.println(v.validCurrencyAmount("D014.00"));
        System.out.println(v.validCurrencyAmount("D14.00"));
        System.out.println(v.validCurrencyAmount("D0.00"));
        System.out.println(v.validCurrencyAmount("D0.01"));
        System.out.println(v.validCurrencyAmount("D0.10"));
    }
    /**
     * @param currency: a string represents a valid or invalid currency amount
     * @return: return whether the given string is a valid currency
     */
    public boolean validCurrencyAmount(String currency) {
        if (currency == null) return false;

        int len = currency.length();
        if (len == 0) return false;

        symbols.add('D');
        symbols.add('E');
        symbols.add('Y');

        char[] chars = currency.toCharArray();

        if(chars[0]=='(' && chars[chars.length-1]!=')') return false;
        if(chars[0]!='(' && chars[chars.length-1]==')') return false;

        boolean bracket=false;
        if(chars[0]=='(' && chars[chars.length-1]==')') {
            currency= currency.substring(1, chars.length-1);
            bracket= true;
        }

         len = currency.length();
         if(len == 0) return false;
        chars = currency.toCharArray();

        int symbol = 0;
        if (chars[0] == '-') {
            symbol = 1;
        }

        if(bracket && chars[0] == '-') return false;

        if (!symbols.contains(chars[symbol])) return false;

        int dot = currency.indexOf(".");
        if (chars[symbol] == 'Y' && dot != -1) return false;

        int i=0;
        int zeros=0;
        for(i=symbol+1;i< (dot==-1? chars.length: dot);i++) {
            if(chars[i]!='0') break;
            else{
                zeros++;
                if(zeros>1) return false;
            }
        }

        if(zeros>1) return false;
        if(zeros==1 && i!= (dot==-1? chars.length: dot)) return false;

        if(dot!=-1) {
            int trail=0;
            for(i=dot+1;i< chars.length;i++) {
                if(chars[i]!='0') break;
                else{
                    trail++;
                }
            }
            if(trail==2 && zeros==1) return false;
        }

        return checkDollar(dot, symbol, chars) && checkCents(dot, chars);

    }

    private boolean checkDollar(int dot, int symbol, char[] chars) {
        if(dot==-1) dot= chars.length;
        int count=1;
        boolean thousand= dot-4>=0 && chars[dot-4]==',';
        for(int i= dot-1;i>symbol;i--) {
            if (thousand && count == 4) {
                if (chars[i] != ',') return false;
                else if (chars[i] == ',') {
                    count = 1;
                    continue;
                }
            }

            if (chars[i] <= '9' && chars[i] >= '0') {
                count++;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkCents(int dot, char[] chars) {
        if(dot==-1) return true;
        if(dot!=chars.length-3) return false;

        for(int i=dot+1;i<chars.length;i++) {
            if(chars[i]<='9'&& chars[i]>='0') continue;
            else return false;
        }
        return true;
    }
}
