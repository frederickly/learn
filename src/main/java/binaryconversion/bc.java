package binaryconversion;

import java.util.LinkedList;
import java.util.Scanner;

public class bc {
    public static void binary(int n,int b) {
        LinkedList<String> bs=new LinkedList<>();
        if(n<b) {
            if(n<10)
                bs.add(String.valueOf(n));
            else if(11==n)
                bs.add("A");
            else if(12==n)
                bs.add("B");
            else if(13==n)
                bs.add("C");
            else if(14==n)
                bs.add("D");
            else if(15==n)
                bs.add("E");
            else bs.add("F");
        }

        else {
            while(n>=b) {
                int a=n%b;
                if(a<10)
                    bs.add(String.valueOf(a));
                else if(10==a)
                    bs.add("A");
                else if(11==a)
                    bs.add("B");
                else if(12==a)
                    bs.add("C");
                else if(13==a)
                    bs.add("D");
                else if(14==a)
                    bs.add("E");
                else bs.add("F");
                n=n/b;
            }
            int c=n%b;
            if(c<10)
                bs.add(String.valueOf(c));
            else if(10==c)
                bs.add("A");
            else if(11==c)
                bs.add("B");
            else if(12==c)
                bs.add("C");
            else if(13==c)
                bs.add("D");
            else if(14==c)
                bs.add("E");
            else bs.add("F");
            //System.out.println(bs);

        }
        LinkedList<String> cs=new LinkedList<String>();
        while(!bs.isEmpty()) {
            cs.add(bs.removeLast());
        }
        System.out.println(cs);
    }
    public static void main(String []args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("请输入n的值：");
        int n=sc.nextInt();
        System.out.println("请输入要转换的进制（2-15）");
        int b=sc.nextInt();
        sc.close();
        binary(n,b);
    }
}
