//
//   Lab6.java
//
//   You should use this program for testing your Translit class.
//   To run it in a console:
//      $ java Lab1 <name of the file to convert>
//
//   To run it from Eclipse you need first to go to 
//       Run/Run Configurations...
//   then click on the tab "(x)= Arguments" and enter the full access
//   path to the file in the "Program arguments:" entry field.
//
package java2_lab06;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


// Define class Translit here.
// You can also define it as a public class in a separate file named 
// Translit.java

public class Lab6 {
    static String fileContent = new String("");

    // This method reads the contents of a file into a String.
    // It specifies that the characters in the file are encoded
    // with the UTF-8 encoding scheme (this is the standard on the Web
    // and on Linux machines; Windows machines use a different default
    // encoding scheme)
    // We will see files in detail later in the course.
    private static void readFile(String fileName)
        throws FileNotFoundException,
               UnsupportedEncodingException,
               IOException {
        // Reads and loads in memory (into fileContent)
        char[] cbuf = new char[1000];
        int    charsRead;
        InputStreamReader isr = new InputStreamReader(new
                       FileInputStream(fileName), "UTF-8");
        while ((charsRead = isr.read(cbuf, 0, 1000)) != -1) {
          fileContent += new String(java.util.Arrays.copyOfRange(cbuf,
                                    0, charsRead));
        }
        isr.close();
    }

    public static void main(String[] args) { 
       // The program takes the name of the file from the command line.
       // Wen it runs, it finds command line parameters into the args array.
       if (args.length > 0) {
          try {
            // Load the content of the file in memory
            readFile(args[0]);
            // Display what has been read for control.
            System.out.println("Input:");
            System.out.println(fileContent);
            // Create a Translit object
            
            Translit tr = new Translit();
            
            // Convert and display. It will all be in lowercase.
            System.out.println("Output:");
            
            System.out.println(tr.convertProp(fileContent));
            
          } catch (Exception e) {
            // If anything goes wrong
            System.err.println(e.getMessage());
          }
       }
    }

}
