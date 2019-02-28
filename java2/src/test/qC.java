package test;

import java.util.Scanner;

public class qC {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        for(int i = 0; i < numCases; i++){
            int n = sc.nextInt();
            int m = sc.nextInt();
            if(n == 1 && m == 1){
                System.out.println("Bob");
            }else
                System.out.println("Alice");
        }
    }
}
