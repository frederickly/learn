package algorithms.bit;

/**
 * https://www.lintcode.com/problem/180/
 * Binary Representation
 *
 * Description
 *
 * Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string.
 * If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.
 * Example
 *
 * Example 1
 *
 * Input: "3.72"
 *
 * Output: "ERROR"
 *
 * Explanation: (3.72)10=(11.10111000010100011111)2 We can't represent it in 32 characters.
 *
 * Example 2
 *
 * Input: "3.5"
 *
 * OUtput: "11.1"
 *
 * Explanation: (3.5)10=(11.1)2
 *
 */
public class BinaryRepresentation {
    /**
     * @param n: Given a decimal number that is passed in as a string
     * @return: A string
     */
    public String binaryRepresentation(String n) {
        int index= n.indexOf(".");
        String res = "";

        if(index==-1) {
            return convertInt2Binary(Long.parseLong(n));
        }

        String[] values= n.split("\\.");
        //System.out.println(values);
        res+= convertInt2Binary(Long.parseLong(values[0]));

        double v = Double.parseDouble("0."+values[1]);
        System.out.println("values[1]="+ values[1]);
        System.out.println("v="+ v);
        if(v!=0) {
            try{
                res+=".";
                res+=convertFraction2Binary(v);
            }catch(Exception e) {
                return "ERROR";
            }
        }
        return res;
    }

    private String convertInt2Binary(long value) {
        String res ="";
        if(value==0) return "0";
        while(value>0) {
            res= value%2 + res;
            value /=2;
        }
        //System.out.println("int="+res);
        return res;
    }

    private String convertFraction2Binary(double value) throws Exception{
        int count=0;
        String res="";
        while(value!=0) {
            value*=2;
            System.out.println("value="+value);
            res = value>=1?res+ "1" : res+"0";
            if (value>=1)
                value-=1;
            count++;
            //System.out.println("fractional="+res);
            if(count>32) {
                System.out.println("fractional="+res);
                //"28187281.128121212121"
                //1101011100001101010010001.00100000110011001000110101"
                //                          0.00100000110011001001
                throw new Exception("Too many algorithms.bit for fractioal part.");
            }
        }

        return res;
    }

    public String binaryRepresentation2(String n) {
        // write your code here
        String[] s = n.split("\\.");

        long integer = Long.parseLong(s[0]);
        long fractional = Long.parseLong(s[1]);

        long num = 1;
        int len = s[1].length();

        while (len > 0) {
            num *= 10;
            len--;
        }

        System.out.println(num);

        String intS = "";
        String fracS = "";

        while (integer > 0) {
            intS = integer % 2 + intS;
            integer /= 2;
        }

        while (fractional > 0 && fracS.length() <= 32) {
            fractional *= 2;
            fracS += ((fractional - num) >= 0 ? 1 : 0);
            fractional = fractional % num;
        }

        if (fracS.length() > 32) {
            return "ERROR";
        }

        return (intS.length() == 0 ? "0" : intS) + (fracS.length() == 0 ? "" : ".") + fracS;
    }
}
