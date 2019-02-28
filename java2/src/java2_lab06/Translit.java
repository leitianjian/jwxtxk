package java2_lab06;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Properties;

public class Translit {
    public String convertHash (String russia_text){
        HashMap<Character, Character> tranTable = new HashMap<>();
        try {
            BufferedReader stream =
                    new BufferedReader(
                    new InputStreamReader(
                    new FileInputStream("C:\\Users\\18504\\IdeaProjects\\simplePro\\src\\java2_lab06\\translit_table_HashMap.txt"), "utf-8"));
            String str;
            while ((str = stream.readLine()) != null){
                String[] info = str.split(", ");
                char key1 = info[0].split("'")[1].toCharArray()[0];
                char key2 = info[1].split("'")[1].toCharArray()[0];
                char value;
                if (key1 != 1068){
                    value = info[2].split("\"")[1].toCharArray()[0];
                } else {
                    value = '\0';
                }
                tranTable.put(key1, value);
                tranTable.put(key2, value);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        char[] char_arr = russia_text.toCharArray();
        for (int i = 0; i < char_arr.length; i ++){
            if (tranTable.containsKey(char_arr[i])){
                char_arr[i] = tranTable.get(char_arr[i]);
            }
        }
        System.out.println(String.valueOf(char_arr));
        return String.valueOf(char_arr);
    }

    public String convertProp (String russia_text) {
        Properties prop = new Properties();
        try {
            BufferedReader stream =
                    new BufferedReader(
                            new InputStreamReader(
                                    new FileInputStream("C:\\Users\\18504\\IdeaProjects\\simplePro\\src\\java2_lab06\\translit_table_Properties.txt"), "utf-8"));
            prop.load(stream);
        } catch (IOException e){
            e.getMessage();
        }
        char[] char_arr = russia_text.toCharArray();
        for (int i = 0; i < char_arr.length; i ++){
            Character temp = char_arr[i];
            String value = prop.getProperty(temp.toString());
            if (value != null){
                if (value.equals("")) {
                    char_arr[i] = '\0';
                } else {
                    char_arr[i] = value.toCharArray()[0];
                }
            }
        }
        return String.valueOf(char_arr);
    }
}
