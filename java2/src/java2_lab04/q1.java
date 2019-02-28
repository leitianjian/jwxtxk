package java2_lab04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//import java.lang.String;

public class q1 {
    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(i < 10){
            if(sc.hasNextInt()) {
//                System.out.println("Please input an integer");
                int temp = sc.nextInt();
                sc.nextLine();
                array.add(temp);
//                String str = "a";
//                Integer.parseInt(str);
                i++;
            }else {
                System.out.println("Invalid input");
                sc.nextLine();
            }
        }

        Collections.reverse(array);
        System.out.println(array.toString());

        Collections.sort(array);
        System.out.println(array.toString());

    }
}
