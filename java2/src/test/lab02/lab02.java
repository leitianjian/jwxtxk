package test.lab02;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import test.test;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class lab02 {
    public static void main(String[] args){
        Date date = readDate();
        System.out.println("Date  = " + date);
    }

    public static Date readDate() {//如果两个代码相互关联，则应该放在同一个代码块中
        FileInputStream readfile = null;
        InputStreamReader ir = null;
        BufferedReader in = null;
        try {
            readfile = new FileInputStream("C:\\Users\\18504\\IdeaProjects\\simplePro\\src\\test\\lab02\\readme.txt");
            ir = new InputStreamReader(readfile);
            in = new BufferedReader(ir);
            // read one line from the file
            String str = in.readLine();
            if (str == null) {
                in.close();
                return null;
            }

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(str);
            in.close();
            return date;
        }catch (FileNotFoundException e1){
            e1.getMessage();
        }catch (ParseException | IOException e) {
            e.getMessage();
        }
//        catch (IOException e1){
//            e1.getMessage();
//        }
        return null;



    }
}
