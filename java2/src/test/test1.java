package test;

import java.math.BigInteger;

public class test1 {
    public static void main(String[] args) {
        int count1 = 0;
        int count2 = 0;
        for(int x = 0 ; x < 100000 ; x ++){
            BigInteger temp = new BigInteger("2");
//            BigInteger num = BigInteger.valueOf(x);
            BigInteger num = temp.pow(x);
            char ch = num.toString().charAt(0);
            if (ch == '7'){
                count1 ++; // 7
            }
            if (ch == '8')
                count2 ++;
        }
        System.out.println(count1 + " " + count2);
    }
}
