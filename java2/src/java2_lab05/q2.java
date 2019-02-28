package java2_lab05;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.swing.text.html.HTMLDocument;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Arrays.*;

public class q2 {
    public static class Tokenizer {
        String fileContent = new String("");
        int    pos = 0;

        public Tokenizer(String fileName)
                throws FileNotFoundException,
                UnsupportedEncodingException,
                IOException {
            // Constructor - reads and loads in memory.
            char[] cbuf = new char[200];
            int    charsRead;
            InputStreamReader isr = new InputStreamReader(new
                    FileInputStream(fileName), "UTF-8");
            while ((charsRead = isr.read(cbuf, 0, 200)) != -1) {
                fileContent += new String(java.util.Arrays.copyOfRange(cbuf,
                        0, charsRead));
            }
            isr.close();
            // System.out.println(fileContent);
        }

        public String nextToken() {
            String  tok = "";
            char    c;
            boolean last_was_quote = false;

            try {
                while (! Character.isLetterOrDigit(fileContent
                        .subSequence(pos, pos+1)
                        .charAt(0))) {
                    pos++;
                }
                c = fileContent.subSequence(pos,pos+1).charAt(0);
                while (Character.isLetterOrDigit(c)
                        || (last_was_quote = (c == '\''))) {
                    tok += fileContent.substring(pos,pos+1);
                    pos++;
                    c = fileContent.subSequence(pos,pos+1).charAt(0);
                }
                if (last_was_quote) {
                    // Remove ending quote
                    while (tok.charAt(tok.length()-1) == '\'') {
                        tok = tok.substring(0, tok.length()-1);
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
            return tok.toLowerCase();
        }

    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        try {
            Tokenizer tokenizer = new Tokenizer("C:\\Users\\18504\\IdeaProjects\\simplePro\\src\\java2_lab05\\King.txt");
            boolean hasNext = true;
            while (hasNext){
                String str = tokenizer.nextToken();
                if (str == null){
                    break;
                }

                if (map.containsKey(str)){
                    map.put(str, map.get(str) + 1);
                } else {
                    map.put(str, 1);
                }
            }
        }catch (IOException e){
            e.getMessage();
        }
        TreeMap<Integer, TreeSet<String>> treeMap = new TreeMap<Integer, TreeSet<String>>();

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            Integer i = entry.getValue();
            if (!treeMap.containsKey(i)) {
                TreeSet<String> treeSet = new TreeSet<>();
                treeSet.add(entry.getKey());
                treeMap.put(entry.getValue(), treeSet);
            }else {
                treeMap.get(i).add(entry.getKey());
                treeMap.put(entry.getValue(), treeMap.get(i));
            }
        }
//        NavigableSet<Integer> navigableSet = treeMap.descendingKeySet();
//        NavigableMap<Integer, TreeSet<String>> treeSetNavigableMap = treeMap.descendingMap();
        Map.Entry<Integer, TreeSet<String>> entry;
        for (int i = 0; i < 10; i ++) {
//            entry = treeSetNavigableMap.pollLastEntry();
            entry = treeMap.pollLastEntry();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

}
