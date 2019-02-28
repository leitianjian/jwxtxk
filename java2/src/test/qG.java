package test;

import java.util.Arrays;
import java.util.Scanner;

public class qG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        for(int i = 0; i < numCases; i ++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] temp = {a, b, c};
            Arrays.sort(temp);
            c = temp[0];
            b = temp[1];
            a = temp[2];
            int[] temp1 = {n, m};
            Arrays.sort(temp1);
            n = temp1[0]; //n << m
            m = temp1[1];

            if(((n >= a + 2 * c && m >= 2 * (b + c)) || (n >= (2 * (b + c)) && m >= (a + 2 * c)) ||
                    ((n >= (b + 2 * c) && m >= (2 * (a + c)))) ||
                    (n >= (b + c) && m >= (3 * a + b + c)) ||
                    (n >= (a + b) && m >= (a + b + 3 * c)) ||
                    ((n >= (a + c) && m >= (a + 3 * b + a)) )))
                System.out.println("Yes");
            else
                System.out.println("No");

        }
    }
}

/**public class qG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        for(int i = 0; i < numCases; i ++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] temp = {a, b, c};
            Arrays.sort(temp);
            c = temp[0];
            b = temp[1];
            a = temp[2];
            int[] temp1 = {n, m};
            Arrays.sort(temp1);
            n = temp1[0]; //n << m
            m = temp1[1];

            if((n >= a + 2 * c && m >= 2 * (b + c)) || (n >= (b + 2 * c) && m >= (2 * (a + c))) || (n >= (b + c) && m >= (3 * a + b + c)) ||
                    (n >= (a + b) && m >= (a + b + 3 * c)) || (n >= (a + c) && m >= (a + 3 * b + a)) || (n >= (2 * (b + c)) && m >= (a + 2 * c))/* ||
                    (m >= b + 2 * c && n >= 2 * (a + c))||
                    (m >= b + c && n >= 3 * a + b + c) ||
                    (m >= a + b && n >= a + b + 3 * c) ||
                    (m >= a + c && n >= a + 3 * b + a))
        System.out.println("Yes");
        else
        System.out.println("No");

        }
        }
        } */