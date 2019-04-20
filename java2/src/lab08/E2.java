package lab08;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class E2 {
    public static void main(String[] args) {
        String reg = "[12]1[2-9]1[0-5][0-9][0-5][0-9]";
        String email = "11711707@mail.sustech.edu.cn";
        Pattern p = Pattern.compile(reg);
        Matcher matcher = p.matcher(email);

        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }
}
