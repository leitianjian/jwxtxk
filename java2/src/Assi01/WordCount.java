package Assi01;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * The Word class is used to store the Chinese char and the
 * corresponding encoding in upper case
 * */
class Word{
    private Character ChineseChar;
    private String encoding;

    public Word (Character ChineseChar, byte[] bytes){
        this.ChineseChar = ChineseChar;
        StringBuilder sb = new StringBuilder();
        for (Byte b : bytes) {
            sb.append(Integer.toHexString(Byte.toUnsignedInt(b)));
        }
        this.encoding = sb.toString().toUpperCase();
    }

    public Character getChineseChar() {
        return ChineseChar;
    }

    public String getEncoding (){
        return encoding;
    }

    /**
     * Override the equals method to compare the word with a single char
     * */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Word){
            return this.ChineseChar.equals(((Word) obj).ChineseChar);
        } else if (obj instanceof Character){
            return this.ChineseChar.equals(obj);
        } else {
            return false;
        }
    }

    /**
     * Specify the output format
     * */
    @Override
    public String toString (){
        return ChineseChar + ", " + encoding + ",";
    }
}

public class WordCount {
    private String DictionaryFileName; // the source dictionary file name
    private String preEncoding;        // original encoding of dictionary file
    private String finEncoding;        // final encoding of dictionary file
    public TreeMap<Word, Integer> words;
    private ArrayList<Comparator<Word>> sortMethodList; // sorting method list

    public WordCount (String DictionaryFileName,
                      String preEncoding,
                      String finEncoding,
                      int    sortMethod) throws IOException{

        this.DictionaryFileName = DictionaryFileName;

        // check the pre-encoding and fin-encoding is correct.
        if (!Charset.isSupported(preEncoding) || !Charset.isSupported(finEncoding)) {
            System.out.println("The encoding parameters is illegal, please check the typo again");
            System.exit(1);
        }

        this.preEncoding        = preEncoding;
        this.finEncoding        = finEncoding;
        this.sortMethodList     = new ArrayList<>();
        addSortMethod();
        this.words              = initialDic(sortMethod);
    }

    /**
     * initial the dictionary as the sortMethod indicated
     * */
    public TreeMap<Word, Integer> initialDic (int sortMethod) throws IOException {
        FileInputStream fis = new FileInputStream(DictionaryFileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, preEncoding));
        TreeMap<Word, Integer> dic = new TreeMap<>(sortMethodList.get(sortMethod));
        String tempLine;
        char[] chars;

        while ((tempLine = br.readLine()) != null){
            chars = tempLine.toCharArray();

            for (char c: chars){
                int encoding = (int) c;
                if (encoding >= 0x4e00 && encoding <= 0x9fa5) {
                    Word temp = new Word(c, Character.toString(c).getBytes(finEncoding));
                    if (!dic.containsKey(temp)) {
                        dic.put(temp, 1);
                    } else {
                        dic.put(temp, dic.get(temp) + 1);
                    }
                }
            }
        }

        return dic;
    }

    // using predicate statements
    private void addSortMethod (){
        sortMethodList.add((w1, w2) -> w1.getChineseChar().compareTo(w2.getChineseChar()));
        sortMethodList.add((w1, w2) -> w1.getEncoding().compareTo(w2.getEncoding()));
    }

    public void setDictionaryFileName(String dictionaryFileName) {
        DictionaryFileName = dictionaryFileName;
    }

    public void setPreEncoding(String preEncoding) {
        this.preEncoding = preEncoding;
    }

    public void setFinEncoding(String finEncoding) {
        this.finEncoding = finEncoding;
    }

    public String getFinEncoding() {
        return finEncoding;
    }

    public String getPreEncoding() {
        return preEncoding;
    }

    public static void main(String[] args) {
        if (args.length == 4){
            try {
                int sortMethod;
                if (args[3].equalsIgnoreCase("char")){
                    sortMethod = 0;
                } else if (args[3].equalsIgnoreCase("code")){
                    sortMethod = 1;
                } else if (args[3].equalsIgnoreCase("count")){
                    sortMethod = 3;
                } else {
                    System.out.println("Unrecognized sort parameter (char, code, count)");
                    return ;
                }
                Writer writer;
                // when using "count" to sort, it need another method
                if (sortMethod == 3) {
                    WordCount wordCount = new WordCount(args[0], args[1], args[2], 0);
                    writer = new FileWriter(args[2] + "_Dict_From_" + args[0]);
                    PrintWriter out = new PrintWriter(new BufferedWriter(writer));
                    wordCount.words.entrySet()
                            .stream()
                            .sorted((e1, e2) -> e2.getValue() - e1.getValue())
                            .map(entry -> entry.getKey().toString() + entry.getValue().toString())
                            .forEach(out::println);
                    out.close();
                } else {
                    WordCount wordCount = new WordCount(args[0], args[1], args[2], sortMethod);
                    writer = new FileWriter(args[2] + "_Dict_From_" + args[0]);
                    PrintWriter out = new PrintWriter(new BufferedWriter(writer));
                    wordCount.words.entrySet()
                            .stream()
                            .map(entry -> entry.getKey().toString() + entry.getValue().toString())
                            .forEach(out::println);
                    out.close();
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        } else {
            System.out.println(
                    "Usage: java -classpath . [fileName] [previous_encoding] [final_encoding] [sort_method]");
        }
    }
}
