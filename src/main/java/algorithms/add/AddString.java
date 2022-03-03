package algorithms.add;

public class AddString {
    public String addStrings(String num1, String num2) {
        // write your code here
        if (num1 == null || num2 == null) {
            return null;
        }
        String res = "";
        int l1 = num1.length();
        int l2 = num2.length();
        int p1 = l1 - 1;
        int p2 = l2 - 1;
        int carry = 0;
        while (p1 >= 0 && p2 >= 0) {
            int digit = (num1.charAt(p1) - '0' + num2.charAt(p2) - '0' + carry) % 10;
            carry = (num1.charAt(p1) - '0' + num2.charAt(p2) - '0' + carry) / 10;
            p1--;
            p2--;
            res = digit + res;
        }
        while (p1 >= 0) {
            int digit = (num1.charAt(p1) - '0' + carry) % 10;
            carry = (num1.charAt(p1) - '0' + carry) / 10;
            p1--;
            res = digit + res;
        }
        while (p2 >= 0) {
            int digit = (num2.charAt(p2) - '0' + carry) % 10;
            carry = (num2.charAt(p2) - '0' + carry) / 10;
            p2--;
            res = digit + res;
        }
        if (carry != 0) {
            res = carry + res;
        }
        return res;
    }
}
