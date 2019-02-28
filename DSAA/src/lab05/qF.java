package lab05;

import java.util.Scanner;

public class qF {
    static void getTable (String str, int[] table){
        int i = -1;
        int j = 0;
        table[0] = -1;
        while (j < str.length() - 1){
            if (i == -1 || str.charAt(i) == str.charAt(j)) {
                i++;
                j++;
                table[j] = i;
            } else {
                i = table[i];
            }
        }
        while (i != -1 && str.charAt(j) != str.charAt(i)){
            i = table[i];
        }
        if (i == -1){
            ++ j;
            table[j] = 0;
        } else {
            if (str.charAt(j) == str.charAt(i)){
                ++ i;
                ++ j;
                table[j] = i;
            }
        }
    }
    static class Pattern {
        String str;
        int[] table;

        public Pattern (String str){
            this.str = str;
            this.table = new int[str.length()];
            getTable(this.str, this.table);
        }
    }

    static void getPatterns (String str){}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        for (; numCases > 0; numCases --){
            int numLine = sc.nextInt();
            String[] words = new String[numLine];

            int minLength = 200;
            int minIndex = 0;
            for (int i = 0; i < numLine; i ++){
                words[i] = sc.nextLine();
                if (words[i].length() < minLength){
                    minIndex = i;
                    minLength = words[i].length();
                }
            }

            Pattern[] patterns = new Pattern[minLength *(minLength - 1) / 2];

        }
    }
}
