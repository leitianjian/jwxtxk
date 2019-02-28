package lab01;

import java.io.*;

public class CharsetConvertor {
  private String preEncoding;
  private String finEncoding;
  private String fileName;
  private String[] lookUpTable;

  public CharsetConvertor (String fileName, String preEncoding, String finEncoding) {
    this.fileName    = fileName;
    this.preEncoding = preEncoding;
    this.finEncoding = finEncoding;
    this.lookUpTable = new String[256];
    createArrLookUp();
  }

  public int convertCharSet (){
    try {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis,preEncoding));
        Writer writer = new FileWriter(finEncoding + "_" + fileName);
        PrintWriter out = new PrintWriter(new BufferedWriter(writer));
        String tempLine;
        while ((tempLine = br.readLine()) != null) {
            byte[] bytes = tempLine.getBytes(finEncoding);
            out.println(byteArr2HexString(bytes));
        }
        br.close();
        fis.close();
        out.close();
        writer.close();
        return 0;
    }catch (IOException exception) {
       exception.printStackTrace();
       return 1;
    }
  }

  /* Direct calculate the hex char of one byte
     then append the hex to the string.
     fast look up array table
     idea source : https://stackoverflow.com/questions/311165\
     /how-do-you-convert-a-byte-array-to-a-hexadecimal-string-and-vice-versa/24343727#24343727
   */
  private String byteArr2HexString (byte[] bytes){
      String str = "";
      for (byte b : bytes){
          str = str.concat(Integer.toHexString(Byte.toUnsignedInt(b))) + " ";
      }
      return str.toUpperCase();
  }

  private void createArrLookUp (){
      for (int i = 0; i < 256; i ++ ){
          lookUpTable[i] = Integer.toHexString(i);
      }
  }

  public static void main(String args[]){
      if (args.length == 3) {
          CharsetConvertor cc = new CharsetConvertor(args[0], args[1], args[2]);
          int status = cc.convertCharSet();
          if (status == 0) {
              System.out.println("Success.");
          } else {
              System.out.println("Failure.");
          }
      } else {
          System.out.println("Usage : java [src_file_name] [origin_file_name] [origin_encoding] [final_encoding]");
      }
  }
}
