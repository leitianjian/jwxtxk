package test;

/**题目描述
 A pair of English letters (a, b) is considered beautiful iff one of them is uppercase,
 the other is lowercase and both of them are consonants.
 (English letters except a, e, i, o, u, w and y).
 Now give you a string which is consists of lowercase English letters.
 Now you can change several letters from lowercase to uppercase.
 Please write a program to calculate the maximum number of beautiful pairs formed by adjacent letters.
 Note: If you change x to X, then all x in the string will become X.
 For example, if we have string strength, we can change it to StRenGtH. In this case, (S,t), (t,R),(n,G),(G,t) and (t,H) are beautiful. So the result is 5.
 输入
 The first line of input is the number of test cases T (1 <= T <= 10)
 For each test case, there will be a string consists of lowercase English letters. The length of the string does not exists 10000.
 输出
 For each test case, print the maximum number of adjacent beautiful pairs you can find. 直接贪心算法试试
 样例输入
 2
 strength
 consonants
 样例输出
 5
 2*/

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class qF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numCases = sc.nextInt();
        sc.nextLine();
        List<Character> alphaTable = Arrays.asList('b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'x', 'z');
        // read the whole line in
        for(int i = 0; i < numCases; i++){
            String s = sc.nextLine();
            char[] array = s.toCharArray();
            if (array.length == 1){
                System.out.println(0);
                continue;
            }
            for(int i1  = 0; i1 < array.length; i1 ++){
                if (array[i1] == 'a' || array[i1] == 'e' || array[i1] == 'i'
                        || array[i1] == 'o' || array[i1] == 'u' || array[i1] == 'w' || array[i1] == 'y')
                    array[i1] = '0';
            }
//            ArrayList<Integer> max = new ArrayList<>();
//            max.add(0);
            int maxNumPairs = 0;
            do {
//                maxNumPairs = 0;
                int maxIndex = -1; // the max pairs of alpha

                // outer loop control the alpha to upper case
                for (int i1 = 0; i1 < alphaTable.size(); i1++) {
                    int count = 0;// the max num pairs of alpha
                    char temp = alphaTable.get(i1);
                    // inner loop to cal the pairs of changing
                    for (int i2 = 0; i2 < array.length; i2++) {
                        if ((array[i2] >= 'B' && array[i2] <= 'Z') || array[i2] == temp) {
                            if (i2 > 0 && i2 < array.length - 1) {
                                if (array[i2 - 1] >= 'b' && array[i2 - 1] <= 'z' && array[i2 - 1] != temp)
                                    count++;
                                if (array[i2 + 1] >= 'b' && array[i2 + 1] <= 'z' && array[i2 + 1] != temp)
                                    count++;
                            }else if(i2 == 0){
                                if (array[i2 + 1] >= 'b' && array[i2 + 1] <= 'z' && array[i2 + 1] != temp)
                                    count++;
                            }else
                            if (array[i2 - 1] >= 'b' && array[i2 - 1] <= 'z' && array[i2 - 1] != temp)
                                count++;
                        }
                    }
                    if(count > maxNumPairs){
                        maxIndex = i1;
                        maxNumPairs = count;
                    }
                    // g 会让它变成 5,，，应该是逻辑有问题
                }
                // 根据最大index将字符串更新
                if(maxIndex == -1) break;
                for(int i1 = 0; i1 < array.length; i1 ++){
                    if (array[i1] == alphaTable.get(maxIndex)){
                        array[i1] -= 32;
                    }
                }
//                max.add(maxNumPairs);
            }while (true/*maxNumPairs > max.get(max.size() - 2)*/);

            System.out.println(maxNumPairs);
        }
    }


}

