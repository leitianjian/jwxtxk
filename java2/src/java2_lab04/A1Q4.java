package java2_lab04;

import java.util.*;

public class A1Q4 {
    static class info{
        Character ch;
        Integer i;
        info(char a, int b){
            this.ch = a;
            this.i = b;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input a string");
        String str = sc.nextLine();
        List<Character> list = new ArrayList<>();
        for (char c: str.toCharArray()){
            list.add(c);
        }
//
        System.out.print("Please input the char you want to search\n(if you input a string, it will return the times the first char appear)\n");
        char charSearch = sc.nextLine().toCharArray()[0];
        while(charSearch != '0') {
            System.out.println(Collections.frequency(list, charSearch));
            System.out.println("Press 0 to exit");
            charSearch = sc.nextLine().toCharArray()[0];
        }
    }
}
