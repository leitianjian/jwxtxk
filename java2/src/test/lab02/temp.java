package test.lab02;
import java.util.Scanner;
public class temp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String temp = sc.nextLine();
        System.out.println(read(temp));
    }

    public static Integer read(String a){
        try {
            return Integer.parseInt(a);
        }catch (NumberFormatException e){
            e.getMessage();
        }
        return null;
    }
}
