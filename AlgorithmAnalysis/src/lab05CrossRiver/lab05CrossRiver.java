package lab05CrossRiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class lab05CrossRiver {
    public static void main(String[] args) {
        InputReader sc  = new InputReader(System.in);
        int numCases = sc.nextInt();
        for (int i = 0; i < numCases; ++ i){
            int boats;
            int man = sc.nextInt();
            int bGoods = sc.nextInt();
            int sGoods = sc.nextInt();

            if (man > sGoods / 2  + bGoods){
                boats = man;
            } else if (man == sGoods / 2 + bGoods){
                boats = man + ((sGoods % 2 == 0) ? 0 : 1);
            } else {
                if (2 * man >= sGoods) {
                    if (sGoods % 2 == 1) {
                        boats = man + 1 + (bGoods - man + (sGoods - 3) / 2 + 1) / 2;
                    } else {
                        boats = man + (bGoods - man + (sGoods) / 2 + 1) / 2;
                    }
                } else {
                    if ((sGoods - 2 * man) % 3 == 1){
                        boats = man + 1 + (sGoods - 2 * man) / 3 + (bGoods /* + 1 - 1*/) / 2;
                    } else if ((sGoods - 2 * man) % 3 == 2) {
                        boats = man + (sGoods - 2 * man) / 3 + 1 + (bGoods + 1) / 2;
                    } else {
                        boats = man + (sGoods - 2 * man) / 3 + (bGoods + 1) / 2;
                    }
                }
            }
            System.out.println(boats);
        }

    }

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
}
