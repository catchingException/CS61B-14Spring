import java.net.*;
import java.io.*;

public class OpenCommercial {

    public static void main(String[] arg) throws Exception {

        BufferedReader keyboard;
        String inputLine;
        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter the name of a company (without spaces): ");
        System.out.flush();        /* Make sure the line is printed immediately. */
        inputLine = keyboard.readLine();

        /* Replace this comment with your solution.  */
        //Part I
        URL u = new URL("https://www." + inputLine + ".com/");
        InputStream ins = u.openStream(); //read the raw data from the URL u
        InputStreamReader isr = new InputStreamReader(ins);// compose the raw data(ins) into characters
        BufferedReader a = new BufferedReader(isr);// compose the characters into entire lines of text

        String[] str = new String[5];
        for (int i = 0; i < 5; i++) {
            str[i] = a.readLine();
        }
        for (int i = 4; i >= 0; i--) {
            System.out.println(str[i]);
        }
    }
}



