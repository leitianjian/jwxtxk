package lab01;

public class test {
    public static void main(String[] args) {
        String str = "  ";
        byte[] bytes = str.getBytes();
        System.out.println(Integer.toHexString(bytes[0]));
    }
}
