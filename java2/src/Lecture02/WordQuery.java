package Lecture02;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/** The word list come from https://github.com/dolph/dictionary/blob/master/unix-words */

// Using a string "222333444555666..." to mapping the alpha to digit

public class WordQuery {
    private String fileName;
    private TreeMap<String, HashSet<String>> mapQuery;
    private HashMap<Character, Integer> mapConvert;

    public WordQuery (String fileName) throws IOException {
        this.fileName = fileName;
        this.mapQuery = new TreeMap<>();
        this.mapConvert = new HashMap<>();

        constructMapConvert();
        constructMapQuery();
    }
    private void constructMapConvert (){
        for (int i = 0; i < 52; ++ i){
            char c;
            if (i < 26) {
                c = (char) ((int) 'a' + i);
            } else {
                c = (char) ((int) 'A' + i - 26);
            }
            switch (c){
                case 'a': case 'b': case 'c':
                case 'A': case 'B': case 'C':
                    mapConvert.put(c, 2);
                    break;
                case 'd': case 'e': case 'f':
                case 'D': case 'E': case 'F':
                    mapConvert.put(c, 3);
                    break;
                case 'g': case 'h': case 'i':
                case 'G': case 'H': case 'I':
                    mapConvert.put(c,4);
                    break;
                case 'j': case 'k': case 'l':
                case 'J': case 'K': case 'L':
                    mapConvert.put(c,5);
                    break;
                case 'm': case 'n': case 'o':
                case 'M': case 'N': case 'O':
                    mapConvert.put(c,6);
                    break;
                case 'p': case 'q': case 'r': case 's':
                case 'P': case 'Q': case 'R': case 'S':
                    mapConvert.put(c,7);
                    break;
                case 't': case 'u': case 'v':
                case 'T': case 'U': case 'V':
                    mapConvert.put(c,8);
                    break;
                case 'w': case 'x': case 'y': case 'z':
                case 'W': case 'X': case 'Y': case 'Z':
                    mapConvert.put(c,9);
                    break;
            }
        }
    }

    private void constructMapQuery () throws  IOException{
            BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                new FileInputStream(fileName), StandardCharsets.UTF_8));
            String readLine;
            readLine = br.readLine();
            StringBuilder sb;
            String sbStr;
            while (readLine != null){
                sb = new StringBuilder();
                for (char c : readLine.toCharArray()){
                    if (c <= 'Z' && c >= 'A' || c >= 'a' && c <= 'z') {
//                    System.out.println(readLine);
                        int value = mapConvert.get(c);
                        sb.append(value);
                    }
                }

                sbStr = sb.toString();

                // add the string into the mapQuery
                if (!mapQuery.containsKey(sbStr)) {
                    mapQuery.put(sbStr, new HashSet<>());
                }
                mapQuery.get(sbStr).add(readLine);
                readLine = br.readLine();
            }
    }

    public int query (String info){
        HashSet<String> result = mapQuery.get(info);
        if (result == null) {
            System.out.println("There is no result corresponding to the query string");
            return 1;
        } else {
            System.out.println(result);
            System.out.println();
            return 0;
        }
    }

    public static void main(String[] args) {
        WordQuery wordQuery;
        Scanner sc = new Scanner(System.in);

        boolean exit = false;
        String fileName;
        L1:
        while (!exit) {
            System.out.println("Please input the word list name and " +
                    "guarantee the file is existing in the correct directory: ");
            fileName = sc.nextLine();
            while (!exit) {
                System.out.println("Choose a branch you want to continue:\n" +
                        "1. Query\n" +
                        "2. Re-choose the words list file\n" +
                        "3. Exit");
                int branch = sc.nextInt();
                sc.nextLine();
                switch (branch) {
                    case 1:
                        try {
                            wordQuery = new WordQuery(fileName);
                            System.out.print("Please input the String you want to query: ");
                            String q = sc.nextLine();
                            wordQuery.query(q);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                            continue L1;
                        }
                        break;
                    case 2:
                        continue L1;
                    case 3:
                        exit = true;
                        break;
                    default:
                        System.out.println("Unexpect choose number please input again.");
                }
            }
        }
    }
}
