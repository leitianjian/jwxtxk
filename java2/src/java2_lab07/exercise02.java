package java2_lab07;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class exercise02 {
    public static void main(String[] args) throws IOException {
        Stream<String> str = Files.lines(Paths.get("gb18030_char.txt"), Charset.forName("utf-8"));

        long countLine = str.count();

        System.out.println(countLine);

    }
}
