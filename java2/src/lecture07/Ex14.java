package lecture07;

import java.util.stream.Stream;

public class Ex14 {
    public static void stringStreamFilter (Stream<String> stringStream){
        String[] temp = new String[1];
        temp[0] = "";
        stringStream.filter(i -> {
            if (i.compareTo(temp[0]) == 0){
                return true;
            } else {
                temp[0] = i;
                return false;
            }
        }).forEach(System.out::println);
    }

    public static void main(String[] args) {
        String[] strings = {"1", "1", "2", "3", "4", "5", "5"};
        stringStreamFilter(Stream.of(strings));

    }
}
