package lab01;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Stack;

public class StableMatching {
    static class InputReader {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        InputReader r = new InputReader(System.in);
        PrintWriter p = new PrintWriter(System.out);
        int numPeople = r.nextInt();

//        HashMap<Integer, String> SAList = new HashMap<>();
        HashMap<String, Integer> SAListInverse = new HashMap<>();
        HashMap<Integer, String> StuList = new HashMap<>();
        HashMap<String, Integer> StuListInverse = new HashMap<>();
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < numPeople; ++ i){
            String temp = r.next();
//            SAList.put(i, temp);
            SAListInverse.put(temp, i);
            s.push(i);
        }

        for (int i = 0; i < numPeople; ++ i){
            String temp = r.next();
            StuList.put(i, temp);
            StuListInverse.put(temp, i);
        }

        int[] StuSAPairList = new int[numPeople];
        int[] SAStuPairList = new int[numPeople];

        for (int i = 0; i < numPeople; ++ i){
            StuSAPairList[i] = -1;
            SAStuPairList[i] = -1;
        }

        int[][] SAPreferList = new int[numPeople][numPeople];
        int[][] StuPreferList = new int[numPeople][numPeople];
        int[][] StuPreferListInverse = new int[numPeople][numPeople];

        for (int i = 0; i < numPeople; ++ i){
            for (int j = 0; j < numPeople; ++ j){
                SAPreferList[i][j] = StuListInverse.get(r.next());
            }
        }

        for (int i = 0; i < numPeople; ++ i){
            for (int j = 0; j < numPeople; ++ j){
                StuPreferList[i][j] = SAListInverse.get(r.next());
            }
        }

        for (int i = 0; i < numPeople; ++ i){
            for (int j = 0; j < numPeople; ++ j){
                StuPreferListInverse[i][StuPreferList[i][j]] = j;
            }
        }


        while (!s.empty()){
            int SAIndex = s.peek();
            for (int stuIndex: SAPreferList[SAIndex]){
                if (StuSAPairList[stuIndex] == -1) {
                    StuSAPairList[stuIndex] = SAIndex;
                    s.pop();
                    break;
                } else {
                    int pairedIndex = StuSAPairList[stuIndex];
                    if (StuPreferListInverse[stuIndex][SAIndex] < StuPreferListInverse[stuIndex][pairedIndex]){
                        s.pop();
                        s.push(pairedIndex);
                        StuSAPairList[stuIndex] = SAIndex;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < numPeople; ++ i){
            SAStuPairList[StuSAPairList[i]] = i;
        }

        for (int i = 0; i < numPeople; ++ i){
            System.out.print(StuList.get(SAStuPairList[i]) + " ");
        }
    }
}
