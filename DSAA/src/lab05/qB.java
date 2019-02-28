package lab05;

import java.util.Scanner;

public class qB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        for (; numCases > 0; numCases --){
            int n = sc.nextInt();
            int m = sc.nextInt();
//            sc.nextLine();
            sc.nextLine();
            String s1 = sc.nextLine();
            String s2 = sc.nextLine();
            if(whetherMatch(s1, s2)){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
    }

    public static boolean whetherMatch(String s1, String s2){
        char[] array1 = s1.toCharArray();
        char[] array2 = s2.toCharArray();
        if (s1.length() > s2.length() + 1){
            return false;
        }
        int i;
        for (i = 0; i < array1.length && array1[i] != '*'; i ++){
            if (array1[i] != array2[i]){
                return false;
            }
        }
//        System.out.println(i);
        int k = array2.length -1;
        for (int j = array1.length - 1; j > i; j --, k --){
            if (array1[j] != array2[k]){
                return false;
            }
        }
        return true;
    }
}
